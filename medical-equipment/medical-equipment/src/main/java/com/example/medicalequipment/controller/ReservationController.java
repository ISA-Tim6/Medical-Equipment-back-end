package com.example.medicalequipment.controller;

import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.medicalequipment.iservice.IReservationService;
import com.example.medicalequipment.model.Appointment;
import com.example.medicalequipment.model.CanceledAppointment;
import com.example.medicalequipment.model.Reservation;
import com.example.medicalequipment.service.CanceledAppointmentService;

@RestController
@RequestMapping(path="api/reservation/")
public class ReservationController {
	@Autowired
	private IReservationService reservationService;
	public ReservationController(IReservationService service)
	{
		this.reservationService = service;
	}
	@CrossOrigin(origins="http://localhost:4200")
    @PostMapping("saveReservation")
	@PreAuthorize("hasAuthority('ROLE_REGISTRATED_USER')")
    public Reservation save(@RequestBody Reservation reservation) throws MailException, InterruptedException, MessagingException {
    	return reservationService.save(reservation);
    }
	@CrossOrigin(origins="http://localhost:4200")
    @GetMapping("getFutureReservation/{id}")
	@PreAuthorize("hasAuthority('ROLE_REGISTRATED_USER')")
    public List<Appointment> getAllFutureReservation(@PathVariable Long id) throws MailException, InterruptedException, MessagingException {
    	System.out.println("usao u kontroler");
		return reservationService.getAllUserReservation(id);
    }
	@CrossOrigin(origins = "http://localhost:4200")
	@PreAuthorize("hasAuthority('ROLE_REGISTRATED_USER')")
	@PostMapping("/cancelAppointment/{appointmentId}")
	public boolean cancelAppointment(@PathVariable Long appointmentId ) {
		return reservationService.cancelReservation(appointmentId);
	}
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("canceledAppointments/{userId}")
	@PreAuthorize("hasAuthority('ROLE_REGISTRATED_USER')")
	public List<CanceledAppointment> canceledAppointments(@PathVariable Long userId) {
		System.out.println("uslo");
		return reservationService.getCanceledAppointments(userId);
	}
}
