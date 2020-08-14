package cn.com.cloudstar.rightcloud.framework.interceptor;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.common.util.concurrent.RateLimiter;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

import cn.com.cloudstar.rightcloud.framework.common.annotation.RateLimit;
import cn.com.cloudstar.rightcloud.framework.common.constants.RestConst;
import cn.com.cloudstar.rightcloud.framework.common.pojo.RestResult;
import cn.com.cloudstar.rightcloud.framework.common.util.JsonUtil;

@Aspect
@Component
@Slf4j
public class RateLimitAspect {
    /**
     * 用来存放不同接口的RateLimiter(key为接口名称，value为RateLimiter)
     */
    private ConcurrentHashMap<String, RateLimiter> map = new ConcurrentHashMap<>();

    private RateLimiter rateLimiter;

    @Autowired
    private HttpServletResponse response;

    @Pointcut("@annotation(cn.com.cloudstar.rightcloud.framework.common.annotation.RateLimit)")
    public void serviceLimit() {
    }

    @Around("serviceLimit()")
    public Object around(ProceedingJoinPoint joinPoint) throws NoSuchMethodException {
        Object obj = null;
        //获取拦截的方法名
        Signature sig = joinPoint.getSignature();
        //获取拦截的方法名
        MethodSignature msig = (MethodSignature) sig;
        //返回被织入增加处理目标对象
        Object target = joinPoint.getTarget();
        //为了获取注解信息
        Method currentMethod = target.getClass().getMethod(msig.getName(), msig.getParameterTypes());
        //获取注解信息
        RateLimit annotation = currentMethod.getAnnotation(RateLimit.class);
        //获取注解每秒加入桶中的token
        double limitNum = annotation.limitNum();
        // 注解所在方法名区分不同的限流策略
        String functionName = msig.getName();

        //获取rateLimiter
        if (map.containsKey(functionName)) {
            rateLimiter = map.get(functionName);
        } else {
            map.put(functionName, RateLimiter.create(limitNum));
            rateLimiter = map.get(functionName);
        }

        try {
            if (rateLimiter.tryAcquire()) {
                //执行方法
                obj = joinPoint.proceed();
            } else {
                //拒绝了请求（服务降级）
                RestResult restResult = new RestResult();
                restResult.setStatus(RestResult.Status.FAILURE);
                restResult.setCode(RestConst.BizError.LIMIT_CHECK_FAILED);
                restResult.setMessage("系统繁忙");
                String result = JsonUtil.toJson(restResult, JsonInclude.Include.NON_NULL);
                log.info("拒绝了请求：" + result);
                outErrorResult(result);
                //TODO 错误日志
            }
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return obj;
    }

    /**
     * 将结果返回
     *
     * @param result
     */
    public void outErrorResult(String result) {
        response.setContentType("application/json;charset=UTF-8");
        try (ServletOutputStream outputStream = response.getOutputStream()) {
            outputStream.write(result.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
