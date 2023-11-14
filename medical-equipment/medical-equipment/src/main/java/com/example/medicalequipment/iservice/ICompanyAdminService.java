package com.example.medicalequipment.iservice;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.medicalequipment.dto.CompanyAdminDto;
import com.example.medicalequipment.model.CompanyAdmin;
import com.example.medicalequipment.model.User;

@Service
public interface ICompanyAdminService {
	CompanyAdmin save(CompanyAdmin companyAdmin);
	CompanyAdmin findOne(Long id);
	CompanyAdmin create(CompanyAdmin companyAdmin);
	List<Long> getOtherCompanyAdminsForCompany(Long company_id,Long user_id);

}
