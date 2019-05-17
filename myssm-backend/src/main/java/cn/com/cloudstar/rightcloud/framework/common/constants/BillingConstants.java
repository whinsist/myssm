/*
 * Copyright (c) 2018 Cloud-Star, Inc. All Rights Reserved..
 */

package cn.com.cloudstar.rightcloud.framework.common.constants;

/**
 * Created by jerry on 2017/10/20.
 */
public interface BillingConstants {

    /**
     * 所属分类
     */
    interface BillCategory {
        String COMPUTE = "compute";
        String BLOCK_STORAGE = "blockStorage";
        String IP_BAND_WIDTH = "ipBandwidth";
        String RDS = "rds";
        String SLB = "slb";
        String SLB_BANDWITH = "slbBandwith";
        String BANDWITH = "bandwith";
        String RDS_STORAGE = "rdsStorage";
    }

    /**
     * 计费类型
     */
    interface Type {
        String HOUR = "hour";
        String DAY = "day";
        String MONTH = "month";
    }

    /**
     * 价格类型 total(一口价) ,multiple
     */
    interface PriceType {
        String TOTAL = "total";
        String MULTIPLE = "multiple";
    }

    /**
     * 系列 normal ，high
     */
    interface Series {
        String NORMAL = "normal";
        String HIGH = "high";
    }

    /**
     * 计费规格对应的类型 cloudEnv ,cloudService
     */
    interface ObjType {
        String CLOUD_ENV = "cloudEnv";
        String CLOUD_SERVICE = "cloudService";
    }

    /**
     * 状态
     */
    interface Status {
        String ENABLE = "enable";
        String DISABLE = "disable";
    }

    /**
     * 带宽数
     */
    interface BandWidth {
        String ONE_M = "1";
        String TWO_M = "2";
    }

    /**
     * 计费方式
     */
    interface BillingChargeType {
        String FREE_TYPE = "00"; //不计费
        String AUTO_INC_TYPE = "01"; //自增计费
        String STATIC_TYPE = "02"; //固定计
    }

    /**
     * code
     */
    interface ModuleCode {
        String CPU = "cpu";
        String MEMORY = "memory";
        String DATE_DISK_SIZE = "dataDiskSize";
        String DISK_TYPE = "diskType";
        String BANDWIDTH = "bandwidth";
        String IP = "ip";
        String ZONE = "zone";
        String VM_TYPE_ID = "zone";
        String VM_TYPE_UUID = "uuid";
        String BILLING_TYPE = "billingType";
        String INSTANCE_TYPE = "instanceType";
        String DB_TYPE = "engine";
        String DB_VERSION = "engineVersion";
        String DB_STORAGE = "dbStorage";
        String CHARGE_TYPE = "chargeType";
        String LOADBALANCER_SPEC = "loadBalancerSpec";
        String UUID = "uuid";
    }
    interface PayMethod{
        String HOUR = "hour";
        String MONTH = "month";
    }

    interface ModuleValue {
        String VALUE_NULL = "null";
        String SLB_BANDWITH_VALUE = "slbOneMbpsOneHour";
        String IP_BANDWITH_VALUE = "ipOneMbpsOneHour";
        String BANDWITH_VALUE = "OneMbpsOneHour";
        String RDS_STORAGE_VALUE = "rdsOneGbOneHour";
    }
    /** 计费方式 */
    interface ChargeType {
        /** 按量计费*/
        String PRE_PAID = "PrePaid";
        /** 包年包月*/
        String POST_PAID = "PostPaid";
    }
    /** 开启计费策略 */
    interface BillingType {
        /** 使用计费策略*/
        String USE_STRATEGY = "strategy";
    }

    /**
     * 支付宝appid
     */
    String ALIPAY_APP_ID = "alipay.app.id";

    /**
     *商户私钥，您的PKCS8格式RSA2私钥
     */
    String ALIPAY_MERCHANT_PRIVATE_KEY = "alipay.merchant.private.key";

    /**
     *支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
     */
    String ALIPAY_PUBLIC_KEY = "alipay.public.key";

    /**
     *服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
     */
    String ALIPAY_NOTIFY_URL = "alipay.notify.url";

    /**
     *页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
     */
    String ALIPAY_RETURN_URL = "alipay.return.url";

    /**
     *签名方式
     */
    String ALIPAY_SIGN_TYPE = "alipay.sign.type";

    /**
     *字符编码格式
     */
    String ALIPAY_CHARSET = "alipay.charset";

    /**
     *支付宝网关
     */
    String ALIPAY_GATEWAYURL = "alipay.gatewayurl";

    /**
     *参数返回格式
     */
    String ALIPAY_FORMAT = "alipay.format";

}
