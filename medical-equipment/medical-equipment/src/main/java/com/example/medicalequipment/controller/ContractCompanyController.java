package com.example.medicalequipment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.medicalequipment.iservice.IContractCompanyService;
import com.example.medicalequipment.model.ContractCompany;

@RestController
@RequestMapping(value = "api/contract")
public class ContractCompanyController {
	@Autowired
	private IContractCompanyService contractService;
	
	public ContractCompanyController(IContractCompanyService _contractService) {
		super();
		this.contractService = _contractService;
	}
	
	 @CrossOrigin(origins="http://localhost:4200")
	    @GetMapping(value = "/{company}")
	 @PreAuthorize("hasAnyAuthority('ROLE_SYSTEM_ADMIN','ROLE_COMPANY_ADMIN')")
		public ResponseEntity<List<ContractCompany>> getAllForCompany(@PathVariable String company) throws Exception {

			List<ContractCompany> contracts=this.contractService.getAllForCompany(company);
			return new ResponseEntity<>(contracts, HttpStatus.OK);
	 }
	 
	 @CrossOrigin(origins="http://localhost:4200")
	    @GetMapping(value = "/{id}/{company}")
	 @PreAuthorize("hasAnyAuthority('ROLE_SYSTEM_ADMIN','ROLE_COMPANY_ADMIN')")
		public boolean cancelContract(@PathVariable long id, @PathVariable String company) throws Exception {

			return this.contractService.cancelIfPossible(id, company);
			
	 }
}
