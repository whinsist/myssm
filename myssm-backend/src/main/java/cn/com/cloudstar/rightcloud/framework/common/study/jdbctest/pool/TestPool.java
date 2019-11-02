package cn.com.cloudstar.rightcloud.framework.common.study.jdbctest.pool;

import java.lang.reflect.Proxy;
import java.sql.Connection;

import cn.com.cloudstar.rightcloud.framework.common.study.jdbctest.pool.MyPool;
import cn.com.cloudstar.rightcloud.framework.common.study.pattern.proxy.proxydyn.ProxyUtil;
import cn.com.cloudstar.rightcloud.framework.common.util.DBCanst;

/**
 * @author Hong.Wu
 * @date: 23:37 2019/10/10
 */
public class TestPool {

    public static void main(String[] args) throws Exception {




        MyPool myPool = new MyPool();
        myPool.setDriverClassName("com.mysql.jdbc.Driver");
        myPool.setUrl(DBCanst.url);
        myPool.setUsername(DBCanst.user);
        myPool.setPassword(DBCanst.password);

        //最大活动数，最多只能有3个活动的连接。超过3个就需要等其他人关闭连接
        //超过最大等待时间，就连接失败
        myPool.setMaxActive(3);
        //最大可保存数，多了就会关闭连接，不会保存到连接池中，也就意味着该连接不会重复使用
        myPool.setMaxIdle(2);

        Connection connection1 = myPool.getConnection();
        Connection connection2 = myPool.getConnection();
        Connection connection3 = myPool.getConnection();
        System.out.println("connection1:" + connection1);
        System.out.println("connection2:" + connection2);
        System.out.println("connection3:" + connection3);

        connection1.close();
        connection2.close();
        connection3.close();

        System.out.println("================================================================");

        Connection connection4 = myPool.getConnection();
        Connection connection5 = myPool.getConnection();
        Connection connection6 = myPool.getConnection();
        System.out.println("connection4:" + connection4 + "    " + (connection4 instanceof Proxy));
        System.out.println("connection5:" + connection5 + "    " + (connection5 instanceof Proxy));
        System.out.println("connection6:" + connection6 + "    " + (connection6 instanceof Proxy));
        connection4.close();
        connection5.close();
        connection6.close();

        System.out.println("================================================================");

        Connection connection7 = myPool.getConnection();
        Connection connection8 = myPool.getConnection();
        Connection connection9 = myPool.getConnection();

        System.out.println("connection7:" + connection7);
        System.out.println("connection8:" + connection8);
        System.out.println("connection9:" + connection9);


    }
}