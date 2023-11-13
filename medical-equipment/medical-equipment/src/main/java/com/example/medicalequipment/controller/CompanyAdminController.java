package com.example.medicalequipment.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.medicalequipment.dto.CompanyAdminDto;
import com.example.medicalequipment.model.CompanyAdmin;
import com.example.medicalequipment.service.CompanyAdminService;

@RestController
@RequestMapping(value = "api/companyAdmin")
public class CompanyAdminController {
	
	private CompanyAdminService companyAdminService;
	
	@Autowired
	public CompanyAdminController(CompanyAdminService _companyAdminService) {
		super();
		this.companyAdminService = _companyAdminService;
	}
	@CrossOrigin(origins="http://localhost:4200")
    @PutMapping(value="/updateUser")
	public ResponseEntity<CompanyAdmin> update(@RequestBody CompanyAdmin companyAdmin) throws Exception {
		return new ResponseEntity<CompanyAdmin>(companyAdminService.update(companyAdmin), HttpStatus.OK);
	}
    
	@CrossOrigin(origins="http://localhost:4200")
    @PostMapping(value="/create")
	public ResponseEntity<CompanyAdmin> create(@RequestBody CompanyAdmin companyAdmin) throws Exception {
		return new ResponseEntity<CompanyAdmin>(companyAdminService.create(companyAdmin), HttpStatus.OK);
	}

	@CrossOrigin(origins="http://localhost:4200")
    @PostMapping(value="/create/{id}")
	public ResponseEntity<CompanyAdmin> createWithCompany(@RequestBody CompanyAdmin companyAdmin, @PathVariable Long id) throws Exception {
		return new ResponseEntity<CompanyAdmin>(companyAdminService.createWithCompany(companyAdmin, id), HttpStatus.OK);
	}
	
    @CrossOrigin(origins="http://localhost:4200")
    @GetMapping(value = "/getAdmin/{id}")
	public ResponseEntity<CompanyAdminDto> getAdmin(@PathVariable Long id) throws Exception {

		CompanyAdmin companyAdmin=companyAdminService.findOne(id);

		if (companyAdmin == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(new CompanyAdminDto(companyAdmin), HttpStatus.OK);
	}
}
