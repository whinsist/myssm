/*
 * Copyright (c) 2018 Cloud-Star, Inc. All Rights Reserved..
 */

package cn.com.cloudstar.rightcloud.framework.common.constants;

/**
 * Created by qct on 2016/3/17.
 *
 * @author qct
 */
public class CacheConstants {
    public static final String USER = "user";
    public static final String TENANT = "tenant";
    public static final String SEPARATOR = ":";
    public static final String CLUSTER = "cluster";

    public static String redisUserKey(String env, String identifier) {
        return USER + SEPARATOR + env + SEPARATOR + identifier;
    }

    public static String redisTenantKey(String env, String identifier) {
        return TENANT + SEPARATOR + env + SEPARATOR + identifier;
    }

    public static String redisClusterPortKey(Long clusterId){
        return CLUSTER + SEPARATOR + clusterId;
    }
}
