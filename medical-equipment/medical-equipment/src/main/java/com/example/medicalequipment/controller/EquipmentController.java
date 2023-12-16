package com.example.medicalequipment.controller;

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

import com.example.medicalequipment.dto.CompanyDto;
import com.example.medicalequipment.dto.EquipmentCompaniesDto;
import com.example.medicalequipment.dto.EquipmentDto;
import com.example.medicalequipment.iservice.IEquipmentService;
import com.example.medicalequipment.model.Address;
import com.example.medicalequipment.model.Company;
import com.example.medicalequipment.model.Equipment;
import com.example.medicalequipment.model.RegistratedUser;
import com.example.medicalequipment.repository.IEquipmentRepository;
import com.example.medicalequipment.service.EquipmentService;

@RestController
@RequestMapping(path="api/equipment")
public class EquipmentController {

	@Autowired
	private IEquipmentService equipmentService;
	
	public EquipmentController(IEquipmentService service)
	{
		this.equipmentService = service;
	}
	@CrossOrigin(origins="http://localhost:4200")
	@GetMapping("/searchEquipment/{name}")
	public ResponseEntity<List<EquipmentCompaniesDto>> searchByName(@PathVariable String name) throws Exception{
		List<EquipmentCompaniesDto> e = new ArrayList<EquipmentCompaniesDto>();
		for(Equipment eq: this.equipmentService.searchByName(name))
			e.add(new EquipmentCompaniesDto(eq));
		
		return new ResponseEntity<>(e, HttpStatus.OK);
	}
	
	@CrossOrigin(origins="http://localhost:4200")
	@GetMapping("/searchEquipmentByCompany/{name}/{company_id}")
	public ResponseEntity<List<EquipmentCompaniesDto>> searchByName(@PathVariable String name, @PathVariable Long company_id) throws Exception{
		List<EquipmentCompaniesDto> e = new ArrayList<EquipmentCompaniesDto>();
		for(Equipment eq: this.equipmentService.searchByNameAndCompany(name, company_id))
			e.add(new EquipmentCompaniesDto(eq));
		
		return new ResponseEntity<>(e, HttpStatus.OK);
	}
	
	@CrossOrigin(origins="http://localhost:4200")
	@GetMapping("/searchEquipment/")
	public ResponseEntity<List<EquipmentCompaniesDto>> search() throws Exception{
		List<EquipmentCompaniesDto> e = new ArrayList<EquipmentCompaniesDto>();
		for(Equipment eq: this.equipmentService.search())
			e.add(new EquipmentCompaniesDto(eq));
		
		return new ResponseEntity<>(e, HttpStatus.OK);
	}
	
	@CrossOrigin(origins="http://localhost:4200")
	@PutMapping(value="updateEquipment/{equipment_id}")
	@PreAuthorize("hasAnyAuthority('ROLE_COMPANY_ADMIN')")

	public ResponseEntity<EquipmentDto> updateEquipment(@RequestBody EquipmentDto equipment,@PathVariable Long equipment_id) throws Exception{

		Equipment c=equipmentService.findById(equipment_id);
		c.setDescription(equipment.getDescription());
		c.setName(equipment.getName());
		c.setPrice(equipment.getPrice());
		c.setQuantity(equipment.getQuantity());
		c.setType(equipment.getType());
		Equipment e=equipmentService.save(c);

		return new ResponseEntity<>(new EquipmentDto(e), HttpStatus.OK);
	}
	
	
	@CrossOrigin(origins="http://localhost:4200")
    @PutMapping("/updateEquipment/")
	public ResponseEntity<Equipment> updateEquipment(@RequestBody Equipment equipment) {
		System.out.println(equipment.getName());
		if (equipment == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(equipmentService.update(equipment), HttpStatus.OK);
	}
}
