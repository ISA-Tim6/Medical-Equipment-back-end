package com.example.medicalequipment.iservice;

import org.springframework.stereotype.Service;

import com.example.medicalequipment.model.ActivationToken;
import com.example.medicalequipment.model.User;

@Service
public interface IActivationTokenService {
	ActivationToken findByToken(String token);
	ActivationToken save(User user);
}
