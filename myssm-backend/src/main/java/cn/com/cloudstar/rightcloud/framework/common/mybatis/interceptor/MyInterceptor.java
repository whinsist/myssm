package cn.com.cloudstar.rightcloud.framework.common.mybatis.interceptor;

import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;

import java.sql.Statement;
import java.util.Properties;

/**
 * @author Hong.Wu
 * @date: 9:22 2019/05/06
 * Mybatis支持对Executor、StatementHandler、PameterHandler和ResultSetHandler 接口进行拦截，也就是说会对这4种对象进行代理
 */
@Intercepts({
//        ResultSetHandler    负责将JDBC返回的ResultSet结果集对象转换成List类型的集合；
        @Signature(type = ResultSetHandler.class, method = "handleResultSets", args = Statement.class)
})
public class MyInterceptor implements Interceptor {

    //拦截目标对象
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        System.out.println(
                "拦截的对象是1：" + invocation.getTarget() + " 方法是：" + invocation.getMethod().toString() + " 参数是：" + invocation
                        .getArgs());
        //执行拦截对象真正的方法
        Object o = invocation.proceed();
        return o;
    }

    //包装目标对象 为目标对象创建动态代理 按照从前到后的顺序执行
    @Override
    public Object plugin(Object target) {
        System.out.println("插件方法1--将要包装的目标对象1：" + target);
        //为当前对象创建代理对象
        Object o = Plugin.wrap(target, this);
        return o;
    }

    //获取插件初始化参数
    @Override
    public void setProperties(Properties properties) {
        //System.out.println("MyInterceptor---"+properties);
    }
}
