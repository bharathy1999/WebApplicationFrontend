package com.signProfile.controller;

import java.util.Properties;
import java.util.Random;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
public class MultiFactorAuthentication {

	public String sendOTP(String toEmail) {
	    Random random=new Random();
	    int randomNumber=random.nextInt(999999);
	    
	 String senderEmail="guruvijay2017@gmail.com";
	 String password="nibx xaao aygw dbuh";
	 
	 Properties properties=new Properties();
	 properties.put("mail.smtp.host", "smtp.gmail.com");
	 properties.put("mail.smtp.auth", "true");
	 properties.put("mail.smtp.port", "587");
	 properties.put("mail.smtp.starttls.enable", "true");
	 properties.put("mail.smtp.ssl.protocols", "TLSv1.2");
	 
	 
	 Session session=Session.getInstance(properties,new Authenticator(){
		 protected PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(senderEmail,password);
		 }
	 });
	
		
		 try {
			 Message message=new MimeMessage(session);
			 message.setFrom(new InternetAddress(senderEmail));
			 message.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
			 message.setSubject("Your Trainee verification from ****");
			 message.setText("your otp number is"+String.format("%06d", randomNumber));
			 Transport.send(message);
		}
		 catch (MessagingException e) {
			e.printStackTrace();
		}
		
	System.out.println(randomNumber);
	    
	return String.format("%06d", randomNumber);
}
}
