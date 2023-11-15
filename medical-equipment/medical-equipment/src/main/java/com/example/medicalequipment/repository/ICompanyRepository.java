package com.example.medicalequipment.repository;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import com.example.medicalequipment.model.Company;
@Repository
public interface ICompanyRepository extends JpaRepository<Company, Long> {
	
	//@Query("select e from Company e join fetch e.admins where lower(e.name) like %:name%")
	List<Company> findByNameContainingIgnoreCase(String name);
	
	//@Query("select e from Company e join fetch e.admins where lower(e.address.city) like %:city%")
	List<Company> findByAddressCityContainingIgnoreCase(String city);
	
	
	@Modifying
	@Transactional
	@Query(value = "DELETE FROM company_equipment WHERE equipment_id=?1 and company_id=?2", nativeQuery = true)
	void deleteEquipmentFromCompany(Long userId, Long fishingInstructorId);
}
