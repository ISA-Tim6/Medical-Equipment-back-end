package com.example.medicalequipment.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.medicalequipment.iservice.IUserService;
import com.example.medicalequipment.model.Category;
import com.example.medicalequipment.model.Employment;
import com.example.medicalequipment.model.User;
import com.example.medicalequipment.repository.IUserRepository;

@Service
public class UserService implements IUserService{
	private final IUserRepository UserRepository;
	@Autowired
    public UserService(IUserRepository userRepository){
    	this.UserRepository = userRepository;
    }
	@Override
    public User save(User u) 
	{
		return this.UserRepository.save(u);
    }

	@Override
	public User findOne(Long id) 
	{
		return UserRepository.findById(id).orElseGet(null);
	}
	
	@Override
	public User findByUsername(String username) 
	{
		return UserRepository.getByUsername(username);
	}
	
	
	


}
