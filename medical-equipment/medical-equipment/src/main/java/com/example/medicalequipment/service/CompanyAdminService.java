package com.example.medicalequipment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.medicalequipment.iservice.ICompanyAdminService;
import com.example.medicalequipment.model.CompanyAdmin;
import com.example.medicalequipment.repository.ICompanyAdminRepository;

@Service
public class CompanyAdminService implements ICompanyAdminService{
	@Autowired
	private final ICompanyAdminRepository CompanyAdminRepository;
	
    public CompanyAdminService(ICompanyAdminRepository companyAdminRepository){
    	this.CompanyAdminRepository = companyAdminRepository;
    }
	
	@Override
	public CompanyAdmin create(CompanyAdmin companyAdmin) {
		companyAdmin.getCompany().add(companyAdmin);

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
	
	@Override
	public List<Long> getOtherCompanyAdminsForCompany(Long company_id, Long user_id){
		return this.CompanyAdminRepository.getOtherCompanyAdminsForCompany(company_id, user_id);
		
	}
}
