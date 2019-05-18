package cn.com.cloudstar.rightcloud.framework.common.pojo.mq;

/**
 * MQConstants
 *
 * @author wuhong
 * @date 2018/01/26
 */
public interface MQConstants {
    String EXCHANGE = "schedule_exchange";
    String ROUTINGKEY = "op.#";
    String QUEUE = "schedule_queue";

    interface MessageType {
        String JOB = "job";
        String ENV_SYNC = "env_sync";
        String ENV_ANALYSIS = "env_analysis";
        String SCHEDULEBACK = "schedulebakc";
        String OPS_JOB = "ops_job";
        String GAAP_COST = "gaap_cost";
        String BILLING_FORECASTING = "billing_forecasting";
        String HC_JOB = "health_check_job";
    }
}