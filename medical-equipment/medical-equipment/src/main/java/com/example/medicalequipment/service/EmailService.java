package com.example.medicalequipment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import com.example.medicalequipment.iservice.IEmailService;
import com.example.medicalequipment.model.RegistratedUser;
@Service
public class EmailService implements IEmailService{
	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	private Environment env;


	public void sendNotificaitionSync(RegistratedUser user) throws MailException, InterruptedException {
		Thread.sleep(100);
		System.out.println("Slanje emaila...");

		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(user.getEmail());
		mail.setFrom("medicinskaopremaisa@gmail.com");
		mail.setSubject("Mail za aktivaciju naloga");
		mail.setText("Pozdrav " + user.getName() + ",\n\nuspješno ste registrovani.");
		javaMailSender.send(mail);

		System.out.println("Email poslat!");
	}
}
