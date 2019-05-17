package cn.com.cloudstar.rightcloud.framework.common.constants;

/**
 * 流程定义变量
 * @author jerry
 * @date 2018/8/7
 */
public interface ProcessConstants {

    /**
     * 租户管理员审核
     */
    String TENANT_AUDIT = "tenant";

    /**
     * 服务发布者所在租户的租户管理员
     */
    String OWNER_AUDIT = "owner";

    /**
     * 平台管理员审核
     */
    String PLATFORM_AUDIT = "platform";

    /**
     * 审核级别
     */
    String AUDIT_LEVEL = "audit_level";

    /**
     * 流程审核变量
     */
    String AUDIT = "audit";

    /**
     * 审核通过
     */
    String AUDIT_PASS = "pass";

    /**
     * 审核拒绝
     */
    String AUDIT_REJECT = "reject";

    /**
     * 流程活动记录状态后缀
     */
    String AUDIT_STATUS_SUFFIX = "_status";

    /**
     * 流程活动记录注释后缀
     */
    String AUDIT_COMMENT_SUFFIX = "_comment";

    /**
     * 审核状态
     */
    String AUDIT_STATUS = "status";

    /**
     * 审核注释
     */
    String AUDIT_COMMENT = "comment";
}
