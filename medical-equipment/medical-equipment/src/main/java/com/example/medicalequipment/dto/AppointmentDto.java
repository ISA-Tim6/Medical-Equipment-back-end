package com.example.medicalequipment.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import com.example.medicalequipment.model.Appointment;
import com.example.medicalequipment.model.AppointmentStatus;


public class AppointmentDto {

	private Long appointment_id;
	private CompanyAdminDto admin;
	private LocalDate date;
	private LocalTime time;
	private double duration;
	private AppointmentStatus appointmentStatus;
	
	public AppointmentDto(Appointment appointment) {
		this.appointment_id=appointment.getAppointment_id();
		this.admin=new CompanyAdminDto(appointment.getAdmin());
		this.date=appointment.getDate();
		this.time=appointment.getTime();
		this.duration=appointment.getDuration();
		this.appointmentStatus=appointment.getAppointmentStatus();	
	}



	public Long getAppointment_id() {
		return appointment_id;
	}

	public void setAppointment_id(Long appointment_id) {
		this.appointment_id = appointment_id;
	}

	public CompanyAdminDto getAdmin() {
		return admin;
	}

	public void setAdmin(CompanyAdminDto admin) {
		this.admin = admin;
	}



	public LocalDate getDate() {
		return date;
	}



	public void setDate(LocalDate date) {
		this.date = date;
	}



	public LocalTime getTime() {
		return time;
	}



	public void setTime(LocalTime time) {
		this.time = time;
	}



	public double getDuration() {
		return duration;
	}

	public void setDuration(double duration) {
		this.duration = duration;
	}

	public AppointmentStatus getAppointmentStatus() {
		return appointmentStatus;
	}

	public void setAppointmentStatus(AppointmentStatus appointmentStatus) {
		this.appointmentStatus = appointmentStatus;
	}
	
}
