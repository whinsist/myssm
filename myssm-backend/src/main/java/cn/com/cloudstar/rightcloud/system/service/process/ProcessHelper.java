package cn.com.cloudstar.rightcloud.system.service.process;

import org.apache.commons.io.FileUtils;

import java.io.File;

/**
 * @author Hong.Wu
 * @date: 7:49 2019/07/03
 */
public class ProcessHelper {



    interface ProcessStuts {
        /**
         * 0: 正常节点(开始、关闭)
         * 1: 服务节点(开通服务、拒绝关闭)
         * 2: 服务拒绝关闭节点
         * 3: 服务开通审批节点
         * 103: 发布持久化节点
         * 104: 已删除
         */
        int NormalNode = 0;
        int ServiceNodeNode = 1;
        int ServiceRejectCloseNode = 2;
        int ServiceOpenAuditNode = 3;
        int PublicDurableNode = 103;
        int DeletedNode = 104;
    }

    enum NodeTypeEnum {
        START(1, "开始"), GATEWAY(2, "角色网关"), USERTASK(3, "审批流程"), SERVICE(4, "服务"), END(5, "结束");
        private final int type;
        private final String value;
        NodeTypeEnum(int type, String value) {
            this.type = type;
            this.value = value;
        }
        public static NodeTypeEnum getEnumByType(int type) {
            for (NodeTypeEnum nodeType : NodeTypeEnum.values()) {
                if (nodeType.getType() == type) {
                    return nodeType;
                }
            }
            return null;
        }
        public int getType() {
            return type;
        }
        public String getValue() {
            return value;
        }
    }


    interface ExpressionsetImplementation {
         String DoOpen = "#{testProcessService.doOpen(execution)}";
         String DoClose = "#{testProcessService.doClose(execution)}";
         String AuditCandidateInlineMgts = "${testProcessService.auditCandidateInlineMgts(execution, '%s')}";
         String ActTackCreatedCallback = "${testProcessService.actTackCreatedCallback(task, '%s')}";
         String ActTackCompletedCallback = "${testProcessService.actTackCompletedCallback(task, '%s')}";
    }
    
    
    public static void main(String[] args) {
        try {
//            String x = FileUtils.readFileToString(new File("E:\\opt\\rightcloud\\files\\ops-1562661837889\\main.yaml"));
            String x = FileUtils.readFileToString(new File("E:/opt/rightcloud/files/ops-1562661837889/main.yaml"));
            System.out.println(x);
        }catch (Exception e) {

        }

    }   

}
