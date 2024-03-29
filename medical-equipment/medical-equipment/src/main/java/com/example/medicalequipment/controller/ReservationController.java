package com.example.medicalequipment.controller;

import java.io.File;
import java.io.IOException;
import java.time.LocalTime;

import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.aspectj.util.FileUtil;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.medicalequipment.dto.ReservationDto;
import com.example.medicalequipment.iservice.IReservationService;
import com.example.medicalequipment.model.Appointment;
import com.example.medicalequipment.model.CanceledAppointment;
import com.example.medicalequipment.model.RegistratedUser;
import com.example.medicalequipment.model.Reservation;
import com.example.medicalequipment.service.CanceledAppointmentService;

import io.micrometer.core.annotation.Timed;

@RestController
@RequestMapping(path="api/reservation/")
public class ReservationController {
	@Autowired
	private IReservationService reservationService;
	public ReservationController(IReservationService service)
	{
		this.reservationService = service;
	}
	@Timed(value = "reservation.time", description = "Time taken to save reservation")
	@CrossOrigin(origins="http://localhost:4200")
    @PostMapping("saveReservation/{penals}")
	@PreAuthorize("hasAuthority('ROLE_REGISTRATED_USER')")
    public Reservation save(@RequestBody Reservation reservation, @PathVariable int penals) throws MailException, InterruptedException, MessagingException {
    	if(penals>=3)
    		return null;
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
	 public ResponseEntity<ReservationDto> findNewReservations(@PathVariable Long admin_id)
	 {
		 ReservationDto result=reservationService.getNewByCompanyAdmin(admin_id);
		 return new ResponseEntity<>(result, HttpStatus.OK);
	 }
	 @Timed(value = "delivery.time", description = "Time taken to deliver reservation")
	 @CrossOrigin(origins="http://localhost:4200")
	 @GetMapping(value = "/deliverReservation/{id}")
	 @PreAuthorize("hasAuthority('ROLE_COMPANY_ADMIN')")
	 public ResponseEntity<ReservationDto> deliverReservation(@PathVariable Long id)
	 {
		 ReservationDto result=reservationService.DeliverReservation(id);
		 return new ResponseEntity<>(result, HttpStatus.OK);
	 }
	 @CrossOrigin(origins="http://localhost:4200")
	 @PostMapping("/uploadQrCode")
	 @PreAuthorize("hasAuthority('ROLE_COMPANY_ADMIN')")
	    public ReservationDto uploadQrCode(@RequestParam MultipartFile qrCodeFile) {
		 	ReservationDto qrContent = new ReservationDto();
	        try {
	           qrContent = reservationService.deliverUsingQRCode(qrCodeFile.getBytes());
	           
	        } catch (IOException e) {
	            
	        }

	        return qrContent;
	    }

  @CrossOrigin(origins="http://localhost:4200")
	    @GetMapping("/acceptedReservations/{id}")
		@PreAuthorize("hasAuthority('ROLE_REGISTRATED_USER')")
	    public List<ReservationDto> getAcceptedReservationsByUser(@PathVariable Long id){
			return reservationService.getAcceptedReservationsByUser(id);
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
