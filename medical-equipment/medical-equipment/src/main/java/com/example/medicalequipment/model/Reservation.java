package com.example.medicalequipment.model;

import java.util.ArrayList;

public class Reservation {
	private RegistratedUser user;
	private ArrayList<Item> items;
	private Appointment appointment;
	private ReservationStatus reservationStatus;
	public Reservation() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Reservation(RegistratedUser user, ArrayList<Item> items, Appointment appointment) {
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
	public ArrayList<Item> getItems() {
		return items;
	}
	public void setItems(ArrayList<Item> items) {
		this.items = items;
	}
	public Appointment getAppointment() {
		return appointment;
	}
	public void setAppointment(Appointment appointment) {
		this.appointment = appointment;
	}
	
}
