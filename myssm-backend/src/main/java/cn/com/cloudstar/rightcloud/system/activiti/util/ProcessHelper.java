package cn.com.cloudstar.rightcloud.system.activiti.util;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.BpmnModel;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import cn.com.cloudstar.rightcloud.system.activiti.util.ProcessConstants;
import cn.com.cloudstar.rightcloud.system.activiti.util.OrderType;

/**
 * @author Hong.Wu
 * @date: 7:49 2019/07/03
 */
public class ProcessHelper {

    /**
     * 支持的流程业务类型
     */
    public static List<Map<String, String>> BUSINESS = Lists.newArrayListWithCapacity(8);

    /**
     * 流程业务类型
     */
    public static Map<String, String> BUSINESS_MAP = Maps.newHashMap();

    static {
        BUSINESS.add(ImmutableMap.of("processCode", "service-apply", "businessName", "服务开通"));
        BUSINESS.add(ImmutableMap.of("processCode", "service-changegrade", "businessName", "服务变更"));
        BUSINESS.add(ImmutableMap.of("processCode", "service-renew", "businessName", "服务续订"));
        BUSINESS.add(ImmutableMap.of("processCode", "service-release", "businessName", "服务退订"));
        BUSINESS.add(ImmutableMap.of("processCode", "service-execScript", "businessName", "执行脚本"));
        BUSINESS.add(ImmutableMap.of("processCode", "ticket", "businessName", "提交工单"));

        for (Map<String, String> business : BUSINESS) {
            BUSINESS_MAP.put(business.get("processCode"), business.get("businessName"));
        }
    }

    public enum NodeTypeEnum {
        START(1, "开始"), GATEWAY(2, "角色网关"), USERTASK(3, "审批流程"), SERVICE(4, "服务"), END(5, "结束");
        private int type;
        private String value;

        NodeTypeEnum(int type, String value) {
            this.type = type;
            this.value = value;
        }

        public int getType() {
            return type;
        }

        public String getValue() {
            return value;
        }
    }


    public interface ExpressionsetImplementation {

        String DoOpen = "#{syncProcessService.doOpen(execution)}";
        String DoClose = "#{syncProcessService.doClose(execution)}";
        String AuditCandidateInlineMgts = "${syncProcessService.auditCandidateInlineMgts(execution, '%s')}";
        String ActTackCreatedCallback = "${syncProcessService.actTackCreatedCallback(task, '%s')}";
        String ActTackCompletedCallback = "${syncProcessService.actTackCompletedCallback(task, '%s')}";
    }


    public static String getProcessBusinessCode(String orderType) {
        String businessCode = null;
        if (OrderType.APPLY.equals(orderType)) {
            businessCode = ProcessConstants.SERVICE_APPLY;
        } else if (OrderType.CHANGEGRADE.equals(orderType)) {
            businessCode = ProcessConstants.SERVICE_CHANGEGRADE;
        } else if (OrderType.RELEASE.equals(orderType)) {
            businessCode = ProcessConstants.SERVICE_RELEASE;
        } else if (OrderType.RENEW.equals(orderType)) {
            businessCode = ProcessConstants.SERVICE_RENEW;
        } else if (OrderType.EXEC_SCRIPT.equals(orderType)) {
            businessCode = ProcessConstants.SERVICE_EXECSCRIPT;
        }
        return businessCode;
    }

    public static String getXmlStr(BpmnModel bpmnModel) {
        BpmnXMLConverter bpmnXMLConverter = new BpmnXMLConverter();
        byte[] convertToXML = bpmnXMLConverter.convertToXML(bpmnModel);
        try {
            return new String(convertToXML, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

}
