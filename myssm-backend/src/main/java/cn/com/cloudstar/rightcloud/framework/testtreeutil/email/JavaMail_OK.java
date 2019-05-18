package cn.com.cloudstar.rightcloud.framework.testtreeutil.email;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

/**
 * https://blog.csdn.net/chenssy/article/details/8279709
 *
 */
public class JavaMail_OK {

	public static void main(String[] args) throws Exception {
		
		// javamail使用rightcloud邮箱发送邮件
//		String emailSmtpHost = "smtp.exmail.qq.com"; 
//		String emailAccount = "noreply@rightcloud.com.cn";
//        String username = "noreply@rightcloud.com.cn"; 
//        String password = "Pioneer@2016"; 
//        String toEmail = "444089024@qq.com";
//		String nickname = "nickname";
        
        
		// javamail使用qq邮箱发送邮件 
		// SMTP服务器地址 JavaMail需要和邮件服务器进行通信，这就要求程序提供许多诸如服务器地址、端口、用户名、密码等信息
        String emailSmtpHost = "smtp.qq.com"; 
        // 获取Session时的账号和Message中设置的邮箱地址setFrom要一致 所以emailAccount要和username一致
		String emailAccount = "444089024@qq.com";
        String username = "444089024@qq.com"; 
        // 如果是qq发送邮箱 passwor为授权码(需要设置独立密码 修改qq登录密码或独立密码是授权码会改变);  
        // "QQ邮箱的【设置】->【账户】->【POP3/IMAP/SMTP...】中获取的授权码"
        String password = "eyznohxpjltbbhbc"; 
        String toEmail = "444089024@qq.com";
        String nickname = "nickname";
        
 
        
        
        
        
		Properties props = new Properties();
        props.setProperty("mail.smtp.host", emailSmtpHost);
        props.setProperty("mail.smtp.auth", "true");
        props.setProperty("mail.debug", "true"); 
         
		Session session = Session.getInstance(props, new Authenticator(){
        	protected PasswordAuthentication getPasswordAuthentication(){
				return new PasswordAuthentication(username, password);
			}
        });
		
		Message message = new MimeMessage(session);
		message.setSubject("第三个JavaMail测试程序");
		message.setFrom(new InternetAddress(emailAccount, nickname));
		message.setRecipients(RecipientType.TO, new Address[]{new InternetAddress(toEmail)});
		
		//邮件正文  
        MimeMultipart multipart = new MimeMultipart("mixed");  
		message.setContent(multipart);
		
		
		// 创建邮件的内容    包括一个邮件正文和两个附件
		MimeBodyPart content = new MimeBodyPart();      //邮件内容
		MimeBodyPart attch1 = new MimeBodyPart();      //附件1
		MimeBodyPart attch2 = new MimeBodyPart();      //附件2
		//将邮件内容添加到multipart中
		multipart.addBodyPart(content);
		multipart.addBodyPart(attch1);
		multipart.addBodyPart(attch2);
		
		//设置附件1
		DataSource ds1 = new FileDataSource("E:\\temp\\image\\1.txt");
		DataHandler dh1 = new DataHandler(ds1);
		attch1.setDataHandler(dh1);
		attch1.setFileName("oracle.txt");
		//设置附件2
		DataSource ds2 = new FileDataSource("E:\\temp\\image\\2.txt");
		DataHandler dh2 = new DataHandler(ds2);
		attch2.setDataHandler(dh2);
		attch2.setFileName(MimeUtility.encodeText("账号.txt"));
		
		
		/*
		 * 设置内容（正文）---是一个复杂体
		 * 包括HTML正文和显示一张图片
		 */
		MimeMultipart bodyMultipart = new MimeMultipart("related");
		content.setContent(bodyMultipart);
		//构造正文
		MimeBodyPart htmlBody = new MimeBodyPart();
		MimeBodyPart gifBody = new MimeBodyPart();
		MimeBodyPart gifBody2 = new MimeBodyPart();
		bodyMultipart.addBodyPart(htmlBody);
		bodyMultipart.addBodyPart(gifBody);
		bodyMultipart.addBodyPart(gifBody2);
	
		//设置图片
		DataSource gifds = new FileDataSource("E:\\temp\\image\\1.jpg");  
        gifBody.setDataHandler(new DataHandler(gifds));  
        gifBody.setHeader("Content-ID", "<"+gifds.getName()+">");  
        
		 
        DataSource gifds2 = new FileDataSource("E:\\temp\\image\\2.jpg");  
		gifBody2.setDataHandler(new DataHandler(gifds2));
		gifBody2.setHeader("Content-ID", "<"+gifds2.getName()+">");  
		
		
		//设置HTML正文
		String imageHtml = "<img src='https://www.rightcloud.com.cn/static/img/wechatcode.d64b682.jpg'/>图片:<img src='cid:"+gifds.getName()+"'/><img src='cid:"+gifds2.getName()+"'/>";
		htmlBody.setContent("<span style='color:red;font-size:16px'>这是我的第三个JavaMail测试哦!包括了附件和图片，有点儿复杂...</span><br>" + imageHtml, "text/html;charset=UTF-8");
		
		
		message.saveChanges();        //生成邮件
		Transport.send(message);
		System.out.println("\nMail was sent successfully.");
	}

	private static void sendSimple() {
		// TODO Auto-generated method stub
		
	}

}