package com.example.medicalequipment.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id", referencedColumnName = "user_id")
	private CompanyAdmin admin;
	@Column(name = "localDateTime",nullable = false)
	private LocalDateTime dateTime;
	@Column(name = "duration",nullable = false)
	private double duration;
	@Column(name = "appointmentStatus",nullable = false)
	private AppointmentStatus appointmentStatus;
	public Appointment() 
	{
		
	}
	public Appointment(CompanyAdmin admin, LocalDateTime dateTime, double duration) {
		super();
		this.admin = admin;
		this.dateTime = dateTime;
		this.duration = duration;
	}
	public CompanyAdmin getAdmin() {
		return admin;
	}
	public void setAdmin(CompanyAdmin admin) {
		this.admin = admin;
	}
	public LocalDateTime getDateTime() {
		return dateTime;
	}
	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}
	public double getDuration() {
		return duration;
	}
	public void setDuration(double duration) {
		this.duration = duration;
	}
	
}
