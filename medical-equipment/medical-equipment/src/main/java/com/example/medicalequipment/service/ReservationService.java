package com.example.medicalequipment.service;

import java.io.IOException;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Service;

import com.example.medicalequipment.iservice.IReservationService;
import com.example.medicalequipment.model.Reservation;
import com.example.medicalequipment.repository.IItemRepository;
import com.example.medicalequipment.repository.IRegistratedUserRepository;
import com.example.medicalequipment.repository.IReservationRepository;
import com.google.zxing.WriterException;

@Service
public class ReservationService implements IReservationService {

	@Autowired
	private final IReservationRepository ReservationRepository;
	@Autowired
	private final EmailService emailService;
	private static final String QR_CODE_IMAGE_PATH = "./src/main/resources/images/QRCode.png";
	public ReservationService(IReservationRepository reservationRepository, EmailService emailService){
    	this.ReservationRepository = reservationRepository;
		this.emailService = emailService;
    }
	private String generateReservationDetails(Reservation reservation) {
		String date[] = reservation.getAppointment().getDate().toString().split(" ");
		String appDetails = "Appointment details \n";
				

		return appDetails;
	}

	private String generateEmailMessage(Reservation reservation) {
		String date[] = reservation.getAppointment().getDate().toString().split(" ");
		String message = "Hi " + reservation.getUser().getName() + " " + reservation.getUser().getSurname() + ". You are successfuly scheduled appointment in our center."
				+ " You appointment are on " + date[0] + " " + date[1] + " " + date[2] + " " + " at " + reservation.getAppointment().getTime().toString() + ".";
		return message;
	}
	@Override
	public Reservation save(Reservation reservation) throws MailException, InterruptedException, MessagingException {
		// TODO Auto-generated method stub
		String qrCodeData = generateReservationDetails(reservation);
		try {
			QRCodeGenerator.generateQRCodeImage(qrCodeData,250,250, QR_CODE_IMAGE_PATH);
		}catch (WriterException | IOException e) {}
		//emailService.sendConfirmationEmail(reservation.getUser(), reservation, QR_CODE_IMAGE_PATH);
		return ReservationRepository.save(reservation);
	}

}
