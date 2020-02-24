package cn.com.cloudstar.rightcloud.framework.interceptor;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import lombok.extern.slf4j.Slf4j;

import cn.com.cloudstar.rightcloud.framework.common.annotation.MyCacheable;
import cn.com.cloudstar.rightcloud.framework.common.cache.JedisUtil;
import cn.com.cloudstar.rightcloud.framework.common.util.BeanUtil;

/**
 * @author Hong.Wu
 * @date: 21:10 2020/02/17
 */
@Component
@Aspect
@Slf4j
public class CacheDataManageAop {


    @Pointcut("execution(* cn.com.cloudstar.rightcloud.system.service..*(..))")
    public void pointcut() {
    }


    @Around(value = "pointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
//        System.out.println("目标方法名为:" + joinPoint.getSignature().getName());
//        System.out.println("目标方法所属类的简单类名:" + joinPoint.getSignature().getDeclaringType().getSimpleName());
//        System.out.println("目标方法所属类的类名:" + joinPoint.getSignature().getDeclaringTypeName());
//        System.out.println("目标方法声明类型:" + Modifier.toString(joinPoint.getSignature().getModifiers()));
//        //获取传入目标方法的参数
        Object[] args = joinPoint.getArgs();

        //
        //MyCacheable cacheData = getAnnotationBak(joinPoint);
        // 读取缓存注解
        MyCacheable myCacheable = this.getMethodAnnotation(joinPoint);
        if (Objects.isNull(myCacheable)) {
            return joinPoint.proceed(args);
        }
        // 读取类注解
        CacheConfig cacheConfig = this.getClassAnnotation(joinPoint);

        // 获得解释之后的key
        String strKey = this.getKey(cacheConfig, myCacheable, joinPoint.getArgs());
        log.debug("解释之后的key:{}", strKey);

        // 在方法执行前判断是否存在缓存
        byte[] readCachedByteData = JedisUtil.instance().get(strKey.getBytes());
        if (readCachedByteData != null) {
            if (myCacheable.state() && myCacheable.ttl() != -1) {
                // 存在缓存&每次访问重置TTL&非永不过期     每次访问后重新刷新TTL，还原为原来值
                JedisUtil.instance().expire(strKey, myCacheable.ttl());
            }
            return BeanUtil.deserialize(readCachedByteData);
        }

        Object result = joinPoint.proceed(args);

        if (result != null) {
            JedisUtil.instance().set(strKey.getBytes(), BeanUtil.serialize(result), myCacheable.ttl());
        } else {
            // 判断是否缓存null
            if (myCacheable.cacheNull()) {
                JedisUtil.instance().set(strKey.getBytes(), null, myCacheable.ttl());
            }
        }
        return result;
    }


    /**
     * 获取类中声明的注解
     *
     * @param joinPoint
     *
     * @throws NoSuchMethodException
     */
    private CacheConfig getClassAnnotation(JoinPoint joinPoint) throws NoSuchMethodException {
        // 反射获取目标类
        Class<?> targetClass = joinPoint.getTarget().getClass();
        return targetClass.getDeclaredAnnotation(CacheConfig.class);
    }

    /**
     * 获取方法中声明的注解
     *
     * @param joinPoint
     *
     * @throws NoSuchMethodException
     */
    private MyCacheable getMethodAnnotation(JoinPoint joinPoint) throws NoSuchMethodException {
        // 获取方法名
        String methodName = joinPoint.getSignature().getName();
        // 反射获取目标类
        Class<?> targetClass = joinPoint.getTarget().getClass();
        // 拿到方法对应的参数类型
        Class<?>[] parameterTypes = ((MethodSignature) joinPoint.getSignature()).getParameterTypes();
        // 根据类、方法、参数类型（重载）获取到方法的具体信息
        Method objMethod = targetClass.getMethod(methodName, parameterTypes);
        // 拿到方法定义的注解信息
        return objMethod.getDeclaredAnnotation(MyCacheable.class);
    }

    /**
     * 解析key表达式，得到实际的key
     *
     * @param myCacheable
     * @param params
     */
    private String getKey(CacheConfig cacheConfig, MyCacheable myCacheable, Object[] params) {
        ExpressionParser parser = new SpelExpressionParser();
        StandardEvaluationContext ctx = new StandardEvaluationContext();
        // 获得原始key的表达式
        String strSourceKey = myCacheable.key();
        int intSeq = -1;
        String strSearchSeq = null;
        int intStartPos = 0;
        // 用SPEL解析表达式
        while (++intSeq < params.length) {
            strSearchSeq = "#p" + intSeq;
            intStartPos = StringUtils.indexOf(strSourceKey, strSearchSeq, intStartPos);
            if (intStartPos < 0) {
                break;
            } else {
                ctx.setVariable("p" + intSeq, params[intSeq]);
            }
        }
        // 执行表达式
        Expression expression = parser.parseExpression(strSourceKey);
        String strKey = expression.getValue(ctx).toString();
        // 拼接上缓存名称,spring cache会加上前缀,是在CacheConfig中配置的。
        if (cacheConfig != null) {
            strKey = cacheConfig.cacheNames()[0] + ":" + strKey;
        }
        return strKey;
    }

    public MyCacheable getAnnotationBak(ProceedingJoinPoint pjp) throws NoSuchMethodException {
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Class[] parameterTypes = signature.getParameterTypes();
        Method targetMethod = pjp.getTarget().getClass()
                                 .getMethod(pjp.getSignature().getName(), parameterTypes);
        MyCacheable cacheData = targetMethod.getDeclaredAnnotation(MyCacheable.class);
        return cacheData;
    }

    public static void main(String[] args) {
        int CACHE_EXPIRE_TIME = Long.valueOf(TimeUnit.MINUTES.toSeconds(2)).intValue();
        System.out.println(CACHE_EXPIRE_TIME);
    }

}
