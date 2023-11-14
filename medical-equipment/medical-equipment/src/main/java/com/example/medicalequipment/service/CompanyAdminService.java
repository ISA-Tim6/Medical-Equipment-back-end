package com.example.medicalequipment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.medicalequipment.iservice.ICompanyAdminService;
import com.example.medicalequipment.model.Company;
import com.example.medicalequipment.model.CompanyAdmin;
import com.example.medicalequipment.repository.ICompanyAdminRepository;
import com.example.medicalequipment.repository.ICompanyRepository;
import com.example.medicalequipment.repository.IUserRepository;

@Service
public class CompanyAdminService implements ICompanyAdminService{
	private final ICompanyRepository CompanyRepository;

	@Autowired
	private final ICompanyAdminRepository CompanyAdminRepository;
	
    public CompanyAdminService(ICompanyAdminRepository companyAdminRepository, ICompanyRepository companyRepository){
    	this.CompanyAdminRepository = companyAdminRepository;
    	this.CompanyRepository = companyRepository;
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
	

	public CompanyAdmin createWithCompany(CompanyAdmin admin, Long id) {
		//Company company = CompanyRepository.getById(id);
		admin.getCompany().setId(id);
		admin.getCompany().add(admin);
		return this.CompanyAdminRepository.save(admin);
	}
	@Override
	public List<Long> getOtherCompanyAdminsForCompany(Long company_id, Long user_id){
		return this.CompanyAdminRepository.getOtherCompanyAdminsForCompany(company_id, user_id);

	}
}
