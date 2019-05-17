/*
 * Copyright (c) 2018 Cloud-Star, Inc. All Rights Reserved..
 */

package cn.com.cloudstar.rightcloud.framework.common.util;

import cn.com.cloudstar.rightcloud.framework.common.cache.JedisUtil;
import com.google.common.base.Strings;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.Properties;

/**
 * 实例化config.properties方法
 *
 * @author 张荣
 */
public class PropertiesUtil {

    private static final String CONFIG_SQL = "SELECT config_value FROM sys_m_config WHERE config_key = ?";
    private static final String CONFIG_RESULT_KEY = "config_value";
    private static final String CACHE_PROPERTY_KEY = "system:config";
    private static final String CACHE_KEY_ROLE = "system:role";
    private static Properties properties = new Properties();

    // 调用方法将配置文件转化为类
    static {
        InputStreamReader reader = null;
        InputStream is = ClassLoaderUtil.getResourceAsStream("config.properties", PropertiesUtil.class);
        if (null != is) {
            try {
                reader = new InputStreamReader(is, "UTF-8");
                properties.load(reader);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取参数值
     *
     * @param key 参数名称
     * @return the property
     */
    public static String getProperty(String key) {
        String result = "";
        try {
            if (properties.containsKey(key)) {
                result = properties.getProperty(key);
            } else {
                // 配置文件中如果不存在, 则在系统配置表中查询
                // TODO redis add
                result = JedisUtil.instance().hget(CACHE_PROPERTY_KEY, key);
                if (Strings.isNullOrEmpty(result)) {
                    Map resultMap = DBUtil.queryMap(CONFIG_SQL, key);
                    if (resultMap != null) {
                        result = StringUtil.nullToEmpty(resultMap.get(CONFIG_RESULT_KEY));
                        JedisUtil.instance().hset(CACHE_PROPERTY_KEY, key, result, 30 * 60);
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    /**
     * @return the properties
     */
    public static Properties getProperties() {
        return properties;
    }

    /**
     * @param properties the properties to set
     */
    public static void setProperties(Properties properties) {
        PropertiesUtil.properties = properties;
    }

}
