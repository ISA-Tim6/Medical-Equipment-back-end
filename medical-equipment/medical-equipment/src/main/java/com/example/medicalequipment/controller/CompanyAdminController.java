package com.example.medicalequipment.controller;


import java.util.List;
import java.util.ArrayList;



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
import org.springframework.web.bind.annotation.RestController;

import com.example.medicalequipment.dto.CompanyAdminDto;
import com.example.medicalequipment.iservice.ICompanyAdminService;
import com.example.medicalequipment.model.CompanyAdmin;
import com.example.medicalequipment.model.User;

@RestController
@RequestMapping(value = "api/companyAdmin")
public class CompanyAdminController {
	@Autowired
	private ICompanyAdminService companyAdminService;
	

	public CompanyAdminController(ICompanyAdminService _companyAdminService) {
		super();
		this.companyAdminService = _companyAdminService;
	}
	
	@CrossOrigin(origins="http://localhost:4200")
	@PostMapping
	public ResponseEntity<CompanyAdminDto> addCompanyAdmin(@RequestBody CompanyAdmin companyAdmin) {


		CompanyAdminDto cadto=new CompanyAdminDto(companyAdminService.create(companyAdmin));

		return new ResponseEntity<>(cadto, HttpStatus.OK);
	}
	
	@CrossOrigin(origins="http://localhost:4200")
	@PutMapping(value = "/{id}")
	public ResponseEntity<CompanyAdminDto> updateCompanyAdmin(@PathVariable Long id,@RequestBody User companyAdmin) {

		CompanyAdmin ca = companyAdminService.findOne(id);
		if (ca == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		ca.setUser_id(ca.getUser_id());
		ca.setCity(companyAdmin.getCity());
		ca.setCountry(companyAdmin.getCountry());
		ca.setEmail(companyAdmin.getEmail());
		ca.setName(companyAdmin.getName());
		ca.setPassword(companyAdmin.getPassword());
		ca.setSurname(companyAdmin.getSurname());
		ca.setPhoneNumber(companyAdmin.getPhoneNumber());
		ca.setLoggedBefore(companyAdmin.getLoggedBefore());
		ca.setInfoAboutInstitution(companyAdmin.getInfoAboutInstitution());
		companyAdminService.save(ca);
		CompanyAdminDto cadto=new CompanyAdminDto(companyAdminService.findOne(id));

		return new ResponseEntity<>(cadto, HttpStatus.OK);
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
    @GetMapping(value = "/{id}")
	public ResponseEntity<CompanyAdminDto> getAdmin(@PathVariable Long id) throws Exception {

		CompanyAdmin companyAdmin=companyAdminService.findOne(id);

		if (companyAdmin == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(new CompanyAdminDto(companyAdmin), HttpStatus.OK);
	}
    
    @CrossOrigin(origins="http://localhost:4200")
    @GetMapping(value = "/{company_id}/{user_id}")
	public ResponseEntity<List<CompanyAdminDto>> getOtherCompanyAdminsForCompany(@PathVariable Long company_id,@PathVariable Long user_id) throws Exception {
		List<CompanyAdminDto> companyAdminDtos=new ArrayList<CompanyAdminDto>();
		for(Long ca:companyAdminService.getOtherCompanyAdminsForCompany(company_id,user_id)) {
			companyAdminDtos.add(new CompanyAdminDto(companyAdminService.findOne(ca)));
		}
		return new ResponseEntity<>(companyAdminDtos, HttpStatus.OK);
	}
    
    
}
