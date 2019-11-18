package cn.com.cloudstar.rightcloud.framework.common.mybatis.interceptor;


import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import cn.hutool.core.util.ReflectUtil;

import cn.com.cloudstar.rightcloud.framework.common.mybatis.interceptor.helper.PermissionAop;
import cn.com.cloudstar.rightcloud.framework.common.mybatis.interceptor.helper.PermissionUtils;

/**
 * mybatis数据权限拦截器 - handleResultSets 对结果集进行过滤
 *
 * @author GaoYuan
 * @date 2018/4/17 上午9:52
 */
@Intercepts({
        @Signature(type = ResultSetHandler.class, method = "handleResultSets", args = {Statement.class})
})
@Component
public class ResultInterceptor implements Interceptor {

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
    }

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        ResultSetHandler resultSetHandler1 = (ResultSetHandler) invocation.getTarget();
        //通过java反射获得mappedStatement属性值
        //可以获得mybatis里的resultype
        MappedStatement mappedStatement = (MappedStatement) ReflectUtil.getFieldValue(resultSetHandler1,
                                                                                      "mappedStatement");
        //获取切面对象
        PermissionAop permissionAop = PermissionUtils.getPermissionByDelegate(mappedStatement);

        //执行请求方法，并将所得结果保存到result中
        Object result = invocation.proceed();
        if (permissionAop != null && result instanceof ArrayList) {
            ArrayList resultList = (ArrayList) result;
            for (int i = 0; i < resultList.size(); i++) {
                Object item = resultList.get(i);
                Method method = item.getClass().getMethod("setRegionCd", String.class);
                // 调用obj对象的 method 方法
                method.invoke(item, "新值");
            }
        }
        return result;
    }


}
