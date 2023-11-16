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

import com.example.medicalequipment.dto.EquipmentCompaniesDto;
import com.example.medicalequipment.dto.EquipmentDto;
import com.example.medicalequipment.iservice.IEquipmentService;
import com.example.medicalequipment.model.Address;
import com.example.medicalequipment.model.Equipment;
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
}
