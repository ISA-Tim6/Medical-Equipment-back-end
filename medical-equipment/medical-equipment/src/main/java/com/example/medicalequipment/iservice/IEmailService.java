package com.example.medicalequipment.iservice;

import javax.mail.MessagingException;

import org.springframework.mail.MailException;

import com.example.medicalequipment.model.ActivationToken;
import com.example.medicalequipment.model.RegistratedUser;

public interface IEmailService {
	public void sendVerificationEmail(RegistratedUser user) throws MailException, InterruptedException, MessagingException;
	void sendDeliveryEmail(String address, String mail);
}
