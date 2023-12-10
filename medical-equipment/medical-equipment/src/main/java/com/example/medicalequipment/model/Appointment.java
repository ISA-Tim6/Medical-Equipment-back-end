package com.example.medicalequipment.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
@Entity
public class Appointment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "appointment_id")
	private Long appointment_id;
	public Long getAppointment_id() {
		return appointment_id;
	}
	public void setAppointment_id(Long appointment_id) {
		this.appointment_id = appointment_id;
	}
	public AppointmentStatus getAppointmentStatus() {
		return appointmentStatus;
	}
	public void setAppointmentStatus(AppointmentStatus appointmentStatus) {
		this.appointmentStatus = appointmentStatus;
	}
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id", referencedColumnName = "user_id")
	private CompanyAdmin admin;
	@Column(name = "localDate",nullable = false)
	private LocalDate date;
	@Column(name = "localTime",nullable = false)
	private LocalTime time;
	@Column(name = "duration",nullable = false)
	private double duration;
	@Column(name = "appointmentStatus",nullable = false)
	private AppointmentStatus appointmentStatus;
	public Appointment() 
	{
		
	}
	public Appointment(CompanyAdmin admin, LocalDate date, double duration,LocalTime time) {
		super();
		this.admin = admin;
		this.date = date;
		this.time=time;
		this.duration = duration;
	}
	public LocalTime getTime() {
		return time;
	}
	public void setTime(LocalTime time) {
		this.time = time;
	}
	public CompanyAdmin getAdmin() {
		return admin;
	}
	public void setAdmin(CompanyAdmin admin) {
		this.admin = admin;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public double getDuration() {
		return duration;
	}
	public void setDuration(double duration) {
		this.duration = duration;
	}
	
}
