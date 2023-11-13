package com.example.medicalequipment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.medicalequipment.iservice.ICompanyService;
import com.example.medicalequipment.model.Company;
import com.example.medicalequipment.model.Equipment;
import com.example.medicalequipment.repository.ICompanyRepository;
import com.example.medicalequipment.repository.IEquipmentRepository;

@Service
public class CompanyService implements ICompanyService{
	private final ICompanyRepository CompanyRepository;
	private final IEquipmentRepository EquipmentRepository;
	@Autowired
    public CompanyService(ICompanyRepository companyRepository,IEquipmentRepository equipmentRepository){
    	this.CompanyRepository = companyRepository;
    	this.EquipmentRepository=equipmentRepository;
    }
	@Override
	public Company findOne(Long id) {
		return CompanyRepository.findById(id).orElseGet(null);
	}
	@Override
	public Company save(Company company) {
		return CompanyRepository.save(company);
	}
	
	@Override 
	public Company addEquipment(Equipment equipment,Long company_id) {
		Company company=CompanyRepository.findById(company_id).orElseGet(null);
		if(company!=null)
			company.addEquipment(equipment);
		EquipmentRepository.save(equipment);
		return CompanyRepository.save(company);
	}
	
	public Company save(Company company)
	{
		return this.CompanyRepository.save(company);
	}
	
	public List<Company> getAll()
	{
		return this.CompanyRepository.findAll();
	}
}
