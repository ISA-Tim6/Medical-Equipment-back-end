package com.example.medicalequipment.iservice;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.medicalequipment.model.Company;
import com.example.medicalequipment.model.Equipment;

@Service
public interface ICompanyService {

	Company findOne(Long id);
	Company save(Company company);
	List<Company> getAll();
	Company addEquipment(Equipment e,Long company_id);
	Company removeEquipment(Long company_id,Long equipment_id);
}
