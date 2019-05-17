/*
 * Copyright (c) 2018 Cloud-Star, Inc. All Rights Reserved..
 */

package cn.com.cloudstar.rightcloud.framework.common.util;


import cn.com.cloudstar.rightcloud.framework.common.constants.WebConstants;

/**
 * Created by liuxiaolu on 2017/8/3.
 */
public class StatusUtil {
    public static Object openstackToPlatformStatus(String status) {
        switch (status.toLowerCase()) {
            case "active":
            case "down":
                return WebConstants.NETWORK_STATUS.CREATE_SUCCESS;
            case "build":
                return WebConstants.NETWORK_STATUS.CREATING;
            case "error":
            case "unrecognized":
                return WebConstants.NETWORK_STATUS.CREATE_FAILURE;
        }
        return null;
    }
}
