package com.example.medicalequipment.service;


import org.springframework.beans.factory.annotation.Autowired;

import com.example.medicalequipment.iservice.IEquipmentService;
import com.example.medicalequipment.model.Equipment;
import com.example.medicalequipment.repository.IEquipmentRepository;

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

}
