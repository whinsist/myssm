package cn.com.cloudstar.rightcloud.framework.common.mybatis.interceptor;


import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.util.Objects;
import java.util.Properties;

import cn.com.cloudstar.rightcloud.framework.common.mybatis.interceptor.helper.PermissionAop;
import cn.com.cloudstar.rightcloud.framework.common.mybatis.interceptor.helper.PermissionUtils;
import cn.com.cloudstar.rightcloud.framework.common.mybatis.interceptor.helper.ReflectUtil;
import cn.com.cloudstar.rightcloud.framework.common.mybatis.interceptor.helper.UserUtils;


/**
 * mybatis数据权限拦截器 - prepare
 *
 * @author GaoYuan
 * @date 2018/4/17 上午9:52
 */
@Intercepts({
        @Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})
})
@Component
public class PrepareInterceptor implements Interceptor {

    /**
     * 日志
     */
    private static final Logger log = LoggerFactory.getLogger(PrepareInterceptor.class);

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
    }

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        if (invocation.getTarget() instanceof RoutingStatementHandler) {
            RoutingStatementHandler handler = (RoutingStatementHandler) invocation.getTarget();
            StatementHandler delegate = (StatementHandler) ReflectUtil.getFieldValue(handler, "delegate");
            MappedStatement mappedStatement = (MappedStatement) ReflectUtil.getFieldValue(delegate, "mappedStatement");

            PermissionAop permissionAop = PermissionUtils.getPermissionByDelegate(mappedStatement);
            if (Objects.nonNull(permissionAop)) {
                BoundSql boundSql = delegate.getBoundSql();
                log.info("数据权限sql.. boundSql-org : {}", formatPrintSQL(boundSql.getSql()));
                ReflectUtil.setFieldValue(boundSql, "sql", permissionSql(boundSql.getSql()));
                log.info("数据权限sql.. boundSql-now : {}", formatPrintSQL(boundSql.getSql()));
            }
        }
        return invocation.proceed();
    }

    private String formatPrintSQL(String sql) {
        String notnStr = sql.replaceAll("\n", "");
        return notnStr.replaceAll("[ ]+", " ");
    }


    /**
     * 权限sql包装
     *
     * @author GaoYuan
     * @date 2018/4/17 上午9:51
     */
    protected String permissionSql(String sql) {
        StringBuilder sbSql = new StringBuilder(sql);
        //当前登录人
        String userId = UserUtils.getUserId();
        //如果用户为 1 则只能查询第一条
        if ("1".equals(userId)) {
            String regionCd = UserUtils.getRegionCdByUserId();
            sbSql = new StringBuilder("select * from (").append(sbSql)
                                                        .append(" ) s where s.region_cd like concat(" + regionCd
                                                                        + ",'%')  ");
        }
        return sbSql.toString();
    }


}
