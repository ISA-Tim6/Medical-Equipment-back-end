package com.example.medicalequipment.iservice;

import org.springframework.stereotype.Service;

import com.example.medicalequipment.model.Company;
import com.example.medicalequipment.model.Equipment;

@Service
public interface ICompanyService {

	Company findOne(Long id);
	Company save(Company company);
	Company addEquipment(Equipment e,Long company_id);
}
