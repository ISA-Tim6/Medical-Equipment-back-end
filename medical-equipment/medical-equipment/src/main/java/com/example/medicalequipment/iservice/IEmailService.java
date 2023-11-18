package com.example.medicalequipment.iservice;

import javax.mail.MessagingException;

import org.springframework.mail.MailException;

import com.example.medicalequipment.model.ActivationToken;
import com.example.medicalequipment.model.RegistratedUser;

public interface IEmailService {
	public void sendNotificaitionSync(RegistratedUser user,ActivationToken token) throws MailException, InterruptedException, MessagingException;
}
