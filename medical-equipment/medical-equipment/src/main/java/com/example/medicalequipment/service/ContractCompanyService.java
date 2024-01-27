package com.example.medicalequipment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.medicalequipment.iservice.IContractCompanyService;
import com.example.medicalequipment.model.ContractCompany;
import com.example.medicalequipment.repository.IContractRepository;


@Service
public class ContractCompanyService implements IContractCompanyService{
	@Autowired
	private final IContractRepository ContractRepository;
    public ContractCompanyService(
    		IContractRepository contractRepository){
    	this.ContractRepository = contractRepository;}
	@Override
	public ContractCompany save(ContractCompany contract) {
		return this.ContractRepository.save(contract);
	}
	@Override
	public List<ContractCompany> getAllForCompany(String name) {
		return this.ContractRepository.getAllForCompany(name);
	}
	@Override
	public void deleteAllForCompany(String company) {
		// TODO Auto-generated method stub
		this.ContractRepository.deleteAllForCompany(company);
	}

}
