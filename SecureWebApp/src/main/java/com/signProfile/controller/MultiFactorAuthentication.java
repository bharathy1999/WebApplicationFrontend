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

// This class provides to sent a otp to sigin user email for verification
public class MultiFactorAuthentication {

	public String sendOTP(String toEmail) {
		// this Random object give a random number from 0 to 999999 for otp
		Random random = new Random();
		int randomNumber = random.nextInt(999999);

		String senderEmail = "guruvijay2017@gmail.com";
		String password = "nibx xaao aygw dbuh";

		Properties properties = new Properties();
		properties.put("mail.smtp.host", "smtp.gmail.com");// used a SMTP server for sending a mail to user
		properties.put("mail.smtp.auth", "true");// authentication is used when connecting to a SMTP server
		properties.put("mail.smtp.port", "587");// this port is for TLS based SMTP server connection
		properties.put("mail.smtp.starttls.enable", "true");// this enable TLS connection
		properties.put("mail.smtp.ssl.protocols", "TLSv1.2");// it specifies TLS version

		Session session = Session.getInstance(properties, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(senderEmail, password);
			}
		});

		// this is responsible for send a message to user
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(senderEmail));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
			message.setSubject("Your Trainee verification from ****");
			// In case the random object gives less than 6 numbers it add a zeros for
			// additional digits
			message.setText("your otp number is" + String.format("%06d", randomNumber));
			Transport.send(message);
		} catch (MessagingException e) {
			e.printStackTrace();
		}

		System.out.println(randomNumber);

		return String.format("%06d", randomNumber);
	}
}
