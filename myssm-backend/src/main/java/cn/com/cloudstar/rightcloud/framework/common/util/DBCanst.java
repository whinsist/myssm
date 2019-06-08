package cn.com.cloudstar.rightcloud.framework.common.util;

import com.alibaba.druid.pool.DruidDataSource;
import com.google.common.base.Strings;

import org.apache.commons.dbutils.QueryRunner;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * @author Hong.Wu
 * @date: 20:21 2019/06/08
 */
public class DBCanst {
    /* 数据库连接 */
    public static String url = null;
    /* 数据库用户名 */
    public static String user = null;
    /* 数据库密码 */
    public static String password = null;


    static {
        Properties p = new Properties();
        // 如果环境变量中有配置信息，优先使用环境变量中的配置信息；反之，则使用配置文件中的信息
        Properties sysProps = System.getProperties();
        if (Strings.isNullOrEmpty(sysProps.getProperty("cloudstar.db.address"))) {
            InputStream is = ClassLoaderUtil.getResourceAsStream("jdbc.properties", DBCanst.class);
            try (InputStreamReader reader = new InputStreamReader(is, "UTF-8")) {
                p.load(reader);
                /* 数据库用户名 */
                user = p.getProperty("jdbc.username");
                /* 数据库密码 */
                password = p.getProperty("jdbc.password");
                /* 数据库连接 */
                url = p.getProperty("jdbc.url");
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
    }
}
