package cn.com.cloudstar.rightcloud.framework.common.util;

import cn.com.cloudstar.rightcloud.framework.common.constants.WebConstants;

import java.util.HashMap;
import java.util.Map;

/**
 * @author luxinglin
 * @version 1.0
 * @Description: TODO
 * @create 2018-03-07 15:33
 **/
public class NotificationUtil {
    private NotificationUtil() {
    }

    /**
     * 通知发送开关是否打开
     *
     * @param switchValue 开关值
     * @return 开启返回true，否则返回false
     */
    public static boolean isSwitchOn(String switchValue) {
        return switchValue != null && (
                "true".equalsIgnoreCase(switchValue) ||
                        "on".equalsIgnoreCase(switchValue) ||
                        "yes".equalsIgnoreCase(switchValue) ||
                        "y".equalsIgnoreCase(switchValue) ||
                        "1".equalsIgnoreCase(switchValue)
        );
    }

    /**
     * 通知发送方式是否支持
     *
     * @param noticeMethod 发送途径
     * @return 支持返回true，否则返回false
     */
    public static boolean noticeMethodSupported(String noticeMethod) {
        return noticeMethod != null && (
                noticeMethod.toLowerCase().contains(WebConstants.NoticeMethodConfig.PLAT_MESSAGE) ||
                        noticeMethod.toLowerCase().contains(WebConstants.NoticeMethodConfig.SHORT_MESSAGE) ||
                        noticeMethod.toLowerCase().contains(WebConstants.NoticeMethodConfig.EMAIL)
        );
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
        return noticeMethod != null &&
                noticeMethod.toLowerCase().contains(WebConstants.NoticeMethodConfig.EMAIL);
    }

    /**
     * @param noticeMethod
     * @return
     */
    public static boolean noticeMethodSupportShortMessage(String noticeMethod) {
        return noticeMethod != null &&
                noticeMethod.toLowerCase().contains(WebConstants.NoticeMethodConfig.SHORT_MESSAGE);
    }

    /**
     * 设置消息模板通用信息
     *
     * @param realName
     * @param account
     * @return
     */
    public static Map<String, String> getBaseMessageContent(String realName, String account) {
        String companyName = PropertiesUtil.getProperty("company.name");
        String systemName = PropertiesUtil.getProperty("system.name");

        Map<String, String> messageContent = new HashMap();
        messageContent.put("owner", StringUtil.isNullOrEmpty(realName) ? account : realName);
        messageContent.put("companyName", companyName);
        messageContent.put("systemName", systemName);

        return messageContent;
    }
}
