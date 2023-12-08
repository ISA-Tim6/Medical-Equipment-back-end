package com.example.medicalequipment.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

@Entity
public class WorkingTimeCalendar {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "workingTimeCalendar_id", unique = true, nullable = false)
	private Long workingTimeCalendar_id;
	@OneToMany()
	//@JoinColumn(name = "appointment_id", nullable=false)
	@JoinTable(name = "appointment_calendar", joinColumns = @JoinColumn(name = "workingTimeCalendar_id", referencedColumnName = "workingTimeCalendar_id"),
	inverseJoinColumns = @JoinColumn(name = "appointment_id", referencedColumnName = "appointment_id"))
	private Set<Appointment> appointments=new HashSet<Appointment>();



	public WorkingTimeCalendar() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Long getWorkingTimeCalendar_id() {
		return workingTimeCalendar_id;
	}



	public void setWorkingTimeCalendar_id(Long workingTimeCalendar_id) {
		this.workingTimeCalendar_id = workingTimeCalendar_id;
	}



	public Set<Appointment> getAppointments() {
		return appointments;
	}



	public void setAppointments(Set<Appointment> appointments) {
		appointments = appointments;
	}



	public WorkingTimeCalendar(Set<Appointment> appointments) {
		super();
		appointments = appointments;
	}

	
}
