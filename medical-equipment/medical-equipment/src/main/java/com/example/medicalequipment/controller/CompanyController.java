package com.example.medicalequipment.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.medicalequipment.dto.CompanyAdminDto;
import com.example.medicalequipment.dto.CompanyDto;
import com.example.medicalequipment.dto.CompanySearchDto;
import com.example.medicalequipment.model.Address;
import com.example.medicalequipment.model.Company;
import com.example.medicalequipment.model.CompanyAdmin;
import com.example.medicalequipment.model.Equipment;
import com.example.medicalequipment.service.CompanyAdminService;
import com.example.medicalequipment.service.CompanyService;

@RestController
@RequestMapping(value = "api/company")
public class CompanyController {
private CompanyService companyService;
	
	@Autowired
	public CompanyController(CompanyService _companyService) {
		super();
		this.companyService = _companyService;
	}
	
	
	
	
	 @CrossOrigin(origins="http://localhost:4200")
	    @GetMapping(value = "/{id}")
		public ResponseEntity<CompanyDto> getCompany(@PathVariable Long id) throws Exception {

			Company company=companyService.findOne(id);

			if (company == null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}

			return new ResponseEntity<>(new CompanyDto(company), HttpStatus.OK);
	 }
	 
	 @CrossOrigin(origins="http://localhost:4200")
		@PutMapping
		public ResponseEntity<CompanyDto> updateCompany(@RequestBody Company company) {

			Company c = companyService.findOne(company.getId());

			if (c == null) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			c.setName(company.getName());
			c.setId(company.getId());
			c.setAverageGrade(company.getAverageGrade());
			c.setAddress(company.getAddress());
			c.setEquipment(company.getEquipment());
			c.setAdmins(company.getAdmins());
			
			
			
			c = companyService.save(c);

			return new ResponseEntity<>(new CompanyDto(c), HttpStatus.OK);
		}
	 
	 @CrossOrigin(origins="http://localhost:4200")
		@PutMapping(value = "/addEquipment/{id}")
		public ResponseEntity<CompanyDto> addEquipment(@RequestBody Equipment equipment,@PathVariable Long id) throws Exception{
			
			Company c=companyService.addEquipment(equipment, id);

			return new ResponseEntity<>(new CompanyDto(c), HttpStatus.OK);
		}
	 
	 @CrossOrigin(origins="http://localhost:4200")
	 @GetMapping(path = "/searchByName", value = "/{name}")
	 public ResponseEntity<ArrayList<CompanySearchDto>> findByName(@PathVariable String name) throws Exception {
		ArrayList<Company> companies = companyService.findByName(name);
		if (companies == null || companies.isEmpty()) 
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		ArrayList<CompanySearchDto> dtos = new ArrayList(companies);
		
		return new ResponseEntity<>(dtos, HttpStatus.OK);
	 }
	 
	 @CrossOrigin(origins="http://localhost:4200")
	 @GetMapping(path = "/searchByCity", value = "/{name}")
	 public ResponseEntity<ArrayList<CompanySearchDto>> findByAddressCity(@PathVariable String city) throws Exception {
		ArrayList<Company> companies = companyService.findByAddressCity(city);
		if (companies == null || companies.isEmpty()) 
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		ArrayList<CompanySearchDto> dtos = new ArrayList(companies);
		
		return new ResponseEntity<>(dtos, HttpStatus.OK);
	 }
	 
	
	 
	 
}
