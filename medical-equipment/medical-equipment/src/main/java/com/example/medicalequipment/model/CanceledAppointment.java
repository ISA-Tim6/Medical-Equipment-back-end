package com.example.medicalequipment.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class CanceledAppointment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "canceledAppointment_id")
	private Long id;
	private Long user_id;
	private Long appointment_id;
	private Date cancelingDate;
	public CanceledAppointment() {
		super();
	}
	public CanceledAppointment(Long userId, Long appointmentId, Date cancelingDate) {
		super();
		this.user_id = userId;
		this.appointment_id = appointmentId;
		this.cancelingDate = cancelingDate;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getUserId() {
		return user_id;
	}
	public void setUserId(Long userId) {
		this.user_id = userId;
	}
	public Long getAppointmentId() {
		return appointment_id;
	}
	public void setAppointmentId(Long appointmentId) {
		this.appointment_id = appointmentId;
	}
	public Date getCancelingDate() {
		return cancelingDate;
	}
	public void setCancelingDate(Date cancelingDate) {
		this.cancelingDate = cancelingDate;
	}
	
}
