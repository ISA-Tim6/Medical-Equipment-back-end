package com.example.medicalequipment.iservice;

import org.springframework.stereotype.Service;

import com.example.medicalequipment.model.CompanyAdmin;
import com.example.medicalequipment.model.User;

@Service
public interface ICompanyAdminService {
	CompanyAdmin save(CompanyAdmin companyAdmin);
	CompanyAdmin findOne(Long id);
	CompanyAdmin create(CompanyAdmin companyAdmin);
	CompanyAdmin createWithCompany(CompanyAdmin admin, Long id);
}
