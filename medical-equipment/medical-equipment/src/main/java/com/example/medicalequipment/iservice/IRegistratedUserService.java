package com.example.medicalequipment.iservice;

import org.springframework.mail.MailException;
import org.springframework.stereotype.Service;

import com.example.medicalequipment.model.RegistratedUser;
import com.example.medicalequipment.model.User;

@Service
public interface IRegistratedUserService {
	RegistratedUser save(RegistratedUser user) throws MailException, InterruptedException;
	RegistratedUser findOne(Long id);
	RegistratedUser findByUsername(String username);
	RegistratedUser update(RegistratedUser user, String oldUsername);
	RegistratedUser findByEmail(String email);
}