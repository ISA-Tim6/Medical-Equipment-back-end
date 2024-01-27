package com.example.medicalequipment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.medicalequipment.iservice.IContractCompanyService;
import com.example.medicalequipment.model.Company;
import com.example.medicalequipment.model.ContractCompany;
import com.example.medicalequipment.repository.ICompanyRepository;
import com.example.medicalequipment.repository.IContractRepository;


@Service
public class ContractCompanyService implements IContractCompanyService{
	@Autowired
	private final IContractRepository ContractRepository;
	@Autowired
	private final ICompanyRepository CompanyRepository;
    public ContractCompanyService(
    		IContractRepository contractRepository, ICompanyRepository companyRepository){
    	this.ContractRepository = contractRepository;
    	this.CompanyRepository = companyRepository;
    }
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
	
	@Override
	public boolean cancelIfPossible(long id, String company)
	{
		//dobavimo kompaniju da provjerimo ima li dovoljno opreme
		ContractCompany storedContract = this.ContractRepository.getById(id);
		List<Company> stored = this.CompanyRepository.findByName(company);
		Company storedCompany = stored.isEmpty() ? null : stored.get(0);	//kompanija iz ugovora
		
		return false;
	}

}
