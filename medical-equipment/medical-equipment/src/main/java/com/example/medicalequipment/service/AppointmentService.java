package com.example.medicalequipment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.medicalequipment.iservice.IAppointmentService;
import com.example.medicalequipment.model.Appointment;
import com.example.medicalequipment.repository.IAppointmentRepository;

@Service
public class AppointmentService implements IAppointmentService{
	@Autowired
	private final IAppointmentRepository AppointmentRepository;

	public AppointmentService(IAppointmentRepository appointmentRepository) {
		this.AppointmentRepository = appointmentRepository;
	}
	@Override
	public Appointment save(Appointment company) {
		// TODO Auto-generated method stub
		return null;
	}
}
