package com.utils;

import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailUtils {
	
	@Autowired
	private JavaMailSender emailSender;
	
	public void sendSimpleMessage(String to,String subject,String text,List<String> list) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("");
		message.setTo(to);
		message.setSubject(subject);
		message.setText(text);
		if (list != null && list.size() > 0)
		message.setCc(getCcArray(list));
		
		emailSender.send(message);
	}
	
	private String[] getCcArray(List<String> ccList) {
		String[] cc = new String[ccList.size()];
		for (int i = 0;i<ccList.size();i++) {
			cc[i] = ccList.get(i);
		}
		return cc;
	}
	
	public void forgotMail(String to,String subject,String password) throws MessagingException {
		MimeMessage message = emailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message,true);
		helper.setFrom("");
		helper.setTo(to);
		helper.setSubject(subject);
		String htmlContent = "<h2>Your Password Information</h2>\n"
				+ "  <p>Hello [Recipient's Name],</p>\n"
				+ "  <p>Your requested password has been generated:</p>\n"
				+ "  <p><strong>Password:</strong> "+password+"</p>\n"
				+ "  <p>Please make sure to keep this password secure and do not share it with anyone.</p>\n"
				+ "  <p>If you have any questions or concerns, feel free to contact our support team.</p>\n"
				+ "  <p>Best regards,</p>\n"
				+ "  <p>Your Cafe Abhinav Team</p>";
		message.setContent(htmlContent,"text/html");
		emailSender.send(message);
	}
	
}
