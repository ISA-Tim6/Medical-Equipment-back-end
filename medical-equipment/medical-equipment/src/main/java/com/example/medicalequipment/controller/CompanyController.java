package com.example.medicalequipment.controller;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.medicalequipment.dto.CompanyCalendarDto;
import com.example.medicalequipment.dto.CompanyDto;
import com.example.medicalequipment.dto.CompanySearchDto;
import com.example.medicalequipment.model.Address;
import com.example.medicalequipment.model.Appointment;

import java.util.ArrayList;
import com.example.medicalequipment.dto.CompanyDto;
import com.example.medicalequipment.dto.CompanyUpdateDto;
import com.example.medicalequipment.dto.WorkingTimeCalendarDto;
import com.example.medicalequipment.dto.EquipmentDto;
import com.example.medicalequipment.dto.ReservationDto;
import com.example.medicalequipment.iservice.ICompanyService;
import com.example.medicalequipment.iservice.IReservationService;
import com.example.medicalequipment.model.Company;
import com.example.medicalequipment.model.Equipment;
import com.example.medicalequipment.service.CompanyService;


@RestController
@RequestMapping(value = "api/company")
public class CompanyController {
	@Autowired
	private ICompanyService companyService;
	
	@Autowired
	private IReservationService reservationService;
	
	public CompanyController(ICompanyService _companyService, IReservationService _reservationService) {
		super();
		this.companyService = _companyService;
		this.reservationService = _reservationService;
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
	 @PreAuthorize("hasAnyAuthority('ROLE_SYSTEM_ADMIN','ROLE_COMPANY_ADMIN','ROLE_REGISTRATED_USER')")
		public ResponseEntity<CompanyDto> getCompany(@PathVariable Long id) throws Exception {

			Company company=companyService.findOne(id);

			if (company == null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			CompanyDto cdto=new CompanyDto(company);
			return new ResponseEntity<>(cdto, HttpStatus.OK);
	 }
	 
	 @CrossOrigin(origins="http://localhost:4200")
		@PutMapping(value="{id}")
	 @PreAuthorize("hasAuthority('ROLE_COMPANY_ADMIN')")
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
	 @PreAuthorize("hasAuthority('ROLE_SYSTEM_ADMIN')")
		public ResponseEntity<CompanyDto> create(@RequestBody Company company) throws Exception {
			return new ResponseEntity<CompanyDto>(new CompanyDto(companyService.save(company)), HttpStatus.OK);
		}
	 
	 @CrossOrigin(origins="http://localhost:4200")
	    @GetMapping(value = "/getAllCompanies")
	 @PreAuthorize("hasAnyAuthority('ROLE_SYSTEM_ADMIN','ROLE_COMPANY_ADMIN')")
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
	 	@PreAuthorize("hasAuthority('ROLE_COMPANY_ADMIN')")
		public ResponseEntity<CompanyDto> addEquipment(@RequestBody Equipment equipment,@PathVariable Long id) throws Exception{
			
			Company c=companyService.addEquipment(equipment, id);

			return new ResponseEntity<>(new CompanyDto(c), HttpStatus.OK);
		}
	 @CrossOrigin(origins="http://localhost:4200")
		@PutMapping(value = "/removeEquipment/{company_id}")
	 @PreAuthorize("hasAuthority('ROLE_COMPANY_ADMIN')")
		public ResponseEntity<CompanyDto> removeEquipment(@PathVariable Long company_id,@RequestBody Equipment equipment) throws Exception{
			Company c=companyService.removeEquipment(company_id, equipment.getEquipment_id());
			if (c!=null)
				return new ResponseEntity<>(new CompanyDto(c), HttpStatus.OK);
			else
				return new ResponseEntity<>(null, HttpStatus.OK);
			
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
	    @PreAuthorize("hasAnyAuthority('ROLE_COMPANY_ADMIN','ROLE_SYSTEM_ADMIN')")

		public ResponseEntity<Integer> addAppointment(@PathVariable Long company_id, @PathVariable Long company_admin_id,@RequestBody Appointment appointment) throws Exception{
			Integer c=companyService.addAppointment(company_id,company_admin_id,appointment);
			if(c==0)
				return new ResponseEntity<>(0, HttpStatus.OK);
			if(c==1)
				return new ResponseEntity<>(1, HttpStatus.OK);
			return new ResponseEntity<>(2, HttpStatus.OK);
		}
	 
	 @CrossOrigin(origins="http://localhost:4200")
	 @GetMapping(value = "/companyCalendar/{company_id}")
	 @PreAuthorize("hasAnyAuthority('ROLE_SYSTEM_ADMIN','ROLE_COMPANY_ADMIN')")
	 public ResponseEntity<CompanyCalendarDto> getWorkingCalendar(@PathVariable Long company_id) throws Exception {
		Company c = companyService.findOne(company_id);
		WorkingTimeCalendarDto wtc = new WorkingTimeCalendarDto(c.getWorkingTimeCalendar());
		List<ReservationDto> reservations = reservationService.getAllByCompany(company_id);
		CompanyCalendarDto dto = new CompanyCalendarDto(wtc, reservations);
		return new ResponseEntity<>(dto, HttpStatus.OK);
	 }

	 @CrossOrigin(origins="http://localhost:4200")
	 @PutMapping("/updateAppointment/{company_id}/{company_admin_id}")
	 public ResponseEntity<Integer> updateAppointment(@PathVariable Long company_id, @PathVariable Long company_admin_id,@RequestBody Appointment appointment) {
		return new ResponseEntity<>(companyService.updateAppointment(company_id,company_admin_id,appointment), HttpStatus.OK);
	 }
	 
	 
	 @CrossOrigin(origins="http://localhost:4200")
	 @GetMapping(value = "/findFreeSlots/{company_id}/{date}")
	 public ResponseEntity<List<LocalTime>> findAvailableAppointments(@PathVariable Long company_id, @PathVariable String date)
	 {
		 return new ResponseEntity<>(companyService.findAvailableAppointments(date, company_id), HttpStatus.OK);
	 }
	 
	 @CrossOrigin(origins="http://localhost:4200")
		@PutMapping(value = "/addExtraordinaryAppointment/{company_id}")
		public ResponseEntity<Long> addExtraordinaryAppointment(@PathVariable Long company_id,@RequestBody Appointment appointment) throws Exception{
			Long c=companyService.addExtraordinaryAppointment(company_id,appointment);	//vracam id appointment
			return new ResponseEntity<>(c, HttpStatus.OK);
		}
	 
}
