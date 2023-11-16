package com.example.medicalequipment.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
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
public class QRCode {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long qr_code_id;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "reservation_id", referencedColumnName = "reservation_id")
	private Reservation reservation;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "qr_code_user", joinColumns = @JoinColumn(name = "qr_code_id", referencedColumnName = "qr_code_id"),
	inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "user_id"))
	private RegistratedUser user;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "qr_code_equipment", joinColumns = @JoinColumn(name = "qr_code_id", referencedColumnName = "qr_code_id"),
	inverseJoinColumns = @JoinColumn(name = "equipment_id", referencedColumnName = "equipment_id"))
	private Set<Equipment> equipments = new HashSet<Equipment>();
	
	public Reservation getReservation() {
		return reservation;
	}
	
	public Long getQr_code_id() {
		return qr_code_id;
	}
	public void setQr_code_id(Long qr_code_id) {
		this.qr_code_id = qr_code_id;
	}
	
	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}
	public RegistratedUser getUser() {
		return user;
	}
	public void setUser(RegistratedUser user) {
		this.user = user;
	}
	public Set<Equipment> getEquipments() {
		return equipments;
	}
	public void setEquipments(Set<Equipment> equipments) {
		this.equipments = equipments;
	}
	public QRCode(Reservation reservation, RegistratedUser user, Set<Equipment> equipments) {
		super();
		this.reservation = reservation;
		this.user = user;
		this.equipments = equipments;
	}
	public QRCode() {
		super();
		// TODO Auto-generated constructor stub
	}
}
