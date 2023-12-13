package com.example.medicalequipment.dto;

public class CompanyCalendarDto {

	public WorkingTimeCalendarDto workingTimeCalendarDto;

	public CompanyCalendarDto(WorkingTimeCalendarDto workingTimeCalendarDto) {
		super();
		this.workingTimeCalendarDto = workingTimeCalendarDto;
	}

	public WorkingTimeCalendarDto getWorkingTimeCalendarDto() {
		return workingTimeCalendarDto;
	}

	public void setWorkingTimeCalendarDto(WorkingTimeCalendarDto workingTimeCalendarDto) {
		this.workingTimeCalendarDto = workingTimeCalendarDto;
	}
	
	

}
