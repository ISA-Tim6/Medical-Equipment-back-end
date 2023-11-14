package com.example.medicalequipment.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.medicalequipment.model.Company;
@Repository
public interface ICompanyRepository extends JpaRepository<Company, Long> {

	ArrayList<Company> findByName(String name);
	ArrayList<Company> findByAddressCity(String city);
}
