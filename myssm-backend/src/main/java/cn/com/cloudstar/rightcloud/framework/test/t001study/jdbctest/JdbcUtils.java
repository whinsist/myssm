package cn.com.cloudstar.rightcloud.framework.test.t001study.jdbctest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import cn.com.cloudstar.rightcloud.framework.common.util.DBCanst;

/**
 * Jdbc工具类
 */
public final class JdbcUtils {

    private static String url = DBCanst.url;
    private static String username = DBCanst.user;
    private static String password = DBCanst.password;

    /**
     * 构造器私用,防止直接创建对象, 当然通过反射可以创建
     */
    private JdbcUtils() {

    }

    //保证只是注册一次驱动
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    /**
     * 获取连接
     *
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    /**
     * 释放资源
     */
    public static void free(ResultSet rs, Statement st, Connection conn) {
        //规范的关系连接的方式
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (st != null) {
                    st.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                if (conn != null) {
                    try {
                        conn.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}