package com.example.medicalequipment.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.medicalequipment.dto.CompanyDto;
import com.example.medicalequipment.model.Company;
import com.example.medicalequipment.service.CompanyService;

@RestController
@RequestMapping(path = "api/company")
public class CompanyController {

@Autowired
private CompanyService companyService;
	
	public CompanyController(CompanyService _companyService) {
		super();
		this.companyService = _companyService;
	}
	
	 @CrossOrigin(origins="http://localhost:4200")
	    @GetMapping(value = "/getCompany/{id}")
		public ResponseEntity<CompanyDto> getCompany(@PathVariable Long id) throws Exception {

			Company company=companyService.findOne(id);

			if (company == null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}

			return new ResponseEntity<>(new CompanyDto(company), HttpStatus.OK);
		}
	 
	 @CrossOrigin(origins="http://localhost:4200")
	    @PostMapping(value="/create")
		public ResponseEntity<Company> create(@RequestBody Company company) throws Exception {
			return new ResponseEntity<Company>(companyService.save(company), HttpStatus.OK);
		}
	 
	 @CrossOrigin(origins="http://localhost:4200")
	    @GetMapping(value = "/getAllCompanies")
		public ResponseEntity<List<CompanyDto>> getAll() throws Exception {

			List<Company> result = companyService.getAll();
			List<CompanyDto> resultDto = new ArrayList<CompanyDto>();
			
			for(int i=0; i< result.size(); i++)
			{
				resultDto.add(new CompanyDto(result.get(i)));
			}

			if (resultDto.size() == 0) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}

			return new ResponseEntity<>(resultDto, HttpStatus.OK);
		}
	    
}
