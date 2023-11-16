package com.example.medicalequipment.iservice;

import org.springframework.mail.MailException;

import com.example.medicalequipment.model.RegistratedUser;

public interface IEmailService {
	public void sendNotificaitionSync(RegistratedUser user) throws MailException, InterruptedException;
}
