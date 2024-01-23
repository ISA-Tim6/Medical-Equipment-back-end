package com.example.medicalequipment.iservice;

import java.util.List;

import javax.mail.MessagingException;

import org.springframework.mail.MailException;
import org.springframework.stereotype.Service;
import com.example.medicalequipment.dto.ReservationDto;
import com.example.medicalequipment.model.Appointment;
import com.example.medicalequipment.model.RegistratedUser;
import com.example.medicalequipment.model.Reservation;


@Service
public interface IReservationService {
	Reservation save(Reservation reservation) throws MailException, InterruptedException, MessagingException;
	List<ReservationDto> getAllByCompany(Long company_id);
	List<Reservation> getFullReservation(Long id);
	List<Appointment> getAllUserReservation(Long id);
	void checkExpiration();
	ReservationDto getNewByCompanyAdmin(Long admin_id);
	ReservationDto DeliverReservation(Long id);

}
