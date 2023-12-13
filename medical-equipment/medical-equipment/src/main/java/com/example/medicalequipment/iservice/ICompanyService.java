package com.example.medicalequipment.iservice;
import java.util.ArrayList;
import java.util.List;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.medicalequipment.dto.EquipmentDto;
import com.example.medicalequipment.model.Appointment;
import com.example.medicalequipment.model.Company;
import com.example.medicalequipment.model.Equipment;

@Service
public interface ICompanyService {
	Company findOne(Long id);
	Company save(Company company);
	List<Company> getAll();
	Company addEquipment(Equipment e,Long company_id);
	List<Company> findByName(String name);
	List<Company> findByAddressCity(String city);
	List<Company> findByNameAndAddressCity(String name, String city);
	Company removeEquipment(Long company_id,Long equipment_id);
	Integer addAppointment(Long company_id,Long company_admin_id,Appointment appointment);
	Company find(Long company_id);
}
