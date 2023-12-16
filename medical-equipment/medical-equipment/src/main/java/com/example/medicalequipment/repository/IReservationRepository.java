package com.example.medicalequipment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
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
public interface IReservationRepository extends JpaRepository<Reservation, Long>{
	@Query(value = "SELECT r.reservation_id from reservation r inner join reservation_item ri on "
			+ "ri.reservation_id=r.reservation_id inner join item i on ri.item_id=i.item_id "
			+ "inner join equipment_item ei on ei.item_id=i.item_id "
			+ "where r.reservation_status=0 "
			+ "and ei.equipment_id=:equipment_id", nativeQuery = true)
	List<Long> getNewReservationForEquipment(Long equipment_id);
	
	//@Query("select r from reservation r join fetch appointment a join fetch admin ca where ca.company_id=?1")
	@Query("select r from Reservation r join fetch r.items join fetch r.user join fetch r.appointment a join fetch a.admin ca join fetch ca.company c where c.company_id=?1")
	List<Reservation> getAllByCompany(Long company_id);

	@Query("select r from Reservation r join fetch r.items join fetch r.user join fetch r.appointment a join fetch a.admin ca join fetch ca.company c where r.reservation_id=?1")
	List<Reservation> getFullReservation(Long id);
	
	@Query("select r from Reservation r join fetch r.items join fetch r.user u join fetch r.appointment a join fetch a.admin ca join fetch ca.company c where u.user_id=?1")
	List<Reservation> getAllUserReservation(Long id);

}
