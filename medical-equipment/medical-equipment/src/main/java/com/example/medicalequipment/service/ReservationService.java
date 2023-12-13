package com.example.medicalequipment.service;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Service;

import com.example.medicalequipment.iservice.IReservationService;
import com.example.medicalequipment.model.Reservation;
import com.example.medicalequipment.repository.IItemRepository;
import com.example.medicalequipment.repository.IRegistratedUserRepository;
import com.example.medicalequipment.repository.IReservationRepository;

@Service
public class ReservationService implements IReservationService {

	@Autowired
	private final IReservationRepository ReservationRepository;
	
	public ReservationService(IReservationRepository reservationRepository){
    	this.ReservationRepository = reservationRepository;
    }
	@Override
	public Reservation save(Reservation reservation) throws MailException, InterruptedException, MessagingException {
		// TODO Auto-generated method stub
		return ReservationRepository.save(reservation);
	}

}
