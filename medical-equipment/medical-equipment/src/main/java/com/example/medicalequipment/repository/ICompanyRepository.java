package com.example.medicalequipment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.medicalequipment.model.Company;
@Repository
public interface ICompanyRepository extends JpaRepository<Company, Long> {

}
