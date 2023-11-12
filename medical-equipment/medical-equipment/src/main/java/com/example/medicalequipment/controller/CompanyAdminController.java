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
import com.example.medicalequipment.model.User;
import com.example.medicalequipment.service.CompanyAdminService;

@RestController
@RequestMapping(value = "/companyAdmin")
public class CompanyAdminController {
	
	private CompanyAdminService companyAdminService;
	
	@Autowired
	public CompanyAdminController(CompanyAdminService _companyAdminService) {
		super();
		this.companyAdminService = _companyAdminService;
	}
	@CrossOrigin(origins="http://localhost:4200")
	@PutMapping
	public ResponseEntity<CompanyAdminDto> updateCompanyAdmin(@RequestBody User companyAdmin) {

		CompanyAdmin ca = companyAdminService.findOne(companyAdmin.getUser_id());
		System.out.println(ca.getCity()+"pppppppppppppppppppppppppppppppp");
		if (ca == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		ca.setCity(companyAdmin.getCity());
		ca.setCountry(companyAdmin.getCountry());
		ca.setEmail(companyAdmin.getEmail());
		ca.setName(companyAdmin.getName());
		ca.setPassword(companyAdmin.getPassword());
		ca.setSurname(companyAdmin.getSurname());
		ca.setPhoneNumber(companyAdmin.getPhoneNumber());
		ca.setLoggedBefore(companyAdmin.getLoggedBefore());
		ca.setInfoAboutInstitution(companyAdmin.getInfoAboutInstitution());
		System.out.println(ca.getCity()+"vvvvvvvvvvvvvvvvvvvvvvvvvvv");

		ca = companyAdminService.save(ca);
		System.out.println(ca.getCity()+"hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh");

		return new ResponseEntity<>(new CompanyAdminDto(ca), HttpStatus.OK);
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
}
