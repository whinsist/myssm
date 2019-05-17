/*
 * Copyright (c) 2018 Cloud-Star, Inc. All Rights Reserved..
 */

package cn.com.cloudstar.rightcloud.framework.common.constants;

/**
 * UUID常量类
 *
 * @author 刘洋
 */
public interface UuidConstants {


    /**
     * UUID前缀
     */
    interface PrefixCode {

        /**
         * 云主机
         */
        String CS = "cs-";

        /**
         * 块存储
         */
        String CBS = "cbs-";

        /**
         * SSH密钥对
         */
        String KEYPAIR = "sk-";

        /**
         * 弹性IP
         */
        String EIP = "eip-";

        /**
         * 私有网络
         */
        String VPC = "vpc-";

        /**
         * 负责均衡
         */
        String LOADBALANCE = "lb-";
    }
}
