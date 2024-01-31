package com.example.medicalequipment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.medicalequipment.model.Address;
import com.example.medicalequipment.model.Equipment;
@Repository
public interface IEquipmentRepository extends JpaRepository<Equipment, Long>{
	@Query("select e from Equipment e join fetch e.companies where LOWER(e.name) LIKE LOWER(concat('%', :name, '%'))")
	public List<Equipment> searchByName(String name);
	
	@Query("select e from Equipment e join fetch e.companies c where LOWER(e.name) LIKE LOWER(concat('%', :name, '%')) and c.company_id = :company_id")
	public List<Equipment> searchByNameAndCompany(String name, Long company_id);
	
	@Query("select e from Equipment e join fetch e.companies")
	public List<Equipment> search();
	
	@Query("select e from Equipment e join fetch e.companies where e.equipment_id=:id")
	public Equipment getById(Long id);
}
