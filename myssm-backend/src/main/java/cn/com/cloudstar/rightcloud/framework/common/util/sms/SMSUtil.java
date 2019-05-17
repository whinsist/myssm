/*
 * Copyright (c) 2018 Cloud-Star, Inc. All Rights Reserved..
 */

package cn.com.cloudstar.rightcloud.framework.common.util.sms;

import cn.com.cloudstar.rightcloud.framework.common.constants.SysConfigConstants;
import cn.com.cloudstar.rightcloud.framework.common.pojo.ConfigObj;
import cn.com.cloudstar.rightcloud.framework.common.util.DBUtil;
import cn.com.cloudstar.rightcloud.framework.common.util.sms.httpclient.SDKHttpClient;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;


/**
 * The type Sms util.
 *
 * @author nobody
 */
public class SMSUtil {

    private static final String CONFIG_SQL = "select CONFIG_KEY,CONFIG_VALUE from sys_m_config where CONFIG_TYPE = ?";
    /**
     * 软件序列号,请通过亿美销售人员获取
     */
    private static String cdkey;
    /**
     * 序列号首次激活时自己设定
     */
    private static String key;
    /**
     * 密码,请通过亿美销售人员获取
     */
    private static String password;
    private static String baseUrl;
    private static String seqid;
    private static String isEnable;
    private static String CONFIG_TYPE = "sms_config";

    static {
        List<ConfigObj> resultMaps = DBUtil.queryBeanList(CONFIG_SQL, ConfigObj.class, CONFIG_TYPE);
        if (!resultMaps.isEmpty()) {
            resultMaps.forEach(configObj -> {
                if (SysConfigConstants.SMS_CDKEY.equals(configObj.getCONFIG_KEY())) {
                    cdkey = configObj.getCONFIG_VALUE();
                } else if (SysConfigConstants.SMS_KEY.equals(configObj.getCONFIG_KEY())) {
                    key = configObj.getCONFIG_VALUE();
                } else if (SysConfigConstants.SMS_PASS_KEY.equals(configObj.getCONFIG_KEY())) {
                    password = configObj.getCONFIG_VALUE();
                } else if (SysConfigConstants.SMS_URL.equals(configObj.getCONFIG_KEY())) {
                    baseUrl = configObj.getCONFIG_VALUE();
                } else if (SysConfigConstants.SMS_SEQID.equals(configObj.getCONFIG_KEY())) {
                    seqid = configObj.getCONFIG_VALUE();
                } else if (SysConfigConstants.SMS_ENABLE.equals(configObj.getCONFIG_KEY())) {
                    isEnable = configObj.getCONFIG_VALUE();
                }
            });
        }
    }

    /**
     * Send sms boolean.
     *
     * @param phone   the phone
     * @param message the message
     * @return the boolean
     */
    public static boolean sendSms(String phone, String message) {
        if (Integer.parseInt(isEnable) == 0) {
            return false;
        }
        StringBuilder sb = new StringBuilder();
        try {
            sb.append("cdkey=").append(cdkey).append("&password=").append(password).append("&phone=").append(phone)
                    .append("&message=").append(URLEncoder.encode(message, "UTF-8")).append("&seqid=").append(seqid);
            return "0".equals(SDKHttpClient.sendSMS(baseUrl + "sendsms.action", sb.toString()));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean regist() {
        StringBuilder sb = new StringBuilder();
        sb.append("cdkey=").append(cdkey).append("&password=").append(password);
        return "0".equals(SDKHttpClient.regist(baseUrl + "regist.action", sb.toString()));
    }

//    public static void main(String args[]) {
////        System.out.println(regist());
////        System.out.println(sendSms("18908338113", "【融翼云】你此电话号码为：18908338113"));
////        System.out.println(sendSms("18601013475", "【融翼云】你此电话号码为：18601013475"));
////        System.out.println(sendSms("17701314977", "【融翼云】你此电话号码为：17701314977"));
////        System.out.println(sendSms("15823571244", "【融翼云】你此电话号码为：15823571244"));
//        SMSUtil.sendSms("asd","asd");
//    }

}
