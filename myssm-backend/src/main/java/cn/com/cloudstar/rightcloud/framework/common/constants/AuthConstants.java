/*
 * Copyright (c) 2018 Cloud-Star, Inc. All Rights Reserved..
 */

package cn.com.cloudstar.rightcloud.framework.common.constants;

import java.util.Base64;

/**
 * Created by qct on 2016/2/4.
 */
public class AuthConstants {
    public static final String CLAIMS_KEY = "claims";
    public static final int PERIED_TIME = 60 * 60;
    public static final String CACHE_KEY_USER_PREFIX = "portal:user:";
    public static final String CACHE_KEY_ACCOUNT_PREFIX = "portal:account:id";
    public static final String CACHE_KEY_USERID = "portal:account:userid";
    public static final String CACHE_KEY_ROLE_PREFIX = "portal:role:";
    public static final String CACHE_USER_TOKEN = "user:token:";
    public static final String USER_INFO_PASS = "user:info:pass";
    public static final String HARBOR_PROJECT_ID = "harbor:project:id";
    public static final String CACHE_KEY_ROLE = "system:role:";
    public static long TTL_MILLIS = 86400000;
    public static String SUBJECT = "inner";
    public static String SECRET_KEY = Base64.getEncoder().encodeToString("secretKey".getBytes());

//    static {
//        if (!Strings.isNullOrEmpty(PropertiesUtil.getProperty("xxx.ttl.millis"))) {
//            TTL_MILLIS = Long.valueOf(PropertiesUtil.getProperty("xxx.ttl.millis"));
//        }
//        //TODO 从配置文件或者数据库中读取配置替换默认值
//    }


    public static final String LOGIN_COOKIE_NAME = "PLATFORM_CMP_TOKEN";

}
