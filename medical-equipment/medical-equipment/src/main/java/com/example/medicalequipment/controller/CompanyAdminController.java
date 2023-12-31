package com.example.medicalequipment.controller;


import java.util.List;
import java.security.Principal;
import java.util.ArrayList;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
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
import com.example.medicalequipment.iservice.IRegistratedUserService;
import com.example.medicalequipment.iservice.IUserService;
import com.example.medicalequipment.model.CompanyAdmin;
import com.example.medicalequipment.model.User;

@RestController
@RequestMapping(value = "api/companyAdmin")
public class CompanyAdminController {
	@Autowired
	private ICompanyAdminService companyAdminService;
	@Autowired
	private IUserService userService;
	@Autowired
	private IRegistratedUserService registratedUserService;
	@Autowired
    public PasswordEncoder passwordEncoder;

	public CompanyAdminController(ICompanyAdminService _companyAdminService, IUserService _userService,IRegistratedUserService rus) {
		super();
		this.companyAdminService = _companyAdminService;
		this.userService=_userService;
		this.registratedUserService=rus;
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
		ca.setUsername(companyAdmin.getUsername());
		ca.setCountry(companyAdmin.getCountry());
		ca.setEmail(companyAdmin.getEmail());
		ca.setName(companyAdmin.getName());
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
	@PreAuthorize("hasAnyAuthority('ROLE_SYSTEM_ADMIN')")
	public ResponseEntity<CompanyAdmin> createWithCompany(@RequestBody CompanyAdmin companyAdmin, @PathVariable Long id) throws Exception {
		return new ResponseEntity<CompanyAdmin>(companyAdminService.createWithCompany(companyAdmin, id), HttpStatus.OK);
	}
	
    @CrossOrigin(origins="http://localhost:4200")
    @GetMapping(value = "/{id}")
    //@PreAuthorize("hasAnyAuthority('COMPANY_ADMIN')")
	public ResponseEntity<CompanyAdminDto> getAdmin(@PathVariable Long id) throws Exception {

		CompanyAdmin companyAdmin=companyAdminService.findOne(id);
		for(GrantedAuthority i:companyAdmin.getAuthorities()) {
			System.out.println(i.getAuthority());
		}
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
    @GetMapping("/whoami")
	public CompanyAdminDto user(Principal user) {
    	Long id=this.userService.findIdByUsername(user.getName());
    	System.out.println("Id: "+id);
    	CompanyAdmin ca=this.companyAdminService.findOne(id);
    	System.out.println("Email"+ca.getEmail());
		return new CompanyAdminDto(ca);
	}
    
	@CrossOrigin(origins="http://localhost:4200")
	@PutMapping(value = "/changePassword/{id}")
	
	public ResponseEntity<CompanyAdminDto> changePassword(@PathVariable Long id,@RequestBody String password) {

		/*CompanyAdmin ca = companyAdminService.findOne(id);
		if (ca == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		String encodedPassword = passwordEncoder.encode(password);
		System.out.println(encodedPassword);
		ca.setPassword(encodedPassword);
		ca.setLoggedBefore(true);
		ca.setActive(true);
		
		companyAdminService.save(ca);*/
		CompanyAdmin ca=this.registratedUserService.changePassword(password, id);

		CompanyAdminDto cadto=new CompanyAdminDto(companyAdminService.findOne(id));

		return new ResponseEntity<>(cadto, HttpStatus.OK);
	}
    
}
