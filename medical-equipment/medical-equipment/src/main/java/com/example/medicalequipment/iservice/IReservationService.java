package com.example.medicalequipment.iservice;

import javax.mail.MessagingException;

import org.springframework.mail.MailException;
import org.springframework.stereotype.Service;

import com.example.medicalequipment.model.RegistratedUser;
import com.example.medicalequipment.model.Reservation;

@Service
public interface IReservationService {
	Reservation save(Reservation reservation) throws MailException, InterruptedException, MessagingException;

}
