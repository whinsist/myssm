package cn.com.cloudstar.rightcloud.system.enums;


import cn.hutool.core.util.StrUtil;

import cn.com.cloudstar.rightcloud.framework.common.exception.BizException;

public enum  OrderStatusEnum {

    APPROVING("approving", "审核中"),

    SUCCEED("succeed", "已开票");


    private String code;
    private String name;

    OrderStatusEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static String getName(String code) {
        if (StrUtil.isBlank(code)) {
            return null;
        }
        for (OrderStatusEnum value : OrderStatusEnum.values()) {
            if (value.code.equals(code)) {
                return value.name;
            }
        }

        throw new BizException("Unrecognized code: " + code);
    }
}
