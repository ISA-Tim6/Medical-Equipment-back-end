package com.example.medicalequipment.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.medicalequipment.iservice.IActivationTokenService;
import com.example.medicalequipment.model.ActivationToken;
import com.example.medicalequipment.model.RegistratedUser;
import com.example.medicalequipment.model.User;
import com.example.medicalequipment.repository.IActivationTokenRepository;
import com.example.medicalequipment.repository.IRegistratedUserRepository;

@Service
public class ActivationTokenService implements IActivationTokenService{
	@Autowired
	private final IActivationTokenRepository activationTokenRepository;
	@Autowired
	private final IRegistratedUserRepository userRepository;
	@Autowired
	private final EmailService emailService;

	public ActivationTokenService(IActivationTokenRepository activationTokenRepository, IRegistratedUserRepository userRepository, EmailService emailService) {
		super();
		this.activationTokenRepository = activationTokenRepository;
		this.userRepository = userRepository;
		this.emailService = emailService;
	}

	@Override
	public ActivationToken findByToken(String token) {
		// TODO Auto-generated method stub
		return activationTokenRepository.findByToken(token);
	}

	@Override
	public ActivationToken save(User user) {
		// TODO Auto-generated method stub
		String token=UUID.randomUUID().toString();
		ActivationToken activationToken = new ActivationToken(token,user);
		return activationTokenRepository.save(activationToken);
	}
	
}
