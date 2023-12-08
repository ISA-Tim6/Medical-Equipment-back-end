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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.medicalequipment.dto.CompanyDto;
import com.example.medicalequipment.dto.CompanySearchDto;
import com.example.medicalequipment.model.Address;
import com.example.medicalequipment.model.Appointment;

import java.util.ArrayList;
import com.example.medicalequipment.dto.CompanyDto;
import com.example.medicalequipment.dto.CompanyUpdateDto;
import com.example.medicalequipment.iservice.ICompanyService;
import com.example.medicalequipment.model.Company;
import com.example.medicalequipment.model.Equipment;
import com.example.medicalequipment.service.CompanyService;


@RestController
@RequestMapping(value = "api/company")
public class CompanyController {
	@Autowired
	private ICompanyService companyService;
	
	public CompanyController(ICompanyService _companyService) {
		super();
		this.companyService = _companyService;
	}
	
	@CrossOrigin(origins="http://localhost:4200")
    @GetMapping
	public ResponseEntity<List<CompanyDto>> getAllCompanies() throws Exception {
		List<CompanyDto> companiesDto=new ArrayList<CompanyDto>();
		for(Company c:companyService.getAll())
			companiesDto.add(new CompanyDto(c));

		return new ResponseEntity<>(companiesDto, HttpStatus.OK);
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
		@PutMapping(value="{id}")
		public ResponseEntity<CompanyDto> updateCompany(@RequestBody CompanyUpdateDto company,@PathVariable Long id) throws Exception{

			Company c = companyService.findOne(id);

			if (c == null) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			c.setName(company.getName());
			c.setId(id);
			c.setAverageGrade(company.getAverageGrade());
			c.setAddress(company.getAddress());
			c.setOpeningHours(company.getOpeningHours());
			c.setClosingHours(company.getClosingHours());
			
			
			c = companyService.save(c);

			return new ResponseEntity<>(new CompanyDto(c), HttpStatus.OK);
		}
	 
	 @CrossOrigin(origins="http://localhost:4200")
	    @PostMapping(value="/create")
		public ResponseEntity<CompanyDto> create(@RequestBody Company company) throws Exception {
			return new ResponseEntity<CompanyDto>(new CompanyDto(companyService.save(company)), HttpStatus.OK);
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
	 @CrossOrigin(origins="http://localhost:4200")
		@PutMapping(value = "/addEquipment/{id}")
		public ResponseEntity<CompanyDto> addEquipment(@RequestBody Equipment equipment,@PathVariable Long id) throws Exception{
			
			Company c=companyService.addEquipment(equipment, id);

			return new ResponseEntity<>(new CompanyDto(c), HttpStatus.OK);
		}
	 @CrossOrigin(origins="http://localhost:4200")
		@PutMapping(value = "/removeEquipment/{company_id}")
		public ResponseEntity<CompanyDto> removeEquipment(@PathVariable Long company_id,@RequestBody Equipment equipment) throws Exception{
			Company c=companyService.removeEquipment(company_id, equipment.getEquipment_id());
			return new ResponseEntity<>(new CompanyDto(c), HttpStatus.OK);
		}
	 

	 @CrossOrigin(origins="http://localhost:4200")
	 @GetMapping(value = "/searchByName/{name}")
	 public ResponseEntity<List<CompanyDto>> findByName(@PathVariable String name) {
		 List<Company> companies = companyService.findByName(name);
		 List<CompanyDto> dtos = new ArrayList<CompanyDto>();
		if (companies == null || companies.isEmpty()) {
			dtos = new ArrayList();
			return new ResponseEntity<>(dtos,HttpStatus.OK);
		}
		
		for(int i=0; i< companies.size(); i++)
		{
			dtos.add(new CompanyDto(companies.get(i)));
		}
		
		return new ResponseEntity<>(dtos, HttpStatus.OK);
	 }
	 
	 @CrossOrigin(origins="http://localhost:4200")
	 @GetMapping(value = "/searchByCity/{city}")
	 public ResponseEntity<List<CompanyDto>> findByAddressCity(@PathVariable String city) throws Exception {
		 List<Company> companies = companyService.findByAddressCity(city);
		 List<CompanyDto> dtos = new ArrayList();
		if (companies == null || companies.isEmpty()) {
			dtos = new ArrayList();
			return new ResponseEntity<>(dtos, HttpStatus.OK);
		}
			
		for(int i=0; i< companies.size(); i++)
		{
			dtos.add(new CompanyDto(companies.get(i)));
		};
		
		return new ResponseEntity<>(dtos, HttpStatus.OK);
	 }
	 
	 @CrossOrigin(origins="http://localhost:4200")
	 @GetMapping(value = "/searchByNameAndCity/{name}/{city}")
	 public ResponseEntity<List<CompanyDto>> findByNameAndCity(@PathVariable String name, @PathVariable String city) throws Exception {
		 List<Company> companies = companyService.findByNameAndAddressCity(name,city);
		 List<CompanyDto> dtos = new ArrayList();
		if (companies == null || companies.isEmpty()) {
			dtos = new ArrayList();
			return new ResponseEntity<>(dtos, HttpStatus.OK);
		}
			
		for(int i=0; i< companies.size(); i++)
		{
			dtos.add(new CompanyDto(companies.get(i)));
		};
		
		return new ResponseEntity<>(dtos, HttpStatus.OK);
	 }
	 
	 @CrossOrigin(origins="http://localhost:4200")
		@PutMapping(value = "/addAppointment/{company_id}/{company_admin_id}")
		public ResponseEntity<CompanyDto> addAppointment(@PathVariable Long company_id, @PathVariable Long company_admin_id,@RequestBody Appointment appointment) throws Exception{
			Company c=companyService.addAppointment(company_id,company_admin_id,appointment);
			return new ResponseEntity<>(new CompanyDto(c), HttpStatus.OK);
		}
	 
}
