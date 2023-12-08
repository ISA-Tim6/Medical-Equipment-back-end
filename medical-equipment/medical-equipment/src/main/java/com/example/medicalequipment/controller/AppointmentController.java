package com.example.medicalequipment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.medicalequipment.dto.CompanyDto;
import com.example.medicalequipment.iservice.IAppointmentService;
import com.example.medicalequipment.model.Address;
import com.example.medicalequipment.model.Company;
import com.example.medicalequipment.model.Equipment;

@RestController
@RequestMapping(path="api/appointment")
public class AppointmentController {

	@Autowired

	private IAppointmentService appointmentService;
	
	public AppointmentController(IAppointmentService service) {
		this.appointmentService = service;
	}	
	
	

	
}
