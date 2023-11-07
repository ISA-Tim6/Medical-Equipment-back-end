package com.example.medicalequipment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.medicalequipment.model.Address;

@Repository
public interface IAddressRepository  extends JpaRepository<Address, Long>{

}
