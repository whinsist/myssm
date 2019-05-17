/*
 * Copyright (c) 2018 Cloud-Star, Inc. All Rights Reserved..
 */
package cn.com.cloudstar.rightcloud.framework.common.util;

import cn.com.cloudstar.rightcloud.framework.common.pojo.Attachment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 * 邮件发送共通类
 *
 * @author 刘洋
 */
public final class MailUtil {

    /**
     * 日志
     */
    private static Logger logger = LoggerFactory.getLogger(MailUtil.class);

    /**
     * 发送邮件
     *
     * @param toAddressList  主送邮件地址列表
     * @param ccAddressList  抄送邮件地址列表
     * @param bccAddressList 密送邮件地址列表
     * @param subject        邮件名
     * @param text           内容
     * @param attachtList    附件列表
     * @return 发送结果 true:发送成功 flase:发送失败
     */
    public static boolean sendMail(List<String> toAddressList, List<String> ccAddressList, List<String> bccAddressList,
                                   String subject, String text, List<Attachment> attachtList) {

        // 获取配置文件中的参数
        // 邮件发送smtp服务器
        String emailSmtpHost = "smtp.exmail.qq.com";//PropertiesUtil.getProperty("mail.smtp.host");
        // 邮件发送人的邮件地址
        String emailAccount = "noreply@rightcloud.com.cn";//PropertiesUtil.getProperty("mail.account");
        // 发件人的邮件帐户
        String username = "noreply@rightcloud.com.cn";//PropertiesUtil.getProperty("mail.username");
        // 发件人的邮件密码
        String password = "Pioneer@2016";//PropertiesUtil.getProperty("mail.password");
        // 发件人昵称
        String nickname = "RightCloud";//PropertiesUtil.getProperty("mail.nickname");


        try {
            if (toAddressList == null || toAddressList.isEmpty()) {
                return false;
            }

            Properties props = new Properties();
            // 定义邮件服务器的地址
            props.put("mail.smtp.host", emailSmtpHost);
            props.put("mail.smtp.auth", "true");
            // 取得Session
            Session session = Session.getInstance(props, new Authenticator() {
                @Override
                public PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });

            MimeMessage message = new MimeMessage(session);
            // 邮件标题
            message.setSubject(subject, "UTF-8");
            // 发件人的邮件地址
            message.setFrom(new InternetAddress(emailAccount, nickname));
            // 设置主送邮件地址
            for (String toAddress : toAddressList) {
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(disposeAddress(toAddress)));
            }

            // 设置抄送邮件地址
            if (ccAddressList != null && !ccAddressList.isEmpty()) {
                for (String ccAddress : ccAddressList) {
                    message.addRecipient(Message.RecipientType.CC, new InternetAddress(disposeAddress(ccAddress)));
                }
            }
            // 设置密送邮件地址
            if (bccAddressList != null && !bccAddressList.isEmpty()) {
                for (String bccAddress : bccAddressList) {
                    message.addRecipient(Message.RecipientType.BCC, new InternetAddress(disposeAddress(bccAddress)));
                }
            }
            // 邮件发送的时间日期
            message.setSentDate(new Date());

            // 新建一个MimeMultipart对象用来存放BodyPart对象 related意味着可以发送html格式的邮件
            Multipart mp = new MimeMultipart("related");
            // 新建一个存放信件内容的BodyPart对象
            BodyPart bodyPart = new MimeBodyPart();
            // 给BodyPart对象设置内容和格式/编码方式
            bodyPart.setContent(text, "text/html;charset=utf-8");
            // 将BodyPart加入到MimeMultipart对象中
            mp.addBodyPart(bodyPart);

            // 设置附件信息
            if (attachtList != null && !attachtList.isEmpty()) {
                for (Attachment att : attachtList) {
                    MimeBodyPart mbp = new MimeBodyPart();
                    // 得到数据源
                    FileDataSource fds = new FileDataSource(att.getAttachmentLocation());
                    // 得到附件本身并至入BodyPart
                    mbp.setDataHandler(new DataHandler(fds));
                    // 得到文件名同样至入BodyPart
                    mbp.setFileName(MimeUtility.encodeText(att.getOriginalName(), "UTF-8", "B"));
                    // 将BodyPart加入到MimeMultipart对象中
                    mp.addBodyPart(mbp);
                }
            }

            // 设置邮件内容
            message.setContent(mp);

            // 发送邮件
            Transport.send(message);
            logger.info("Message sent successfully.");
            return true;
        } catch (Exception ex) {
//            logger.error("邮件发送失败：" + ex.getLocalizedMessage());
            logger.error("邮件发送失败：", ex);
            return false;
        }
    }

    /**
     * 将收件人地址姓名UTF-8后拼接,无姓名的直接返回
     *
     * @param address 单个收件人地址格式为"Name<address@xxx.com>"
     * @return 返回姓名位置为UTF-8格式的字符串
     */
    private static String disposeAddress(String address) {
        if (address.contains("<")) {
            int startNum = address.indexOf("<");
            int end = address.indexOf(">");
            String str = address.substring(0, startNum);
            String mail = address.substring(startNum + 1, end);
            String nick = "";
            try {
                nick = MimeUtility.encodeText(str);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            address = nick + " <" + mail + ">";
        }
        return address;
    }
}
