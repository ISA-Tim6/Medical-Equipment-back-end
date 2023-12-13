package com.example.medicalequipment.controller;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.medicalequipment.iservice.IEquipmentService;
import com.example.medicalequipment.iservice.IItemService;
import com.example.medicalequipment.model.Item;
import com.example.medicalequipment.model.RegistratedUser;

@RestController
@RequestMapping(path="api/item/")
public class ItemController {
	@Autowired
	private IItemService itemService;
	
	public ItemController(IItemService service)
	{
		this.itemService = service;
	}
	@CrossOrigin(origins="http://localhost:4200")
    @PostMapping("saveItem")
    public Item save(@RequestBody Item item) throws MailException, InterruptedException, MessagingException {
    	return itemService.save(item);
    }
}
