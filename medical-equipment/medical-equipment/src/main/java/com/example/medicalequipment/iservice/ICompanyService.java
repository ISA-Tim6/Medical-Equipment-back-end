package com.example.medicalequipment.iservice;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.medicalequipment.model.Company;

@Service
public interface ICompanyService {

	Company findOne(Long id);
	Company save(Company company);
	List<Company> getAll();
}
