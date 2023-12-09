package com.example.medicalequipment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.medicalequipment.model.RegistratedUser;
import com.example.medicalequipment.model.User;


@Repository
public interface IRegistratedUserRepository extends JpaRepository<RegistratedUser, Long> {
	@Query("select u from User u where u.username=?1")
	RegistratedUser getByUsername(String username);

	@Query("select u from User u where u.email=?1")
	RegistratedUser findByEmail(String email);
	
	@Query("SELECT u FROM User u WHERE u.verificationCode = ?1")
    public RegistratedUser findByVerificationCode(String code);
}
