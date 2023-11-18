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


	public void sendNotificaitionSync(RegistratedUser user, ActivationToken token) throws MailException, InterruptedException, MessagingException {
		String subject = "Activate account mail";
		String message = "<html><body>" +
                "<h2>Activate Your Account</h2>" +
                "<p>Click the following link to activate your account:</p>" +
                "<a href=\"http://localhost:81/api/activate/\">Activate Account</a>" +
                "</body></html>";

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
        mimeMessageHelper.setTo(user.getEmail());
        mimeMessageHelper.setSubject(subject);
        mimeMessageHelper.setText(message, true); 
        javaMailSender.send(mimeMessage);
		System.out.println("Email successfully sent!");
	}
	
}
