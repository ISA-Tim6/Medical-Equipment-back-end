package com.example.medicalequipment.iservice;

import org.springframework.stereotype.Service;

import com.example.medicalequipment.model.Equipment;

@Service
public interface IEquipmentService {
	Equipment findById(Long id);
	Equipment save(Equipment e);
}
