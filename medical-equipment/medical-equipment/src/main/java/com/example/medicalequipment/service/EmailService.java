package com.example.medicalequipment.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import com.example.medicalequipment.iservice.IEmailService;
import com.example.medicalequipment.model.ActivationToken;
import com.example.medicalequipment.model.RegistratedUser;
@Service
public class EmailService implements IEmailService{
	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	private Environment env;

	public void sendVerificationEmail(RegistratedUser user)
			throws MailException, InterruptedException, MessagingException {
		System.out.println(user.getVerificationCode());
	    String toAddress = user.getEmail();
	    String fromAddress = "Your email address";
	    String senderName = "Your company name";
	    String subject = "Please verify your registration";
	    String content = "Dear [[name]],<br>"
	            + "Please click the link below to verify your registration:<br>"
	            + "<a href=\"http://localhost:81/api/auth/verify/" + user.getVerificationCode() + "\">Activate Account</a>" 
	            + "Thank you,<br>"
	            + "Your company name.";
	     
	    MimeMessage message = javaMailSender.createMimeMessage();
	    MimeMessageHelper helper = new MimeMessageHelper(message);
	     
	    helper.setTo(user.getEmail());
        helper.setSubject(subject); 
	    content = content.replace("[[name]]", user.getName());
	    
	    
	    helper.setText(content, true);
	     
	    javaMailSender.send(message);
	     
	}

	
}
