package com.example.medicalequipment.iservice;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.medicalequipment.model.ContractCompany;

@Service
public interface IContractCompanyService {
	ContractCompany save(ContractCompany contract);
	List<ContractCompany> getAllForCompany(String name);
	void deleteAllForCompany (String company);
	boolean cancelIfPossible(long id, String company);

}
