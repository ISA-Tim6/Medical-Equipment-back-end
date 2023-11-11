package com.example.medicalequipment.dto;

import javax.persistence.Column;

import com.example.medicalequipment.model.Employment;
import com.example.medicalequipment.model.User;

public class UserResponseDto {
	private long id;
	private String name;
	private String surname;
	private String username;
	private String password;
	private String email;
	private Boolean loggedBefore;
	private String phoneNumber;
	private String city;
	private String country;
	private Employment employment;
	public UserResponseDto() {}
	
	public UserResponseDto(User user) {
		this(user.getUser_id(), user.getName(), user.getSurname(), user.getUsername(), user.getPassword(), user.getEmail(), user.getLoggedBefore(), user.getPhoneNumber(), user.getCity(), user.getCountry(), user.getEmployment(), user.getInfoAboutInstitution());
	}
	public UserResponseDto(long id, String name, String surname, String username, String password, String email,
			Boolean loggedBefore, String phoneNumber, String city, String country, Employment employment,
			String infoAboutInstitution) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.username = username;
		this.password = password;
		this.email = email;
		this.loggedBefore = loggedBefore;
		this.phoneNumber = phoneNumber;
		this.city = city;
		this.country = country;
		this.employment = employment;
		this.infoAboutInstitution = infoAboutInstitution;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getLoggedBefore() {
		return loggedBefore;
	}

	public void setLoggedBefore(Boolean loggedBefore) {
		this.loggedBefore = loggedBefore;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Employment getEmployment() {
		return employment;
	}

	public void setEmployment(Employment employment) {
		this.employment = employment;
	}

	public String getInfoAboutInstitution() {
		return infoAboutInstitution;
	}

	public void setInfoAboutInstitution(String infoAboutInstitution) {
		this.infoAboutInstitution = infoAboutInstitution;
	}

	private String infoAboutInstitution;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
