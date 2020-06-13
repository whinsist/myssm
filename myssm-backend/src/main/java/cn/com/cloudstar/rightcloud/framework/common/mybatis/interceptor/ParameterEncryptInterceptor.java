package cn.com.cloudstar.rightcloud.framework.common.mybatis.interceptor;

import com.alibaba.fastjson.JSON;

import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.util.Objects;
import java.util.Properties;

import lombok.extern.slf4j.Slf4j;



/**
 * mybatis参数加密拦截器
 */
@Slf4j
@Component
@Intercepts({
        @Signature(type = ParameterHandler.class, method = "setParameters", args = PreparedStatement.class),
})
public class ParameterEncryptInterceptor implements Interceptor {


    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        log.debug("拦截器ParameterEncryptInterceptor [参数加密拦截器]运行---------");
        //拦截 ParameterHandler 的 setParameters 方法 动态设置参数
        if (invocation.getTarget() instanceof ParameterHandler) {
            ParameterHandler parameterHandler = (ParameterHandler) invocation.getTarget();

            // 反射获取 参数对像
            Field parameterField = parameterHandler.getClass().getDeclaredField("parameterObject");
            parameterField.setAccessible(true);
            Object parameterObject = parameterField.get(parameterHandler);
            if (Objects.nonNull(parameterObject)) {
                Class<?> parameterObjectClass = parameterObject.getClass();
                if (EncryptDecryptUtil.needDecryptOrDecrypt(parameterObject)) {
                    log.debug("加密dao对象:[{}]", parameterObjectClass.getName());
                    final Object encrypt = EncryptDecryptUtil.encrypt(parameterObject,true);
                    if (log.isDebugEnabled()) {
                        log.debug("加密后的结果: [{}]", JSON.toJSONString(encrypt));
                    }
                }
            }
        }
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object o) {
        return Plugin.wrap(o, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}