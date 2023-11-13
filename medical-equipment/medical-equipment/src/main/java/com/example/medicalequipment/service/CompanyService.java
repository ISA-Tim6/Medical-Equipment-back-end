package com.example.medicalequipment.service;

import java.util.List;

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
	
	public Company save(Company company)
	{
		return this.CompanyRepository.save(company);
	}
	
	public List<Company> getAll()
	{
		return this.CompanyRepository.findAll();
	}
}
