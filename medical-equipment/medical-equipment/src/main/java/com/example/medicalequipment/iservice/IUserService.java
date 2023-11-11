package com.example.medicalequipment.iservice;

import org.springframework.stereotype.Service;

import com.example.medicalequipment.model.User;

@Service
public interface IUserService {

	User save(User user);
	User findOne(Long id);
	User findByUsername(String username);
	User update(User user, String oldUsername);
}
