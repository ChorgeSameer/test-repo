package com.demo.productinventory.utils;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MailSender {

	@Value("${email.username}")
	private String userName;

	@Value("${email.password}")
	private String passWord;
	

	// Recipient's email ID needs to be mentioned.
	public boolean sendMailWithSubject(String email, String mailBody, String subject) {
	String to = email;
	// Sender's email ID needs to be mentioned
	String from = userName;
	final String username = userName;// change accordingly hh
	final String password = passWord;// change
	// Assuming you are sending email from localhost
	String host = "smtp.gmail.com";
	// Get system properties
	Properties properties = new Properties();
	properties.put("mail.smtp.auth", "true");
	properties.put("mail.smtp.starttls.enable", "true");
	properties.put("mail.smtp.host", host);
	properties.put("mail.smtp.port", "587");
	// Setup mail server
	properties.setProperty("mail.smtp.host", host);
	// Get the default Session object.
	Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
		protected PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(username, password);
		}
	});
	try {
		// Create a default MimeMessage object.
		MimeMessage message = new MimeMessage(session);
		// Set From: header field of the header.
		message.setFrom(new InternetAddress(from));
		// Set To: header field of the header.
		message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
		// Set Subject: header field
		message.setSubject(subject);
		// Send the actual HTML message, as big as you like
		// message.setText(mailBody);
		message.setContent(mailBody, "text/html; charset=utf-8");
		// Send message
		Transport.send(message);
	} catch (MessagingException mex) {
		mex.printStackTrace();
	}

	return true;
	}

}
