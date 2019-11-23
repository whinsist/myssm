package cn.com.cloudstar.rightcloud.system.enums;


import cn.hutool.core.util.StrUtil;

import cn.com.cloudstar.rightcloud.framework.common.exception.BizException;

public enum ProcessNodeStatusEnum {
    /**
     * 0: 正常节点(开始、关闭)
     * 1: 服务节点(开通服务、拒绝关闭)
     * 2: 服务拒绝关闭节点
     * 3: 服务开通审批节点
     * 103: 发布持久化节点
     * 104: 已删除
     */
//    int NormalNode = 0;
//    int ServiceNodeNode = 1;
//    int ServiceRejectCloseNode = 2;
//    int ServiceOpenAuditNode = 3;
//    int PublicDurableNode = 103;
//    int DeletedNode = 104;

    ServiceOpenAuditNode (3, "新增的节点"),
    //流程状态 - 正常状态
    PublicDurableNode(103, "发布持久化节点"),


    NormalNode (0, "正常节点"),
    ServiceNodeNode (1, "服务节点"),
    ServiceRejectCloseNode (2, "服务拒绝关闭节点"),
    DeletedNode (104, "正常节点"),
    ;



    private Integer code;
    private String name;

    ProcessNodeStatusEnum(Integer code, String name) {
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
        for (ProcessNodeStatusEnum value : ProcessNodeStatusEnum.values()) {
            if (value.code.equals(code)) {
                return value.name;
            }
        }

        throw new BizException("Unrecognized code: " + code);
    }
}
