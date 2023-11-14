package com.example.medicalequipment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.medicalequipment.iservice.IUserService;
import com.example.medicalequipment.model.User;
import com.example.medicalequipment.repository.IUserRepository;

@Service
public class UserService implements IUserService{
	@Autowired
	private final IUserRepository UserRepository;
	public UserService(IUserRepository userRepository){
    	this.UserRepository = userRepository;
    }
	@Override
    public User save(User u) {
    	return this.UserRepository.save(u);
    }

	@Override
	public User findOne(Long id) {
		return UserRepository.findById(id).orElseGet(null);
	}


}
