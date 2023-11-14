package com.example.medicalequipment.iservice;
import java.util.ArrayList;
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
	ArrayList<Company> findByName(String name);
	ArrayList<Company> findByAddressCity(String city);
}
