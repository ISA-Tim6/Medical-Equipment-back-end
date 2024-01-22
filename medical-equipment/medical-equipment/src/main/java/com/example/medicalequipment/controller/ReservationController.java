package com.example.medicalequipment.controller;

import java.time.LocalTime;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.medicalequipment.dto.ReservationDto;
import com.example.medicalequipment.iservice.IReservationService;
import com.example.medicalequipment.model.Appointment;
import com.example.medicalequipment.model.Reservation;

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
		return reservationService.getAllUserReservation(id);
    }

	@CrossOrigin(origins="http://localhost:4200")
	@GetMapping("/qrs/{id}")
	@PreAuthorize("hasAuthority('ROLE_REGISTRATED_USER')")
    public List<byte[]> getQrsByUser(@PathVariable Long id) throws InterruptedException, MessagingException {
        return reservationService.regenerateQR(id);
    }
    @CrossOrigin(origins="http://localhost:4200")
	@GetMapping("/newqrs/{id}")
	@PreAuthorize("hasAuthority('ROLE_REGISTRATED_USER')")
    public List<byte[]> getNewQrsByUser(@PathVariable Long id) throws InterruptedException, MessagingException {
        return reservationService.regenerateNewQR(id);
    }
    @CrossOrigin(origins="http://localhost:4200")
	@GetMapping("/acceptedqrs/{id}")
	@PreAuthorize("hasAuthority('ROLE_REGISTRATED_USER')")
    public List<byte[]> getAcceptedQrsByUser(@PathVariable Long id) throws InterruptedException, MessagingException {
        return reservationService.regenerateAcceptedQR(id);
    }
    @CrossOrigin(origins="http://localhost:4200")
	@GetMapping("/rejectedqrs/{id}")
	@PreAuthorize("hasAuthority('ROLE_REGISTRATED_USER')")
    public List<byte[]> getRejectedQrsByUser(@PathVariable Long id) throws InterruptedException, MessagingException {
        return reservationService.regenerateRejectedQR(id);
    }

	 @CrossOrigin(origins="http://localhost:4200")
	 @GetMapping(value = "/findNewReservations/{admin_id}")
	 @PreAuthorize("hasAuthority('ROLE_COMPANY_ADMIN')")
	 public ResponseEntity<List<ReservationDto>> findNewReservations(@PathVariable Long admin_id)
	 {
		 List<ReservationDto> result=reservationService.getNewByCompanyAdmin(admin_id);
		 return new ResponseEntity<>(result, HttpStatus.OK);
	 }
	 @CrossOrigin(origins="http://localhost:4200")
	 @GetMapping(value = "/deliverReservation/{id}")
	 @PreAuthorize("hasAuthority('ROLE_COMPANY_ADMIN')")
	 public ResponseEntity<List<ReservationDto>> deliverReservation(@PathVariable Long id)
	 {
		 List<ReservationDto> result=reservationService.DeliverReservation(id);
		 return new ResponseEntity<>(result, HttpStatus.OK);
	 }

}
