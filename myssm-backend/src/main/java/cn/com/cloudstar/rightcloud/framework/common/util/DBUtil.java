/*
 * Copyright (c) 2018 Cloud-Star, Inc. All Rights Reserved..
 */

package cn.com.cloudstar.rightcloud.framework.common.util;

import com.alibaba.druid.pool.DruidDataSource;
import com.google.common.base.Strings;
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

    /**
     * openfalcon数据库查询类
     */
    private static QueryRunner openfalconRunner;

    static {
        Properties p = new Properties();

        /* 数据库用户名 */
        String user = null;
        /* 数据库密码 */
        String password = null;
        /* 数据库连接 */
        String url = null;


        // 如果环境变量中有配置信息，优先使用环境变量中的配置信息；反之，则使用配置文件中的信息
        Properties sysProps = System.getProperties();
        if (Strings.isNullOrEmpty(sysProps.getProperty("cloudstar.db.address"))) {
            InputStream is = ClassLoaderUtil.getResourceAsStream("jdbc.properties", DBUtil.class);
            try (InputStreamReader reader = new InputStreamReader(is, "UTF-8")) {
                p.load(reader);
                /* 数据库用户名 */
                user = p.getProperty("jdbc.username");
                /* 数据库密码 */
                password = p.getProperty("jdbc.password");
                /* 数据库连接 */
                url = p.getProperty("jdbc.url");

                /* openfalcon数据库dirver */
                String openfalcon_driver = p.getProperty("openfalcon.jdbc.driver");
                /*  openfalcon数据库用户名 */
                String openfalcon_user = p.getProperty("openfalcon.jdbc.username");
                /*  openfalcon数据库密码 */
                String openfalcon_password = p.getProperty("openfalcon.jdbc.password");
                /*  openfalcon数据库连接 */
                String openfalcon_url = p.getProperty("openfalcon.jdbc.url");

                DruidDataSource openFalconDataSource = new DruidDataSource();
//                openFalconDataSource.setUrl(openfalcon_url);
//                openFalconDataSource.setUsername(openfalcon_user);
//                openFalconDataSource.setPassword(openfalcon_password);
//                openFalconDataSource.setTestOnBorrow(false);
//                openFalconDataSource.setTestOnReturn(false);
//                openFalconDataSource.setTestWhileIdle(true);
//                openFalconDataSource.setValidationQuery("select 'x'");
//                openFalconDataSource.setMaxActive(20);
//                openFalconDataSource.setMinIdle(1);
//                openFalconDataSource.setPoolPreparedStatements(true);
//                openFalconDataSource.setTimeBetweenEvictionRunsMillis(60 * 1000);
//                openFalconDataSource.setMinEvictableIdleTimeMillis(300000);
//                openFalconDataSource.setRemoveAbandoned(true);
//                openFalconDataSource.setRemoveAbandonedTimeout(3600);

                openfalconRunner = new QueryRunner(openFalconDataSource);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {

            /* 数据库用户名 */
            user = sysProps.getProperty("cloudstar.db.username");
            /* 数据库密码 */
            password = sysProps.getProperty("cloudstar.db.password");
             /* 数据库名 */
            String dbName = null != sysProps.getProperty("cloudstar.db.dbname") ? sysProps.getProperty("cloudstar.db.dbname") : "rightcloud";
            /* 数据库连接 */
            url = String.format("jdbc:mysql://%s:%s/"+dbName+"?useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true&autoReconnect=true&failOverReadOnly=false&useSSL=false"
                    , sysProps.getProperty("cloudstar.db.address"), sysProps.getProperty("cloudstar.db.port"));
        }

        DruidDataSource druidDataSource = new DruidDataSource();
//        druidDataSource.setUrl(url);
//        druidDataSource.setUsername(user);
//        druidDataSource.setPassword(password);
//        druidDataSource.setTestOnBorrow(false);
//        druidDataSource.setTestOnReturn(false);
//        druidDataSource.setTestWhileIdle(true);
//        druidDataSource.setValidationQuery("select 'x'");
//        druidDataSource.setMaxActive(20);
//        druidDataSource.setMinIdle(1);
//        druidDataSource.setPoolPreparedStatements(false);
//        druidDataSource.setTimeBetweenEvictionRunsMillis(60 * 1000);
//        druidDataSource.setMinEvictableIdleTimeMillis(300000);
//        druidDataSource.setRemoveAbandoned(true);
//        druidDataSource.setRemoveAbandonedTimeout(3600);

        runner = new QueryRunner(druidDataSource);
    }

    /**
     * 查询openFalcon数据库下面表的数据.
     *
     * @param sql   查询SQL
     * @param param SQL参数
     * @return 结果 map
     */
    public static Map queryOpenfalconMap(String sql, Object... param) {
        Map result = null;
        try {
            result = openfalconRunner.query(sql, new MapHandler(), param);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * 查询openFalcon数据库下面表的数据.
     *
     * @param sql   查询SQL
     * @param param SQL参数
     * @return 结果 map
     */
    public static List<Map<String, Object>> queryOpenfalconMapList(String sql, Object... param) {
        List<Map<String, Object>> result = null;
        try {
            result = openfalconRunner.query(sql, new MapListHandler(), param);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
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
            result = runner.query(sql, new BeanListHandler<>(beanClazz), param);
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
