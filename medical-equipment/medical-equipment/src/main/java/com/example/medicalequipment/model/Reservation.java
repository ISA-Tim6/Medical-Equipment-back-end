package com.example.medicalequipment.model;

import java.util.ArrayList;
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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Reservation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long reservation_id;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "reservation_user", joinColumns = @JoinColumn(name = "reservation_id", referencedColumnName = "reservation_id"),
			inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "user_id"))
	private RegistratedUser user;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "reservation_item", joinColumns = @JoinColumn(name = "reservation_id", referencedColumnName = "reservation_id"),
	inverseJoinColumns = @JoinColumn(name = "item_id", referencedColumnName = "item_id"))
	private Set<Item> items;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "appointment_id", referencedColumnName = "appointment_id")
	private Appointment appointment;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "qr_code_id", referencedColumnName = "qr_code_id")
	private QRCode qr_code;
	
	public Long getReservation_id() {
		return reservation_id;
	}
	public void setReservation_id(Long reservation_id) {
		this.reservation_id = reservation_id;
	}
	public QRCode getQr_code() {
		return qr_code;
	}
	public void setQr_code(QRCode qr_code) {
		this.qr_code = qr_code;
	}
	public ReservationStatus getReservationStatus() {
		return reservationStatus;
	}
	public void setReservationStatus(ReservationStatus reservationStatus) {
		this.reservationStatus = reservationStatus;
	}
	@Column(name="reservation_status")
	private ReservationStatus reservationStatus;
	public Reservation() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Reservation(RegistratedUser user, Set<Item> items, Appointment appointment) {
		super();
		this.user = user;
		this.items = items;
		this.appointment = appointment;
	}
	public RegistratedUser getUser() {
		return user;
	}
	public void setUser(RegistratedUser user) {
		this.user = user;
	}
	public Set<Item> getItems() {
		return items;
	}
	public void setItems(Set<Item> items) {
		this.items = items;
	}
	public Appointment getAppointment() {
		return appointment;
	}
	public void setAppointment(Appointment appointment) {
		this.appointment = appointment;
	}
	
}
