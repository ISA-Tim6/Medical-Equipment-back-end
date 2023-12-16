package com.example.medicalequipment.repository;

import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.medicalequipment.model.Appointment;
import com.example.medicalequipment.model.Item;
import com.example.medicalequipment.model.RegistratedUser;
import com.example.medicalequipment.model.Reservation;

@Repository
public interface IReservationRepository extends JpaRepository<Reservation, Long> {
	@Query("select r from Reservation r join fetch r.items join fetch r.user join fetch r.appointment a join fetch a.admin ca join fetch ca.company c where r.reservation_id=?1")
	List<Reservation> getFullReservation(Long id);
	@Query("select r from Reservation r join fetch r.user u join fetch r.appointment a join fetch a.admin ca join fetch ca.company c where u.user_id=?1")
	List<Reservation> getAllUserReservation(Long id);
}
