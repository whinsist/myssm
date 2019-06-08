/*
 * Copyright (c) 2018 Cloud-Star, Inc. All Rights Reserved..
 */

package cn.com.cloudstar.rightcloud.framework.common.util;

import com.google.common.base.Strings;

import com.alibaba.druid.pool.DruidDataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * 简易DB工具类
 *
 * @author Chaohong.Mao
 */
public class DBUtil {
    /**
     * 数据库查询类
     */
    private static QueryRunner runner;


    static {

        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUrl(DBCanst.url);
        druidDataSource.setUsername(DBCanst.user);
        druidDataSource.setPassword(DBCanst.password);
        druidDataSource.setTestOnBorrow(false);
        druidDataSource.setTestOnReturn(false);
        druidDataSource.setTestWhileIdle(true);
        druidDataSource.setValidationQuery("select 'x'");
        druidDataSource.setMaxActive(20);
        druidDataSource.setMinIdle(1);
        druidDataSource.setPoolPreparedStatements(false);
        druidDataSource.setTimeBetweenEvictionRunsMillis(60 * 1000);
        druidDataSource.setMinEvictableIdleTimeMillis(300000);
        druidDataSource.setRemoveAbandoned(true);
        druidDataSource.setRemoveAbandonedTimeout(3600);

        runner = new QueryRunner(druidDataSource);
    }



    /**
     * 查询表.
     *
     * @param <T>       the type parameter
     * @param sql       查询SQL
     * @param beanClazz the bean clazz
     * @param param     SQL参数
     * @return 结果 list
     */
    public static <T> List<T> queryBeanList(String sql, Class<T> beanClazz, Object... param) {
        List<T> result = null;
        try {

            BeanListHandler<T> beanListHandler = new BeanListHandler(beanClazz);


            result = runner.query(sql, beanListHandler, param);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * 插入数据.
     *
     * @param sql   SQL
     * @param param 参数
     * @return 影响条数 int
     */
    public static int insert(String sql, Object... param) {
        ResultSetHandler<String> resultHandler = new BeanHandler<>(String.class);
        String genKey = null;
        try {
            genKey = runner.insert(sql, resultHandler, param);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return genKey == null ? 0 : 1;
    }

    /**
     * 更新、删除DB数据.
     *
     * @param sql   SQL
     * @param param 参数
     * @return 影响条数 int
     */
    public static int update(String sql, Object... param) {
        int result = 0;
        try {
            result = runner.update(sql, param);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static void main(String args[]) {
        String sql = "SELECT * FROM sys_m_user where ACCOUNT = ?";
        System.out.println(queryMap(sql, "louxiang"));
//        test();
    }

    /**
     * 查询表.
     *
     * @param sql   查询SQL
     * @param param SQL参数
     * @return 结果 map
     */
    public static Map queryMap(String sql, Object... param) {
        Map result = null;
        try {
            result = runner.query(sql, new MapHandler(), param);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    private static void test() {
        String ROLE_SQL = "SELECT\n" + "  A.ROLE_SID,\n" + "  A.ROLE_NAME,\n" + "  A.STATUS,\n" + "  A.ROLE_TYPE,\n" +
                "  C.MODULE_SID AS moduleIds\n" + "FROM sys_m_role A\n" + "  INNER JOIN sys_m_user_role B\n" +
                "    ON A.ROLE_SID = B.ROLE_SID AND\n" + "       B.USER_SID = ?\n" +
                "  LEFT JOIN sys_m_role_module C ON C.ROLE_SID = A.ROLE_SID\n" + "";
        System.out.println(queryColumnList(ROLE_SQL, "moduleIds", 1749));
    }

    /**
     * 查询表.
     * 结果为出入列的List
     *
     * @param sql   查询SQL
     * @param col   查询列
     * @param param SQL参数
     * @return 结果 list
     */
    public static List<String> queryColumnList(String sql, String col, Object... param) {
        List<String> result = null;
        try {
            result = runner.query(sql, new ColumnListHandler<String>(col), param);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

}
