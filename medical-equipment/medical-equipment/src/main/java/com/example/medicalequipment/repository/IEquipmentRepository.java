package com.example.medicalequipment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.medicalequipment.model.Address;
import com.example.medicalequipment.model.Equipment;
@Repository
public interface IEquipmentRepository extends JpaRepository<Equipment, Long>{

}
