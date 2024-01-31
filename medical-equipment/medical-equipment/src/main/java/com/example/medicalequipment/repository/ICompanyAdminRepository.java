package com.example.medicalequipment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import com.example.medicalequipment.model.CompanyAdmin;


@Repository
public interface ICompanyAdminRepository extends JpaRepository<CompanyAdmin, Long>{
	@Query(value = "SELECT user_id FROM company_admin ca where company_id=?1 and ca.user_id!=?2", nativeQuery = true)
	List<Long> getOtherCompanyAdminsForCompany(Long company_id,Long user_id);
	
	@Query(value = "SELECT ca FROM CompanyAdmin ca join fetch ca.company where ca.id=?1")
	CompanyAdmin getWithCompany(Long id);
	
	@Query(value = "SELECT ca FROM CompanyAdmin ca where ca.username=?1")
	CompanyAdmin getByUsername(String username);
}
