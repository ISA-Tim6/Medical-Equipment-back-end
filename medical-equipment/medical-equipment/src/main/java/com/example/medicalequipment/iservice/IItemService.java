package com.example.medicalequipment.iservice;

import javax.mail.MessagingException;

import org.springframework.mail.MailException;
import org.springframework.stereotype.Service;

import com.example.medicalequipment.model.Item;

@Service
public interface IItemService {
	Item save(Item i) throws MailException, InterruptedException, MessagingException;

}
