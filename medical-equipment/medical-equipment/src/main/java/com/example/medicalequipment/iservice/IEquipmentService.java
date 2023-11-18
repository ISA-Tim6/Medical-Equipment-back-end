package com.example.medicalequipment.iservice;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.medicalequipment.model.Equipment;

@Service
public interface IEquipmentService {
	Equipment findById(Long id);
	Equipment save(Equipment e);
	List<Equipment> searchByName(String name);
	List<Equipment> searchByNameAndCompany(String name, Long company_id);
	public List<Equipment> search();
}
