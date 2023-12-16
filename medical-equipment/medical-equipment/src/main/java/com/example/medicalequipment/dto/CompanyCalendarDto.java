package com.example.medicalequipment.dto;

import java.util.ArrayList;
import java.util.List;

public class CompanyCalendarDto {

	public WorkingTimeCalendarDto workingTimeCalendarDto;
	public List<ReservationDto> reservations = new ArrayList<ReservationDto>();

	public CompanyCalendarDto(WorkingTimeCalendarDto workingTimeCalendarDto, List<ReservationDto> reservations) {
		super();
		this.workingTimeCalendarDto = workingTimeCalendarDto;
		this.reservations = reservations;
	}

	public WorkingTimeCalendarDto getWorkingTimeCalendarDto() {
		return workingTimeCalendarDto;
	}

	public void setWorkingTimeCalendarDto(WorkingTimeCalendarDto workingTimeCalendarDto) {
		this.workingTimeCalendarDto = workingTimeCalendarDto;
	}

	public List<ReservationDto> getReservations() {
		return reservations;
	}

	public void setReservations(List<ReservationDto> reservations) {
		this.reservations = reservations;
	}
	
	

}
