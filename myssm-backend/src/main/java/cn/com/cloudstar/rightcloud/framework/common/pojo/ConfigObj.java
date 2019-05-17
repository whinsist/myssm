/*
 * Copyright (c) 2018 Cloud-Star, Inc. All Rights Reserved..
 */

package cn.com.cloudstar.rightcloud.framework.common.pojo;

/**
 * @author ShiWenQiang
 * @date 2017/12/5
 */
public class ConfigObj {

    /**
     * 定义key
     */
    private String CONFIG_KEY;

    /**
     * 定义值
     */
    private String CONFIG_VALUE;

    public String getCONFIG_KEY() {
        return CONFIG_KEY;
    }

    public void setCONFIG_KEY(String CONFIG_KEY) {
        this.CONFIG_KEY = CONFIG_KEY;
    }

    public String getCONFIG_VALUE() {
        return CONFIG_VALUE;
    }

    public void setCONFIG_VALUE(String CONFIG_VALUE) {
        this.CONFIG_VALUE = CONFIG_VALUE;
    }
}
