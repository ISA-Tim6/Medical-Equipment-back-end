package com.example.medicalequipment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.medicalequipment.iservice.ICompanyAdminService;
import com.example.medicalequipment.model.CompanyAdmin;
import com.example.medicalequipment.model.User;
import com.example.medicalequipment.repository.ICompanyAdminRepository;
import com.example.medicalequipment.repository.IUserRepository;

@Service
public class CompanyAdminService implements ICompanyAdminService{
	private final ICompanyAdminRepository CompanyAdminRepository;
	@Autowired
    public CompanyAdminService(ICompanyAdminRepository companyAdminRepository){
    	this.CompanyAdminRepository = companyAdminRepository;
    }
	
	@Override
	public CompanyAdmin create(CompanyAdmin companyAdmin) {
		return this.CompanyAdminRepository.save(companyAdmin);
	}
	
	@Override
	public CompanyAdmin save(CompanyAdmin companyAdmin) {		
		return CompanyAdminRepository.save(companyAdmin);
		
	}
	
	@Override
	public CompanyAdmin findOne(Long id) {
		 return this.CompanyAdminRepository.findById(id).orElseGet(null);		
	}
}
