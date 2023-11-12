package com.example.medicalequipment.controller;

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
import com.example.medicalequipment.model.Address;
import com.example.medicalequipment.model.Company;
import com.example.medicalequipment.model.CompanyAdmin;
import com.example.medicalequipment.service.CompanyAdminService;
import com.example.medicalequipment.service.CompanyService;

@RestController
@RequestMapping(value = "/company")
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
	 
	
	 
	 
}
