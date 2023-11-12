package com.example.medicalequipment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.medicalequipment.model.Company;
import com.example.medicalequipment.model.CompanyAdmin;
import com.example.medicalequipment.model.User;

@Repository
public interface ICompanyAdminRepository extends JpaRepository<CompanyAdmin, Long>{

}
