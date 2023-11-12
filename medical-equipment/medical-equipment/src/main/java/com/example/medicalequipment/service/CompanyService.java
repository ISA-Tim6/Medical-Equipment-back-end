package com.example.medicalequipment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.medicalequipment.iservice.ICompanyService;
import com.example.medicalequipment.model.Company;
import com.example.medicalequipment.repository.ICompanyRepository;

@Service
public class CompanyService implements ICompanyService{
	private final ICompanyRepository CompanyRepository;
	@Autowired
    public CompanyService(ICompanyRepository companyRepository){
    	this.CompanyRepository = companyRepository;
    }
	@Override
	public Company findOne(Long id) {
		return CompanyRepository.findById(id).orElseGet(null);
	}
	@Override
	public Company save(Company company) {
		return CompanyRepository.save(company);
	}
	
	
}
