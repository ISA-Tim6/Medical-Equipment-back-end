package com.example.medicalequipment.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.medicalequipment.iservice.IUserService;
import com.example.medicalequipment.model.User;
import com.example.medicalequipment.repository.IUserRepository;

@Service
public class UserService implements IUserService{
	private final IUserRepository UserRepository;
	@Autowired
    public UserService(IUserRepository userRepository){
    	this.UserRepository=userRepository;
    }
	@Override
    public User save(User u) {
    	return this.UserRepository.save(u);
    }
	public User getByEmail(String email){
		return UserRepository.findByEmail(email);
	}


    public List<User> getAllUsers(){
        return UserRepository.findAll();
    }



}
