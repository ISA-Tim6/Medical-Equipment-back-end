package com.example.medicalequipment.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.medicalequipment.model.Reservation;

@Repository
public interface IReservationRepository extends JpaRepository<Reservation, Long>{
	@Query(value = "SELECT r.reservation_id from reservation r inner join reservation_item ri on "
			+ "ri.reservation_id=r.reservation_id inner join item i on ri.item_id=i.item_id "
			+ "inner join equipment_item ei on ei.item_id=i.item_id "
			+ "where r.reservation_status=0 "
			+ "and ei.equipment_id=:equipment_id", nativeQuery = true)
	List<Long> getNewReservationForEquipment(Long equipment_id);
}
