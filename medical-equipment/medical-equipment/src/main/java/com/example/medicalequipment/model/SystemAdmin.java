package com.example.medicalequipment.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "SystemAdmin")
public class SystemAdmin extends User{

	public SystemAdmin() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SystemAdmin(String name, String surname, String username, String password, String email, String phoneNumber,
			String city, String country, Employment employment, String infoAboutInstitution,boolean isActive ) {
		super(name, surname, username, password, email, phoneNumber, city, country, employment, infoAboutInstitution,isActive);
		// TODO Auto-generated constructor stub
	}

	public SystemAdmin(User user) {
		super(user);
		// TODO Auto-generated constructor stub
	}

}
