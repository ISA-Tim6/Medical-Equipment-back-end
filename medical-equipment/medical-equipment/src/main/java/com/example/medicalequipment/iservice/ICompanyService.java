package com.example.medicalequipment.iservice;

import org.springframework.stereotype.Service;

import com.example.medicalequipment.model.Company;

@Service
public interface ICompanyService {

	Company findOne(Long id);
	Company save(Company company);
}
