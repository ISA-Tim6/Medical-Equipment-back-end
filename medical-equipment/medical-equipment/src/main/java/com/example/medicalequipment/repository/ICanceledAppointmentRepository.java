package com.example.medicalequipment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.medicalequipment.model.CanceledAppointment;
import com.example.medicalequipment.model.Reservation;

@Repository
public interface ICanceledAppointmentRepository extends JpaRepository<CanceledAppointment, Long> {
	@Query("SELECT ca FROM CanceledAppointment ca WHERE ca.user_id = ?1")
    List<CanceledAppointment> findAllByUserId(Long userId);
}
