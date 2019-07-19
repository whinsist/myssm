package cn.com.cloudstar.rightcloud.system.activiti.util;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;

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

    /**
     * 当前流程是否一结束
     */
    ThreadLocal<Boolean> PROCESS_FINISHED = new ThreadLocal<>();

    /**
     * 支持的流程业务类型
     */
    List<Map<String, String>> BUSINESS = Lists.newArrayListWithCapacity(8);

    /**
     * 流程业务类型
     */
    Map<String, String> BUSINESS_MAP = Maps.newHashMap();

    /**
     * 服务申请
     */
    String SERVICE_APPLY = "service-apply";
    /**
     * 服务变更
     */
    String SERVICE_CHANGEGRADE = "service-changegrade";
    /**
     * 服务续订
     */
    String SERVICE_RENEW = "service-renew";
    /**
     * 服务释放
     */
    String SERVICE_RELEASE = "service-release";
    /**
     * 脚本执行
     */
    String SERVICE_EXECSCRIPT = "service-execScript";
    /**
     * 工单
     */
    String TICKET = "ticket";

    /**
     * 自服务审批流程状态
     */
    String SELF_SERVICE_AUDIT_STATUS = "self.service.audit.status";

    /**
     * 工单审批流程状态
     */
    String TICKET_AUDIT_STATUS = "ticket.audit.status";

    /**
     * 流程开启
     */
    String AUDIT_OPEN = "1";

    /**
     * 流程关闭
     */
    String AUDIT_CLOSE = "0";
}
