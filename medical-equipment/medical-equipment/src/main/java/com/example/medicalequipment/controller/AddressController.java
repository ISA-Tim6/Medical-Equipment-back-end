package com.example.medicalequipment.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.medicalequipment.model.Address;
import com.example.medicalequipment.service.AddressService;


@RestController
public class AddressController {

	private AddressService addressService;
	
	@Autowired
	public AddressController(AddressService service) {
		this.addressService = service;
	}

	
	
	@PostMapping("/addressPost")
	public Address CreateAdress(@RequestBody Address address) {
		return addressService.saveAddress(address);
	}
}
