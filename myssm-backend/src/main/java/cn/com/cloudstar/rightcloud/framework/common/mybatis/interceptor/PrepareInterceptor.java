package cn.com.cloudstar.rightcloud.framework.common.mybatis.interceptor;


import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.util.Properties;

import cn.com.cloudstar.rightcloud.framework.common.mybatis.interceptor.helper.PermissionAop;
import cn.com.cloudstar.rightcloud.framework.common.mybatis.interceptor.helper.PermissionUtils;
import cn.com.cloudstar.rightcloud.framework.common.mybatis.interceptor.helper.ReflectUtil;
import cn.com.cloudstar.rightcloud.framework.common.mybatis.interceptor.helper.UserUtils;


/**
 * mybatis数据权限拦截器 - prepare
 * @author GaoYuan
 * @date 2018/4/17 上午9:52
 */
@Intercepts({
//          @Signature(type = Executor.class, method = "update", args = { MappedStatement.class, Object.class }),
        @Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class,Integer.class })
//        @Signature(method = "query", type = Executor.class, args = { MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class })
})
@Component
public class PrepareInterceptor implements Interceptor {
    /** 日志 */
    private static final Logger log = LoggerFactory.getLogger(PrepareInterceptor.class);

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {}

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        if(log.isInfoEnabled()){
            log.info("进入 PrepareInterceptor 拦截器...");
        }
        if(invocation.getTarget() instanceof RoutingStatementHandler) {

//            @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}),
            Object[] args = invocation.getArgs();
//            MappedStatement ms = (MappedStatement) args[0];
//            Object parameter = args[1];
//            RowBounds rowBounds = (RowBounds) args[2];
//            ResultHandler resultHandler = (ResultHandler) args[3];

            RoutingStatementHandler handler = (RoutingStatementHandler) invocation.getTarget();
            StatementHandler delegate = (StatementHandler) ReflectUtil.getFieldValue(handler, "delegate");
            //通过反射获取delegate父类BaseStatementHandler的mappedStatement属性
            MappedStatement mappedStatement = (MappedStatement) ReflectUtil.getFieldValue(delegate, "mappedStatement");
            //千万不能用下面注释的这个方法，会造成对象丢失，以致转换失败
            //MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];

            PermissionAop permissionAop = PermissionUtils.getPermissionByDelegate(mappedStatement);
            if(permissionAop == null){
                if(log.isInfoEnabled()){
                    log.info("数据权限放行...");
                }
                return invocation.proceed();
            }
            if(log.isInfoEnabled()){
                log.info("数据权限处理【拼接SQL】...");
            }
            BoundSql boundSql = delegate.getBoundSql();

//            final Object[] queryArgs = invocation.getArgs();
//            final Object parameter = queryArgs[1];
//            BoundSql boundSql2 = mappedStatement.getBoundSql(parameter);

            //Connection connection = (Connection) invocation.getArgs()[0];
            ReflectUtil.setFieldValue(boundSql, "sql", permissionSql(boundSql.getSql()));
            System.out.println("");
        }
        return invocation.proceed();


    }


    /**
     * 权限sql包装
     * @author GaoYuan
     * @date 2018/4/17 上午9:51
     */
    protected String permissionSql(String sql) {
        StringBuilder sbSql = new StringBuilder(sql);
        //当前登录人
        String userId = UserUtils.getUserId();
        //如果用户为 1 则只能查询第一条
        if("1".equals(userId)){
            String regionCd = UserUtils.getRegionCdByUserId();
            sbSql = new StringBuilder("select * from (").append(sbSql).append(" ) s where s.region_cd like concat("+ regionCd +",'%')  ");
        }
        return sbSql.toString();
    }



}
