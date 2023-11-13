package com.example.medicalequipment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.medicalequipment.model.RegistratedUser;
import com.example.medicalequipment.model.User;


@Repository
public interface IRegistratedUserRepository extends JpaRepository<RegistratedUser, Long> {
	
	RegistratedUser getByUsername(String username);

	@Query("select u from RegistratedUser u where u.email=?1")
	RegistratedUser findByEmail(String email);
}
