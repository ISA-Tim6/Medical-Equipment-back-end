package com.example.medicalequipment.repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.medicalequipment.model.Appointment;
import com.example.medicalequipment.model.RegistratedUser;

@Repository
public interface IAppointmentRepository extends JpaRepository<Appointment, Long>{

	@Query(value = "SELECT appointment_id from working_time_calendar_appointments w inner join appointment a on "
			+ "	a.appointment_id=w.appointments_appointment_id"
			+ "	where w.working_time_calendar_working_time_calendar_id=:wtcid"
			+ "	and"
			+ "	a.local_date=:date"
			+ "	and"
			+ "	a.local_time>=:startTime"
			+ "	and"
			+ "	a.local_time<=:endTime", nativeQuery = true)
	List<Long> getAllOverlappingStart(Long wtcid,LocalTime startTime,LocalTime endTime,LocalDate date);
	
	@Query(value = "SELECT appointment_id from working_time_calendar_appointments w inner join appointment a on "
			+ "	a.appointment_id=w.appointments_appointment_id"
			+ "	where w.working_time_calendar_working_time_calendar_id=:wtcid"
			+ "	and"
			+ "	a.local_date=:date"
			+ "	and"
			+ "	a.end>:startTime"
			+ "	and"
			+ "	a.end<:endTime", nativeQuery = true)
	List<Long> getAllOverlappingEnd(Long wtcid,LocalTime startTime,LocalTime endTime,LocalDate date);
}
