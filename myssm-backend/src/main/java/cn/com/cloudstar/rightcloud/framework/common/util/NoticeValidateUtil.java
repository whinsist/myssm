/*
 * Copyright (c) 2018 Cloud-Star, Inc. All Rights Reserved..
 */

package cn.com.cloudstar.rightcloud.framework.common.util;

import cn.com.cloudstar.rightcloud.framework.common.constants.WebConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 */
public class NoticeValidateUtil {

    private static Logger logger = LoggerFactory.getLogger(NoticeValidateUtil.class);

    private NoticeValidateUtil() {
    }

    /**
     * 通知发送开关是否打开
     *
     * @param switchValue 开关值
     * @return 开启返回true，否则返回false
     */
    public static boolean isSwitchOn(String switchValue) {
        return switchValue != null && ("true".equalsIgnoreCase(switchValue) || "on".equalsIgnoreCase(switchValue) ||
                "yes".equalsIgnoreCase(switchValue) || "y".equalsIgnoreCase(switchValue) ||
                "1".equalsIgnoreCase(switchValue));
    }

    /**
     * 通知发送方式是否支持
     *
     * @param noticeMethod 发送途径
     * @return 支持返回true，否则返回false
     */
    public static boolean noticeMethodSupported(String noticeMethod) {
        return noticeMethod != null &&
                (noticeMethod.toLowerCase().contains(WebConstants.NoticeMethodConfig.PLAT_MESSAGE) ||
                        noticeMethod.toLowerCase().contains(WebConstants.NoticeMethodConfig.SHORT_MESSAGE) ||
                        noticeMethod.toLowerCase().contains(WebConstants.NoticeMethodConfig.EMAIL));
    }

    /**
     * @param noticeMethod
     * @return
     */
    public static boolean noticeMethodSupportPlatMessage(String noticeMethod) {
        return noticeMethod != null &&
                noticeMethod.toLowerCase().contains(WebConstants.NoticeMethodConfig.PLAT_MESSAGE);
    }

    /**
     * @param noticeMethod
     * @return
     */
    public static boolean noticeMethodSupportMail(String noticeMethod) {
        return noticeMethod != null && noticeMethod.toLowerCase().contains(WebConstants.NoticeMethodConfig.EMAIL);
    }

    /**
     * @param noticeMethod
     * @return
     */
    public static boolean noticeMethodSupportShortMessage(String noticeMethod) {
        return noticeMethod != null &&
                noticeMethod.toLowerCase().contains(WebConstants.NoticeMethodConfig.SHORT_MESSAGE);
    }
}
