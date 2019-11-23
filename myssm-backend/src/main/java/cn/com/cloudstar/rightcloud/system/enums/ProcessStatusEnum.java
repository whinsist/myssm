package cn.com.cloudstar.rightcloud.system.enums;


import cn.hutool.core.util.StrUtil;

import cn.com.cloudstar.rightcloud.framework.common.exception.BizException;

public enum ProcessStatusEnum {
    // 流程状态 - 编辑未发布的状态
    EDITED (1, "编辑未发布"),
    //流程状态 - 正常状态
    NORMAL(0, "正常");
//    private static final Integer PROCESS_STATUS_= 1;
//    private static final Integer PROCESS_STATUS_NORMAL = 0;

    private Integer code;
    private String name;

    ProcessStatusEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public static String getName(String code) {
        if (StrUtil.isBlank(code)) {
            return null;
        }
        for (ProcessStatusEnum value : ProcessStatusEnum.values()) {
            if (value.code.equals(code)) {
                return value.name;
            }
        }

        throw new BizException("Unrecognized code: " + code);
    }
}
