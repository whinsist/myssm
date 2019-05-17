/*
 * Copyright (c) 2018 Cloud-Star, Inc. All Rights Reserved..
 */

package cn.com.cloudstar.rightcloud.framework.common.constants;

/**
 * 业务异常代码定义
 * Created by swq on 3/3/2016.
 */
public interface BusinessMessageConstants {

    /**
     * 操作拦截头描述定义
     */
    interface ActionTrace {
        String COMMON_ARGS_IS_NULL = "common.action.trace.args.null";
        String ORDER_MANAGEMENT = "order.action.trace.title.management";
        String ORDER_CANCEL = "order.action.trace.title.cancel";
        String ORDER_SUBMIT = "order.action.trace.title.submit";
        String ORDER_PAY = "order.action.trace.title.pay";
    }

    interface CloudAppMessage {
        //云应用开通申请
        String CLOUD_APP_APPLY = "cloud.app.message.1001";
        //云应用开通成功
        String CLOUD_APP_OPEN_SUCCESS = "cloud.app.message.1002";
    }

    /**
     * 订单相关业务消息
     */
    interface OrderMessage {
        String CAN_NOT_FIND_ORDER_INFO = "order.business.msg.1001";
        String CAN_NOT_FIND_ORDER_DETAIL_INFO = "order.business.msg.1002";
        String ACCOUNT_BALANCE_ERROR = "order.business.msg.1003";
        String SPECIFICATION_FORMAT_ERROR = "order.business.msg.1004";
        String SERVICE_INSTANCE_CREATE_ERROR = "order.business.msg.1005";
        String VM_CREATE_ERROR = "order.business.msg.1006";
        String FLOW_START_ERROR = "order.business.msg.1007";
        String ERROR_KEY = "order.business.msg.1008";
        String ORDER_STATUS_ERROR = "order.business.msg.1009";
    }

    /**
     * 资费相关业务消息
     */
    interface BillingMessage {
        String CAN_NOT_FIND_BILLING_PLAN = "billing.business.msg.1001";
        String CAN_NOT_FIND_BILLING_PRICE_CONFIG = "billing.business.msg.1002";
        String CAN_NOT_FIND_BILLING_PRICE_DETAIL_CONFIG = "billing.business.msg.1003";
        String SERVICE_CODE_ERROR = "billing.business.msg.1004";
    }


    /**
     * 充值相关业务消息
     */
    interface PaymentMessage {
        String CAN_NOT_FIND_PAYMENT_RECORD = "payment.business.msg.1001";
    }

    interface ServiceInstance {
        //无法找到服务实例id信息
        String CAN_NOT_FIND_SERVICE_INSTANCE = "service.instance.msg.1001";
        //无法找到服务实例与资源的关联信息
        String CAN_NOT_FIND_SERVICE_INSTANCE_RES = "service.instance.msg.1002";
    }

    /**
     * 云主机操作
     */
    interface VmMessage {
        String VM_START = "info.vm.start";
        String VM_STOP = "info.vm.stop";
        String VM_RESTART = "info.vm.restart";
        String VM_DELETE = "info.vm.delete";
    }

    /**
     * 弹性公网IP
     */
    interface EipMessage {
        String EIP_DELETE = "info.eip.delete";
    }

    interface VdMessage {
        String VD_DELETE = "info.vd.delete";
        String VD_ATTACH = "info.vd.attach";
        String VD_DETACH = "info.vd.detach";
        String VD_EXPAND = "info.vd.expand";
        String VD_ATTACH_ERROR = "info.vd.attach.error";
        String VD_DETACH_ERROR = "info.vd.detach.error";
        String VD_EXPAND_ERROR = "info.vd.expand.error";
    }

    interface QuotaCheck {
        String QUOTA_CONFIG_ERROR = "quota.check.error.1001";
        String QUOTA_TYPE_ERROR = "quota.check.error.1002";
        String OVER_QUOTA = "quota.check.error.1003";
    }

    interface DeployMessage {
        String APP_VERSION_ERROR = "deploy.version.error";
        String APP_INSTANCE_ERROR = "deploy.app.instance.error";
    }

    interface ClusterMessage {
        /**
         * 集群master节点>1
         */
        String CLUSTER_MASTER_NODE_NOT_NULL_ERROR = "cluster.master.node.not.null.error";

        /**
         * 无法找到集群信息
         */
        String CLUSTER_NOT_FOUND_ERROR = "cluster.not.found.error";

        /**
         * 无法找到集群节点信息
         */
        String CLUSTER_NODE_NOT_FOUND_ERROR = "cluster.node.not.found.error";

        /**
         * 已加入集群无法删除主机
         */
        String NODE_CAN_NOT_DELETE = "node.not.can.be.delete";

        /**
         * 暂不支持的k8s kind
         */
        String NO_SUPPORT_KIND = "k8s.no.support.kind";

        /**
         * 集群无法连接
         */
        String CONNECTED_ERROR = "k8s.connected.error";

        /**
         * 无法找到应用服务
         */
        String APP_INST_SVC_NOT_FOUND = "app.inst.svc.not.found";

        /**
         * 容器无法找到
         */
        String CONTAINER_NOT_FOUND = "container.not.found";
    }


    interface CloudMessage {
        /**
         * 镜像删除错误
         */
        String CLOUD_IMAGE_DELETE_ERROR = "cloud.image.delete.error";
        /**
         * 脚本删除错误
         */
        String CLOUD_SCRIPT_DELETE_ERROR = "cloud.script.delete.error";
        /**
         * 云环境未找到错误
         */
        String CLOUD_ENV_NOT_FOUND = "cloud.env.not.found.error";
        /**
         * 脚本环境类型为找到错误
         */
        String CLOUD_SCRIPT_ENV_TYPE_NOT_FOUND = "cloud.script.env.type.not.fount.error";
        /**
         * 自服务默认配置无法找到审批错误
         */
        String SELF_APPROVAL_ERROR_NOT_FOUNT_CONFIG = "self.service.approval.error";
        /**
         * 参数不匹配错误
         */
        String CLOUD_PARAM_NOT_MATCH = "cloud.param.not.match";
        /**
         * 参数不匹配错误
         */
        String CLOUD_HOST_KEY_PAIR_NOT_FOUND = "cloud.host.keypair.not.found";
        /**
         * 监控数据获取失败
         */
        String CLOUD_MONITOR_INVOKE_ERROR = "cloud.monitor.invoke.error";
        /**
         * 无法找到云主机信息
         */
        String CLOUD_HOST_NOT_FOUND_ERROR = "cloud.host.not.found.error";
        /**
         * 无法找到策略信息
         * */
        String CLOUD_STRATEGY_NOT_FOUND_ERROR = "cloud.strategy.not.found.error";
    }
}
