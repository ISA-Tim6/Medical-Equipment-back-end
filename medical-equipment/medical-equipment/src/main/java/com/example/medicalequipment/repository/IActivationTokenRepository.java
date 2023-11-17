package com.example.medicalequipment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.medicalequipment.model.ActivationToken;

@Repository
public interface IActivationTokenRepository extends JpaRepository<ActivationToken,Long>{
	ActivationToken findByToken(String token);
}
