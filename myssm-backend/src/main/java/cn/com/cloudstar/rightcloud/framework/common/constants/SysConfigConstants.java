/*
 * Copyright (c) 2018 Cloud-Star, Inc. All Rights Reserved..
 */

package cn.com.cloudstar.rightcloud.framework.common.constants;

/**
 * Created by swq on 4/7/2016.
 *
 * @author swq
 */
public interface SysConfigConstants {
    /**
     * 充值送配置
     */
    String CHARGE_GIFT_CONFIG = "charge.gift.config";

    /**
     * 云会议秘钥
     */
    String CLOUD_MEETING_SECRET = "cloud_meeting_secret";

    /**
     * 云会地址
     */
    String CLOUD_MEETING_URL = "cloud_meeting_url";

    /**
     * 用户访问地址
     */
    String USER_VISIT_URL = "user_visit_url";

    /**
     * 用户网盘地址
     */
    String E_BOX_URL = "ebox_url";

    /**
     * 系统portal地址
     */
    String PORTAL_URL = "portal.url";

    /**
     * 系统是否开启了订购资费计算
     */
    String SYSTEM_ORDER_BILLING = "system.order.billing";

    /**
     * 云会议错误响应页面
     */
    String CLOUD_MEETING_ERROR_URL = "cloud.meeting.error.url";

    /**
     * 云会议登出页面
     */
    String CLOUD_MEETING_OUT_URL = "cloud.meeting.out.url";

    /**
     * ansible server url
     */
    String ANSIBLE_SERVER_URL = "ansible.server.url";

    String OPEN_FALCON_ALARM_CALLBACK_URL = "openfalcon.alarm.callback.url";

    String TRIAL_HOST_TIME_LIMIT = "trial.host.time.limit";

    /**
     * kubernetes集群默认dns ip配置
     */
    String KUBERNETES_DEFAULT_CLUSTER_DNS = "kubernetes.default.cluster.dns";

    /**
     * kubernetes集群默认domain配置
     */
    String KUBERETES_DEFAULT_CLUSTER_DOMAIN = "kubernetes.default.cluster.domain";

    //通知提醒相关配置参数 add by luxinglin 2017-11-10
    /**
     * 服务申请单一级审批时限
     */
    String LEV1_AUDIT_DURATION = "lev1.audit.duration";
    /**
     * 服务申请单二级审批时限
     */
    String LEV2_AUDIT_DURATION = "lev2.audit.duration";

    /**
     * 一级审批角色配置
     */
    String LEV1_AUDIT_ROLE = "lev1.audit.role";
    /**
     * 二级审批角色配置
     */
    String LEV2_AUDIT_ROLE = "lev2.audit.role";
    /**
     * 服务申请单审批超时后提醒发送频率
     */
    String AUDIT_OVERTIME_NOTICE_FREQUENCY = "audit.overtime.notice.frequency";
    /**
     * 审批超时提醒开关配置参数
     */
    String PROC_AUDIT_OVERTIME_NOTICE_ON = "proc.audit.overtime.notice.on";
    /**
     * 审批超时提醒方式配置参数
     */
    String PROC_AUDIT_OVERTIME_NOTICE_METHOD = "proc.audit.overtime.notice.method";
    /**
     * 流程流转提醒开关配置参数
     */
    String PROC_NOTICE_ON = "proc.notice.on";
    /**
     * 流程流转提醒方式配置参数
     */
    String PROC_NOTICE_METHOD = "proc.notice.method";
    /**
     * 预过期时间配置参数
     */
    String SERVICE_EXPIRE_DURATION = "service.expire.notice.duration";
    /**
     * 服务到期持续发送提醒频率配置参数
     */
    String SERVICE_EXPIRE_NOTICE_FREQUENCY = "service.expire.notice.frequency";
    /**
     * 服务到期提醒开关配置参数
     */
    String SERVICE_EXPIRE_NOTICE_ON = "service.expire.notice.on";
    /**
     * 服务到期提醒方式配置参数
     */
    String SERVICE_EXPIRE_NOTICE_METHOD = "service.expire.notice.method";

    //end 通知提醒相关配置参数 add by luxinglin 2017-11-10

    /**
     * 平台类型
     */
    String PLATFORM_TYPE = "platform.type";

    /**
     * 短信相关配置
     */
    String SMS_CDKEY = "sms.cdkey";

    String SMS_KEY = "sms.key";

    String SMS_PASS_KEY = "sms.password";

    String SMS_URL = "sms.url";

    String SMS_SEQID = "sms.seqid";

    String SMS_ENABLE = "sms.enable";

    /**
     * 平台logo存储根路径
     */
    String PLATFORM_LOGO_PATH = "platform.logo.root.path";

    /**
     * 平台许可密钥
     */
    String PLATFORM_LICENCE_KEY = "platform.licence.key";

    /**
     * 平台密钥key
     */
    String SECURITY_KEY = "security.key";

    /**
     * ldap证书路径
     */
    String LDAP_CREDENTIALS_PATH = "ldap.credentials.path";

    /**
     * openfalcon 查询历史指标监控数据
     */
    String OPENFALCON_QUERY_HISTORY_URL = "query.history.url";

    /**
     * mongo 用户执行记录日志集合名
     */
    String MONGO_ACTION_LOG_COLLECTION_NAME = "action_log";

    /**
     * novnc 主机ip
     */
    String NOVNC_INNERIP = "novnc.innerip";

    /**
     * novnc 用户账号
     */
    String NOVNC_ACCOUNT = "novnc.account";

    /**
     * novnc 密码
     */
    String NOVNC_PASSWORD = "novnc.password";

    /**
     * novncToken本地路径
     */
    String NOVNC_FILE_LOCAL_PATH = "novnc.file.local.path";

    /**
     * novncToken上传路径
     */
    String NOVNC_FILE_REMOTE_PATH = "novnc.file.remote.path";

    /**
     * novnc主机端口号
     */
    String NOVNC_PORT = "novnc.port";


    /***
     * swagger 在线文档开关
     * */
    String SWAGGER_ENABLE = "swagger.enable";
    /**
     * 监控 influxdb相关配置
     * */
    String MONITOR_INFLUX_URL = "monitor.influx.url";
    String MONITOR_INFLUX_USERNAME = "monitor.influx.username";
    String MONITOR_INFLUX_PASSWORD = "monitor.influx.password";
    String MONITOR_INFLUX_DBNAME = "monitor.influx.dbname";
    String MONITOR_SERVER_URL = "monitor.server.url";

    /**
     * dcuc api url
     */
    String DCUC_API_PREFIX = "dcuc.api.url.prefix";
    /**
     * 华为网关相关配置
     * **/
    String DCUC_API_HTTP_PREFIX="dcuc.api.url.http.prefix";
    String DCUC_API_HTTPS_PREFIX="dcuc.api.url.https.prefix";
    String HUAWEI_DUCU_APPKEY="huawei.dcuc.appkey";
    String HUAWEI_DUCU_APPSECRET="huawei.dcuc.appsecret";
    String HUAWEI_TENANT_APPKEY="huawei.tenant.appkey";
    String HUAWEI_TENANT_APPSECRET="huawei.tenant.appsecret";


}
