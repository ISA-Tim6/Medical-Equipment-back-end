package com.example.medicalequipment.model;

import java.util.ArrayList;

public class QRCode {
	private Reservation reservation;
	private RegistratedUser user;
	private ArrayList<Equipment> equipments;
	public Reservation getReservation() {
		return reservation;
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
	public ArrayList<Equipment> getEquipments() {
		return equipments;
	}
	public void setEquipments(ArrayList<Equipment> equipments) {
		this.equipments = equipments;
	}
	public QRCode(Reservation reservation, RegistratedUser user, ArrayList<Equipment> equipments) {
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
