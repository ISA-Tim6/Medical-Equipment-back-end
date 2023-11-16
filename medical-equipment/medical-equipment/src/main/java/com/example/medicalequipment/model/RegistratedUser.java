package com.example.medicalequipment.model;

import static javax.persistence.InheritanceType.JOINED;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.Table;

@Entity
@Table(name = "`RegistratedUser`")
@Inheritance(strategy=JOINED)
public class RegistratedUser extends User {

	@Column(name = "penals", nullable = false)
	private int penals;
	@Column(name = "category", nullable = false)
	private Category category;
	
	public RegistratedUser() {
		this.penals = 0;
		this.category = Category.REGULAR;
	}
	
	public  RegistratedUser(RegistratedUser user) {
		super(user.getName(), user.getSurname(), user.getUsername(), user.getPassword(), user.getEmail(), user.getPhoneNumber(), user.getCity(), user.getCountry(), user.getEmployment(), user.getInfoAboutInstitution());
		this.penals = 0;
		this.category = Category.REGULAR;
	}

	public RegistratedUser(String name, String surname, String username, String password, String email,
			String phoneNumber, String city, String country, Employment employment, String infoAboutInstitution) {
		super(name, surname, username, password, email, phoneNumber, city, country, employment, infoAboutInstitution);
		this.penals = 0;
		this.category = Category.REGULAR;
	}



	public int getPenals() {
		return penals;
	}

	public void setPenals(int penals) {
		this.penals = penals;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
	
}
