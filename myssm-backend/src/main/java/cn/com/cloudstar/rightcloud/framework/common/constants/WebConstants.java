
/*
 * Copyright (c) 2018 Cloud-Star, Inc. All Rights Reserved..
 */

package cn.com.cloudstar.rightcloud.framework.common.constants;

/**
 * 共通常量类
 *
 * @author swq
 */
public interface WebConstants {

    /**
     * 保存session中的用户信息key
     */
    String CURRENT_PORTAL_USER = "TS_CLOUD_CURRENT_PORTAL_USER";

    String CURRENT_PLATFORM_USER = "TS_CLOUD_CURRENT_PLATFORM_USER";

    String MONITOR_SEETINF_FAILED_JOB_KEY = "monitor:setting:failed:job";

    String K8S_CLUSTER_DEPLOYED_FAILED_JOB_KEY = "k8s:deployed:failed:job";

    String VERSION_HEAD = "HEAD";
    String VERSION_CHANGE = "Rev";
    String ERROR = "Error";
    String CLONE_DEFAULT = "v";
    String SCRIPT_END_SUCCESS_FLAG = "任务成功.";
    String SCRIPT_END_FAILED_FLAG = "任务失败.";

    /**
     * 参数规格定义
     */
    interface SpecificationProperty {

        String DATA = "data";
        String SPECIFICATIONS = "specifications";
        String SPECIFICATIONS_DESC = "specificationsDesc";
        String SERVICE_CODE = "serviceCode";
        String QUANTITY = "quantity";
        String BILLING_TYPE = "billingType";
        String DURATION = "duration";
        String ORDER_TYPE = "orderType";
        String IS_ACT_END = "isActEnd";
        String USER_ID = "userId";
    }

    interface Protocol {
        String HTTP = "http";
        String HTTPS = "https";
        String UDP = "udp";
        String TCP = "tcp";
    }

    /**
     * 快照状态
     */
    interface SnapshotStatus{
        /**
         * 创建中
         */
        String CREATING = "01";
        /**
         * 创建成功
         */
        String SUCCESS = "02";
        /**
         * 创建失败
         */
        String FAILURE = "03";

        /***
         * 删除中
         */
        String DELETING = "04";

        /***
         * 回滚中
         * */
        String RECOVERYING = "05";
    }

    /**
     * 主企业、项目的标识
     */
    interface MasterKey {
        char MASTER_KEY = 'Y';
    }

    interface LoadBalance {
        String STOPPED = "stopped";
        String VSERVER = "vServer";
    }
    /**
     * 工单状态
     */
    interface WorkTicketStatus {
        /** 未分配*/
        String NOT_SHARE = "01";
        /** 处理中*/
        String DEAL = "02";
        /** 已完成*/
        String FINISH = "03";
    }

    /**
     * 工单回复类型状态
     */
    interface WorkTicketFeedBackType {
        /** 01*/
        String SALES = "01";
        /** 02*/
        String USER = "02";
    }

    interface DealStatus {
        /** 基础*/
        String BASE = "01";
        /** 处理人*/
        String DEAL = "02";
        /** 工单人*/
        String USER = "03";
    }

    /**
     * 规格项计费状态
     */
    interface SpecBillStatus {

        int NO_BILLED = 0;
        int BILLED = 1;
    }

    /**
     * 功能菜单所属系统
     */
    interface ModuleCategory {

        /**
         * 系统：0
         */
        Integer SYSTEM = 0;

        /**
         * 自服务：1
         */
        Integer PROTAL = 1;

        /**
         * 云管理：2
         */
        Integer DASHBOARD = 2;

        /**
         * 云分析：3
         */
        Integer ANALYSIS = 3;
    }

    /**
     * 功能菜单类型
     */
    interface ModuleType {

        /**
         * 菜单:0
         */
        Integer MEMU = 0;

        /**
         * 功能:1
         */
        Integer FUNCTION = 1;
    }

    interface GiftCardBatchStatus {

        //0 - 未生成
        Integer GIFT_CARD_NOT_GENERATED = 0;
        //1 - 已生成
        Integer GIFT_CARD_GENERATED = 1;
        //2 - 已作废
        Integer BATCH_INVALID = 2;
    }

    interface GiftCardStatus {

        //0未激活
        Integer NOT_ACTIVATED = 0;
        Integer ACTIVATED = 1;
    }

    interface CloudEnvKey {
        String BILLING_STRATEGY = "billingStrategy";
    }

    /**
     * 固定角色主键
     */
    interface RoleSid {

        /**
         * 租户用户
         */
        Long T_USER = 104L;

        /**
         * 租户管理员
         */
        Long T_MANAGER = 103L;

        /**
         * 运营管理员
         */
        Long O_MANAGER = 102L;

        /**
         * 企业管理员SID
         */
        Long ENTERPRICE_ADMIN = 101L;

        /**
         * 运维管理员
         */
//        Long OM_MANAGER = 101L;
        Long OM_MANAGERKLB = 204L;

        /**
         * 超级管理员
         */
        Long AD_MANAGER = 100L;

        Long MGT_OBJ_MANAGER = 205L;
    }

    /**
     * 服务编码
     */
    interface ServiceCode {

        /**
         * 云主机
         */
        String CS = "cs";

        /**
         * 对象存储
         */
        String OSS = "oss";

        /**
         * 块存储
         */
        String CBS = "cbs";

        /**
         * 弹性IP
         */
        String EIP = "eip";

        /**
         * 私有网络
         */
        String VPC = "vpc";

        /**
         * CND
         */
        String CND = "cdn";

        /**
         * 负责均衡
         */
        String LOADBALANCE = "loadbalance";
    }

    /**
     * 服务SID
     */
    interface ServiceSid {

        /**
         * VM服务
         */
        Long SERVICE_VM = 100L;

        /**
         * 对象存储服务
         */
        Long SERVICE_OBJ_STORAGE = 104L;

        /**
         * 块存储服务
         */
        Long SERVICE_STORAGE = 105L;

        /**
         * 弹性IP服务
         */
        Long FLOATING_IP = 106L;

        /**
         * CDN自服务
         */
        Long SERVICE_CDN = 107L;

        /**
         * EXCHANGE服务
         */
        Long SERVICE_EX = 114L;

        /**
         * SHAREPOINT服务
         */
        Long SERVICE_SP = 115L;

        /**
         * 自动化部署服务
         */
        Long SERVICE_DEPLOYMENT = 120L;

        /**
         * 物理机服务
         */
        Long SERVICE_PM = 121L;

        /**
         * 项目变更经理
         */
        Long CHANGE_MANAGER_SERVICE = -100L;

        /**
         * 虚机变更项目
         */
        Long CHANGE_MGTOBJ_SERVICE = -200L;

        /**
         * 企业网盘
         */
        Long AWD = 1004L;

    }

    /**
     * 服务类型
     */
    interface ServiceType {

        /**
         * VM服务
         */
        String VM = "VM";

        /**
         * SHAREPOINT服务
         */
        String SHAREPOINT = "SHAREPOINT";

        /**
         * EXCHANGE服务
         */
        String EXCHANGE = "EXCHANGE";

        /**
         * STORAGE服务
         */
        String STORAGE = "STORAGE";

        /**
         * OBJECT STORAGE服务
         */
        String OBJECT_STORAGE = "OBJECT_STORAGE";

        /**
         * DEPLOYMENT服务
         */
        String DEPLOYMENT = "DEPLOYMENT";

        /**
         * 物理机服务
         */
        String PM = "PM";

        /**
         * 弹性IP服务
         */
        String FLOATING_IP = "FLOATING_IP";

        /**
         * 弹性IP服务
         */
        String CDN = "CDN";

        /**
         * 其他
         */
        String OTHER = "OTHER";
    }

    /**
     * 消息代码
     */
    interface MsgCd {
        /* 提示消息 */
        /**
         * info.insert.success=创建成功。
         */
        String INFO_INSERT_SUCCESS = "info.insert.success";

        /**
         * info.register.success=注册成功。
         */
        String INFO_REGISTER_SUCCESS = "info.register.success";

        /**
         * info.update.success=更新成功。
         */
        String INFO_UPDATE_SUCCESS = "info.update.success";

        /**
         * info.publish.success=发布成功。
         */
        String INFO_PUBLISH_SUCCESS = "info.publish.success";

        /**
         * info.delete.success=删除成功。
         */
        String INFO_DELETE_SUCCESS = "info.delete.success";

        /**
         * info.relation.success=关联操作成功。
         */
        String INFO_RELATION_SUCCESS = "info.relation.success";

        /**
         * info.approve.success=审核成功。
         */
        String INFO_APPROVE_SUCCESS = "info.approve.success";

        /**
         * info.message.success=发送成功。
         */
        String INFO_MESSAGE_SUCCESS = "info.message.success";

        /**
         * info.inventory.success=盘点成功。
         */
        String INFO_INVENTORY_SUCCESS = "info.inventory.success";

        /**
         * info.in.success=入库成功。
         */
        String INFO_IN_SUCCESS = "info.in.success";

        /**
         * info.relate.success=关联成功。
         */
        String INFO_RELATE_SUCCESS = "info.relate.success";

        /**
         * info.out.success=出库成功。
         */
        String INFO_OUT_SUCCESS = "info.out.success";

        /**
         * info.copy.success=复制成功。
         */
        String INFO_COPY_SUCCESS = "info.copy.success";

        /**
         * vm.start.success=启动成功。
         */
        String VM_START_SUCCESS = "vm.start.success";

        /**
         * vm.stop.success=关机成功。
         */
        String VM_STOP_SUCCESS = "vm.stop.success";

        /**
         * vm.restart.success=重启成功。
         */
        String VM_RESTART_SUCCESS = "vm.restart.success";

        /**
         * vm.reconfig.success=调整成功。
         */
        String VM_RECONFIG_SUCCESS = "vm.start.reconfig";

        /**
         * vm.migrate.success=迁移成功。
         */
        String VM_MIGRATE_SUCCESS = "vm.migrate.success";

        /**
         * vm.destory.success=退订成功。
         */
        String VM_DESTORY_SUCCESS = "vm.destory.success";

        /**
         * vm.managed.success=纳管成功。
         */
        String VM_MANAGED_SUCCESS = "vm.managed.success";

        /**
         * vm.rename.success=虚拟机修改名称成功。
         */
        String VM_RENAME_SUCCESS = "vm.rename.success";

        /**
         * task.issued.success=任务下发成功，请到日志中心查看详情。<br>待任务完成后请手动进行刷新操作。
         */
        String TASK_ISSUED_SUCCESS = "task.issued.success";

        /**
         * image.disabled.success=停用成功。
         */
        String IMAGE_DISABLED_SUCCESS = "image.disabled.success";

        /**
         * info.ticket.success=分配工单成功
         */
        String INFO_TICKET_SUCCESS = "info.ticket.success";

        /**
         * info.vm.res.check=资源检查成功。
         */
        String INFO_VM_RES_CHECK = "info.vm.res.check";

        /**
         * info.ticket.execute=重新执行工单成功。
         */
        String INFO_TICKET_EXECUTE = "info.ticket.execute";

        /**
         * info.ticket.execute=操作成功。
         */
        String INFO_OPERATE_SUCCESS = "info.operate.success";

        String SYSTEM_MESSAGE_COUPON = "system.message.coupon";

        String INFO_MOBILE_GET_SUCCESS = "info.mobile.get.success";

        /**
         * error.edit.failure=编辑失败。
         */
        String ERROR_EDIT_FAILURE = "error.edit.failure";
        /**
         * info.edit.success=编辑成功。
         */
        String INFO_EDIT_SUCCESS = "info.edit.success";

        /* 错误消息 */
        /**
         * error.system.exception
         **/
        String ERROR_SYS_EXCEPTION = "error.system.exception";

        /* 警告消息 */
        /**
         * warning_service_repeat=对不起，该服务不能重复订购。
         */
        String WARNING_SERVICE_REPEAT = "warning_service_repeat";

        /**
         * warning.query.failure=对不起，数据为空。
         */
        String WARNING_QUERY_FAILURE = "warning.query.failure";

        /* 错误消息 */
        /**
         * error.insert.failure=创建失败。
         */
        String ERROR_INSERT_FAILURE = "error.insert.failure";
        /**
         * error.insert.failure.param={0}创建失败。
         */
        String ERROR_INSERT_FAILURE_PARAM = "error.insert.failure.param";
        /**
         * error.message.failure=发送失败。
         */
        String ERROR_MESSAGE_FAILURE = "error.message.failure";

        /**
         * error.query.failure=获取信息失败，数据已被更新或删除。
         */
        String ERROR_QUERY_FAILURE = "error.query.failure";

        /**
         * error.register.failure=注册失败，数据或已存在，请重试。
         */
        String ERROR_REGISTER_FAILURE = "error.register.failure";

        /**
         * error.update.failure=更新失败，数据已被更新或删除。
         */
        String ERROR_UPDATE_FAILURE = "error.update.failure";

        /**
         * error.publish.success=发布失败。
         */
        String ERROR_PUBLISH_FAILURE = "error.publish.failure";

        /**
         * error.delete.failure=删除失败，数据已被更新或删除。
         */
        String ERROR_DELETE_FAILURE = "error.delete.failure";

        /**
         * error.approve.failure=审核失败。
         */
        String ERROR_APPROVE_FAILURE = "error.approve.failure";

        /**
         * error.sendmail.failure=邮件发送失败。
         */
        String ERROR_SENDMAIL_FAILURE = "error.sendmail.failure";

        /**
         * error.inventory.failure=盘点失败。
         */
        String ERROR_INVENTORY_FAILURE = "error.inventory.failure";

        /**
         * error.in.failure=入库失败。
         */
        String ERROR_IN_FAILURE = "error.in.failure";

        /**
         * error.relate.failure=关联失败。
         */
        String ERROR_RELATE_FAILURE = "error.relate.failure";

        /**
         * error.out.failure=出库失败。
         */
        String ERROR_OUT_FAILURE = "error.out.failure";

        /**
         * error.copy.failure=复制失败。
         */
        String ERROR_COPY_FAILURE = "error.copy.failure";

        /**
         * error.relation.failure=关联操作失败，数据已被更新或删除。
         */
        String ERROR_RELATION_FAILURE = "error.relation.failure";

        /**
         * error.data.exist={0}已经存在，请重新填写。
         */
        String ERROR_DATA_EXIST = "error.data.exist";

        /**
         * error.data.relation={0}，不能进行删除。
         */
        String ERROR_DATA_RELATION = "error.data.relation";

        /**
         * error.data.relation.delete=存在关联关系，不能进行删除。
         */
        String ERROR_DATA_RELATION_DELETE = "error.data.relation.delete";
        /**
         * error.data.relation.delete.param=部分数据无法删除，请检查{0}是否存在关联关系。
         */
        String ERROR_DATA_RELATION_DELETE_PARAM = "error.data.relation.delete.param";
        /**
         * error.data.relation.update=不能进行修改。
         */
        String ERROR_DATA_RELATION_UPDATE = "error.data.relation.update";

        /**
         * error.data.relation={0}，数据已被更新或删除。
         */
        String ERROR_DATA_FAILURE = "error.data.failure";

        /**
         * error.file.oversize=您选择的文件过大，请重新选择。
         */
        String ERROR_FILE_OVERSIZE = "error.file.oversize";

        /**
         * error.plan.published=该名称预案已经发布。
         */
        String ERROR_PLAN_PUBLISHED = "error.plan.published";

        /**
         * error.plan.published=该预案已经停用。
         */
        String ERROR_PLAN_DISABLED = "error.plan.disabled";

        /**
         * error.assess.maxnum={0}不能超过十个。
         */
        String ERROR_ASSESS_MAXNUM = "error.assess.maxnum";

        /**
         * 订单取消失败。
         */
        String ERROR_CANCEL_ORDER = "error.cancel.order";

        /**
         * error.vm.start=启动失败。
         */
        String ERROR_VM_START = "error.vm.start";

        /**
         * error.vm.stop=关机失败。
         */
        String ERROR_VM_STOP = "error.vm.stop";

        /**
         * error.vm.restart=重启失败。
         */
        String ERROR_VM_RESTART = "error.vm.restart";

        /**
         * error.vm.reconfig=调整失败。
         */
        String ERROR_VM_RECONFIG = "error.vm.reconfig";

        /**
         * error.vm.migrate=迁移失败。
         */
        String ERROR_VM_MIGRATE = "error.vm.migrate";

        /**
         * error.vm.managed=纳管失败。
         */
        String ERROR_VM_MANAGED = "error.vm.managed";

        /**
         * error.vm.scan=扫描失败。
         */
        String ERROR_VM_SCAN = "error.vm.scan";

        /**
         * error.vm.destory=退订失败。
         */
        String ERROR_VM_DESTORY = "error.vm.destory";

        /**
         * error.vm.rename=虚拟机修改名称失败。
         */
        String ERROR_VM_RENAME = "error.vm.rename";

        /**
         * error.task.issued=任务发送失败。
         */
        String ERROR_TASK_ISSUED = "error.task.issued";
        /**
         * error.image.disabled=停用失败。
         */
        String ERROR_IMAGE_DISABLED = "error.image.disabled";

        /**
         * error.recover.busy={0}，请等待。
         */
        String ERROR_RECOVER_BUSY = "error.recover.busy";

        /* 警告消息 */
        /**
         * warning_ippool_repeat=对不起，该IP不能重复添加到资源池。
         */
        String WARNING_IPPOOL_REPEAT = "warning_ippool_repeat";

        /* 警告消息 */
        /**
         * warning_ip_repeat=对不起，该IP已经添加到资源。
         */
        String WARNING_IP_REPEAT = "warning_ip_repeat";

        /**
         * error.ticket.allocate=分配工单失败。
         */
        String ERROR_TICKET_ALLOCATE = "error.ticket.allocate";

        /**
         * error.vm.res.check=资源不足。
         */
        String ERROR_VM_RES_CHECK = "error.vm.res.check";

        /**
         * error.biz.user.check=没有用户关联到所选业务名称。
         */
        String ERROR_BIZ_USER_CHECK = "error.biz.user.check";

        /**
         * error.ticket.execute=重新执行工单失败。
         */
        String ERROR_TICKET_EXECUTE = "error.ticket.execute";

        /**
         * error.operate.failure=操作失败。
         */
        String ERROR_OPERATE_FAILURE = "error.operate.failure";

        /**
         * warning.mgtobjres.failure=该租户尚未关联资源。
         */
        String WARNING_MGTOBJRES_FAILURE = "warning.mgtobjres.failure";

        /**
         * error.operate.failure=获取虚拟化环境失败。
         */
        String ERROR_VE_FAILURE = "error.ve.failure";

        /**
         * INFO_GIFT_CARD_GEN_SUCCESS
         */
        String INFO_GIFT_CARD_GEN_SUCCESS = "info_gift_card_gen_success";
        String ERROR_PARAMETER_WRONG = "error_parameeter_wrong";
        String LOGIN_SUCCESS = "user.login.success";
        String LOGIN_FAILED = "user.login.failed";
        String LOGOUT_SUCCESS = "user.logout.success";

        String ERROR_TRIAL_HOST_CREATE = "error.vm.trial.failure";
        String ERROR_VM_TRIAL_RETRY_FAILURE = "error.vm.trial.retry.failure";
        String ERROR_TRIAL_HOST_ENV_ABSENT = "error.vm.trial.env.absent";

        /**
         * ldap info
         */
        String LDAP_CONNECT_SUCCESS = "ldap.connect.success";
        String LDAP_CONNECT_FAILURE = "ldap.connect.failure";
    }

    /**
     * 数据字典类型代码
     */
    interface CodeCategroy {

        /**
         * 服务状态
         */
        String SERVICE_STATUS = "SERVICE_STATUS";

        /**
         * 订单状态
         */
        String ORDER_STATUS = "ORDER_STATUS";

        /**
         * 订单类型
         */
        String ORDER_TYPE = "ORDER_TYPE";

        /**
         * 审核状态
         */
        String APPROVE_STATUS = "APPROVE_STATUS";

        /**
         * 审核流程类型
         */
        String PROCESS_TYPE = "PROCESS_TYPE";

        /**
         * 服务实例状态
         */
        String SERVICE_INASTANCE_STATUS = "SERVICE_INASTANCE_STATUS";

        /**
         * 是否可用
         */
        String AVAILABLITY = "AVAILABLITY";

        /**
         * 运行状态
         */
        String PERFORMANCE = "PERFORMANCE";

        /**
         * 租户类型
         */
        String TENANT_TYPE = "TENANT_TYPE";

        /**
         * 计费状态
         */
        String BILL_STATUS = "BILL_STATUS";

        /**
         * 租户管理员状态
         */
        String TENANT_ADMIN = "TENANT_ADMIN";

        /**
         * 租户用户状态
         */
        String TENANT_USER = "TENANT_USER";

        /**
         * 运营管理员状态
         */
        String OPERATION_ADMIN = "OPERATION_ADMIN";

        /**
         * 运维管理员状态
         */
        String MAINTENANCE_ADMIN = "MAINTENANCE_ADMIN";

        /**
         * 服务模板状态
         */
        String SERVICE_TEMPLATE_STATUS = "SERVICE_TEMPLATE_STATUS";

        /**
         * 资源池状态
         */
        String RESOURCE_POOL_STATUS = "RESOURCE_POOL_STATUS";

        /**
         * 资源实例状态
         */
        String RESOURCE_INSTANCE_STATUS = "RESOURCE_INSTANCE_STATUS";

        /**
         * 云主机资源实例状态
         */
        String RES_HOST_INSTANCE_STATUS = "RES_HOST_INSTANCE_STATUS";

        /**
         * 块存储资源实例状态
         */
        String RES_STORAGE_INSTANCE_STATUS = "RES_STORAGE_INSTANCE_STATUS";

        /**
         * IP资源实例状态
         */
        String RES_IP_INSTANCE_STATUS = "RES_IP_INSTANCE_STATUS";

        /**
         * IP资源状态
         */
        String IP_RESOURCE_STATUS = "IP_RESOURCE_STATUS";

        /**
         * IP池类型
         */
        String IP_POOL_TYPE = "IP_POOL_TYPE";

        /** */
        String OPERATION_TYPE = "OPERATION_TYPE";

        /** */
        String B_DETAIL = "B_DETAIL";

        /**
         * 资源状态
         */
        String RESOURCE_STATUS = "RESOURCE_STATUS";

        /**
         * 虚机平台类型
         */
        String VIRTUAL_PLATFORM_TYPE = "VIRTUAL_PLATFORM_TYPE";

        /**
         * 分配策略
         */
        String ALLOCATION_POLICY = "ALLOCATION_POLICY";

        /**
         * 性能保障等级
         */
        String PERF_LEVEL = "PERF_LEVEL";

        /**
         * VLAN类型
         */
        String VLAN_TYPE = "VLAN_TYPE";

        /**
         * 存储类型
         */
        String STORAGE_TYPE = "STORAGE_TYPE";

        /**
         * 主机类型
         */
        String HOST_TYPE = "HOST_TYPE";

        /**
         * 管理状态
         */
        String MANAGEMENT_STATUS = "MANAGEMENT_STATUS";

        /**
         * 使用状态
         */
        String USAGE_STATUS = "USAGE_STATUS";

        /**
         * CPU类型
         */
        String CPU_TYPE = "CPU_TYPE";

        /**
         * IP类型
         */
        String IP_TYPE = "IP_TYPE";

        /**
         * IP分类
         */
        String IP_CATEGORY = "IP_CATEGORY";

        /**
         * 租户状态
         */
        String TENANT_STATUS = "TENANT_STATUS";

        /**
         * 企业类型
         */
        String BUSINESS_TYPE = "BUSINESS_TYPE";

        /**
         * 用户类型
         */
        String USER_TYPE = "USER_TYPE";

        /**
         * 用户状态
         */
        String USER_STATUS = "USER_STATUS";

        /**
         * 操作系统类型
         */
        String OS_TYPE = "OS_TYPE";

        /**
         * 操作系统版本
         */
        String OS_VERSION = "OS_VERSION";

        /**
         * 计费计划类型
         */
        String BILLING_PLAN_TYPE = "BILLING_PLAN_TYPE";

        /**
         * 计费计划状态
         */
        String BILLING_PLAN_STATUS = "BILLING_PLAN_STATUS";

        /**
         * 计费类型(包年包月)
         */
        String BILLING_TYPE_YM = "BILLING_TYPE_YM";

        /**
         * 告警级别
         */
        String ALARM_LEVEL = "ALARM_LEVEL";

        /**
         * 告警类型
         */
        String ALARM_TYPE = "ALARM_TYPE";

        /**
         * 告警指标
         */
        String ALARM_KPI = "ALARM_KPI";

        /**
         * 判断方法
         */
        String CHECK_OPTR = "CHECK_OPTR";

        /**
         * 判断方法
         */
        String BILLING_CHARGE_TYPE = "BILLING_CHARGE_TYPE";

        /**
         * 部署类型
         */
        String DEPLOYMENT_TYPE = "DEPLOYMENT_TYPE";

        /**
         * 部署软件类型
         */
        String SOFTWARE_TYPE = "SOFTWARE_TYPE";

        /**
         * 部署软件版本
         */
        String SOFTWARE_VERSION = "SOFTWARE_VERSION";

        /**
         * 硬盘类型
         */
        String HARD_DISK_TYPE = "HARD_DISK_TYPE";

        /**
         * 存储类别
         */
        String STORAGE_CATEGORY = "STORAGE_CATEGORY";

        /**
         * 存储用途
         */
        String STORAGE_PURPOSE = "STORAGE_PURPOSE";

        /**
         * 告警状态
         */
        String ALARM_STATUS = "ALARM_STATUS";

        /**
         * 物理主机操作系统
         */
        String HOST_OS_TYPE = "HOST_OS_TYPE";

        /**
         * 工单问题分类
         */
        String QUESTION_TYPE = "QUESTION_TYPE";

        /**
         * 工单问题级别
         */
        String QUESTION_LEVEL = "QUESTION_LEVEL";

        /**
         * 工单状态
         */
        String TICKET_STATUS = "TICKET_STATUS";

        /**
         * 租户配额
         */
        String TENANT_QUOTA = "TENANT_QUOTA";

        /**
         * 用户配额
         */
        String USER_QUOTA = "USER_QUOTA";

        /**
         * 部署任务类别
         */
        String DEPLOY_TASK_TYPE = "DEPLOY_TASK_TYPE";

        /**
         * 云环境名称
         */
        String CLOUD_ENV_TYPE = "CLOUD_ENV_TYPE";
        /**
         * 主机状态名称
         */
        String CLOUD_HOST_STATUS = "CLOUD_HOST_STATUS";

        /**
         * 宿主机状态名称
         */
        String HOST_STATUS = "HOST_STATUS";

        /**
         * 存储状态名称
         */
        String STORAGE_STATUS = "STORAGE_STATUS";

        /**
         * 镜像类型
         */
        String CLOUD_OS_PLATFORM = "CLOUD_OS_PLATFORM";
        /**
         * 主机管理状态
         */
        String CLOUD_HOST_MANAGE_STATUS = "CLOUD_HOST_MANAGE_STATUS";
        /**
         * 应用状态
         */
        String APP_INSTANCE_STATUS = "APP_INSTANCE_STATUS";
        /**
         * 部署主机类型
         */
        String SERVER_TYPE = "SERVER_TYPE";
        /**
         * 接口访问类型
         */
        String ACTION_LOG = "ACTION_LOG";

        /**
         * 阿里云磁盘种类
         */
        String ALIYUN_DISK_CATEGORY = "ALIYUN_DISK_CATEGORY";

        /**
         * 自服务种类
         */
        String SELF_SERVICE_CATEGORY = "SELF_SERVICE_CATEGORY";

        /**
         * 服务状态
         */
        String SELF_SERVICE_STATUS = "SELF_SERVICE_STATUS";

        /**
         * 腾讯云磁盘种类
         */
        String QCLOUD_DISK_CATEGORY = "QCLOUD_DISK_CATEGORY";

        /**
         * TOPO分类
         */
        String RES_TOPOLOGY_TYPE = "RES_TOPOLOGY_TYPE";
        /**
         * 云环境各类操作
         */
        String CLOUD_ENV_OP_TYPE = "CLOUD_ENV_OP_TYPE";
    }

    /**
     * 计费策略
     */
    interface BillingStrategy {
        String CHARGED_BY_PLATFORM = "chargedByPlatform";
        String CHARGED_BY_CUSTOM = "chargedByCustom";
    }

    /**
     * 订单状态
     */
    interface OrderType {

        /**
         * 新购
         */
        String NEW_BUY = "01";
    }

    /**
     * 订单申请等级
     */
    interface OrderProcessType {

        /**
         *  项目用户申请
         */
        String PROJECT_USER = "01";

        /**
         *  项目管理员申请
         */
        String PROJECT_MGT = "02";

        /**
         *  企业管理员申请
         */
        String COMPANY_MGT = "03";
    }

    /**
     * 订单审批状态代码
     */
    interface OrderStatusCd {

        /**
         * 01：已创建
         */
        String DRAFT = "01";

        /**
         * 02：审核中
         */
        String APPROVING = "02";

        /**
         * 03：已审批
         */
        String APPROVED = "03";

        /**
         * 04：开通中
         */
        String OPENING = "04";

        /**
         * 05：已开通
         */
        String OPENED = "05";

        /**
         * 99：已取消
         */
        String CANCEL = "99";

        // ChengQi start
        /**
         * 98：已拒绝
         */
        String REJECTED = "98";
        // ChengQi end

        /**
         * 97:未支付
         */
        String NO_PAY = "97";

        /**
         * 97:以支付
         */
        String PAYED = "96";
    }

    /**
     * 资源池类型代码
     */
    interface ResPoolType {

        /**
         * 主机资源池
         */
        String RES_POOL_VM = "RES-POOL-VM";

        /**
         * IP资源池
         */
        String RES_POOL_IP = "RES-POOL-IP";

        /**
         * VLAN资源池
         */
        String RES_POOL_VLAN = "RES-POOL-VLAN";

        /**
         * 存储资源池
         */
        String RES_POOL_STORAGE = "RES-POOL-STORAGE";

        /**
         * EXCHANGE资源池
         */
        String RES_POOL_EXCHANGE = "RES-POOL-EXCHANGE";

        /**
         * SHAREPOINT资源池
         */
        String RES_POOL_SHAREPOINT = "RES-POOL-SHAREPOINT";

        /**
         * 物理机资源池
         */
        String RES_POOL_PM = "RES-POOL-PM";

    }

    /**
     * 资源类型代码
     */
    interface ResourceType {

        /**
         * 虚拟机资源
         */
        String RES_VM = "RES-VM";

        /**
         * 虚拟磁盘资源
         */
        String RES_VD = "RES-VD";

        /**
         * 主机资源
         */
        String RES_HOST = "RES-HOST";

        /**
         * 路由器资源
         */
        String RES_ROUTER = "RES-ROUTER";


        /**
         * 负载均衡资源
         */
        String RES_SLB = "RES-SLB";

        /**
         * IP资源
         */
        String RES_IP = "RES-IP";

        /**
         * VLAN资源
         */
        String RES_VLAN = "RES-VLAN";

        /**
         * 存储资源
         */
        String RES_STORAGE = "RES-STORAGE";

        /**
         * 存储类型资源
         */
        String RES_STORAGE_TYPE = "RES-STORAGE-TYPE";

        /**
         * 实例类型资源
         */
        String RES_VOLUME_TYPE = "RES_VOLUME_TYPE";

        /**
         * EX资源
         */
        String RES_EXCHANGE = "RES-EXCHANGE";

        /**
         * SP资源
         */
        String RES_SHAREPOINT = "RES-SHAREPOINT";

        /**
         * PM资源
         */
        String RES_PM = "RES-PM";

        /**
         * 网络资源
         */
        String RES_NETWORK = "RES-NETWORK";

        /**
         * CDN
         */
        String RES_CDN = "RES-CDN";

        /**
         * 软件资源
         */
        String RES_SOFTWARE = "RES-SOFTWARE";

        /**
         * 软件资源
         */
        String RES_FLOATING_IP = "RES-FLOATING-IP";

        /**
         * 资源目录
         */
        String RES_FOLDER = "RES-FOLDER";

        /**
         * RDS
         */
        String RES_RDS = "RES-RDS";


    }

    /**
     * 资源实例类型代码
     */
    interface ResourceInstanceType {

        /**
         * 主机实例资源
         */
        String RES_INST_VM = "RES-INST-VM";

        /**
         * IP实例资源
         */
        String RES_INST_IP = "RES-INST-IP";

        /**
         * VLAN实例资源
         */
        String RES_INST_VLAN = "RES-INST-VLAN";

        /**
         * 存储实例资源
         */
        String RES_INST_STORAGE = "RES-INST-STORAGE";

        /**
         * EXCHANGE实例资源
         */
        String RES_INST_EXCHANGE = "RES-INST-EXCHANGE";

        /**
         * SHAREPOINT实例资源
         */
        String RES_INST_SHAREPOINT = "RES-INST-SHAREPOINT";

        /**
         * 自动化部署实例资源
         */
        String RES_INST_DEPLOYMENT = "RES-INST-DEPLOYMENT";

        /**
         * 物理机实例资源
         */
        String RES_INST_PM = "RES-INST-PM";

    }

    /**
     * 服务配置Email模板代码
     */
    interface ServiceConfigEmail {

        /**
         * 开通成功通知用户Email
         */
        String SEND_TO_OWNER = "SEND_TO_OWNER";

        /**
         * 开通成功通知租户Email
         */
        String SEND_TO_TENANT = "SEND_TO_TENANT";

        /**
         * 开通成功通知运维管理员Email
         */
        String SEND_TO_OM_MANAGER = "SEND_TO_OM_MANAGER";

        /**
         * 退订云主机服务通知用户Email
         */
        String UNSUBSCRIBE_VM_SEND_TO_OWNER = "UNSUBSCRIBE_VM_SEND_TO_OWNER";

        /**
         * 退订云主机服务通知租户Email
         */
        String UNSUBSCRIBE_VM_SEND_TO_TENANT = "UNSUBSCRIBE_VM_SEND_TO_TENANT";
    }

    /**
     * 服务配置Activiti流程
     */
    interface ServiceConfigActiviti {

        /**
         * 01 VM服务审批流程
         */
        String VM_SERVICE_APPROVE_PROCESS1 = "VM_SERVICE_APPROVE_PROCESS";

        /**
         * 02 VM服务自动开通流程
         */
        String VM_SERVICE_START_PROCESS1 = "VM_SERVICE_START_PROCESS";

        /**
         * 03 VM服务退订流程
         */
        String VM_CANCEL_PROCESS = "VM_CANCEL_PROCESS";

        /**
         * 04 EXCHANGE订单审批流程
         */
        String EXCHANGE_SERVICE_APPROVE_PROCESS = "EXCHANGE_SERVICE_APPROVE_PROCESS";

        /**
         * 05 EXCHANGE服务自动开通流程
         */
        String EXCHANGE_SERVICE_START_PROCESS = "EXCHANGE_SERVICE_START_PROCESS";

        /**
         * 06 EXCHANGE服务退订流程
         */
        String EXCHANGE_CANCEL_PROCESS = "EXCHANGE_CANCEL_PROCESS";

        /**
         * 07 SHAREPOINT订单审批流程
         */
        String SHAREPOINT_SERVICE_APPROVE_PROCESS = "SHAREPOINT_SERVICE_APPROVE_PROCESS";

        /**
         * 08 SHAREPOINT服务自动开通流程
         */
        String SHAREPOINT_SERVICE_START_PROCESS = "SHAREPOINT_SERVICE_START_PROCESS";

        /**
         * 09 SHAREPOINT服务退订流程
         */
        String SHAREPOINT_CANCEL_PROCESS = "SHAREPOINT_CANCEL_PROCESS";

        /**
         * 01 CLOUD服务审批流程
         */
        String CLOUD_SERVICE_APPROVE_PROCESS = "CLOUD_SERVICE_APPROVE_PROCESS";

        /**
         * 02 CLOUD服务自动开通流程
         */
        String CLOUD_SERVICE_START_PROCESS = "CLOUD_SERVICE_START_PROCESS";

        /**
         * 03 VM服务退订流程
         */
        String CLOUD_SERVICE_CANCEL_PROCESS = "CLOUD_SERVICE_CANCEL_PROCESS";

        /**
         * 11 闲置资源回收流程
         */
        String FREE_RESOURCE_REDUCE_PROCESS = "FREE_RESOURCE_REDUCE_PROCESS";

        /**
         * 12  实例变更流程
         */
        String INSTANCE_ADJUST_PROCESS = "INSTANCE_ADJUST_PROCESS";

        /**
         * 13 项目经理变更流程
         */
        String CHANGE_MANAGER_PROCESS = "CHANGE_MANAGER_PROCESS";

        /**
         * 14  虚机所属项目变更流程
         */
        String CHANGE_VM_MGT_OBJ_PROCESS = "CHANGE_VM_MGT_OBJ_PROCESS";
    }

    /**
     * 服务实例状态代码
     */
    interface ServiceInstanceStatus {

        /**
         * 0 待开通
         */
        String PENDING = "00";

        /**
         * 1 开通中
         */
        String OPENING = "01";

        /**
         * 2 无效实例
         */
        String INVALID = "02";

        /**
         * 3 已开通
         */
        String OPENED = "03";

        /**
         * 4 初始化异常
         */
        String EXCEPTION = "04";

        /**
         * 5 已禁用
         */
        String DISABLED = "05";

        /**
         * 6 退订中
         */
        String CANCELING = "06";

        /**
         * 7 变更中
         */
        String CHANGEING = "07";

        /**
         * 8 已拒绝
         */
        String REFUSED = "08";


        /**
         * 9 已退订
         */
        String CANCELED = "99";
    }

    /**
     * 资源状态代码
     */
    interface ResourceStatus {

        /**
         * 01 可用
         */
        String AVAILABLE = "01";

        /**
         * 02 预占中
         */
        String PREBOOK = "02";

        /**
         * 03 已占用
         */
        String OCCUPIED = "03";

        /**
         * 04 禁用
         */
        String DISABLED = "04";

    }

    /**
     * 资源实例状态
     */
    interface ResourceInstanceStatus {

        /**
         * 01 预占中
         */
        String PREBOOK = "01";

        /**
         * 02 使用中
         */
        String USED = "02";

        /**
         * 03 已停用
         */
        String STOPED = "03";

        /**
         * 99 已释放
         */
        String RELEASED = "99";

    }

    /**
     * 主机资源实例状态
     */
    interface ResHostInstanceStatus {

        /**
         * 00 预占中
         */
        String PREBOOK = "00";

        /**
         * 01等待中
         */
        String WAITING = "01";

        /**
         * 02 正常
         */
        String NORMAL = "02";

        /**
         * 03 已关机
         */
        String SHUTDOWN = "03";

        /**
         * 04 已暂停
         */
        String PAUSE = "04";

        /**
         * 99 已销毁
         */
        String DESTROY = "99";

    }

    /**
     * 存储资源实例状态
     */
    interface ResStorageInstanceStatus {

        /**
         * 00 预占中
         */
        String PREBOOK = "00";

        /**
         * 01等待中
         */
        String WAITING = "01";

        /**
         * 02 可用
         */
        String AVAILABLE = "02";

        /**
         * 03 使用中
         */
        String USING = "03";

        /**
         * 04 已暂停
         */
        String PAUSE = "04";

        /**
         * 99 已删除
         */
        String DELETED = "99";

    }

    /**
     * IP资源实例状态
     */
    interface ResIpInstanceStatus {

        /**
         * 00 预占中
         */
        String PREBOOK = "00";

        /**
         * 01等待中
         */
        String WAITING = "01";

        /**
         * 02 可用
         */
        String AVAILABLE = "02";

        /**
         * 03 使用中
         */
        String USING = "03";

        /**
         * 04 已暂停
         */
        String PAUSE = "04";

        /**
         * 99 已删除
         */
        String DELETED = "99";

    }

    /**
     * 消费记录操作类型
     */
    interface billingAccountCdrOpType {

        /**
         * 00 不设置
         */
        String NO_SETTING = "00";

        /**
         * 01 充值
         */
        String DEPOSIT = "01";

        /**
         * 02 扣款
         */
        String CUT_PAYMENT = "02";

        /**
         * 退款
         */
        String ADD_PAYMENT = "03";
    }

    /**
     * 消费记录状态
     */
    interface billingAccountCdrStatus {

        /**
         * 01 交易成功
         */
        String SUCCESS = "01";

        /**
         * 02 交易失败
         */
        String FAILURE = "02";

        /**
         * 02 交易中
         */
        String LOADING = "03";

    }

    /**
     * EXCHANGE资源实例状态
     */
    interface ResExchangeInstanceStatus {

        /**
         * 00 预占中
         */
        String PREBOOK = "00";

        /**
         * 01等待中
         */
        String WAITING = "01";

        /**
         * 02 可用
         */
        String AVAILABLE = "02";

        /**
         * 03 使用中
         */
        String USING = "03";

        /**
         * 04 已暂停
         */
        String PAUSE = "04";

        /**
         * 99 已删除
         */
        String DELETED = "99";

    }

    /**
     * SHAREPOINT资源实例状态
     */
    interface ResSharepointInstanceStatus {

        /**
         * 00 预占中
         */
        String PREBOOK = "00";

        /**
         * 01等待中
         */
        String WAITING = "01";

        /**
         * 02 可用
         */
        String AVAILABLE = "02";

        /**
         * 03 使用中
         */
        String USING = "03";

        /**
         * 04 已暂停
         */
        String PAUSE = "04";

        /**
         * 99 已删除
         */
        String DELETED = "99";

    }

    /**
     * 物理机资源实例状态
     */
    interface ResPmInstanceStatus {

        /**
         * 00 预占中
         */
        String PREBOOK = "00";

        /**
         * 01等待中
         */
        String WAITING = "01";

        /**
         * 02 可用
         */
        String AVAILABLE = "02";

        /**
         * 03 使用中
         */
        String USING = "03";

        /**
         * 04 已暂停
         */
        String PAUSE = "04";

        /**
         * 99 已删除
         */
        String DELETED = "99";

    }

    /**
     * DEPLOYMENT资源实例状态
     */
    interface ResDeploymentInstanceStatus {

        /**
         * 00 预占中
         */
        String PREBOOK = "00";

        /**
         * 01等待中
         */
        String WAITING = "01";

        /**
         * 02 可用
         */
        String AVAILABLE = "02";

        /**
         * 03 使用中
         */
        String USING = "03";

        /**
         * 04 已暂停
         */
        String PAUSE = "04";

        /**
         * 99 已删除
         */
        String DELETED = "99";

    }

    /**
     * 资源实例类型
     */
    interface ResInstType {

        /**
         * 南向接口创建虚机URL
         */
        String RES_INST_VM = "RES-INST-VM";
    }

    /**
     * IP资源状态
     */
    interface ResIpStatus {

        /**
         * 00 预占中
         */
        String PRE_OCCUPIED = "00";

        /**
         * 01 未使用
         */
        String UNOCCUPIED = "01";

        /**
         * 02 已使用
         */
        String OCCUPIED = "02";

    }

    /**
     * IP资源状态
     */
    interface ResIpUsageStatus {

        /**
         * 02 使用中
         */
        String USEING = "02";

        /**
         * 01 未使用
         */
        String AVAILABLE = "01";

    }

    /**
     * IP池类型
     */
    interface IpPoolType {

        /**
         * 01 内网
         */
        String INTRANET = "1";

        /**
         * 02 公网
         */
        String NETWORK = "2";

    }

    /**
     * activiti流程图名称
     */
    interface ActivitiFlow {

        /**
         * 01 订单审批流程
         */
        String ORDER_APPROVE_PROCESS = "VApproveProcess";

        /**
         * 02 服务自动开通流程
         */
        String ORDER_SERVICE_START_PROCESS = "OrderServiceStartProcess";

        /**
         * 03 服务退订流程
         */
        String SERVICE_CANCEL_PROCESS = "ServiceCancelProcess";

    }

    /**
     * 服务实例规格类型
     */
    interface InstanceSpecType {

        /**
         * 01 CPU核数
         */
        String CPU = "cpu";

        /**
         * 02 内存大小
         */
        String MEMORY = "memory";

        // /** 03 数据盘 */
        // String DATA_DISK = "DATA_DISK";

        /**
         * 04 是否需要外网IP
         */
        String NEED_WAN = "NEED_WAN";

        /**
         * 05 性能保障等级
         */
        String PERF_LEVEL = "PERF_LEVEL";

        /**
         * 06 VLAN类型
         */
        String VLAN_TYPE = "VLAN_TYPE";

        /**
         * 07 操作系统
         */
        String OS = "os";

        /**
         * 08 SHAREPOINT_VOLUME
         */
        String SHAREPOINT_VOLUME = "SHAREPOINT_VOLUME";

        /**
         * 09 EACH_MAIL_VOLUME
         */
        String EACH_MAIL_VOLUME = "EACH_MAIL_VOLUME";

        /**
         * 10 DOMAIN
         */
        String DOMAIN = "DOMAIN";

        /**
         * 11 USER_AMOUNT
         */
        String USER_AMOUNT = "USER_AMOUNT";

        /**
         * 12 STORAGE_TYPE
         */
        String STORAGE_TYPE = "STORAGE_TYPE";

        /**
         * 13 STORAGE_PURPOSE
         */
        String STORAGE_PURPOSE = "STORAGE_PURPOSE";

        /**
         * 14 DISK_SIZE
         */
        String DISK_SIZE = "DISK_SIZE";

        /**
         * 15 deploymentType
         */
        String DEPLOYMENT_TYPE = "deploymentType";

        /**
         * 16 softwareType
         */
        String SOFTWARE_TYPE = "softwareType";

        /**
         * 17 softwareVersion
         */
        String SOFTWARE_VERSION = "softwareVersion";

        /**
         * 18 runOsCategory
         */
        String RUN_OS_CATEGORY = "runOsCategory";

        /**
         * 19 runTargetHost
         */
        String RUN_TARGET_HOST = "runTargetHost";

        /**
         * 20 dbName
         */
        String DB_NAME = "dbName";

        /**
         * 21 dbPassword
         */
        String DB_PASSWORD = "dbPassword";

        /**
         * 22 dbMemoryLimit
         */
        String DB_MEMORY_LIMIT = "dbMemoryLimit";

        /**
         * 23 domainName
         */
        String DOMAIN_NAME = "domainName";

        /**
         * 24 虚拟化平台类型
         */
        String VIRTUAL_PLATFORM_TYPE = "VIRTUAL_PLATFORM_TYPE";

        /**
         * 数据盘
         */
        String DATA_DISK = "dataDisk";

        /**
         * 是否需要内网IP
         */
        String NEED_LAN = "needLan";

        /**
         * 系统盘
         */
        String SYSTEM_DISK = "systemDisk";

        /**
         * 网络
         */
        String NETS = "nets";

        /**
         * 对象存储容量
         */
        String OBJECT_DISK = "OBJECT_DISK";

        /**
         * 宽带规格
         */
        String TAPE_WIDTH = "TAPE_WIDTH";

        /**
         * IP个数
         */
        String IP_COUNT = "IP_COUNT";

        /**
         * 公钥、私钥
         **/
        String KEY_PAIR = "keyPair";

        /**
         * 预安装软件
         **/
        String INSTALL_SOFTWARE = "installSoftware";

        /**
         * 回收方式
         **/
        String RECOVERY_TYPE = "recoveryType";

        /**
         * 备注
         **/
        String REMARK = "remark";

        /**
         * 挂载点
         **/
        String MOUNT_POINT = "MOUNT_POINT";

        /**
         * 文件系统
         **/
        String FILE_SYSTEM = "FILE_SYSTEM";

        /**
         * 区域
         **/
        String REGION = "region";

        /**
         * 分区
         **/
        String ZONE = "zone";

        /**
         * 安全组
         **/
        String SECURITY_GROUP = "securityGroup";

    }

    /**
     * 虚拟平台类型
     */
    interface VirtualPlatformType {

        /**
         * 01 Vmware
         */
        String VMWARE = "VMware";

        /**
         * 02 OPENSTACK
         */
        String OPENSTACK = "OpenStack";

        /**
         * 03 Hyper-V
         */
        String HYPERV = "Hyper-V";

        /**
         * 04 PowerVM
         */
        String PowerVM = "PowerVM";

        /**
         * 05 HP-UX
         */
        String HP_UX = "HP-UX";

        /**
         * 06 Hmc
         */
        String HMC = "HMC";

        /**
         * 07 IVM
         */
        String IVM = "IVM";

        /**
         * 08 Other
         */
        String OTHER = "Other";

    }

    /**
     * 存储卷类型
     */
    interface VolumeType {

        /**
         * 01 VG
         */
        String VG = "KVM";

        /**
         * 02 Datastore
         */
        String DATASTORE = "VMware";

        /**
         * 03 Hyper-V
         */
        String HYPERV = "Hyper-V";

        /**
         * 04 PowerVM
         */
        String PowerVM = "PowerVM";

        /**
         * 05 HP-UX
         */
        String HP_UX = "HP-UX";

        /**
         * 默认存储
         */
        String DEFAULT_VOLUME = "默认类型";

        /**
         * 默认存储uuid
         */
        String DEFAULT_VOLUME_UUID = "defaultVolume";

    }

    /**
     * 存储卷类型
     */
    interface VolumeStatus {

        /**
         * 01 可用
         */
        String NORMAL = "01";

        /**
         * 02 不可用
         */
        String OUTLINE = "02";

    }

    /**
     * 是否需要外网ip
     */
    interface NeedWan {

        /**
         * 0 不需要
         */
        String NO = "0";

        /**
         * 1 需要
         */
        String YES = "1";

    }

    /**
     * 审核状态
     */
    interface ApproveStatus {

        /**
         * 01 同意
         */
        String AGREE = "01";

        /**
         * 02 不同意
         */
        String DISAGREE = "02";

    }

    /**
     * 流程审核类型
     */
    interface ProcessType {

        /**
         * 01 VM订单开通
         */
        String VM_OPEN = "01";

        /**
         * 02 VM服务退订
         */
        String VM_CANCEL = "02";

        /**
         * 03 EXCHANGE开通
         */
        String EXCHANGE_OPEN = "03";

        /**
         * 04 EXCHANGE退订
         */
        String EXCHANGE_CANCEL = "04";

        /**
         * 05 SHAREPOINT开通
         */
        String SHAREPOINT_OPEN = "05";

        /**
         * 06 SHAREPOINT退订
         */
        String SHAREPOINT_CANCEL = "06";

        /**
         * 07 DEPLOYMENT开通
         */
        String DEPLOYMENT_OPEN = "07";

        /**
         * 08 DEPLOYMENT退订
         */
        String DEPLOYMENT_CANCEL = "08";

        /**
         * 09 块存储开通
         */
        String STORAGE_OPEN = "09";

        /**
         * 10 块存储退订
         */
        String STORAGE_CANCEL = "10";

        /**
         * 11 闲置资源回收
         */
        String FREE_RESOURCE_REDUCE = "11";

        /**
         * 12 实例变更
         */
        String INSTANCE_ADJUST = "12";

        /**
         * 13 对象存储开通
         */
        String OBJECT_STORAGE_OPEN = "13";

        /**
         * 14 对象存储退订
         */
        String OBJECT_STORAGE_CANCEL = "14";

        /**
         * 15 弹性IP服务开通
         */
        String FLOATING_IP_OPEN = "15";

        /**
         * 16 弹性IP服务退订
         */
        String FLOATING_IP_CANCEL = "16";

        /**
         * 17 CDN服务开通
         */
        String CDN_OPEN = "17";

        /**
         * 18 CDN服务开通
         */
        String CDN_CANCEL = "18";

        /**
         * 19 块存储扩容
         */
        String STORAGE_EXPAND = "19";

        /**
         * 20  项目经理变更
         */
        String MANAGER_CHANGE = "20";

        /**
         * 21  项目变更
         */
        String MGT_OBJ_CHANGE = "21";
    }

    /**
     * 流程审核类型
     */
    interface ProcessApproveStatus {

        /**
         * 01 未审批
         */
        String APPROVING = "01";

        /**
         * 02 已审批
         */
        String APPROVED = "02";

    }

    /**
     * 主机管理状态
     */
    interface HostManagementStatus {

        /**
         * 01 已纳入管理
         */
        String YES = "01";

        /**
         * 02 未纳入管理
         */
        String NO = "02";

    }

    /**
     * 用户类型
     */
    interface USER_TYPE {

        /**
         * 10 民警
         */
        String USER_TYPE_10 = "10";

        /**
         * 20 辅警
         */
        String USER_TYPE_20 = "20";

        /**
         * 30 外部人员
         */
        String USER_TYPE_30 = "30";

    }

    /**
     * 角色类型
     */
    interface RORLE_TYPE {

        /**
         * 01 前台角色
         */
        String FOREGROUND = "01";

        /**
         * 02 后台角色
         */
        String BACKGROUND = "02";

    }

    /**
     * 用户状态
     */
    interface UserStatus {

        /**
         * 1 有效
         */
        String AVAILABILITY = "1";

        /**
         * 0 禁用
         */
        String FORBIDDEN = "0";

        /**
         * 2 待审核
         */
        String NOTAPPROVE = "2";

        /**
         * 3 待激活
         */
        String NOTACTIVATE = "3";

        /**
         * 9 锁定
         */
        String LOCKED = "9";

    }

    /**
     * 租户状态
     */
    interface TenantStatus {

        /**
         * 1 待审核
         */
        String NOTAPPROVE = "1";

        /**
         * 2 正常
         */
        String NORMAL = "2";

        /**
         * 3 禁止
         */
        String FORBIDDEN = "3";

    }


    /**
     * 租户类型
     */
    interface TenantType {

        /**
         * 01 企业
         */
        String COMPANY = "01";

        /**
         * 02 个人
         */
        String PERSONAL = "02";

    }

    /**
     * 分配对象
     */
    interface AllocTargetType {

        /**
         * 企业
         */
        String COMPANY = "company";

        /**
         * 个人
         */
        String PROJECT = "project";

    }

    /**
     * 主机管理状态
     */
    interface ApproveAction {

        /**
         * 01 租户审批
         */
        String TENANT_APPROVE = "TenantApprove";

        /**
         * 02 运维审批
         */
        String ORDER_APPROVE = "OrderApprove";

    }

    /**
     * 调用北向接口返回结果状态
     */
    interface ResultStatus {

        /**
         * success 成功
         */
        String SUCCESS = "success";

        /**
         * failure 失败
         */
        String FAILURE = "failure";
    }

    /**
     * 流水号类别
     */
    interface SidCategory {

        /**
         * 01 订单编号
         */
        String ORDER = "ORDER_ID";

        /**
         * 02 用户自定义镜像ID
         */
        String UD_IMAGE_ID = "UD_IMAGE_ID";

        /**
         * 03 工单编号
         */
        String TICKET = "TICKET_NO";

        /**
         * 04 虚拟机名称
         */
        String VM_NAME = "VM_NAME";

        /**
         * 03 磁盘名称
         */
        String VD_NAME = "VD_NAME";

        /**
         * VM_SNAPSHOT_ID 虚拟机快照编号
         */
        String VM_SNAPSHOT_ID = "VM_SNAPSHOT_ID";

        /**
         * VM_BACKUP_ID 虚拟机快照编号
         */
        String VM_BACKUP_ID = "VM_BACKUP_ID";

        /**
         * VD_SNAPSHOT_ID 虚拟机快照编号
         */
        String VD_SNAPSHOT_ID = "VD_SNAPSHOT_ID";

        /**
         * VM_BACKUP_ID 虚拟机快照编号
         */
        String VD_BACKUP_ID = "VD_BACKUP_ID";

        /**
         * 账单ID
         */
        String BILL_ID = "BILL_ID";

        /**
         * 使用申请订单id
         */
        String TRIAL_ORDER_ID = "TRIAL_ORDER_ID";
        /**
         * 使用申请订单前缀
         */
        String TRIAL_ORDER_ID_PREFIX = "TO";
    }

    /**
     * IP分类
     */
    interface IpCategory {

        /**
         * 01 内网
         */
        String INTRANET_IP = "01";

        /**
         * 02 公网
         */
        String PUBLIC = "02";
    }

    /**
     * IP 类型
     */
    interface IpType {

        /**
         * 01 IPv4
         */
        String IPV4 = "01";

        /**
         * 02 IPv6
         */
        String IPV6 = "02";
    }

    /**
     * IP 类型
     */
    interface EtherType {

        /**
         * 01 IPv4
         */
        String IPV4 = "IPv4";

        /**
         * 02 IPv6
         */
        String IPV6 = "IPv6";
    }

    /**
     * 使用状态
     */
    interface UsageStatus {

        /**
         * 01 已使用
         */
        String USED = "01";

        /**
         * 02 未使用
         */
        String UNUSED = "02";
    }

    /**
     * 计费计划类型
     */
    interface BillingPlanType {

        /**
         * YM 包年包月
         */
        String YM = "YM";

        /**
         * Metering 按量付费
         */
        String METERING = "Metering";
    }

    /**
     * 计费类型
     */
    interface BillingType {

        /**
         * YEAR 按年
         */
        String YEAR = "Year";

        /**
         * MONTH 按月
         */
        String MONTH = "Month";

        /**
         * DAY 按天
         */
        String DAY = "Day";
    }

    /**
     * 数据字典状态
     */
    interface CodeEnable {

        /**
         * 1 启用
         */
        String ABLE = "1";

        /**
         * 0 不启用
         */
        String UNABLE = "0";
    }

    /**
     * 服务状态
     */
    interface ServiceStatus {

        /**
         * 00 已新建
         */
        String CREATED = "00";

        /**
         * 01 已提交
         */
        String COMMITED = "01";

        /**
         * 02 已审批
         */
        String APPROVED = "02";

        /**
         * 03 已发布
         */
        String RELEASED = "03";

        /**
         * 04 已部署
         */
        String DEPLOYED = "04";

        /**
         * 05 已停用
         */
        String DISABLED = "05";

        /**
         * 99 已注销
         */
        String LOGOUT = "99";
    }

    /**
     * 服务状态
     */
    interface ServiceTemplateStatus {

        /**
         * 00 已新建
         */
        String CREATED = "00";

        /**
         * 01 已发布
         */
        String RELEASED = "01";

        /**
         * 02 已停用
         */
        String DISABLED = "02";

    }

    /**
     * Email模板代码
     */
    interface MailTemplateId {

        /**
         * 激活账号Email
         */
        String EMAIL_TO_ACTIVATE = "4";

        /**
         * 意见反馈Email
         */
        String EMAIL_TO_FEEDBACK = "5";

        /**
         * 准备开通服务通知Email
         */
        String PRE_LAUNCH_SERVICE = "21";

        /**
         * 订单审批提醒
         */
        String ORDER_APPROVE_NOTIFY = "22";

        /**
         * 激活账号
         */
        String ACCOUNT_TO_ACTIVATE = "41";

        /**
         * 闲置资源审批提醒
         */
        String FREE_RES_APPROVE_NOTIFY = "23";

        /**
         * 报表通知
         */
        String EMAIL_TO_EXCEL = "24";

        /**
         * 注册完成通知
         */
        String EMAIL_TO_REGISTER = "25";

        /**
         * 审核完成通知
         */
        String EMAIL_TO_APPROVE = "26";

        /**
         * 资源闲置回收通知
         */
        String FREE_RES_RECOVER_NOTIFY = "27";

        /**
         * 服务开通成功通知
         */
        String SERVICE_OPEN_SUCCESS_EMAIL = "2";

        /**
         * 服务变更成功通知
         */
        String SERVICE_CHANGE_SUCCESS_EMAIL = "28";

        /**
         * 服务退订成功通知
         */
        String SERVICE_CENCAL_SUCCESS_EMAIL = "29";

        /**
         * 告警信息运维管理员通知邮件
         */
        String TICKET_NOTICE_ADMIN_SEND = "30";

        /**
         * 闲置报表通知
         */
        String FRES_EMAIL_TO_EXCEL = "31";

        /**
         * 回收报表通知
         */
        String RECOVERY_EMAIL_TO_EXCEL = "32";

        /**
         * 运维周报通知
         */
        String WEEKREPORT_EMAIL_TO_EXCEL = "33";

        /**
         * 待审核用户注册邮件通知
         */
        String PENDING_TO_EMAIL = "34";

        /**
         * 项目到期邮件通知
         */
        String MGTOBJ_EXPIREDATE_TO_EMAIL = "35";

        /**
         * 项目申请，运维管理员审核通知邮件
         */
        String MGTOBJ_APPLY_ADMIN_EMAIL = "36";
        /**
         * 项目审核完成，项目经理通知邮件
         */
        String APPROVE_PROJECT_EMAIL = "37";
//		/**项目到期，资源回收通知邮件*/
//		String RES_RECYCLING_NOTIFY_EMAIL = "38";
        /**服务申请单开通完成，相关人员通知邮件*/

        /**
         * 工单分配，相关人员通知邮件
         */
        String TICKET_ALLOCATION_NOTICE_EMAIL = "38";
        /**
         * 服务审核拒绝邮件通知
         */
        String ORDER_REFUSE_NOTIFY = "39";
        /**
         * 告警信息邮件通知
         */
        String ALARM_INFO_EMAIL = "40";

        /**
         * 云应用开通通知
         */
        String CLOUD_APP_OPEN_NOTIFI = "open_app_notification";

        /**
         * 云应用释放通知
         */
        String CLOUD_APP_RELEASE_NOTIFI = "release_app_notification";

        /**
         * 用户导入通知
         */
        String USER_IMPORT_NOTIFI = "user_import_notification";

        /**
         * 用户邮件变更验证
         */
        String USER_EMAIL_CHANGE_VALIDATE = "email_change_notification";

        /**
         * 重置密码Email
         */
        String EMAIL_TO_CHANGEPWD = "email_to_changepwd_notification";

        /**
         * 处理试用申请Email
         */
        String EMAIL_TO_TRIAL_REQUEST = "email_to_trial_request";

        /**
         * 邮件邀请参会
         */
        String EMAIL_TO_INVITE_MEETING = "email_to_invite_meeting";

        /**
         * 应用变更通知邮件
         */
        String EMAIL_TO_APP_CHANGE = "email_to_app_change";

        /**
         * 用户结束时间变更
         */
        String EMAIL_TO_USER_END_TIME_CHANGE = "email_to_user_end_time_change";

        /**
         * 用户结束时间变更
         */
        String EMAIL_USER_EXPIRE = "email_user_expire";

        /**
         * 用户结束时间变更
         */
        String EMAIL_USER_EXPIRE_TO_ADMIN = "email_user_expire_to_admin";

        /**
         * 监控告警消息提示 -- 未恢复
         */
        String ALARM_NOTIFICATION_PROBLEM = "alarm_notification_problem";

        /**
         * 监控告警消息提示 -- 已恢复
         */
        String ALARM_NOTIFICATION_OK = "alarm_notification_ok";

        /**
         * 激活告警联系人
         */
        String ALARM_CONTACT_ACTIVE = "alarm_contact_active";

        /**
         * 应用部署开始通知
         */
        String APP_DEPLOY_START_NOTIFI = "app_deploy_start_notification";

        /**
         * 应用部署成功通知
         */
        String APP_DEPLOY_SUCCESS_NOTIFI = "app_deploy_success_notification";

        /**
         * 应用部署失败通知
         */
        String APP_DEPLOY_FAILED_NOTIFI = "app_deploy_failed_notification";

        /**
         * 应用开始释放
         */
        String APP_DELETE_START_NOTIFI = "app_delete_start_notification";

        /**
         * 应用删除成功
         */
        String APP_DELETE_SUCCESS_NOTIFI = "app_delete_success_notification";

        /**
         * 应用开始释放
         */
        String APP_REDEPLOY_START_NOTIFI = "app_redeploy_start_notification";

        /**
         * 应用开始停止
         */
        String APP_STOP_START_NOTIFI = "app_stop_start_notification";

        /**
         * 应用停止成功
         */
        String APP_STOP_SUCCESS_NOTIFI = "app_stop_success_notification";

        /**
         * 应用开始启动
         */
        String APP_STARTOVER_START_NOTIFI = "app_startover_start_notification";


        /**
         * 应用启动成功
         */
        String APP_STARTOVER_SUCCESS_NOTIFI = "app_startover_success_notification";


        /**
         * 应用操作未知异常
         */
        String APP_OP_UNKNOW_ERROR_NOTIFI = "app_op_unknow_error_notification";

        /**
         * 主机开始创建
         */
        String CLOUD_HOST_CREATE_START_NOTIFI = "cloud_host_create_start_notification";


        /**
         * 主机导入
         */
        String CLOUD_HOST_IMPORT_NOTIFI = "cloud_host_import_notification";

        /**
         * 主机删除成功
         */
        String CLOUD_HOST_DELETE_NOTIFI = "cloud_host_delete_notification";

        /**
         * 主机创建成功
         */
        String CLOUD_HOST_CREATE_SUCCESS_NOTIFI = "cloud_host_create_success_notification";

        /**
         * 主机重新配置成功
         */
        String CLOUD_HOST_RE_CONFIG_SUCCESS_NOTIFI = "cloud_host_re_config_success_notification";

        /**
         * 主机创建失败
         */
        String CLOUD_HOST_CREATE_FAILED_NOTIFI = "cloud_host_create_failed_notification";

        /**
         * 主机重新配置失败
         */
        String CLOUD_HOST_RE_CONFIG_FAILED_NOTIFI = "cloud_host_re_config_failed_notification";

        /**
         * 白皮书下载
         */
        String WHITE_BOOK_DOWN = "white_book_down";

        /**
         * 服务到期提醒
         */
        String SELF_SERVICE_DEPLOY_INST_EXPIRE = "self_service_deploy_inst_expire";

    }

    /**
     * 检查开通实例状态
     */
    interface CheckInstanceStatus {

        /**
         * VMWare开启
         */
        String POWERED_ON = "poweredOn";

        /**
         * KVM 开启
         */
        String ACTIVE = "ACTIVE";

        /**
         * 关机
         */
        String POWERED_OFF = "poweredOff";

        /**
         * 暂停
         */
        String SUSPENDED = "suspended";

        /**
         * 正常
         */
        String NORMAL = "NORMAL";

        /**
         * 不正常
         */
        String NON_NORMAL = "NON_NORMAL";

        /**
         * 错误
         */
        String ERROR = "ERROR";
    }

    /**
     * 检查开通实例状态
     */
    interface RepeatOrder {

        /**
         * 0 否
         */
        String NO = "0";

        /**
         * 1 是
         */
        String YES = "1";

    }

    /**
     * 存储目的
     */
    interface StoragePurpose {

        /**
         * 01 系统盘
         */
        String SYSTEM_DISK = "01";

        /**
         * 02 数据盘
         */
        String DATA_DISK = "02";

        /**
         * 03 系统数据盘
         */
        String SYSTEM_DATA_DISK = "03";
    }

    /**
     * 分配方式
     */
    interface AllocationMode {

        /**
         * 按内存
         */
        String MEMORY = "M";

        /**
         * 按CPU
         */
        String CPU = "C";

    }

    /**
     * 分配策略
     */
    interface AllocationPolicy {

        /**
         * 均分
         */
        String STRIPING = "Striping";

        /**
         * 填满
         */
        String PACKING = "Packing";

        /**
         * 轻负载
         */
        String LOAD_AWARE = "Load-Aware";

        /**
         * 高可用
         */
        String HA_AWARE = "HA-Aware";

    }

    /**
     * 告警状态
     */
    interface AlarmStatus {

        /**
         * 未处理
         */
        String UNTREATED = "01";

        /**
         * 已确认
         */
        String CONFIRMED = "02";

        /**
         * 已清除
         */
        String CLEARED = "03";

    }

    /**
     * 租户配额
     */
    interface TenantQuota {

        /**
         * 虚拟内核
         */
        String CORES = "cores";

        /**
         * 内存MB
         */
        String RAMS = "rams";

        /**
         * 云主机
         */
        String INSTANCES = "instances";

        /**
         * 外置存储
         */
        String STORAGES = "storageQuota";

        /**
         * 虚机个数
         */
        String VM_NUM = "vmNum";

        /**
         * 元数据条目
         */
        String METADATA_ITEMS = "metadata_items";

        /**
         * 注入的文件
         */
        String INJECTED_FILES = "injected_files";

        /**
         * 注入的文件内容字节数
         */
        String INJECTED_FILE_CONTENT_BYTES = "injected_file_content_bytes";

        /**
         * 存储GB
         */
        String GIGABYTES = "gigabytes";

        /**
         * 块存储
         */
        String VOLUMES = "volumes";

        /**
         * 快照
         */
        String SNAPSHOTS = "snapshots";

        /**
         * 安全组
         */
        String SECURITY_GROUP = "security_group";

        /**
         * 安全组规则
         */
        String SECURITY_GROUP_RULE = "security_group_rule";

        /**
         * 浮动IP
         */
        String FLOATINGIP = "floatingip";

        /**
         * 网络
         */
        String NETWORK = "network";

        /**
         * 端口
         */
        String PORT = "port";

        /**
         * 路由
         */
        String ROUTER = "router";

        /**
         * 子网
         */
        String SUBNET = "subnet";

    }

    /**
     * 计费状态
     */
    interface BillStatus {

        /**
         * 月结完成
         */
        String MONTH_COMPLETED = "01";

        /**
         * 已审核
         */
        String APPROVED = "02";

        /**
         * 已缴费
         */
        String PAYED = "03";

        /**
         * 作废
         */
        String CANCELLATION = "09";

    }

    /**
     * 资费计划状态
     */
    interface BillPlanStatus {

        /**
         * 1 启用
         */
        String ABLE = "01";

        /**
         * 0 不启用
         */
        String UNABLE = "02";
    }

    /**
     * 计费计价类型
     */
    interface BillingChargeType {

        /**
         * 01 增量收费
         */
        String INCREMENT_CHARGE = "01";

        /**
         * 02 固定收费
         */
        String FIX_CHARGE = "02";
    }

    /**
     * 工单状态
     */
    interface TicketStatus {

        /**
         * 01 新工单
         */
        String CREATED = "01";

        /**
         * 02 已分配
         */
        String ALLOCATED = "02";

        /**
         * 03 处理中
         */
        String PROCESSING = "03";

        /**
         * 04 已解决
         */
        String RESOLVE = "04";

        /**
         * 05 已关闭
         */
        String CLOSE = "05";

        /**
         * 99 已取消
         */
        String CANCEL = "99";

    }

    /**
     * 工单操作
     */
    interface TicketOperate {

//		/** 01 前台回复 */
//		String FRONT_REPLY = "01";
//
//		/** 02 前台关闭 */
//		String FRONT_CLOSE = "02";
//
//		/** 03 后台回复 */
//		String BACK_REPLY = "03";
//
//		/** 04 后台关闭 */
//		String BACK_CLOSE = "04";
//
//		/** 05 后台移除 */
//		String BACK_REMOVE = "05";

        /**
         * 01 自动处理处理
         */
        String MANUAL_HANDLER = "01";

        /**
         * 02 手动处理
         */
        String AUTO_HANDLER = "02";

    }

    /**
     * 单点登录用户类型操作
     */
    interface SSOUserType {

        /**
         * tenantUser 租户用户
         */
        String TENANT_USER = "tenantUser";

        /**
         * tenantAdmin 租户管理员
         */
        String TENANT_ADMIN = "tenantAdmin";

        /**
         * plantformAdmin 后台管理员
         */
        String PLANTFORM_ADMIN = "platformAdmin";

    }

    /**
     * 镜像状态
     */
    interface ImageStatus {

        /**
         * 00已新建
         */
        String NEWCREATE = "00";

        /**
         * 01 已发布
         */
        String AVAILABILITY = "01";

        /**
         * 02 已停用
         */
        String FORBIDDEN = "02";

    }

    /**
     * 镜像类型
     */
    interface ImageType {

        /**
         * 01 公有
         */
        String PUBLIC = "01";

        /**
         * 02 私有
         */
        String PRIVATE = "02";
    }

    /**
     * 发现主机状态
     */
    interface FIND_HOST_STATUS {

        /**
         * 获取信息中
         */
        String GETING_INFO = "01";

        /**
         * 未部署系统
         */
        String NO_DEPLOYMENT_SYSTEM = "02";

        /**
         * 系统部署中
         */
        String SYSTEM_DEPLOYMENTING = "03";

        /**
         * 已部署系统
         */
        String DEPLOYED_SYSTEM = "04";

        /**
         * 已加入平台
         */
        String ADDED_TO_PLATFORM = "05";

    }

    /**
     * 系统日志级别
     */
    interface SYS_LOG_LEVEL {

        /**
         * 提示
         */
        String INFO = "01";

        /**
         * 警告
         */
        String WARNING = "02";

        /**
         * 错误
         */
        String ERROR = "03";

        /**
         * 严重错误
         */
        String FATAL = "04";
    }

    /**
     * 系统日志操作结果
     */
    interface SYS_LOG_RESULT {

        /**
         * 失败
         */
        String FAIL = "01";

        /**
         * 成功
         */
        String SUCCESS = "02";
    }

    /**
     * 前后台门户区分名称
     */
    interface PlatformName {

        /**
         * 前台消费门户
         */
        String PORTAL = "PORTAL";

        /**
         * 后台管理门户
         */
        String PLATFORM = "PLATFORM";
    }

    /**
     * 资费类型
     */
    interface BILLING_TYPE {

        /**
         * 年
         */
        String YEAR = "Year";

        /**
         * 月
         */
        String MONTH = "Month";

        /**
         * 天
         */
        String DAY = "Day";
    }

    /**
     * 资费计划类型
     */
    interface BILLING_PLAN_TYPE {

        /**
         * 包年包月
         */
        String YM = "YM";

        /**
         * 按量付费
         */
        String METERING = "Metering";
    }

    /**
     * 资费计划状态
     */
    interface BILL_PLAN_STATUS {

        /**
         * 禁用
         */
        String UNABLE = "02";

        /**
         * 启用
         */
        String ABLE = "01";
    }

    /**
     * 收费类型
     */
    interface BILLING_CHARGE_TYPE {

        /**
         * 增量收费
         */
        String INCREMENT_CHARGE = "01";

        /**
         * 固定收费
         */
        String FIX_CHARGE = "02";
    }

    /**
     * 计费状态
     */
    interface BILL_STATUS {

        /**
         * 未缴费
         */
        String UNPAYED = "01";

        /**
         * 已缴费
         */
        String PAYED = "02";

        /**
         * 已作废
         */
        String CANCELLATION = "09";
    }

    /**
     * 服务实例状态代码
     */
    interface SERVICE_INSTANCE_CD {

        /**
         * 0 已创建
         */
        String CREATED = "00";

        /**
         * 1 开通中
         */
        String OPENING = "01";

        /**
         * 2 无效实例
         */
        String INVALID = "02";

        /**
         * 3 已开通
         */
        String OPENED = "03";

        /**
         * 4 初始化异常
         */
        String EXCEPTION = "04";

        /**
         * 5 已禁用
         */
        String DISABLED = "05";

        /**
         * 6 退订中
         */
        String CANCELING = "06";

        /**
         * 9 已退订
         */
        String CANCELED = "99";
    }

    /**
     * 订单状态
     */
    interface ORDER_STATUS {

        /**
         * 1 已创建
         */
        String CREATED = "01";

        /**
         * 2 审核中
         */
        String CHECKING = "02";

        /**
         * 3 已审核
         */
        String INVALID = "03";

        /**
         * 4 开通中
         */
        String OPENING = "04";

        /**
         * 5 已开通
         */
        String OPENED = "05";

        /**
         * 9 已退订
         */
        String UNSUBSCRIBED = "09";

        /**
         * 9 已取消
         */
        String CANCELED = "99";
    }

    /**
     * 订单状态
     */
    interface RES_TOPOLOGY_TYPE {

        /**
         * 1 区域
         */
        String REGION = "R";

        /**
         * 2 数据中心
         */
        String DC = "DC";

        /**
         * 3 虚拟化环境
         */
        String VE = "VE";

        /**
         * 4 虚拟化资源集群
         */
        String VC = "VC";

        /**
         * 5 资源分区
         */
        String RZ = "RZ";

        /**
         * 6 x86虚拟化资源池
         */
        String PCVX = "PCVX";

        /**
         * 7 x86非虚拟化资源池
         */
        String PCX = "PCX";

        /**
         * 8 Power虚拟化资源池
         */
        String PCVP = "PCVP";

        /**
         * 9 Power非虚拟化资源池
         */
        String PCP = "PCP";

        /**
         * 10 存储资源池
         */
        String PS = "PS";

        /**
         * 11 网络资源池
         */
        String PN = "PN";

        /**
         * 12 DVS
         */
        String DVS = "PND";

        /**
         * 13 VLAN池
         */
        String PNV = "PNV";

        /**
         * 14 内部网络池
         */
        String PNI = "PNI";

        /**
         * 15 外部网络池
         */
        String PNE = "PNE";

        /**
         * 16 公网IP池
         */
        String PNP = "PNP";

        /**
         * 17 存储类别
         */
        String RSC = "RSC";

        /**
         * 18 计算资源池
         */
        String PC = "PC";
    }

    /**
     * 任务状态
     */
    interface TaskStatus {

        /**
         * 执行中
         */
        String RUNNING = "01";

        /**
         * 成功
         */
        String SUCCESS = "02";

        /**
         * 失败
         */
        String FAIL = "09";

    }

    /**
     * 任务类型
     */
    interface TaskType {

        /* 虚拟机 */
        /**
         * 创建虚拟机
         */
        String CREATE_VM = "createVm";
        /**
         * 操作虚拟机
         */
        String OPERATE_VM = "operateVm";
        /**
         * 调整虚拟机配置
         */
        String RECONFIG_VM = "reconfigVm";
        /**
         * 删除虚拟机
         */
        String DELETE_VM = "deleteVm";
        /**
         * 添加虚拟机网卡
         */
        String ADD_NET_VM = "addVmNetwork";
        /**
         * 删除虚拟机网卡
         */
        String DEL_NET_VM = "delVmNetwork";
        /**
         * 迁移虚拟机
         */
        String MIGRATE_VM = "migrateVm";
        /**
         * 同步虚拟机
         */
        String SCAN_VM = "resVmSync";

        /* 镜像 */
        /**
         * 同步虚拟机模板
         */
        String CREATE_IMAGE = "resVeImageSync";

        /* 磁盘 */
        /**
         * 创建虚拟磁盘
         */
        String CREATE_VD = "createVd";

        /**
         * 挂载虚拟磁盘
         */
        String ATTACH_VD = "acttachVd";

        /**
         * 卸载虚拟磁盘
         */
        String DETACH_VD = "detachVd";

        /**
         * 虚拟磁盘扩容
         */
        String EXPAND_VD = "expandVd";

        /**
         * 删除虚拟磁盘
         */
        String DELETE_VD = "deleteVd";


        /* 网络 */
        /**
         * 创建网络
         */
        String CREATE_NETWORK = "createNetwork";

        /**
         * 同步虚拟化环境
         */
        String SCAN_VCENTER = "resVeSync";

        /**
         * 同步集群
         */
        String SCAN_CLUSTER = "resVcSync";

        /**
         * 同步主机
         */
        String SCAN_HOST = "resHostSync";

        /**
         * 同步网络
         */
        String SCAN_NETWORK = "resNetworkSync";

        /* 浮动IP */
        /**
         * 创建浮动IP
         */
        String CREATE_FLOATINGIP = "createFloatingIp";

        /**
         * 挂载浮动IP
         */
        String ATTACH_FLOATINGIP = "attachFloatingIp";

        /**
         * 卸载浮动IP
         */
        String DETACH_FLOATINGIP = "detachFloatingIp";


        /**
         * 删除浮动IP
         */
        String DELETE_FLOATINGIP = "deleteFloatingIp";

        /* 虚拟机快照 */
        /**
         * 创建虚机快照
         **/
        String CREATE_VM_SNAPSHOT = "createVmSnapshot";

        /**
         * 删除虚机快照
         **/
        String DELETE_VM_SNAPSHOT = "deleteVmSnapshot";

        /**
         * 恢复虚机快照
         **/
        String REVERT_VM_SNAPSHOT = "revertVmSnapshot";

        /* 安全组 */
        /**
         * 创建安全组
         **/
        String CREATE_SECURITY_GROUP = "createSecurityGroup";

        /**
         * 删除安全组
         **/
        String DELETE_SECURITY_GROUP = "deleteSecurityGroup";

        /**
         * 创建块存储快照
         **/
        String CREATE_VD_SNAPSHOT = "createVdSnapshot";

        /**
         * 创建块存储备份
         **/
        String CREATE_VD_BACKUP = "createVdBackup";

        /**
         * 恢复块存储快照
         **/
        String REVERT_VD_SNAPSHOT = "revertVdSnapshot";

        /**
         * 恢复块存储备份
         **/
        String REVERT_VD_BACKUP = "revertVdBackup";

        /**
         * 删除块存储快照
         **/
        String DELETE_VD_SNAPSHOT = "deleteVdSnapshot";

        /**
         * 删除块存储备份
         **/
        String DELETE_VD_BACKUP = "deleteVdBackup";

        /**
         * 绑定安全组
         **/
        String BUND_SECURITY_GROUP = "bundSecurityGroup";

        /**
         * 绑定安全组失败
         **/
        String BUND_SECURITY_FAIL = "bundSecurityFail";

        /**
         * 解绑安全组失败
         **/
        String UNBUND_SECURITY_FAIL = "unbundSecurityFail";

        /**
         * 解绑安全组
         **/
        String UNBUND_SECURITY_GROUP = "unbundSecurityGroup";

        /* CDN */
        /**
         * 创建CDN
         **/
        String CREATE_CDN = "createCdn";

        /**
         * 删除CDN
         **/
        String DELETE_CDN = "deleteCdn";

        /**
         * 安装软件
         */
        String INSTALL_SOFTWARE = "installSoftware";

        /**
         * 存储
         */
        String DELETE_DATASTORAGE = "deleteDatastore";
        String EXTEND_DATASTORAGE = "extendDatastore";
        String CREATE_DATASTORAGE = "createDatastore";

    }

    /**
     * 虚拟机状态
     */
    interface ResVmStatus {

        /**
         * occuping 预占中
         */
        String OCCUPING = "occuping";

        /**
         * creating 创建中
         */
        String CREATING = "creating";

        /**
         * normal 正常
         */
        String NORMAL = "normal";

        /**
         * booting 关机中
         */
        String BOOTING = "booting";

        /**
         * rebooting 重启中
         */
        String REBOOTING = "rebooting";

        /**
         * setting 配置中
         */
        String SETTING = "setting";

        /**
         * poweringOff 关机中
         */
        String POWERINGOFF = "poweringOff";

        /**
         * poweredOff 已关机
         */
        String POWEREDOFF = "poweredOff";

        /**
         * pasueing 暂停中
         */
        String PAUSEING = "pasueing";

        /**
         * paused 已关机
         */
        String PAUSED = "已暂停";

        /**
         * suspending 挂起中
         */
        String SUSPENDING = "suspending";

        /**
         * suspended 已挂起
         */
        String SUSPENDED = "suspended";

        /**
         * migrating 迁移中
         */
        String MIGRATING = "migrating";

        /**
         * failure 故障
         */
        String FAILURE = "failure";

        /**
         * deleting 销毁中
         */
        String DELETING = "deleting";

        /**
         * deleted 已销毁
         */
        String DELETED = "deleted";

        /**
         * recovering 恢复中
         */
        String RECOVERING = "recovering";

        /**
         * Firmware 打开固件
         */
        String FIRMWARE = "firmware";

        /**
         * Unavailable 不可用
         */
        String UNAVAILABLE = "unavailable";

    }

    /**
     * 主机状态
     */
    interface ResHostStatus {

        /**
         * 01 正常
         */
        String NORMAL = "01";

        /**
         * 02 离线
         */
        String OUTLINE = "02";

        /**
         * 03 维护
         */
        String STANDBY = "03";

        /**
         * 04 故障
         */
        String UNKNOWN = "04";

    }

    /**
     * 存储状态
     */
    interface ResStorageStatus {

        /**
         * 01 可用
         */
        String NORMAL = "01";

        /**
         * 02 故障
         */
        String UNKNOWN = "02";

        /**
         * 03 不可用
         */
        String OUTLINE = "03";


    }

    /**
     * 存储状态
     */
    interface ResStorageType {

        /**
         * VMFS
         */
        String VMFS = "VMFS";

        /**
         * NFS
         */
        String NFS = "NFS";

        /**
         * CIFS
         */
        String CIFS = "CIFS";

        /**
         * VFAT
         */
        String VFAT = "VFAT";
    }

    /**
     * 存储类别
     */
    interface StorageCategory {

        /**
         * 01 本地存储
         */
        String LOCAL = "01";

        /**
         * 02 共享存储
         */
        String SHARE = "02";

        /**
         * 03 CINDER块存储
         */
        String CINDER = "03";

    }

    /**
     * 虚拟化环境连接状态
     */
    interface ResVeConnectStatus {

        /**
         * 01 正常
         */
        String SUCCESS = "01";

        /**
         * 02 离线
         */
        String FAILED = "02";
    }

    /**
     * 虚拟化环境更新状态
     */
    interface ResVeUpdateStatus {

        /**
         * 01 更新中
         */
        String UPDATING = "01";

        /**
         * 02 更新成功
         */
        String UPDATE_SUCCESS = "02";

        /**
         * 03 更新失败
         */
        String UPDATE_FAIL = "09";
    }

    /**
     * 配额关联对象类型
     */
    interface QuotaObjectType {

        /**
         * 0 业务
         */
        Long BIZ = 0L;

        /**
         * 1 组织
         */
        Long ORG = 1L;

        /**
         * 2 租户
         */
        Long TENANT = 2L;
    }

    /**
     * 问题状态
     */
    interface IssueStatus {

        /**
         * 01 新问题
         */
        String NEW = "01";

        /**
         * 02 已回复
         */
        String REPLIED = "02";

    }

    /**
     * 问题回复记录类型
     */
    interface IssueReplyType {

        /**
         * 01 管理员回复
         */
        String ADMIN = "01";

        /**
         * 02 用户回复
         */
        String USER = "02";
    }

    /**
     * 虚拟机操作
     */
    interface VmOperation {

        /**
         * 01 开启
         */
        String START = "start";

        /**
         * 02 关闭
         */
        String STOP = "stop";

        /**
         * 03 重启
         */
        String REBOOT = "reboot";

        /**
         * 04 暂停
         */
        String PAUSE = "pause";

        /**
         * 05  取消暂停
         */
        String UNPAUSE = "unpause";

        /**
         * 06 挂起
         */
        String SUSPEND = "suspend";

        /**
         * 07 继续
         */
        String RESUME = "resume";

        /**
         * 08 退订
         */
        String DESTORY = "destory";

        /**
         * 09 迁移
         */
        String MIGRATE = "migrate";

        /**
         * 10 Power开通
         */
        String ACTIVATE = "activate";

        /**
         * 11 Power关机
         */
        String SHUTDOWN = "shutdown";

        /**
         * 12 删除
         */
        String DELETE = "delete";

        /**
         * 13 创建
         */
        String CREATE = "create";
    }


    /**
     * 网卡操作
     */
    interface NetOperate {

        /**
         * add 添加
         */
        String ADD = "add";

        /**
         * delete 删除
         */
        String DELLETE = "delete";

        /**
         * unchange 不变
         */
        String UNCHANGE = "unchange";
    }

    /**
     * 网卡操作
     */
    interface VdOperate {

        /**
         * add 添加
         */
        String ADD = "add";

        /**
         * expand 扩容
         */
        String EXPAND = "expand";

        /**
         * delete 删除
         */
        String DELLETE = "delete";
    }

    /**
     * 磁盘状态
     */
    interface ResVdStatus {

        /**
         * creating 创建中
         */
        String CREATING = "creating";


        /**
         * setting 配置中
         */
        String SETTING = "setting";

        /**
         * setting 恢复中
         */
        String RECOVERING = "recovering";

        /**
         * normal 正常
         */
        String NORMAL = "normal";

        /**
         * failure 故障
         */
        String FAILURE = "failure";

        /**
         * deleting 删除中
         */
        String DELETING = "deleting";

        /**
         * deleted 删除
         */
        String DELETED = "deleted";

        /**
         * modifying 修改
         */
        String MODIFYING = "modifying";

        /**
         * 错误的硬盘
         */
        String ERROR = "error";
    }

    interface NetworkStatus {
        // 可用
        String ACTIVE = "ACTIVE";
        // 不可用
        String INACTIVE = "INACTIVE";
        // 创建中
        String CREATING = "CREATING";
        // 删除中
        String DELETING = "DELETING";
        // 创建失败
        String CREATE_FAILURE = "CREATE_FAILURE";
        // 创建失败
        String STOPPED = "STOPPED";
        //未使用
        String UNUSED = "UNUSED";
        //绑定中
        String BANDING = "BANDING";
        //绑定中
        String UNBANDING = "UNBANDING";
        //已删除
        String DELETED = "DELETED";
        //预占用
        String RESERVED = "RESERVED";
    }

    /**
     * 磁盘类型
     */
    interface ResVdDiskType {

        /**
         * 01 系统盘
         */
        String SYSTEM_DISK = "01";

        /**
         * 02 非系统盘
         */
        String NO_SYSTEM_DISK = "02";


    }

    /**
     * 磁盘状态
     */
    interface ResVmNetworkStatus {

        /**
         * occuping 预占中
         */
        String OCCUPING = "occuping";

        /**
         * creating 创建中
         */
        String CREATING = "creating";

        /**
         * setting 配置中
         */
        String SETTING = "setting";

        /**
         * normal 正常
         */
        String NORMAL = "normal";

        /**
         * failure 故障
         */
        String FAILURE = "failure";

        /**
         * deleting 删除中
         */
        String DELETING = "deleting";

        /**
         * deleted 删除
         */
        String DELETED = "deleted";

    }


    /**
     * 网络类型
     */
    interface ResNetworkType {

        /**
         * 01 内部
         */
        String PRIVATE = "01";

        /**
         * 02 外部
         */
        String PUBLIC = "02";

        /**
         * 03 自定义
         */
        String CUSTOM = "03";
    }

    /**
     * 实例规格状态
     */
    interface SpecStatus {

        /**
         * 0 处理中
         */
        String changing = "0";

        /**
         * 1 有效
         */
        String valid = "1";

        /**
         * 2 无效
         */
        String invalid = "2";
    }

    interface IdcConstants {

        /**
         * 服务订单_合同号
         */
        String CONTRACTID = "IDC000000";
    }

    interface IdcDispatchCode {

        /**
         * IDC接口反馈成功
         */
        String SUCCESS = "000000";

        /**
         * IDC接口反馈失败
         */
        String FAILURE = "000001";
    }

    interface IdcServCode {

        /**
         * 勘查
         */
        String CHECK = "20001";

        /**
         * 开通
         */
        String OPEN = "20002";

        /**
         * 撤单
         */
        String CANCEL = "20005";

        /**
         * 退订
         */
        String UNSUBSCRIBE = "20010";

        /**
         * 同步
         */
        String SYNC = "20011";

        /**
         * 操作
         */
        String OPERATE = "20013";

        /**
         * 故障
         */
        String INCIDENT = "20014";
    }

    interface IdcVmOpType {

        /**
         * 增加
         */
        Long ADD = 1L;

        /**
         * 删除
         */
        Long DELETE = 2L;

        /**
         * 变更
         */
        Long CHANGE = 3L;
    }

    /**
     * 问题级别
     */
    interface QuestionLevel {

        String BEST_LOW = "01";

        String LOW = "02";

        String NORMAL = "03";

        String HIGH = "04";

        String BEST_HIGH = "05";
    }

    /**
     * 服务变更记录状态
     */
    interface ServiceChangeStatus {

        Long NOT_CHANGE = 0L;

        Long CHANGED = 1L;

        Long FAIL_CHANGE = 2L;

        Long CHANGE_DISAGREE = 3L;
    }

    /**
     * 资源池配置Key
     */
    interface ResPoolConfigKey {

        /**
         * 资源分配策略
         */
        String ALLOCATIONPOLICY = "allocation_policy";

        /**
         * 资源分配方式
         */
        String ALLOCATIONMODE = "allocation_mode";

        /**
         * 资源分配比率
         */
        String ALLOCATIONRATE = "allocation_rate";

        /**
         * 资源可分配阈值
         */
        String ALLOCATIONTHRESHOLD = "allocation_threshold";
    }


    /**
     * 资源池配置Key
     */
    interface ResVsType {

        /**
         * 01 分布式交换机
         */
        String DISTRIBUTE_VS = "01";

        /**
         * 02 标准交换机
         */
        String STANDARD_VS = "02";

    }

    /**
     * 资源池配置Key
     */
    interface ResConfig {

        /**
         * 资源环境类型
         */
        String RES_ENV_TYPE = "res_env_type";
        /**
         * 资源环境ID
         */
        String RES_ENV_ID = "res_env_id";
        /**
         * Openstack区域名称
         */
        String REGION_NAME = "region_name";

    }

    /**
     * 天馈接口常数
     */
    interface TKMonitorNode {

        String OPERATE_ADD = "0";

        String OPERATE_UPDATE = "1";

        String OPERATE_DEL = "2";

        String STATUS_UNMONITORED = "未监控";

        String STATUS_MONITORED = "已监控";

    }

    /**
     * 主网卡标识常数
     */
    interface NetPrimary {

        /**
         * P 主网卡
         */
        String P = "P";

    }

    /**
     * 是否回调服务层
     */
    interface NeedCallbackService {

        /**
         * true 是
         */
        boolean YES = true;

        /**
         * false 否
         */
        boolean NO = false;

    }

    /**
     * 工单处理类型
     */
    interface TicketProcessType {

        /**
         * 01 VM订单开通
         */
        String VM_OPEN = "01";

        /**
         * 02 VM服务退订
         */
        String VM_CANCEL = "02";

        /**
         * 03 EXCHANGE开通
         */
        String EXCHANGE_OPEN = "03";

        /**
         * 04 EXCHANGE退订
         */
        String EXCHANGE_CANCEL = "04";

        /**
         * 05 SHAREPOINT开通
         */
        String SHAREPOINT_OPEN = "05";

        /**
         * 06 SHAREPOINT退订
         */
        String SHAREPOINT_CANCEL = "06";

        /**
         * 07 DEPLOYMENT开通
         */
        String DEPLOYMENT_OPEN = "07";

        /**
         * 08 DEPLOYMENT退订
         */
        String DEPLOYMENT_CANCEL = "08";

        /**
         * 07 存储开通
         */
        String STORAGE_OPEN = "09";

        /**
         * 08 存储退订
         */
        String STORAGE_CANCEL = "10";

        /**
         * 11 闲置资源回收
         */
        String FREE_RESOURCE_REDUCE = "11";

        /**
         * 12 实例变更
         */
        String INSTANCE_ADJUST = "12";

        /**
         * 13   磁盘缩容
         */
        String DISK_REDUCE = "13";

        /**
         * 14 对象存储开通
         */
        String OBJECT_STORAGE_OPEN = "14";

        /**
         * 15 弹性IP开通
         */
        String FLOATING_IP_OPEN = "15";

        /**
         * 16 对象存储退订
         */
        String OBJECT_STORAGE_CANCEL = "16";

        /**
         * 17 弹性IP退订
         */
        String FLOATING_IP_CANCEL = "17";

        /**
         * 18 弹性IP退订
         */
        String CDN_OPEN = "18";

        /**
         * 19 弹性IP退订
         */
        String CDN_CANCEL = "19";

        /**
         * 20 块存储扩容
         */
        String STORAGE_EXPAND = "20";

    }

    /**
     * 配置类型
     **/
    interface ConfigType {

        /**
         * 首页图的显示
         */
        String INDEX_CHART = "indexshow_config";

        /**
         * 首页资源配置
         */
        String RES_CONFIG = "res_config";

        /**
         * 邮件地址配置
         */
        String EMAIL_ADDRESS = "email_config";

        /**
         * 其他
         */
        String OTHER = "other_config";

        /**
         * 通知
         */
        String NOTIFY = "notify_config";

    }

    /**
     * 扫描虚拟化环境时，标识虚拟机变化
     **/
    interface scanVmChangeType {

        /**
         * 虚拟机变化
         */
        String CHANGE = "change";

        /**
         * 虚拟机删除
         */
        String DELETE = "delete";

    }

    /**
     * IDC订单状态
     */
    interface IdcOrderStatus {

        /**
         * 已撤销
         */
        Long CANCELED = 0L;

        /**
         * 待审核
         */
        Long NOT_APPROVE = 1L;

        /**
         * 审核通过
         */
        Long APPROVED_PASS = 2L;

        /**
         * 审核拒绝
         */
        Long APPROVED_REJECTED = 3L;

        /**
         * 待开通
         */
        Long WAIT_OPEN = 4L;

        /**
         * 处理中
         */
        Long PROCESSING = 5L;

        /**
         * 处理完成
         */
        Long COMPLETED = 6L;

        /**
         * 已反馈
         */
        Long FEEDBACKED = 7L;

    }

    /**
     * IDC订单明细状态
     */
    interface IdcOrderDetailStatus {

        /**
         * 待处理
         */
        Long PENDING = 1L;

        /**
         * 处理中
         */
        Long PROCESSING = 2L;

        /**
         * 处理成功
         */
        Long SUCCESS = 3L;

        /**
         * 处理失败
         */
        Long FAILURE = 4L;

    }

    /**
     * IDC操作记录状态
     */
    interface IdcServiceRecordStatus {

        /**
         * 待处理
         */
        Long PENDING = 0L;

        /**
         * 已处理
         */
        Long COMPLETED = 1L;
    }

    /**
     * IDC操作类型
     */
    interface IdcServiceRecordOpType {

        /**
         * 勘察
         */
        Long CHECK = 1L;

        /**
         * 开通
         */
        Long OPEN = 2L;

        /**
         * 撤单
         */
        Long CANCEL = 3L;

        /**
         * 退订
         */
        Long UNSUBSCRIBE = 4L;
    }

    /**
     * IDC订单类型
     */
    interface IdcOrderType {

        /**
         * 开通
         */
        Long OPEN = 1L;

        /**
         * 变更
         */
        Long CHANGE = 2L;

        /**
         * 退订
         */
        Long UNSUBSCRIBE = 3L;
    }

    /**
     * IDC-故障申报状态
     */
    interface IdcIncidentStatus {

        /**
         * 未处理
         */
        String UNHANDLE = "01";

        /**
         * 处理中
         */
        String HANDLING = "02";

        /**
         * 已处理
         */
        String HANDLED = "03";

        /**
         * 已反馈
         */
        String FEEDBACKED = "04";

        /**
         * 已关闭
         */
        String CLOSED = "99";
    }

    /**
     * BIZ属性
     */
    interface BIZ_TYPE {

        /**
         * 1 自有合作
         */
        Long COOPERATION = 1L;

        /**
         * 2内容引入
         */
        Long CONTENT = 2L;

        /**
         * 3 创新业务
         */
        Long INNOVATION = 3L;
    }

    /**
     * IDC-处理结果反馈状态
     */
    interface IdcFeedbackStatus {

        /**
         * 解决
         */
        String SOLVED = "1";

        /**
         * 未解决
         */
        String UNRESOLVED = "2";

        /**
         * 撤销
         */
        String REVOKE = "3";
    }

    /**
     * 重启类型
     */
    interface RebootType {

        /**
         * 软重启
         */
        String SOFT = "SOFT";

        /**
         * 硬重启
         */
        String HARD = "HARD";

    }

    /**
     * 管理对象类型
     */
    interface MgtObjType {

        /**
         * 个人
         */
        Long PERSON = 1L;

        /**
         * 企业
         */
        Long ENTERPRISE = 2L;

        /**
         * 企业
         */
        String COMPANY = "company";
    }

    /**
     * 管理对象分组ID
     */
    interface MgtObjGroupId {

        /**
         * 个人
         */
        Long PERSON = 112L;

        /**
         * 企业
         */
        Long ENTERPRISE = 111L;
    }

    /**
     * 备份类型
     */
    interface BACKUP_TYPE {

        /**
         * 01 快照
         */
        String SNAPSHOT = "01";

        /**
         * 02 备份
         */
        String BACKUP = "02";
    }

    /**
     * 快照状态
     **/
    interface BACKUP_STATUS {

        /**
         * 01 创建中
         */
        String CREATING = "creating";

        /**
         * 02 创建完成
         */
        String CREATE_SUCCESS = "normal";

        /**
         * 03  删除中
         */
        String DELETING = "deleting";

    }

    /**
     * 网络状态
     */
    interface NETWORK_STATUS {

        /**
         * 01 创建中
         */
        String CREATING = "01";

        /**
         * 02 创建成功
         */
        String CREATE_SUCCESS = "02";

        /**
         * 03 创建失败
         */
        String CREATE_FAILURE = "03";

        /**
         * 04 删除中
         */
        String REMOVING = "04";

        /**
         * 05 删除失败
         */
        String REMOVE_FAILURE = "05";

    }

    /**
     * 伸缩组类型
     */
    interface ELASTIC_GROUP_TYPE {

        /**
         * 01 基于告警
         */
        String BASE_ALARM = "01";

    }

    /**
     * 对象存储状态
     */
    interface ResObjStorageInstStatus {

        /**
         * creating 创建中
         */
        String CREATING = "creating";

        /**
         * normal 创建成功
         */
        String NORMAL = "normal";

    }

    /**
     * 路由器状态
     */
    interface ResRouterStatus {

        /**
         * creating 创建中
         */
        String CREATING = "creating";

        /**
         * normal 创建成功
         */
        String NORMAL = "normal";

        /**
         * pending 创建中
         */
        String PENDING = "pending";

    }


    /**
     * 路由器接口
     */
    interface ResRouterInterface {

        /**
         * router_interface 内部接口
         */
        String ROUTERINTERFACE = "network:router_interface";

        /**
         * router_gateway 外部网关
         */
        String ROUTERGATEWAY = "network:router_gateway";

        /**
         * inner 内部接口
         */
        String INNER = "inner";

        /**
         * public 外部网关
         */
        String PUBLIC = "public";

        /**
         * failure 创建失败
         */
        String FAILURE = "failure";

        /**
         * pending 创建中
         */
        String PENDING = "pending";

        /**
         * Custom 用户自定义
         */
        String CUSTOM = "Custom";
    }


    /**
     * 对象存储状态
     */
    interface ResFloatingIpStatus {

        /**
         * creating 创建中
         */
        String CREATING = "creating";

        /**
         * normal 创建成功
         */
        String NORMAL = "normal";

        /**
         * deleting 创建成功
         */
        String DELETING = "deleting";

        /**
         * deleted 创建成功
         */
        String DELETED = "deleted";

        /**
         * error 异常
         */
        String ERROR = "error";
    }

    /**
     * 安全组状态
     **/
    interface SECURITY_GROUP_STATUS {

        /**
         * 01 创建中
         */
        String CREATING = "creating";

        /**
         * 02 创建完成
         */
        String CREATE_SUCCESS = "normal";

        /**
         * 03  删除中
         */
        String DELETING = "deleting";

    }


    /**
     * CDN结果
     **/
    interface CdnResult {

        /**
         * 0 成功
         */
        String SUCCESS = "0";

        /**
         * 1 已启用或已停用
         */
        String OPERATED = "1";

        /**
         * 2 失败
         */
        String FAILED = "2";
    }

    /**
     * CDN实例状态
     **/
    interface CdnInstStatus {

        /**
         * normal 正常
         */
        String NORMAL = "normal";

        /**
         * deleted 已退订
         */
        String DELETED = "deleted";

    }


    interface instanceChangeType {

        /**
         * 创建
         */
        String CREATE = "1";

        /**
         * 变更
         */
        String CHANGE = "2";

        /**
         * 退订
         */
        String UNSUBSCRIBE = "3";

        /**
         * 项目变更项目经理
         */
        String CHANGE_MANAGER = "4";

        /**
         * 虚机变更项目
         */
        String CHANGE_MGT_OBJ = "5";
    }

    interface instanceChangeStatus {

        /**
         * 待变更
         */
        String UNCHANGE = "1";

        /**
         * 变更中
         */
        String CHANGEING = "2";

        /**
         * 变更完成
         */
        String CHANGED = "3";

        /**
         * 变更取消
         */
        String CANCELED = "0";
    }

    /**
     * 计费时，不满一月是否按照一月计算
     */
    interface IS_BILLING_MONTH {

        /**
         * 0  是否
         */
        int NO = 0;

        /**
         * 1 创建完成
         */
        int YES = 1;

    }

    /**
     * 管理对象状态
     */
    interface MGT_OBJ_STATUS {

        /**
         * 01  待审核
         */
        String UNAPPROVE = "01";

        /**
         * 02  正常
         */
        String NORMAL = "02";

        /**
         * 03  禁用
         */
        String DISABLE = "03";

        /**
         * 04  变更中
         */
        String SETTING = "04";

    }

    /**
     * 项目资源回收方式
     */
    interface RECOVERY_REMARK_TYPE {

        /**
         * 1  到期提醒
         */
        String NOTICE = "1";

        /**
         * 2 到期回收
         */
        String RECOVER = "2";

    }

    /**
     * 配额类型
     */
    interface QUOTA_TYPE {

        /**
         * Linux,Windows
         */
        String X86 = "Linux,Windows";

        /**
         * AIX
         */
        String AIX = "AIX";


    }

    interface OS_TYPE {

        /**
         * Windows系列操作系统
         */
        String WINDOWS = "Windows";

        /**
         * Linux系列操作系统
         */
        String LINUX = "Linux";

        /**
         * AIX系列操作系统
         */
        String AIX = "AIX";

    }

    interface WindowsType {

        /**
         * Windows2003系列操作系统
         */
        String WIN_2003 = "2003";

        /**
         * Windows2008系列操作系统
         */
        String WIN_2008 = "2008";

        /**
         * Windows2012系列操作系统
         */
        String WIN_2012 = "2012";

        /**
         * Windows2016系列操作系统
         */
        String WIN_2016 = "2016";

    }

    /**
     * 主机是否安装VIOS
     *
     * @author Maochaohong
     */
    interface VirtualIoServerCapable {

        /**
         * 1 是
         */
        String YES = "1";

        /**
         * 0 否
         */
        String NO = "0";

    }

    /**
     * 主机是否安装VIOS
     *
     * @author Maochaohong
     */
    interface HostItemTypeCode {

        /**
         * 1 本地盘
         */
        String LOCAL_DISK = "1";

        /**
         * 2 光纤卡
         */
        String OPTICAL_CARD = "2";

        /**
         * 3 网卡
         */
        String NETWORK_CARD = "3";

        /**
         * ethernet 网卡端口
         */
        String ETHERNET = "Ethernet";

        /**
         * Disk 挂盘信息
         */
        String DISK = "Disk";

        /**
         * FC FC信息
         */
        String FC = "FC";

    }

    /**
     * 配件资源分配状态标识
     *
     * @author Maochaohong
     */
    interface ResHostItemAllocFlag {

        /**
         * 0 未占用
         */
        String NOT_OCCUPIED = "1";

        /**
         * 1 已占用
         */
        String OCCUPIED = "2";

    }

    /**
     * 配件资源分配状态
     */
    interface HostItemAllocStatus {

        /**
         * 0 未占用
         */
        int FREE = 0;

        /**
         * 1 已占用
         */
        int OCCUPY = 1;

    }

    interface resourceTopologyType {

        /**
         * X86虚拟机化资源池
         */
        String PCVX = "PCVX";

        /**
         * X86非虚拟机资源池
         */
        String PCX = "PCX";

        /**
         * Power虚拟化资源池
         */
        String PCVP = "PCVP";

        /**
         * Power非虚拟机化资源池
         */
        String PCP = "PCP";
    }

    /**
     * 工单类型
     */
    interface ticketType {

        /**
         * 日常维护工单
         */
        String DAILY_MAINTENANCE_TICKET = "01";

        /**
         * 云主机自动开通失败工单
         */
        String VM_AUTO_OPEN_FAILURE_TICKET = "02";

        /**
         * 物理机开通工单
         */
        String HOST_OPEN_TICKET = "03";

        /**
         * AIX系统外置盘开通工单
         */
        String AIX_DISK_OPEN_TICKET = "04";

        /**
         * AIX系统外置盘卸载工单
         */
        String AIX_DISK_REMOVE_TICKET = "05";

        /**
         * 软件安装工单
         */
        String SOFTWARE_INSTALL_TICKET = "06";

        /**
         * 云主机自动回收失败工单
         */
        String VM_AUTO_UNSUBSCRIBE_FAILURE_TICKET = "07";

        /**
         * 云主机自动变更失败工单
         */
        String VM_AUTO_CHANGE_FAILURE_TICKET = "08";

        /**
         * 应用开通工单
         */
        String VM_SOFTWARE_SERVICE_TICKET = "09";

        /**
         * 应用关闭工单
         */
        String CLOSE_VM_SOFTWARE_SERVICE_TICKET = "10";

        /**
         * 试用应用开通工单
         */
        String TRIAL_APP_OPEN_TICKET = "11";

        /**
         * 试用应用关闭工单
         */
        String TRIAL_APP_CLOSE_TICKET = "12";


    }

    /**
     * 终审资源类型
     */
    interface ResType {

        /**
         * 虚拟机
         */
        String VM = "1";

        /**
         * 主机
         */
        String HOST = "2";

    }

    /**
     * 虚拟机化环境
     */
    interface VirtualEnv {

        String X86 = "VMware";


        String POWER = "HMC,IVM";

    }

    /**
     * 操作系统软件状态
     */
    interface OsSoftwareStatus {

        /**
         * 待安装
         */
        String WAITING = "01";

        /**
         * 安装中
         */
        String PROCESSING = "02";

        /**
         * 已安装
         */
        String INSTALLED = "03";

        /**
         * 异常
         */
        String EXCEPTION = "99";

    }

    /**
     * Power分区类型
     */
    interface PowerPartitionType {

        /**
         * Lpar 物理分区
         */
        String LPAR = "0";

        /**
         * Mpar 虚拟分区
         */
        String MPAR = "1";
    }

    /**
     * 主机是否有VIOS 01:"否"；"02":"是"
     */
    interface IsViosFlag {

        /**
         * The NO.
         */
        String NO = "01";

        /**
         * The YES.
         */
        String YES = "02";
    }

    /**
     * 文件系统
     */
    interface FileSystem {

        String JSF2 = "JSF2";

        String NTFS = "NTFS";

        String EXT4 = "ext4";
    }

    /**
     * 磁盘类型 MQ参数
     */
    interface VmDiskType {

        /**
         * 系统盘
         */
        String SYS_DISK = "sysDisk";
        /**
         * 数据盘
         */
        String DATA_DISK = "dataDisk";
    }

    /**
     * 审核的分配方式
     */
    interface AllocateType {

        /**
         * 创建
         */
        String CREATE = "1";
        /**
         * 纳管
         */
        String NANOTUBE = "2";
    }

    /**
     * 账户状态
     */
    interface ACCOUNT_STATUS {

        /**
         * 01 激活
         */
        String ACTIVE = "01";

        /**
         * 02 未激活
         */
        String INACTIVE = "02";

        /**
         * 03 冻结
         */
        String FREEZE = "03";
    }

    /**
     * 账户类型
     *
     * @author chxiaoqi
     */
    interface ACCOUNT_TYPE {

        /**
         * 01 个人
         */
        String PERSONAL = "01";

        /**
         * 02 企业
         */
        String ENTERPRISE = "02";

        /**
         * 03 管理员
         */
        String ADMINISTRATION = "03";
    }

    /**
     * 资源释放方式
     *
     * @author Chaohong.Mao
     */
    interface ReleaseMode {

        /**
         * 随实例释放
         */
        String WITH_INSTANCE = "1";
        /**
         * 单独释放
         */
        String STAND_ALONE = "2";
    }

    /**
     * 充值支付状态
     */
    interface RECHARGE_STATUS {

        /**
         * 0未支付
         */
        String NO_PAY = "0";

        /**
         * 1已支付
         */
        String PAYED = "1";
    }

    /**
     * 币种
     */
    interface CURRENCY {

        /**
         * 01 RMB
         */
        String RMB = "01";

        /**
         * 02 USD
         */
        String USD = "02";

    }

    /**
     * 充值方式
     */
    interface CHARGE_CHANNEL {

        /**
         * 01 支付宝
         */
        String ALIPAY = "01";

        /**
         * 02 礼品卡
         */
        String HYCARD = "02";

        /**
         * 03 银行转账
         */
        String BANK = "03";

        /**
         * 04 微信充值
         */
        String WEIXIN = "04";

        /**
         * 06 直充
         */
        String ZHICHONG = "05";
    }

    interface ACTION_LEVEL {

        String LEVEL1 = "1";  //基础操作级
        String LEVEL2 = "2";  //业务操作级
    }

    interface ACT_TARGET {
        String USER_MANAGEMENT = "01";
        //用户中心
        String USER_CENTER = "02";
        //订单管理
        String ORDER_MANAGEMENT = "03";
        //云主机
        String VM_MANAGEMENT = "04";
        //弹性公网IP
        String EIP_MANAGEMENT = "05";
        //云硬盘
        String VD_MANAGEMENT = "06";
        //应用订购
        String CLOUD_APP = "07";
    }

    interface CHARGE_UNIT_ZH {
        String RMB = "元";
        String DOLLOR = "美元";
    }


    /**
     * 云硬盘操作
     */
    interface CBS_OPERATION {
        String ATTACH = "attach";
        String DETACH = "detach";
        String RELEASE = "release";
        String EXPAND = "expand";
    }

    interface DiskType {
        /**
         * 普通硬盘
         */
        String NORMAL_DISK = "normal";
        /**
         * SSD硬盘
         */
        String SSD_DISK = "ssd";
    }

    interface INVOICE {

        //发票状态
        String STATUS_UNOPEN = "0";//未开
        String STATUS_OPEN = "1";//已开

        //发票是否寄送
        String SEND_NO = "0";//否
        String SEND_YES = "1";//是

        //发票类型
        String UNIVERSAL = "00";//增值税普通发票
        String SPECIAL = "01";//增值税专用发票
    }

    interface OrderedAppStatus {
        /**
         * 开通中
         */
        String INPROCESS = "0";

        /**
         * 以开通
         */
        String DONE = "1";
    }

    interface QuestionType {
        /**
         * 咨询
         */
        String CONSULT = "01";
        /**
         * 建议
         */
        String ADVISE = "02";
        /**
         * 故障
         */
        String FAULT = "03";
        /**
         * 其他
         */
        String OTHER = "99";
    }

    interface QuotaType {
        /**
         * 单服务额度类型
         */
        int SINGLE_SERVICE_TYPE = 0;
        /**
         * 总服务额度类型
         */
        int TOTAL_SERVICE_TYPE = 1;

        /**
         * 混合服务额度类型
         */
        int BLEND_SERVICE_TYPE = 2;
    }

    interface SpecType {
        /**
         * 0：固定值规格项
         */
        int VALUE_SPEC = 0;

        /**
         * 1：非固定值规格项
         */
        int QUEASY_VALUE_SPEC = 1;
    }

    interface AppSid {
        /**
         * 云会议室
         */
        int CLOUD_MEETING = 1003;

        /**
         * 企业建站
         */
        int VPS = 1004;

        /**
         * 网盘
         */
        int E_BOX = 1005;
    }

    interface IsAutoOpen {

        /**
         * 工单开通
         */
        int TICKET_OPEN = 1;

        /**
         * 自动开通
         */
        int AUTO_OPEN = 0;
    }

    interface SalesCustomerType {
        /**
         * 管理员角色
         */
        String ADMIN_TYPE = "01";

        /**
         * 一般角色
         */
        String NORMAL_TYPE = "02";
    }

    interface SalesCustomerStatus {
        /**
         * 新增
         */
        String NEW_CREATE = "01";

        /**
         * 变更
         */
        String CHANGED = "02";

        /**
         * 正常
         */
        String NO_CHANGED = "03";

        /**
         * 删除
         */
        String REMOVED = "04";

        /**
         * 处理中
         */
        String HANDLING = "05";
    }

    interface AttachmentsType {
        /**
         * 文档
         */
        String DOCUMENT = "1";
        /**
         * 音频
         */
        String AUDIO = "2";
        /**
         * 视频
         */
        String VIDEO = "3";
    }

    interface MessageReadFlag {
        /**
         * 已读
         */
        String READ = "01";
        /**
         * 未读
         */
        String UNREAD = "02";
    }

    interface MessageVisible {
        /**
         * 可见
         */
        Integer VISIBLE = 1;
        /**
         * 不可见
         */
        Integer INVISIBLE = 0;
    }

    interface TRIAL_REQUEST_STATUS {
        /**
         * 未处理
         */
        String UNTREATED = "00";
        /**
         * 已处理
         */
        String TREATED = "01";
        /**
         * 已释放
         */
        String RELEASED = "02";
    }

    interface SERVICE_CATALOG_SID {
        /**
         * 企业应用
         */
        Long ENTERPRICE_APP = 1023L;
    }

    interface MsgType {
        // 公告
        String ANNOUNCEMENT = "01";
        // 产品更新
        String PRODUCT_UPGRADE = "02";
        // 故障通知
        String ERROR_NOTIFY = "03";
        // 通知
        String NOTICE = "04";
    }

    interface ServiceConfigName {
        // 应用信息模板
        String TRIAL_APP_INFO_TEMPLATE = "TrialAppInfoTemplate";
        // 应用信息模板
        String APP_INFO_TEMPLATE = "AppInfoTemplate";
    }

    interface UserExpireConfig {
        /**
         * 用户到期 | 即将到期邮件发送频率
         */
        String MAIL_USER_EXPIRE_FREQUENCY = "mail.user.expire.frequency";

        /**
         * 用户到期 | 到期后邮件发送频率
         */
        String MAIL_USER_EXPIRED_FREQUENCY = "mail.user.expired.frequency";

        /**
         * 用户到期 | 检查期限
         */
        String MAIL_USER_EXPIRE_CHECK_DAYS = "mail.user.expire.check.days";

        /**
         * 用户到期 | 用户提示邮件开关
         */
        String MAIL_USER_EXPIRE_SEND = "mail.user.expire.send";

        /**
         * 用户到期 | 管理员提示邮件开关
         */
        String MAIL_USER_EXPIRE_ADMIN_SEND = "mail.user.expire.admin.send";
    }

    interface AnsibleMqConfig {
        /**
         * platform rclink id default config
         */
        String ANSIBLE_PLATFORM_RCLINK_ID = "platform";

        /**
         * ansible exchange prefix
         */
        String ANSIBLE_EXCHANGE_PREFIX = "ansible.control.";

        /**
         * routing_key node control script
         */
        String NODE_CONTROL_ROUTING_KEY_INCOMPLETE = ".node.control.";

        /**
         * routing_key gp control script
         */
        String GP_CONTROL_ROUTING_KEY_INCOMPLETE = ".gp.control.";

        /**
         * routing_key node control script
         */
        String APP_OPERATION_ROUTING_KEY_INCOMPLETE = ".app.operation.";

        /**
         * routing_key cluster incomplete
         */
        String CLUSTER_CONTROLROUTING_KEY_INCOMPLETE = ".cluster.control.";
    }

    interface AnsibleTaskTypeConfig {
        String NODE_CONTROL_AGENT = "agent";

        String NODE_CONTROL_MONITOR = "monitor";

        String NODE_CONTROL_DOCKER = "docker";

        String NODE_CONTROL_UNINSTALL_AGENT = "uninstall";

        String NODE_CONTROL_SCRIPT = "script";

        String APP_OPS_RELEASE_CONTAINER = "release.container";

        String APP_OPS_RELEASE_NATIVE = "release.native";

        String APP_OPS_RELEASE_COMPONENT = "release.component";

        String APP_OPS_RELEASE_K8SNODE = "release.k8snode";

        String APP_OPS_STOP_CONTAINER = "stop.container";

        String APP_OPS_STOP_NATIVE = "stop.native";

        String APP_OPS_START_CONTAINER = "start.container";

        String CLUSTRT_CONTROL_DEPLOY = "deploy";
    }

    interface DeployConst {
        String ANSIBLE_PLAYBOOKS_CHANNEL = "ansible_playbooks_channel";

        String DEPLOY_APP_CHANNEL = "deploy_app_channel";

        String K8S_CLUSTER_CHANNEL = "k8s_cluster_channel";

        String ANSIBLE_HEALTH_CHECK_CHANNEL = "ansible_healthcheck";

        String HOST_LOG_KEY_PREFIX = "log:host:";

        String APP_LOG_KEY_PREFIX = "log:app:";

        String TASK_LOG_KEY_PREFIX = "log:task:";

        String CLUSTER_LOG_KEY_PREFIX = "log:cluster:";

        String CLOUD_DEPLOY_HOST = "cloud:deploy:host";

        String CLOUD_DEPLOY_APP = "cloud:deploy:app";

        String TASK_LOG_KEY_PREFIX_EXPIRE = "log:task:expre";

        String APP_LOG_KEY_PREFIX_EXPIRE = "log:app:expre";

        String HOST_PORT_KEY_PREFIX = "port:host:";

    }

    interface CloudEnvType {
        String ALIYUN = "Aliyun";
        String QCLOUD = "Qcloud";
        String AWS = "Aws";
        String OPEN_STACK = "OpenStack";
        String ES_CLOUD = "ESCloud";
        String VMWARE = "VMware";
        String POWER_VC = "PowerVC";
    }

    interface CloudEnvImageOwnerAlias {
        String PUBLIC = "public";
        String SELF = "self";
    }

    interface CloudEnvApiKey {
        String API_KEY = "apiKey";
        String SECURE_TOKEN = "secureToken";
        String ENV_ACCOUNT = "envAccount";
    }

    interface CloudEnvTenantKey {
        String MANAGEMENT_URL = "managementUrl";
        String MANAGEMENT_TENANT = "managementTenant";
        String MANAGEMENT_USER = "managementUser";
        String MANAGEMENT_PASSWORD = "managementPassword";
        String PROVIDER_URL = "providerUrl";
        String TENANT_NAME = "tenantName";
        String TENANT_ID = "tenantId";
        String TENANT_USER_NAME = "tenantUserName";
        String TENANT_USER_PASS = "tenantUserPass";
        String ENV_ACCOUNT = "envAccount";
        String REGION = "region";
        String COMPANY = "company";
    }

    interface CloudEnvStatus {
        String NORMAL = "normal";
        String INACTIVE = "inactive";
        String ERROR = "error";
        String DISCONNECT = "disconnect";
    }

    interface CloudHostModuleStatus {
        String PENDING = "pending";
        String NONE = "none";
        String RUNNING = "running";
        String INSTALLING = "installing";
        String FAILURE = "failure";
    }

    interface CloudHostIOStatus {
        String OPTIMIZED = "optimized";
        String NONE = "none";
    }

    interface CloudHostStatus {
        String PENDING = "pending"; //待创建;
        String CREATING = "creating"; //创建中
        String STARTING = "starting";//启动中
        String RUNNING = "running"; //运行中
        String STOPPING = "stopping"; //关机中
        String STOPPED = "stopped"; //已关机
        String DELETING = "deleting"; //删除中
        String DELETED = "deleted"; //已删除
        String SETTING = "setting"; //配置中
        String FAILURE = "failure"; //异常;
        String CREATE_FAILURE = "create_failure"; //创建失败;
        String INACTIVE = "inactive"; //待创建;
        String SUSPENDED = "suspended"; //已挂起;
        String PAUSED = "paused"; //暂停;
        String LOCKED = "locked"; //锁定
        String DEBLOCKING = "deblocking"; //解锁
        String TERMINATED = "terminated"; // aws已终止
        String SHUTTING_DOWN = "shutting-down"; // aws终止中
        String EXPIRED = "expired";//已过期
    }

    interface RdsStatus {
        //创建中
        String CREATING = "CREATING";

        //正常
        String ACTIVE = "ACTIVE";

        //正常
        String RUNNING = "RUNNING";

        //正常
        String NORMAL = "Normal";

        //重启中
        String RESTARTING = "RESTARTING";

        //创建失败
        String CREATE_FAILURE = "CREATE_FAILURE";

        //删除中
        String DELETING = "DELETING";

        //未锁定
        String UNLOCK = "Unlock";

        // 不存在
        String NULL = "The specified network type at least one.";
    }

    interface ResObject {
        //RDS
        String RDS = "rds";

        //CLOUD_ENV
        String CLOUD_ENV = "cloud_env";
    }

    interface StoppedMode {
        /**
         * 停机后继续收费
         */
        String KEEP_CHARGING = "KeepCharging";

        /**
         * 停机后不收费
         */
        String STOP_CHARGING = "StopCharging";

        /**
         * 支持停机不收费功能
         */
        String NOT_APPLICABLE = "Not-applicable";
    }

    interface ResRdsConnect {
        //public
        String PUBLIC = "Public";
    }

    interface CloudHostManageStatus {
        // 未接入
        String UNUNITED = "ununited";
        // 连接中
        String CONNECTING = "connecting";
        // 未连接
        String DISCONNECT = "disconnect";
        // 已连接
        String CONNECTED = "connected";
    }

    interface AppInstanceStatus {

        String DEPLOYING = "deploying";
        String STARTING = "starting";
        String RUNNING = "running";
        String DISPATCHING = "dispatching";
        String STOPPING = "stopping";
        String STOPPED = "stopped";
        String DELETING = "deleting";
        String DELETED = "deleted";
        String FAILURE = "failure";
        String SCALEUP = "scaleup";
    }

    interface PodStatus {
        /** 镜像下载失败 */
        String IMAGE_PULL_BACK_OFF = "ImagePullBackOff";

        /** 容器启动失败 */
        String CRASH_LOOP_BACK_OFF = "CrashLoopBackOff";
    }

    interface AnsibleServerMethod {
        /**
         * 执行对应playboos 方法
         */
        String EXEC = "exec";

        /**
         * 纳管方法
         */
        String CONTROL = "control";

        /**
         * 依赖执行
         */
        String DEPEND = "depend";

        /**
         * 应用移除方法
         */
        String RELEASE = "release";

        /**
         * 应用停止方法
         */
        String STOP = "stop";

        /**
         * 应用开始方法
         */
        String START = "start";

        /**
         * k8s集群部署调用接口
         */
        String K8S_CLUSTER_DEPLOY = "deploy/kubernetes";

        interface ControlApi {
            String AGENT = "control/agent";
            String MONITOR = "control/monitor";
            String DOCKER = "control/docker";
            String SCRIPT = "control/script";
            String UNINSTALL = "control/uninstall";
            String RELEASE = "release/component";
            String RELEASE_K8S_node = "release/k8snode";
        }
    }

    interface DeploymentType {
        /**
         * 容器方式
         */
        String CONTAINER = "container";
        /**
         * 本地方式
         */
        String NATIVE = "native";
        /**
         * 编排方式
         */
        String STACK = "stack";
    }

    interface DeploymentMode {
        /**
         * 主机模式
         */
        String HOST = "host";

        /**
         * 集群模式
         */
        String CLUSTER = "cluster";
    }

    interface AccessModel {
        /**
         * 内部服务
         */
        String INTERNAL = "internal";
        /**
         * 外部服务
         */
        String EXTERNAL = "external";
    }

    interface KubernetesKind {
        String SERVICE = "Service";
        String DEPLOYMENT = "Deployment";
        String PERSISTENT_VOLUME = "PersistentVolume";
        String PERSISTENT_VOLUME_CLAIM = "PersistentVolumeClaim";
        String REPLICATIONCONTROLLER = "ReplicationController";
        String POD = "Pod";
        String REPLICASETS = "ReplicaSets";
    }

    interface AppConfigKey {
        String DEPLOYMENT_TYPE = "deployment_type";
        String APP_VERSION = "app_version";
    }

    /**
     * 可变参数名定义
     */
    interface PlayBooksExtra {
        /**
         * 容器env 固定参数名
         */
        String CONTAINER_ENVIRONMENT = "CONTAINER_ENVIRONMENT";

        /**
         * 脚本根目录固定参数名
         */
        String WOOK_ROOT_PATH = "work_root_path";

        /**
         * 容器镜像 固定参数名
         */
        String IMAGE_TAG = "IMAGE_TAG";

        /**
         * 容易镜像仓库地址 固定参数名
         */
        String REGISTRY = "REGISTRY";

        /**
         * 容器名称 固定参数
         */
        String CONTAINER_NAME = "CONTAINER_NAME";

        /**
         * 本地应用full version name 固定参数名
         */
        String NATIVEAPP_FULL_VERSION_NAME = "app_full_version_name";

        /**
         * 本地应用service app name 固定参数名
         */
        String NATIVEAPP_NAME = "navite_app_name";

        /**
         * 容器暴露端口固定参数名
         */
        String CONTAINER_EXPOSE = "CONTAINER_EXPOSE";

        /**
         * 容器映射端口 固定参数名
         */
        String CONTAINER_PORTS = "CONTAINER_PORTS";

        /**
         * 容器挂载 固定参数名
         */
        String CONTAINER_VOLUMES = "CONTAINER_VOLUMES";

        /**
         * 容器执行命令 固定参数名
         */
        String CONTAINER_COMMAND = "CONTAINER_COMMAND";

        /**
         * k8s master tls url
         */
        String ANSIBLE_K8S_MASTER_URL_TLS = "kubernetes_master_url_tls";

        /**
         * k8s 集群默认token
         */
        String ANSIBLE_K8S_CLUSTER_DEFAULT_TOKEN = "kubernetes_cluster_default_token";

        /**
         * etcd 地址参数
         */
        String ANSIBLE_ETCD_SERVERS = "etcd_servers";

        /**
         * 私钥地址
         */
        String PRIVATE_KEY = "private_key";

        /**
         * cluster_dns
         */
        String CLUSTER_DNS = "cluster_dns";

        /**
         * cluster_domain
         */
        String CLUSTER_DOMAIN = "cluster_domain";

        /**
         * hostname_override
         */
        String HOSTNAME_OVERRIDE = "hostname_override";

        /**
         * 集群node iface
         */
        String K8S_BIND_IFACE = "k8s_bind_iface";

        /**
         * openfalcon 监控参数
         */
        String OPEN_FALCON_HOST_NAME = "open_falcon_host_name";

        /**
         * openfalcon 监控参数
         */
        String OPEN_FALCON_HEART_BEAT_ADDR = "open_falcon_heart_beat_addr";

        /**
         * openfalcon 监控参数
         */
        String OPEN_FALCON_TRANSFER_ADDR = "open_falcon_transfer_addr";

        /**
         * openfalcon 监控参数
         */
        String OPEN_FALCON_TRANSFER_ADDR_HTTP = "open_falcon_transfer_addr_http";

        /**
         * 脚本自定义参数
         */
        String CUSTOM_SCRIPT_PARAMS = "custom_script_params";

        /**
         * boolean, following parameters will be ignored if false.
         */
        String DOCKER_REQUIRE_LOGIN = "DOCKER_REQUIRE_LOGIN";

        /**
         * url for registry
         */
        String DOCKER_REGISTRY = "DOCKER_REGISTRY";

        /**
         * username
         */
        String DOCKER_USER = "DOCKER_USER";

        /**
         * password
         */
        String DOCKER_PWD = "DOCKER_PWD";


    }

    /**
     * 部署任务类型
     */
    interface DeployTaskType {
        // 创建
        String CREATE_HOST = "createHost";
        // 重启
        String RESTART_HOST = "restartHost";
        // 删除
        String DELETE_HOST = "deleteHost";
        // 更改配置
        String RE_CONFIG_HOST = "reConfigHost";
        // 纳管
        String IMPORT_HOST = "importHost";
        // 安装监控组件
        String INSTALL_MONITOR = "installMonitor";
        // 安装容器组件
        String INSTALL_DOCKER = "installDocker";
        // 卸载监控组件
        String UNINSTALL_AGENT = "unInstallAgent";
        // 运行启动脚本
        String BOOT_SCRIPT = "bootScript";
        // 运行结束脚本
        String DECOMMISSION_SCRIPT = "decommissionScript";

        // 运行执行脚本
        String EXEC_SCRIPT = "execScript";
        // 部署应用
        String DEPOLY_APP = "depolyApp";
        // 释放应用
        String RELEASE_APP = "releaseApp";
        // 脱管主机
        String RELEASE_COMPONENT = "releaseComponent";
        // 集群任务
        String CLUSTER_SETUP = "clusterSetup";
        // 集群合并任务
        String CLUSTER_JOIN = "clusterJoin";
        // 环境同步
        String SYNC_ENV = "syncEnv";
    }

    /**
     * 部署任务状态
     */
    interface DeployTaskStatus {
        // 待执行
        String PENDING = "pending";
        // 运行中
        String RUNNING = "running";
        // 成功
        String SUCCESS = "success";
        // 失败
        String FAILURE = "failure";
        // 取消
        String CANCEL = "cancel";
        // 全部失败
        String ALL_FAILURE = "allfailure";

    }

    /**
     * 部署任务状态
     */
    interface DeployPlaybookStatus {
        // 运行中
        String RUNNING = "running";
        // 成功
        String SUCCESS = "success";
        // 失败
        String FAILURE = "failure";
    }

    interface EnvMessageFlag {
        String SYNC_ENV_COMPLETE_MESSAGE = "#Complete#";
    }

    /**
     * 部署任务状态
     */
    interface AnsibleMessageFlag {
        String NORMAL_SUCCESS_MESSAGE_FLAG = "#Success#";

        String NORMAL_FAILED_MESSAGE_FLAG = "#Failed#";

        String NODE_AGENT_MESSAGE_FLAG = "#agent#";

        String K8S_MASTER_MESSAGE_FLAG = "#k8sMaster#";

        String K8S_NODE_MESSAGE_FLAG = "#k8sNode#";

        String DOCKER_MESSAGE_FLAG = "#docker#";

        String MONITOR_MESSAGE_FLAG = "#monitor#";

        String APP_RELEASE_SUCCESS_FLAG = "#Release Success#";

        String APP_STOP_SUCCESS_FLAG = "#Stop Success#";

        String APP_START_SUCCESS_FLAG = "#Start Success#";

        String APP_CONTAINER_ID_FLAG = "#Container ID#";

        String RELEASE_COMPONENT = "#Release Component#";

        String UNREACHABLE_MESSAGE_FLAG = "#Unreachable#";

        String GROUP_TASK_FLAG = "#Group Task#";

        String SKIP_FLG = "skipped";
    }

    interface ConfigClass {
        /**
         * 隐藏
         */
        String FIXED = "fixed";

        /**
         * 可修改编辑
         */
        String MODIFY = "modify";

        /**
         * 用户自定义值
         */
        String CUSTOM = "custom";

        /**
         * 与modify一致，区别extra为平台使用,modify的值为应用使用
         */
        String EXTRA = "extra";
    }

    interface CloudOsPlatform {
        String UBUNTU = "Ubuntu";
        String CENT_OS = "CentOs";
        String OTHER = "Other";
    }

    interface AlarmSource {
        String OPEN_FALCON = "open-falcon";
        String PG_OS = "pgos";
    }

    interface AlarmType {
        String HOST = "host";
        String CONTAINER = "container";
    }

    interface AlarmStatusExpress {

        /**
         * 激活可使用状态
         */
        String ACTIVE = "active";

        /**
         * 未激活不可使用状态
         */
        String INACTIVE = "inactive";
    }

    interface AlarmDataStatus {

        /**
         * 未恢复
         */
        String PROBLEM = "PROBLEM";

        /**
         * 已恢复
         */
        String OK = "OK";
    }

    interface CloudInfoCacheKey {
        String REGION = "cloud:info:region";
        String ZONE = "cloud:info:zone";
    }

    interface MonitorInfoKey {
        String CPU_BUSY = "cpu.busy";
        String MEM_USED = "mem.memused.percent";
        String DISK_IO = "disk.io.write_bytes/device=xvda";
        String DISK_USED = "df.statistics.used.percent";
        String CACHE_MONITOR_KEY = "monitor:agent:host";
    }

    interface cloudEnvCategory {
        String CUSTOMER = "customer";
        String PLATFORM_TRIAL = "platform_trial";
        String PLATFORM_PRD = "platform_prd";
    }

    interface CloudHostNetworkType {
        String CLASSIC = "classic";
        String VPC = "vpc";
    }

    interface DBInstanceNetworkType {
        String CLASSIC = "classic";
        String VPC = "vpc";
    }

    interface RemoteLoginType {
        String BY_PASS = "ByPassword";
        String BY_KEY = "ByKeypair";
    }

    interface ClusterNodeType {
        String MASTER = "Master";
        String NODE = "Node";
    }

    interface ClusterStatus {
        String CREATING = "creating";
        String CONNECTING = "connecting";
        String CONNECTED = "connected";
        String NOT_CONNECTED = "notConnected";
    }

    interface ClusterCategory{
        String IMPORTED = "imported";
        String CREATED = "created";
    }

    interface ClusterNodeStatus {
        String CREATING = "creating";
        String RUNNING = "running";
        String NOT_READY = "notReady";
        String ERROR = "error";
    }

    interface DeployAppsStackClass {
        String PUBLIC = "public";
        String PRIVATE = "private";

    }

    interface AlarmMetric {
        /**
         * CPU利用率
         */
        String CPU_BUSY = "cpu.busy";
        /**
         * 内存使用率
         */
        String MEM_MEMUSED_PERCENT = "mem.memused.percent";
        /**
         * 存储使用率
         */
        String DF_BYTES_USED_PERCENT = "df.bytes.used.percent";
        /**
         * 网络入流量
         */
        String NET_IF_IN_BYTES = "net.if.in.bytes";
        /**
         * 网络出流量
         */
        String NET_IF_OUT_BYTES = "net.if.out.bytes";
    }

    interface FreeMarkerTemplateName {
        String NORMAL_CONTAINER_CONVERT_CLUSTER_SERVICE = "Container2ClusterService.ftl";
    }

    interface ImageHarbor {
        String URL = "image.harbor.url";
        String ADMIN = "image.harbor.admin";
        String PASSWD = "image.harbor.admin.passwd";
    }

    interface CloudEnvImageStatus {
        String ERROR = "error";
        String NOT_READY = "notReady";
        String OK = "ok";
        String NOT_CONFIG = "notConfig";
    }

    interface MQ_LOG_KEY {
        String CLUSTER = "log.cluster";
        String HOST = "log.host";
        String APP = "log.app";
    }

    interface CloudEnvExtType {
        String HOST = "host";
        String NETWORK = "network";
        String IMAGE = "image";
    }

    interface SelfServiceInstanceStatus {
        String ERROR = "error";
        String RUNNING = "running";
        String CREATING = "creating";
        String CHANGING = "changing";
        // 已删除
        String DELETED = "deleted";

        // 处理中
        String PROCESSING = "processing";

        // 退订中
        String UNSUBSCRIBING = "unsubscribing";

        // 已停止
        String STOPPED = "stopped";
    }

    interface ServerType {
        /* 实例*/ String INSTANCE = "instance";
        /* 主机*/ String SERVER = "server";
        /* 主机实例*/ String SERVER_INSTANCE = "serverInstance";
        /* 弹性伸缩组初始化配置*/ String ELASTIC = "elastic";
        /* 弹性伸缩组实例*/ String ELASTIC_INSTANCE = "elasticInstance";
    }

    interface PublishStatus {

        /**
         * 已发布
         */
        String publish = "publish";
        /**
         * 未发布
         */
        String unpublish = "unpublish";
    }



    interface ScriptCategory {

        /**
         * 启动
         */
        String BOOT = "boot";

        /**
         * 可操作脚本
         */
        String OPERATIONAL = "operational";

        /**
         * 销毁实例需要执行的脚本类别
         */
        String DECOMMISSION = "decommission";

        /**
         * 其他执行过的脚本
         */
        String OTHER = "other";
    }

    interface ScriptType {

        String SHELL = "shell";

        String PLAYBOOK = "playbook";

        String PYTHON = "python";
    }

    interface Order_Type {
        // 申请
        String APPLY = "apply";

        // 升配
        String UPGRADE = "upgrade";

        // 降配
        String DEGRADE = "degrade";

        // 变更配置
        String CHANGEGRADE = "changegrade";

        // 续订
        String RENEW = "renew";

        // 退订
        String RELEASE = "release";

        String EXTEND = "extend";

        String MODIFY = "modify";

        String DELETE = "delete";
    }

    interface NetworkManagement {
        /*network可用*/ String NORMAL = "normal";

        /*network不可用*/ String DISABLED = "disabled";

        /*networkIp未使用*/ String AVAILABLE = "unused";

        /*networkIp已使用*/ String UNAVAILABLE = "used";

        /*networkIp预留*/ String RESERVED = "reserved";

        /*networkIp保留*/ String KEEP = "keep";

        /*ipv4*/ String IPV4 = "ipv4";
    }

    interface TagTargetType {
        /*标签管理类型-云主机*/ String CLOUDHOST = "CloudHost";
        /*标签管理类型-资源组*/ String CLOUDDEPLOYMENT = "CloudDeployment";
    }

    interface CloudEnvDefaultDefCategory {

        /**
         * 参数为部署类型
         */
        String DEPLOYMENT = "deployment";

        /**
         * 参数为企业参数配置
         */
        String COMPANY = "company";
    }

    interface ScriptClass {
        /**
         * 公共类型脚本
         */
        String PUBLIC = "public";

        /**
         * 私有脚本
         */
        String PRIVATE = "private";
    }

    interface Feedback {

        /**
         * 功能要求
         */
        String FEATURE = "feature";

        /**
         * 缺陷
         */
        String BUG = "bug";

        /**
         * 其它
         */
        String OTHER = "other";

        /**
         * 待处理
         */
        String PENDING = "pending";

        /**
         * 已处理
         */
        String HANDLED = "handle";

        /**
         * 云管理
         */
        String CLOUDMANAGEMENT = "cloud-management";

        /**
         * 云分析
         */
        String CLOUDANALYTICS = "cloud-analytics";

        /**
         * 自服务
         */
        String SELFSERVICE = "self-service";
    }

    interface ResClusterID {
        /**
         * 所有主机
         */
        String ALL_HOSTS = "all";
        /**
         * 非集群主机
         */
        String NONE_CLUSTER = "none";
    }

    interface EnvQuotaKey {
        /**
         * 实例个数
         */
        String CPU_COUNT = "cpuCount";
        /**
         * CPU核数
         */
        String MEMORY_MAX = "memoryMax";
        /**
         * 内存大小
         */
        String STORAGE_MAX = "storageMax";
        /**
         * 存储大小
         */
        String VM_COUNT = "vmCount";
    }

    interface ElasticMode {
        /**
         * 增长
         */
        String INCREASE = "add";

        /**
         * 缩减
         */
        String DECREASE = "reduce";
    }

    interface ElasticStatus {
        /**
         * 增长
         */
        String RUNNING = "running";

        /**
         * 缩减
         */
        String COMPLETED = "completed";

        String LAUNCHED = "launched";
    }

    interface MarketPublishStatus {
        /**
         * 发布状态
         */
        String UNPUBLISH = "unpublish";
        /**
         * 未发布状态
         */
        String PUBLISHED = "published";
    }

    interface MarketPublishType {
        /**
         * 主机模板
         */
        String TEMPLATE = "template";
        /**
         * 脚本
         */
        String SCRIPT = "script";
        /**
         * 多云镜像
         */
        String IMAGE = "image";
    }

    interface MarketImportStatus {
        /**
         * 表示导入
         */
        String IMPORTED = "imported";
        /**
         * 表示发布为企业内部
         */
        String SCOPE = "1";
    }

    interface VMWare {
        /**
         * dataStore
         */
        String DATASTORE = "Datastore";
        /**
         * summary
         */
        String SUMMARY = "summary";
        /**
         * parent
         */
        String PARENT = "parent";
        /**
         * busLogic
         */
        String BUSLOGIC = "busLogic";
        /**
         * VirtualMachine
         */
        String VIRTUALMACHINE = "VirtualMachine";
        /**
         * persistent
         */
        String PERSISTENT = "persistent";
        /**
         * config.hardware.device
         */
        String CONFIG_HARDWARE_DEVICE = "config.hardware.device";
        /**
         * config
         */
        String CONFIG = "config";
        /**
         * vmware比较对象
         */
        String COM_VMWARE_VIM = "com.vmware.vim25.VirtualDisk";
        /**
         * 瘦置备
         */
        String THIN = "thin";
        /**
         * 厚置备
         */
        String THICK = "thick";
        /**
         * 厚置备延迟置零
         */
        String EAGERZEROEDTHICK = "eagerZeroedThick";
    }

    /**
     * 默认值类型
     */
    interface DefaultValueType {

        /**
         * 文本类型
         */
        String TEXT = "text";
        /**
         * 环境类型
         */
        String ENV = "env";
        /**
         * json类型
         */
        String JSON = "json";

    }

    interface ScriptParamValueEnvType {
        /**
         * 实例ID
         */
        String INSTANCE_ID = "INSTANCE_ID";

        /**
         * 公共ip
         */
        String INSTANCE_PUBLIC_IP = "INSTANCE_PUBLIC_IP";

        /**
         * 私有ip
         */
        String INSTANCE_PRIVATE_IP = "INSTANCE_PRIVATE_IP";

        /**
         * 实例名称
         */
        String INSTANCE_NAME = "INSTANCE_NAME";

        /**
         * 子网ID
         */
        String SUBNET_ID = "SUBNET_ID";

        /**
         * ldap connect success
         */
        String LDAP_CONNECT_SUCCESS = "ldap.connect.success";

        /**
         * ldap connect failure
         */
        String LDAP_CONNECT_FAILURE = "ldap.connect.failure";
    }

    interface CodeCategory {
        /**
         * 脚本环境参数类型
         */
        String SCRIPT_PARAM_VALUE_ENV_TYPE = "SCRIPT_PARAM_VALUE_ENV_TYPE";
    }

    interface ElasticVoteStatus {
        /**
         * 有效
         */
        String VALID = "valid";

        /**
         * 无效
         */
        String INVALID = "invalid";
    }

    interface SyncKey {
        /**
         * 云环境
         */
        String ENV = "env";

        /**
         * 虚拟机
         */
        String VM = "vm";

        /**
         * 镜像
         */
        String IMAGE = "image";

        /**
         * 分区
         */
        String ZONE = "zone";

        /**
         * 集群
         */
        String CLUSTER = "cluster";

        /**
         * 主机
         */
        String HOST = "host";

        /**
         * 存储
         */
        String STORAGE = "storage";

        /**
         * 网络
         */
        String NETWORK = "network";

        /**
         * 网络
         */
        String VPCPORT = "vpcport";

        /**
         * 子网
         */
        String SUBNET = "subnet";

        /**
         * 弹性IP
         */
        String FLOATINGIP = "floatingIp";

        /**
         * 安全组
         */
        String SECURITYGROUPS = "securityGroups";

        /**
         * SSH key
         */
        String SSHKEY = "sshKey";

        /**
         * 实例类型
         */
        String VMTYPE = "vmType";

        /**
         * 硬盘
         */
        String VD = "vd";

        /**
         * 路由器
         */
        String ROUTER = "router";

        /**
         * 路由器接口
         */
        String ROUTERINTERFACE = "routerinterface";

        /**
         * 路由表
         */
        String ROUTERROUTE = "routerroute";

        /**
         * 存储类型
         */
        String STORAGETYPE = "storagetype";

        /**
         * RDS实例
         */
        String DB_INSTANCE = "db_instance";

        String LOADBALANCE = "loadBalance";

        /**
         * RDS实例白名单
         */
        String DB_INSTANCE_IPARRAY = "db_instance_iparray";

        /**
         * RDS实例账户
         */
        String DB_INSTANCE_ACCOUNT = "db_instance_account";

        /**
         * RDS实例连接信息
         */
        String DB_INSTANCE_CONNECTION = "db_instance_connection";

        /**
         * 快照
         */
        String SNAPSHOT = "snapshot";

        /**
         * RDS价格配置
         */
        String PRICE_CONFIG= "priceConfig";
    }

    interface AllocFlag {
        //分配
        String ALLOC = "alloc";

        //创建
        String CREATE = "create";
    }

    interface SelfServiceCategory {
        /**
         * 计算
         */
        String COMPUTING = "computing";

        /**
         * 硬盘
         */
        String STORAGE = "storage";

        /**
         * 数据库
         */
        String DATABASE = "database";

        /**
         * 中间件
         */
        String MIDDLEWARE = "middleware";


        /**
         * 网络
         */
        String NETWORK = "network";

        /**
         * 容器
         */
        String CONTAINER = "container";
    }

    interface SelfServiceStatus {
        // 未发布
        String UNPUBLISH = "01";

        // 已发布
        String PUBLISHED = "02";

        // 禁用
        String DISABLED = "03";
    }


    /**
     * AWS EBS存储卷类型
     */
    interface EBSVolumeType {

        /**
         * 通用 SSD (GP2)
         */
        String GP2 = "gp2";

        /**
         * 预配置 IOPS SSD (IO1)
         */
        String IO1 = "io1";

        /**
         * Cold HDD (SC1)
         */
        String ST1 = "st1";

        /**
         * 吞吐优化 HDD (ST1)
         */
        String SC1 = "sc1";

        /**
         * 磁介质
         */
        String STANDARD = "standard";

    }


    interface DeployTaskTriggerType {
        /**
         * 手动执行
         */
        String MANUAL = "manual";

        /**
         * 自动执行
         */
        String AUTO = "auto";

        /**
         * 定时任务
         */
        String SCHEDULER = "scheduler";
    }

    interface DeployTaskSubTargetType {
        /**
         * 子目标类型为instance
         */
        String INSTANCE = "instance";

        /**
         * 子目标类型为资源组
         */
        String DEPLOYMENT = "deployment";

        /**
         * 子目标类型为定时调度
         */
        String SCHEDULER = "scheduler";
    }

    interface ScheduleType {

        /**
         * 定时执行
         */
        String TIME = "time";

        /**
         * 周期执行
         */
        String CYCLE = "cycle";

        /**
         * 自定义表达式
         */
        String CUSTOM = "custom";
    }

    interface BillingConfigCategory {
        /**
         * 计算
         */
        String COMPUTER = "compute";
        /**
         * 存储
         */
        String BLOCK_STORAGE = "blockStorage";
        /**
         * ip带宽
         */
        String IP_BAND_WIDTH = "ipBandwidth";
    }

    interface ScheduleTypeClass {
        /**
         * 分钟
         */
        String MINUTE = "minute";
        /**
         * 小时
         */
        String HOUR = "hour";
        /**
         * 天
         */
        String DAY = "day";
        /**
         * 周
         */
        String WEEK = "week";
        /**
         * 月
         */
        String MONTH = "month";
        /**
         * 年
         */
        String YEAR = "year";
    }

    interface EnableString {
        /**
         * 启用
         */
        String ENABLE = "enable";
        /**
         * 禁用
         */
        String DISABLE = "disable";
    }

    interface SelfServer {
        /**
         * 存储自服务
         */
        String Storage = "Storage";
    }

    interface ALIYUNROUTE {
        String INSTANCE = "Instance";
    }

    interface ResOperation {
        /**
         * 创建
         */
        String CREATE = "create";

        /**
         * 删除
         */
        String DELETE = "delete";

        /**
         * 挂载
         */
        String ATTACH = "attach";

        /**
         * 卸载
         */
        String DETACH = "detach";

        /**
         * 关联
         */
        String ALLOC = "alloc";

        /**
         * 取消关联
         */
        String UNALLOC = "unalloc";

        /**
         * 修改
         */
        String MODIFY = "modify";
    }

    /**
     * 资源配额控制方式
     */
    interface QuotaMode {
        // 按用量控制
        String USE = "use";
        // 按费用控制
        String FEE = "fee";
        // 不控制
        String NOLIMIT = "nolimit";
    }


    interface EnvLinkType {
        String RC_LINK = "RCLink";
        String DIRECT = "Direct";
    }

    interface RCLinkMQ {
        String RCLINK_MQ_SERVER = "rclink.mq.server";
        String RCLINK_MQ_USER = "rclink.mq.user";
        String RCLINK_MQ_PASSWORD = "rclink.mq.password";
    }

    interface RCLinkProperty {
        String RCLINK_TOKEN = "RCLinkToken";
        String RCLINK_ID = "RCLinkID";
    }

    interface BillingConfigObjType {
        /**
         * 云服务
         */
        String CLOUD_SERVICE = "cloudService";
        /**
         * 云环境
         */
        String CLOUD_ENV = "cloudEnv";
    }

    interface BillingTypeConfig {
        /**
         * 按小时
         */
        String HOUR = "hour";
        /**
         * 按天
         */
        String DAY = "day";
        /**
         * 按月
         */
        String MONTH = "month";
    }

    interface ServiceOpenTyle {
        /**
         * 立即自动开通
         */
        String NOW = "01";
        /**
         * 手动开通
         */
        String MANUAL = "02";
    }

    //提醒方式常量定义 add by luxinglin 2017-11-10
    interface NoticeMethodConfig {
        /**
         * 平台消息
         */
        String PLAT_MESSAGE = "plat_message";
        /**
         * 短信
         */
        String SHORT_MESSAGE = "short_message";
        /**
         * 邮件
         */
        String EMAIL = "email";
    }
    //end 提醒方式常量定义 add by luxinglin 2017-11-10

    interface ScheduleCategory {
        /**
         * 普通类型
         */
        String NORMAL = "normal";

        /**
         * 健康体检类型
         */
        String HEALTH = "health";
    }

    interface HealthSpecCod {
        /**
         * 登录方式
         */
        String CHECK_AVAILABLE = "check_available";
        /**
         * ping 方式
         */
        String CHECK_AVAILABLE_PING = "check_ping";
    }

    interface HealthSepcType {
        /**
         * 可用性
         */
        String AVAILABLE = "exa.available";

        /**
         * 安全性
         */
        String SECURITY = "exa.security";

    }

    interface AnsibleResultMsg {
        //"7037:0.0.0.0:failed": "#Failed# is unreachable ['10.102.10.107:22']"
        String IS_UNREACHABLE = "unreachable";
    }

    interface LdapPropertyKey {
        /**
         * 域名
         */
        String LDAP_DOMAINNAME = "ldap.domainname";

        /**
         * 服务器
         */
        String LDAP_SERVERNAME = "ldap.servername";

        /**
         * 登录用户名
         */
        String LDAP_USERNAME = "ldap.username";

        /**
         * 密码
         */
        String LDAP_PASSWORD = "ldap.password";

        /**
         * 端口
         */
        String LDAP_PORT = "ldap.port";

        /**
         * 链接方式
         */
        String LDAP_CONNECT_TYPE = "ldap.connecttype";

        /**
         * 证书路径
         */
        String LDAP_CREDENTIALS_PATH = "ldap.credentials.path";

        /**
         * 证书名称
         */
        String LDAP_CREDENTIALS = "credentials.cer";

    }

    /**
     * 用户认证类型
     */
    interface USER_AUTH_TYPE {

        /**
         * 第三方用户
         */
        String AUTH_TYPE_SSO = "sso";

        /**
         * 本地用户
         */
        String AUTH_TYPE_LOCAL = "local";

    }

    /**
     * 云监控客户端类型
     */
    interface CloudMonitorClientType {
        String ALIYUN = "Aliyun";
        String QCLOUD = "Qcloud";
        String AWS = "Aws";
        String OPEN_STACK = "OpenStack";
        String ES_CLOUD = "ESCloud";
        String VMWARE = "VMware";
        String POWER_VC = "PowerVC";
        String OPEN_FALCON = "OpenFalcon";
    }

    /**
     * 统计指标类型
     */
    interface CloudMonitorCf {
        /**
         * 平均值
         */
        String AVERAGE = "average";
        /**
         * 最大值
         */
        String MAX = "max";
        /**
         * 最小值
         */
        String MIN = "min";
    }

    interface ComparisonOperator{
        /**
         * 大于
         */
        String GREATER_THAN = ">";
        /**
         * 小于
         */
        String LESS_THAN = "<";
        /**
         * 大于等于
         */
        String EQUALS_AND_GREATER_THAN = ">=";
        /**
         * 小于等于
         */
        String EQUALS_AND_LESS_THAN = "<=";
    }

    //提醒方式常量定义 add by luxinglin 2018-03-07
    interface NoticeMethodType {
        /**
         * 站内消息
         */
        int PLAT_MESSAGE = 0;
        /**
         * 邮件
         */
        int EMAIL = 1;
        /**
         * 短信
         */
        int SHORT_MESSAGE = 2;
    }
    //end 提醒方式常量定义 add by luxinglin 2018-03-07

    interface ZoneStatus{
        /**
         * The Unavailable.
         */
        String UNAVAILABLE = "UNAVAILABLE";

        /**
         * The Available.
         */
        String AVAILABLE = "AVAILABLE";

    }

    interface RouterDefault{
        /**
         * nextType
         */
        String NEXT_TYPE = "2";

        /**
         * 目的网段
         */
        String DESTINATION_CIDR_BLOCK = "Local";

        /**
         * 下一跳地址
         */
        String NETX_HUB = "Local";
    }

    interface RouteType{
        /**
         * 系统类型
         */
        String SYSTEM = "System";

        /**
         * 自定义类型
         */
        String Custom = "Custom";
    }

    interface RouterNextType{
        /**
         * 云主机(公网网关)
         */
        String COMMOM_GATEWAY = "0";

        /**
         * vpn网关
         */
        String VPN_GATEWAY = "1";

        /**
         * 专线网关
         */
        String DIRECT_CONNECT_GATEWAY = "3";

        /**
         * 对等连接
         */
        String PEERING_CONNECT_GATEWAY = "4";

        /**
         * 运维管理VPN
         */
        String OPERATION_MNG_VPN = "7";

        /**
         * NAT网关
         */
        String NAT_GATEWAY = "8";
    }

    interface ManagementAccount{

        /**
         * root
         */
        String ROOT = "root";

        /**
         * administrator
         */
        String ADMINISTRATOR = "Administrator";

    }

    interface SysConfigKey {
        String PROJECTADMIN_AUDITSTATUS = "self.service.projectadmin.audit.status";
        String COMPANYADMIN_AUDITSTATUS = "self.service.companyadmin.audit.status";
    }


    /**
     * cloud deployment type
     */
    interface CloudDeploymentType {

        /** 编排 */
        String STACK = "stack";

        /** 脚本类 */
        String SCRIPT = "script";

        /** 应用类 */
        String APP = "app";

        /** 服务类*/
        String SERVICE = "service";

        /**云服务器*/
        String INFRA = "infra";
    }


    /**
     * self service yaml config param
     */
    interface SelfServiceYamlConfig {
        /** 查询k8s 启动type*/
        String k8s_key_type = "cn.com.cloudstar.nodes.Kubernetes";

        String app_key_type = "cn.com.cloudstar.nodes.App";

        /** 查询script
         *  启动type*/
        String script_key_type = "cn.com.cloudstar.nodes.Script";

        /** cluster id key*/
        String cluster_key = "kubernetes_cluster";

    }

    /**
     * self service yaml config param
     */
    interface SelfServiceYamlDeployType {
        /** 查询k8s 启动type*/
        String STACK = "stack";
        /** 查询k8s 启动type*/
        String SCRIPT = "script";
        /** 查询 Service 启动type*/
        String SERVICE = "service";

        String APP = "app";

        String INFRA = "infra";
    }

    interface SelfServiceDeployActionType {
        /** 初始化创建*/
        String DEPLOY = "deploy";

        /** 增加节点*/
        String SCALE_UP = "scale_up";

        /** 删除服务*/
        String DELETE = "delete";
    }

    interface SelfServiceNodePrefix{
        String SCALE = "scale";
    }

    interface ApproveType {
        // 企业管理员审核
        String COMPANY_MGT_AUDIT = "approve.company.mgt";
        // 项目管理员审核
        String PROJECT_MGT_AUDIT = "approve.project.mgt";
    }

    interface ServiceCategory {
        // IaaS
        String IAAS = "IaaS";
        // PaaS
        String PAAS = "PaaS";

        String DAAS = "DaaS";

        //数据资源
        String DATA_RESOURCE = "DataResource";
        //采集服务
        String COLLECTION_SERVICE = "CollectionService";
        //数据处理
        String DATA_PROCESSING = "DataProcessing";
        //数据治理
        String DATA_GOVERNANCE = "DataGovernance";
        //数据服务
        String DATA_SERVICE = "DataService";
        //数据应用
        String DATA_APPLICATION = "DataApplication";

    }

    interface ServiceCategoryOpenType{
        String FULL = "full";
        String PART = "part";
    }

    //服务开通状态
    interface SfServiceCategoryOpenStatus{
        //已开通
        String OPENED = "02";
        //未开通
        String NONACTIVATED ="01";
    }

    interface KubernetesAuthType {
        String BASIC = "basic";
        String TOKEN = "token";
        String NONE = "none";
    }

    interface ClusterType{

        String KUBERNETES = "kubernetes";
        String SWARM = "swarm";
    }

    interface IconType {
        /* 自定义图标*/ String CUSTOMICON = "customIcon";
        /* 系统图标*/ String SYSTEMICON = "systemIcon";
    }

}


