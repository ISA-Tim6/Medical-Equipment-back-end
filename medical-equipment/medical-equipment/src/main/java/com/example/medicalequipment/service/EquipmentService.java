package com.example.medicalequipment.service;


import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.medicalequipment.iservice.IEquipmentService;
import com.example.medicalequipment.model.Equipment;
import com.example.medicalequipment.repository.IEquipmentRepository;

@Service
public class EquipmentService implements IEquipmentService{
	@Autowired
	private final IEquipmentRepository EquipmentRepository;
	
    public EquipmentService(IEquipmentRepository equipmentRepository){
    	this.EquipmentRepository=equipmentRepository;
    }
	@Override
	public Equipment findById(Long id) {
		return EquipmentRepository.findById(id).orElseGet(null);
	}
	@Override
	public Equipment save(Equipment e) {
		return EquipmentRepository.save(e);
	}
	@Override
	public List<Equipment> searchByName(String name) {
		return EquipmentRepository.searchByName(name);
	}
	@Override
	public List<Equipment> searchByNameAndCompany(String name, Long company_id) {
		return EquipmentRepository.searchByNameAndCompany(name, company_id);
	}

	@Override
	public List<Equipment> search() {
		return EquipmentRepository.search();
	}
}
