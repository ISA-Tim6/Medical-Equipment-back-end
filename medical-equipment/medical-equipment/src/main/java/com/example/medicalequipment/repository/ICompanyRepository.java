package com.example.medicalequipment.repository;
import java.util.ArrayList;
import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import com.example.medicalequipment.model.Company;
@Repository
public interface ICompanyRepository extends JpaRepository<Company, Long> {
	ArrayList<Company> findByNameContainingIgnoreCase(String name);
	ArrayList<Company> findByAddressCityContainingIgnoreCase(String city);
	@Modifying
	@Transactional
	@Query(value = "DELETE FROM company_equipment WHERE equipment_id=?1 and company_id=?2", nativeQuery = true)
	void deleteEquipmentFromCompany(Long userId, Long fishingInstructorId);
}
