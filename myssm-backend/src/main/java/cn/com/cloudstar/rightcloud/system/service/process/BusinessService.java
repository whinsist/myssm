package cn.com.cloudstar.rightcloud.system.service.process;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

import cn.com.cloudstar.rightcloud.framework.common.pojo.AuthUser;
import cn.com.cloudstar.rightcloud.framework.common.util.RequestContextUtil;
import cn.com.cloudstar.rightcloud.system.activiti.util.ProcessConstants;
import cn.com.cloudstar.rightcloud.system.activiti.util.ProcessHelper;
import cn.com.cloudstar.rightcloud.system.entity.act.ServiceOrder;
import cn.com.cloudstar.rightcloud.system.service.system.RoleService;

/**
 * @author Hong.Wu
 * @date: 16:03 2019/07/22
 */
@Service
@Slf4j
public class BusinessService {

    @Autowired
    private RoleService roleService;

    @Autowired
    private ServiceOrderService serviceOrderService;

    public List<String> queryCandidateUsers(String businessId, Map<String, Object> variables, List<String> roles) {
        String orgSidStr = (String) variables.get("__org_sid");
        Long orgSid;
        if (StringUtils.isNotBlank(orgSidStr)) {
            orgSid = Long.parseLong(orgSidStr);
        } else {
            AuthUser authUser = RequestContextUtil.getAuthUserInfo();
            orgSid = authUser.getOrgSid();
        }
        return Arrays.asList("100");

//        List<User> projectUsers = null;
//        List<User> platformUsers = null;
//        List<String> userAllStrs = Lists.newArrayList();
//
//        for (String roleStr : roles) {
//            Role role = roleService.selectByPrimaryKey(Long.parseLong(roleStr));
//            if (Objects.isNull(role)) {
//                continue;
//            }
//
//            if ("platform".equals(role.getRoleType())) {
//                if (Objects.nonNull(platformUsers)) {
//                    continue;
//                }
//
//                Long compayId = roleService.getCompayIdByOrgSid(orgSid);
//                platformUsers = orgService.findUsersAndJoinedByOrgIdHasApprovalCustomize(compayId);
//
//                List<String> userList = platformUsers.stream().filter(user -> {
//                    if (user.getRoles() == null) {
//                        return false;
//                    }
//
//                    for (Role r : user.getRoles()) {
//                        if (roles.contains(r.getRoleSid().toString())) {
//                            return true;
//                        }
//                    }
//
//                    return false;
//                }).map(user -> user.getUserSid().toString()).collect(Collectors.toList());
//
//                userAllStrs.addAll(userList);
//            } else if ("project".equals(role.getRoleType())) {
//                if (Objects.nonNull(projectUsers)) {
//                    continue;
//                }
//
//                projectUsers = orgService.findUsersAndJoinedByOrgIdHasApprovalCustomize(orgSid);
//                List<String> userList = projectUsers.stream().filter(user -> {
//                    if (user.getRoles() == null) {
//                        return false;
//                    }
//
//                    for (Role r : user.getRoles()) {
//                        if (roles.contains(r.getRoleSid().toString())) {
//                            return true;
//                        }
//                    }
//
//                    return false;
//                }).map(user -> user.getUserSid() + "").collect(Collectors.toList());
//
//                userAllStrs.addAll(userList);
//            }
//        }
//
//        return userAllStrs.stream().distinct().collect(Collectors.toList());
    }


    public void taskCreatedMessage(String businessId, List<Long> candidates,
                                   Map<String, Object> execVariables, List<String> notifyWays) {
        if (candidates == null || candidates.size() < 1) {
            return;
        }
        if (notifyWays == null || notifyWays.size() < 1) {
            return;
        }

        String businessCode = (String) execVariables.get("_business_code");
        String serviceOrderId = (String) execVariables.get("_service_order_id");
        String serviceContent = (String) execVariables.get("_service_order_content");
        String createdTime = (String) execVariables.get("_service_order_created_time");
        String applyName = (String) execVariables.get("_apply_uname");
        String businessName = ProcessHelper.BUSINESS_MAP.get(businessCode);

        String msgTemplate = "【RightCloud】服务单流程流转提醒，您有一个[%s]申请单需要审批，申请单号：%s，请登录系统查看。";
        String msg = String.format(msgTemplate, businessName, businessId);

        log.info("任务创建消息: {}", msg);

        for (String notifyWay : notifyWays) {
            if ("station".equals(notifyWay)) {
            } else if ("sms".equals(notifyWay)) {
            } else if ("mail".equals(notifyWay)) {
            }
        }
    }

    public void taskCompletedMessage(String businessId, String assigneeName, Long applyUserSid,
                                     Map<String, Object> execVariables, List<String> notifyWays) {
        if (notifyWays == null || notifyWays.size() < 1) {
            return;
        }

        ServiceOrder serviceOrder = serviceOrderService.selectByOrderSn(businessId);

        String businessCode = (String) execVariables.get("_business_code");
        String businessName = ProcessHelper.BUSINESS_MAP.get(businessCode);

        String audit = (String) execVariables.get(ProcessConstants.AUDIT);
        String auditApproveAdvice = (String) execVariables.get("_audit_approve_advice");
        String auditStatus = ProcessConstants.AUDIT_PASS.equals(audit) ? "通过了" : "拒绝了";

        String msgTemplate = "【RightCloud】服务单流程流转提醒，[%s]审批[%s]您的[%s]申请，申请单号：%s，请登录系统查看。";
        String msg = String.format(msgTemplate, assigneeName, auditStatus, businessName, businessId);

        log.info("任务完成消息: {}", msg);

        for (String notifyWay : notifyWays) {
            if ("station".equals(notifyWay)) {
                // 站内信
            } else if ("sms".equals(notifyWay)) {
                // 短信
            } else if ("mail".equals(notifyWay)) {
                // 邮件
            }
        }
    }
}
