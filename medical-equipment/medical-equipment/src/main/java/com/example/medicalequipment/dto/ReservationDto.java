package com.example.medicalequipment.dto;

import java.util.HashSet;
import java.util.Set;

import com.example.medicalequipment.model.Appointment;
import com.example.medicalequipment.model.Item;
import com.example.medicalequipment.model.RegistratedUser;
import com.example.medicalequipment.model.Reservation;

public class ReservationDto {

	private Long reservation_id;
	private RegistratedUser user;
	private Set<ItemDto> items;
	private AppointmentDto appointment;
	private String reservationStatus;
	public String getReservationStatus() {
		return reservationStatus;
	}
	public void setReservationStatus(String reservationStatus) {
		this.reservationStatus = reservationStatus;
	}
	public ReservationDto() {}
	public ReservationDto(Long reservation_id, RegistratedUser user, Set<Item> items, Appointment appointment, String status) {
		super();
		this.reservation_id = reservation_id;
		this.user = user;
		this.items = new HashSet<ItemDto>();
		if(!items.isEmpty())
		{
			for (Item item : items) {
				this.items.add(new ItemDto(item));
			}
		}
		this.appointment = new AppointmentDto(appointment);
		this.reservationStatus = status;
	}
	public ReservationDto(Reservation r) {
		this.reservation_id = r.getReservation_id();
		this.user = r.getUser();
		this.items = new HashSet<ItemDto>();
		if(!items.isEmpty())
		{
			for (Item item : r.getItems()) {
				this.items.add(new ItemDto(item));
			}
		}
		this.appointment = new AppointmentDto(r.getAppointment());
		this.reservationStatus = r.getReservationStatus().toString();
	}
	public Long getReservation_id() {
		return reservation_id;
	}
	public void setReservation_id(Long reservation_id) {
		this.reservation_id = reservation_id;
	}
	public RegistratedUser getUser() {
		return user;
	}
	public void setUser(RegistratedUser user) {
		this.user = user;
	}
	public Set<ItemDto> getItems() {
		return items;
	}
	public void setItems(Set<ItemDto> items) {
		this.items = items;
	}
	public AppointmentDto getAppointment() {
		return appointment;
	}
	public void setAppointment(AppointmentDto appointment) {
		this.appointment = appointment;
	}
}
