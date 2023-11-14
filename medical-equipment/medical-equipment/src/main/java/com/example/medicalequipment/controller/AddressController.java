package com.example.medicalequipment.controller;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.medicalequipment.iservice.IAdressService;
import com.example.medicalequipment.model.Address;


@RestController
@RequestMapping(path="api/")
public class AddressController {
	@Autowired

	private IAdressService addressService;
	
	public AddressController(IAdressService service) {
		this.addressService = service;
	}

	
	@CrossOrigin(origins="http://localhost:4200")
	@PostMapping("/addressPost")
	public Address CreateAdress(@RequestBody Address address) {
		return addressService.saveAddress(address);
	}
	
}
