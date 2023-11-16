package com.example.medicalequipment.service;


import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
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
	@Autowired
	private final ICompanyRepository CompanyRepository;
	@Autowired
	private final IEquipmentRepository EquipmentRepository;
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
		Equipment e=new Equipment(equipment.getDescription(),equipment.getName(),equipment.getType());
		e.setCompanies(new HashSet<Company>());
		Company company=CompanyRepository.findById(company_id).orElseGet(null);
		e = EquipmentRepository.save(e);
		e.getCompanies().add(company);
		if(company!=null)
			company.addEquipment(e);
		return CompanyRepository.save(company);
	}
	@Override
	public Company removeEquipment(Long company_id, Long equipment_id) {
		CompanyRepository.deleteEquipmentFromCompany(equipment_id, company_id);
		Company c=findOne(company_id);
		return c;
	}
	@Override
	public List<Company> getAll() {
		return CompanyRepository.findAll();
	}

	
	@Override
	public List<Company> findByName(String name){
		return CompanyRepository.findByNameContainingIgnoreCase(name);	
	}
	
	@Override
	public List<Company> findByAddressCity(String city) {
		return CompanyRepository.findByAddressCityContainingIgnoreCase(city);
	}
	
}
