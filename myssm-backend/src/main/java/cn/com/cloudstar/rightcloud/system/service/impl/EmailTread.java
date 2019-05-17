package cn.com.cloudstar.rightcloud.system.service.impl;

import java.util.Collections;

import cn.com.cloudstar.rightcloud.framework.common.util.MailUtil;

/**
 * @author Hong.Wu
 * @date: 15:00 2019/04/20
 */
public class EmailTread implements Runnable {
    private String userEmail;
    @Override
    public void run() {
       // String userEmail = "444089024@qq.com";
        String subject = "test";
        String content = "test";
        boolean sendMailFlag = MailUtil.sendMail(Collections.singletonList(userEmail), null, null, subject, content,
                                                 null);
        System.out.println(sendMailFlag);
    }
    public EmailTread (String name) {
        this.userEmail = name;
    }
}
