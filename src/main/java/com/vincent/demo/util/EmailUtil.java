package com.vincent.demo.util;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.vincent.common.log.Logger;

public class EmailUtil {
	private static final Logger log = new Logger(EmailUtil.class);
	
	public static boolean createEmail(String host, String protocol, Integer port, String userName, String passWord, String charset, String from, String to,String subject, String text, boolean isHtml){
		boolean is = false;
		try {
			// 邮件服务器地址，
			JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
			mailSender.setHost(host); // 服务器地址
			mailSender.setProtocol(protocol); // 协议
			mailSender.setPort(port); // 端口号
			mailSender.setUsername(userName);
			mailSender.setPassword(passWord);

			// 设置发件人。
			MimeMessageHelper helper = new MimeMessageHelper(mailSender.createMimeMessage(), charset); // charset UTF-8
			helper.setFrom(from);// 发件人 xxx@qq.com
			helper.setTo(to); // 接收者 mailTask@163.com
			helper.setSubject(subject); // 邮件主题"Hello Word"
			helper.setText(text, isHtml); // 1.文件内容 2.true 代表THML格式，
			mailSender.send(helper.getMimeMessage()); // 发送
			log.info("send Email succeed");
			is = true;
		} catch (Exception e) {
			log.error("createEmail error message:" + e.getMessage());
			e.printStackTrace();
		}
		return is;
	}

	 public static void main(String[] args) {
		 createEmail("smtp.exmail.qq.com","smtp",25,"changluo@inforstack.com","tomcat0","UTF-8","changluo@inforstack.com","1543453892@qq.com","Hi，","你好,测试数据请勿回复 ",false);
	}
}
