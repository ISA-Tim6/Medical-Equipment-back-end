package com.example.medicalequipment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.medicalequipment.model.ContractCompany;

@Repository
public interface IContractRepository extends JpaRepository<ContractCompany, Long> {
	@Query(value = "SELECT * from contract_company where company_name=:company", nativeQuery = true)
	List<ContractCompany> getAllForCompany(String company);
}
