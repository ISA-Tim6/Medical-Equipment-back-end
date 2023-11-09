package com.example.medicalequipment.service;

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

    /*public void registerUser(User u) {
        String encodedPassword = passwordEncoder.encode(u.getPassword());
        u.setPassword(encodedPassword);
        ActivationCode activationCode = activationCodeService.generateAndSaveCode(u.getEmail());
        emailService.sendActivationEmail(u.getEmail(), activationCode.getCode());

        u.setUserStatus(UserStatus.NOT_ACTIVATED);
        u.setURN("123456789012");
        this.AddressRepository.save(u.getAddress());
    	this.UserRepository.save(u);
    }*/


}
