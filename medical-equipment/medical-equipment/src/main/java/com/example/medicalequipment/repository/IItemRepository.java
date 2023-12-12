package com.example.medicalequipment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.medicalequipment.model.Item;

public interface IItemRepository extends JpaRepository<Item, Long> {
	

}
