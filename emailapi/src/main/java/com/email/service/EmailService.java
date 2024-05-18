package com.email.service;

import java.util.Properties;

import org.springframework.stereotype.Service;

import jakarta.mail.*;
import jakarta.mail.Message;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;


@Service
public class EmailService {
	
	
	public boolean sendEmail(String subject, String message, String to) {
		
		
		boolean f = false;
		String from = "vikasnagre@gmail.com";
				
		
		
		//variable for gmail
		String host = "smtp.gmail.com";
		
		//get the system properties
		Properties properties = new Properties();
		System.out.println("Properties: "+ properties);
		
		//setting important information to properties object
		
		//host set 
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "587");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.auth", "true");
		
		
		String username = "vikasnagre@gmail.com";
		String password = "password";
		/*this password you will get from the google account by following steps
			step1: open google account and go to manage account
			step2: open security section and enable two step verification
			step3: back to security section and click on the 'app password'
			step4: type name for specific app and copy the code and paste it in your code
		*/
		//step1: to get the sessing obhect..
		
	Session session = Session.getInstance(properties, new Authenticator() {
	
		protected PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(username,password );
		}		
	});
		session.setDebug(true);
		//step 2: compose the message[text, multi media]
		MimeMessage mimeMessage =new MimeMessage(session);
		
		try {
			//from email
			mimeMessage.setFrom(new InternetAddress(from));
			
			//adding recipient to message
			mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
    		
    		//adding subject to message
			mimeMessage.setSubject(subject);
			
			//adding text to message
			mimeMessage.setText(message);
			
			//step3: send the message using transport class
			Transport.send(mimeMessage);
			
			System.out.println("Sent success.....");
			
			f=true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return f;
		
	}
}
