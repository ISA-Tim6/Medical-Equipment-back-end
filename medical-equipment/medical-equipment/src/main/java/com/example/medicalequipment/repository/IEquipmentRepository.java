package com.example.medicalequipment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.medicalequipment.model.Address;
import com.example.medicalequipment.model.Equipment;
@Repository
public interface IEquipmentRepository extends JpaRepository<Equipment, Long>{
	@Query("select e from Equipment e join fetch e.companies where e.name=?1")
	public List<Equipment> searchByName(String name);
}
