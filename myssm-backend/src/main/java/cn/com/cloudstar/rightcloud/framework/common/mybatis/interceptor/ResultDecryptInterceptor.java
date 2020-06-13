package cn.com.cloudstar.rightcloud.framework.common.mybatis.interceptor;

import com.alibaba.fastjson.JSON;

import org.apache.commons.collections.CollectionUtils;
import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.springframework.stereotype.Component;

import java.sql.Statement;
import java.util.List;
import java.util.Objects;
import java.util.Properties;

import lombok.extern.slf4j.Slf4j;


/**
 * mybatis 结果解密拦截器
 */
@Slf4j
@Component
@Intercepts({
        @Signature(type = ResultSetHandler.class, method = "handleResultSets", args = {Statement.class})
})
public class ResultDecryptInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        log.debug("拦截器ResultDecryptInterceptor [结果解密拦截器] 运行---------");
        Object result = invocation.proceed();
        if (Objects.isNull(result)) {
            return null;
        }
        Class c = Object.class;
        if (result instanceof List) {
            List list = (List) result;
            if (list == null || CollectionUtils.isEmpty(list)
                    || !EncryptDecryptUtil.needDecryptOrDecrypt(list.get(0))) {
                return result;
            }
            c = list.get(0).getClass();
        } else {
            if (!EncryptDecryptUtil.needDecryptOrDecrypt(result)) {
                return result;
            }
            c = result.getClass();
        }
        log.debug("解密dao对象:[{}]", c.getName());
        Object decrypt = EncryptDecryptUtil.decrypt(result,true);
        if (log.isDebugEnabled()) {
            log.debug("解密密后的结果: [{}]", JSON.toJSONString(decrypt));
        }
        return decrypt;
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
