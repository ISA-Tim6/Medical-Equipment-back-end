package com.example.medicalequipment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.medicalequipment.model.Item;
import com.example.medicalequipment.model.Reservation;

@Repository
public interface IReservationRepository extends JpaRepository<Reservation, Long> {

}
