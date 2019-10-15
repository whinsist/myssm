package cn.com.cloudstar.rightcloud.framework.common.study.jdbctest.pool;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbcp.BasicDataSource;

import cn.com.cloudstar.rightcloud.framework.common.util.DBCanst;

/**
 * @author Hong.Wu
 * @date: 21:12 2019/10/11
 */
public class DbcpTest {
    public static void main(String[] args) throws SQLException {
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        basicDataSource.setUrl(DBCanst.url);
        basicDataSource.setUsername(DBCanst.user);
        basicDataSource.setPassword(DBCanst.password);



        //最大活动数，最多只能有3个活动的连接。超过3个就需要等其他人关闭连接
        //超过最大等待时间，就连接失败
        basicDataSource.setMaxActive(3);
        //最大可保存数，多了就会关闭连接，不会保存到连接池中，也就意味着该连接不会重复使用
        basicDataSource.setMaxIdle(2);
        //最大等待时间
        basicDataSource.setMaxWait(3000);


        Connection connection1 = basicDataSource.getConnection();
        Connection connection2 = basicDataSource.getConnection();
        Connection connection3 = basicDataSource.getConnection();
        System.out.println("connection1:"+connection1.hashCode());
        System.out.println("connection2:"+connection2.hashCode());
        System.out.println("connection3:"+connection3.hashCode());
        connection1.close();
        connection2.close();
        connection3.close();

        System.out.println("===========================================");

        Connection connection4 = basicDataSource.getConnection();
        Connection connection5 = basicDataSource.getConnection();
        Connection connection6 = basicDataSource.getConnection();
        System.out.println("connection4:"+connection4.hashCode());
        System.out.println("connection5:"+connection5.hashCode());
        System.out.println("connection6:"+connection6.hashCode());
//        Connection connection7 = basicDataSource.getConnection();
    }
}
