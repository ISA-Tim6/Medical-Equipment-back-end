package com.example.medicalequipment.dto;

import java.util.HashSet;
import java.util.Set;

import com.example.medicalequipment.model.Appointment;
import com.example.medicalequipment.model.WorkingTimeCalendar;



public class WorkingTimeCalendarDto {

	private Long workingTimeCalendar_id;
	private Set<AppointmentDto> appointments=new HashSet<AppointmentDto>();
	
	public WorkingTimeCalendarDto(WorkingTimeCalendar wtc) {
		this.workingTimeCalendar_id=wtc.getWorkingTimeCalendar_id();
		if(!wtc.getAppointments().isEmpty())
		{
			for(Appointment a:wtc.getAppointments()) {
				appointments.add(new AppointmentDto(a));
			}		
		}
		
	}
}
