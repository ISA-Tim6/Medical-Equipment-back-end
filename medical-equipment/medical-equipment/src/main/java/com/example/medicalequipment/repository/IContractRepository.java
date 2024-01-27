package com.example.medicalequipment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.medicalequipment.model.ContractCompany;

@Repository
public interface IContractRepository extends JpaRepository<ContractCompany, Long> {
	@Query(value = "SELECT * from contract_company where company_name=:company", nativeQuery = true)
	List<ContractCompany> getAllForCompany(String company);
	
	@Transactional
	@Modifying
	@Query(value = "DELETE FROM contract_company WHERE company_name = :company", nativeQuery = true)
	void deleteAllForCompany(String company);


}
