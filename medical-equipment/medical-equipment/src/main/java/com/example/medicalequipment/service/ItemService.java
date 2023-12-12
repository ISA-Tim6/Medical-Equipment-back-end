package com.example.medicalequipment.service;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Service;

import com.example.medicalequipment.iservice.IItemService;
import com.example.medicalequipment.model.ActivationToken;
import com.example.medicalequipment.model.Category;
import com.example.medicalequipment.model.Item;
import com.example.medicalequipment.model.RegistratedUser;
import com.example.medicalequipment.repository.IActivationTokenRepository;
import com.example.medicalequipment.repository.IEquipmentRepository;
import com.example.medicalequipment.repository.IItemRepository;
import com.example.medicalequipment.repository.IRegistratedUserRepository;
import com.example.medicalequipment.repository.IUserRepository;

@Service
public class ItemService implements IItemService {

	@Autowired
	private final IItemRepository ItemRepository;
	
	public ItemService(IItemRepository itemRepository){
    	this.ItemRepository = itemRepository;
    }
	@Override
	public Item save(Item item) throws MailException, InterruptedException, MessagingException {
		return this.ItemRepository.save(item);
	
	}

}
