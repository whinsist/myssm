package cn.com.cloudstar.rightcloud.framework.test.t001study.jdbctest.pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


public class MyPool {

    private String driverClassName;//驱动名字
    private String url;//数据库地址
    private String username;//数据库用户名
    private String password;//数据库密码

    private int maxActive;//最大可活动数
    private int maxIdle;//最大可保存数

    //当前连接活动数
    private static int active = 0;
    //用来存放连接的连接池
    private static Set<Connection> set = new HashSet<>();

    //获取连接
    public Connection getConnection() throws Exception {
        //判断当前连接活动数active是否已经达到最大可活动数maxActive 3
        if (active < maxActive) {
            //可获取连接，首先看连接池是否有连接
            if (!set.isEmpty()) {
                //连接池不为空，直接返回连接池中的一个连接给用户
                Iterator<Connection> iterable = set.iterator();
                Connection connection = iterable.next();

                set.remove(connection);//从连接池删除刚刚获取要返回给用户的连接
                active++;//当前活动数+1
                System.out.println("==========连接池获取连接===========");
                //return  connection;
                MyInvocaHandler myInvocaHandler = new MyInvocaHandler();
                return (Connection) myInvocaHandler.bind(connection, maxIdle);

                //return (Connection)ProxyUtil.proxyConnectionInterface(connection);
            } else {
                Class.forName(driverClassName);
                Connection connection = DriverManager.getConnection(url, username, password);

                active++;//当前活动数+1
                System.out.println("==========驱动获取连接==========active=" + active);

                MyInvocaHandler myInvocaHandler = new MyInvocaHandler();
                return (Connection) myInvocaHandler.bind(connection, maxIdle);
            }

        }
        return null;
    }

    public String getDriverClassName() {
        return driverClassName;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getMaxActive() {
        return maxActive;
    }

    public void setMaxActive(int maxActive) {
        this.maxActive = maxActive;
    }

    public int getMaxIdle() {
        return maxIdle;
    }

    public void setMaxIdle(int maxIdle) {
        this.maxIdle = maxIdle;
    }

    public static int getActive() {
        return active;
    }

    public static void setActive(int active) {
        MyPool.active = active;
    }

    public static Set<Connection> getSet() {
        return set;
    }

    public static void setSet(Set<Connection> set) {
        MyPool.set = set;
    }

}
