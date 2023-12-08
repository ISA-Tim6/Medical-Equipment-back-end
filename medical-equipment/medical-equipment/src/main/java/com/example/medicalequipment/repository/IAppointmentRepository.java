package com.example.medicalequipment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.medicalequipment.model.Appointment;

@Repository
public interface IAppointmentRepository extends JpaRepository<Appointment, Long>{

}
