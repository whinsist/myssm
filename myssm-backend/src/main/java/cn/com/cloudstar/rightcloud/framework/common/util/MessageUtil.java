/*
 * Copyright (c) 2018 Cloud-Star, Inc. All Rights Reserved..
 */

package cn.com.cloudstar.rightcloud.framework.common.util;

import cn.com.cloudstar.rightcloud.framework.common.constants.WebConstants;
import org.joda.time.DateTime;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Locale;

/**
 * cn.com.cloudstar.rightcloud.common.util
 *
 * @author Chaohong.Mao
 */
public class MessageUtil {

    private static final String MESSAGE_PROPERTY_PREFIX = "msg.";
    private static MessageSource messageSource;

    static {
        try {
            // 获取消息处理类
            messageSource = new ClassPathXmlApplicationContext("classpath:spring-message-bean.xml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 取得系统消息
     *
     * @param msgId 消息ID
     * @return 消息内容
     */
    public static String getMessage(String msgId) {
        return getMessage(msgId, null);
    }

    /**
     * 取得系统消息
     *
     * @param msgId 消息ID
     * @param arg   消息设置参数
     * @return 消息内容
     */
    public static String getMessage(String msgId, Object[] arg) {
        String message = "";
        try {
            message = messageSource.getMessage(MESSAGE_PROPERTY_PREFIX + msgId, arg, Locale.CHINA);
        } catch (Exception e) {
        }

        return message;
    }

    public static String getLogMessage(String msg) {
        return String.format("[%s][INFO] - %s", DateTime.now().toString("yyyy-MM-dd HH:mm:ss"), msg);
    }

    public static String getEndLogMessage(String status, String msg) {
        return String.format("[%s][%s] - %s. %s",
                             DateTime.now().toString("yyyy-MM-dd HH:mm:ss"),
                             WebConstants.AnsibleMessageFlag.NORMAL_SUCCESS_MESSAGE_FLAG.equals(status) ? "INFO" : "ERROR",
                             status,
                             msg
        );
    }
}
