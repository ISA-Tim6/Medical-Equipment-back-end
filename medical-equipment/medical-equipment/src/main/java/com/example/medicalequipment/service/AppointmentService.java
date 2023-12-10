package com.example.medicalequipment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.medicalequipment.iservice.IAppointmentService;
import com.example.medicalequipment.model.Appointment;
import com.example.medicalequipment.repository.IAppointmentRepository;

@Service
public class AppointmentService implements IAppointmentService{
	public AppointmentService(IAppointmentRepository appointmentRepository) {
	}
}
