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
	public CompanyAdmin update(CompanyAdmin companyAdmin) {
		CompanyAdmin currentAdmin=findOne(companyAdmin.getUser_id());
		currentAdmin.setCity(companyAdmin.getCity());
		currentAdmin.setCountry(companyAdmin.getCountry());
		currentAdmin.setEmail(companyAdmin.getEmail());
		currentAdmin.setName(companyAdmin.getName());
		currentAdmin.setSurname(companyAdmin.getSurname());
		currentAdmin.setPassword(companyAdmin.getPassword());
		currentAdmin.setPhoneNumber(companyAdmin.getPhoneNumber());
		currentAdmin.setUsername(companyAdmin.getUsername());
		
		return CompanyAdminRepository.save(currentAdmin);
		
	}
	
	@Override
	public CompanyAdmin findOne(Long id) {
		CompanyAdmin res=this.CompanyAdminRepository.findById(id).orElseGet(null);
		
		return res;
	}
}
