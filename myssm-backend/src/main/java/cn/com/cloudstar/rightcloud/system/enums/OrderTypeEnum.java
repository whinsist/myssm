package cn.com.cloudstar.rightcloud.system.enums;


import cn.hutool.core.util.StrUtil;

import cn.com.cloudstar.rightcloud.framework.common.exception.BizException;

public enum OrderTypeEnum {

    APPROVING("apply", "申请"),
    EXTEND("extend", "延期"),
    MODIFY("modify", "变更"),
    DELETE("delete", "删除"),
    EXECSCRIPT("execScript", "脚本部署"),
    RELEASE("release", "退订"),
    CHANGEGRADE("changegrade", "变更配置"),
    RENEW("renew", "续订");


    private String code;
    private String name;

    OrderTypeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static String getName(String code) {
        if (StrUtil.isBlank(code)) {
            return null;
        }
        for (OrderTypeEnum value : OrderTypeEnum.values()) {
            if (value.code.equals(code)) {
                return value.name;
            }
        }

        throw new BizException("Unrecognized code: " + code);
    }
}
