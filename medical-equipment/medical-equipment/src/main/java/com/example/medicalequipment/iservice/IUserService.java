package com.example.medicalequipment.iservice;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.medicalequipment.dto.UserRequest;
import com.example.medicalequipment.model.User;

@Service
public interface IUserService {

	//User save(User user);
	//User findOne(Long id);
	User findByUsername(String username);
	User findByEmail(String email);
	User findById(Long id);
	List<User> findAll ();
	User save(UserRequest userRequest);
	Long findIdByUsername(String username);
	User saveSystemAdmin(User user);
	List<User> getRegistratedUsersThatHaveReservationsAtCompany(Long company_id);

}
