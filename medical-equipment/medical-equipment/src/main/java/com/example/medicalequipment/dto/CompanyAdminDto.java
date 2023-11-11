package com.example.medicalequipment.dto;

import com.example.medicalequipment.model.CompanyAdmin;
import com.example.medicalequipment.model.Employment;

public class CompanyAdminDto {
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
	private CompanyDto company;
	
	@Override
	public String toString() {
		return "CompanyAdminDto [id=" + id + ", name=" + name + ", surname=" + surname + ", username=" + username
				+ ", password=" + password + ", email=" + email + ", loggedBefore=" + loggedBefore + ", phoneNumber="
				+ phoneNumber + ", city=" + city + ", country=" + country + ", employment=" + employment + ", company="
				+ company.toString() + "]";
	}

	public CompanyAdminDto(CompanyAdmin ca){
		this.id=ca.getUser_id();
		this.name=ca.getName();
		this.surname=ca.getSurname();
		this.username=ca.getUsername();
		this.password=ca.getPassword();
		this.email=ca.getEmail();
		this.loggedBefore=ca.getLoggedBefore();
		this.phoneNumber=ca.getPhoneNumber();
		this.city=ca.getCity();
		this.country=ca.getCountry();
		this.employment=ca.getEmployment();
		this.company=new CompanyDto(ca.getCompany());
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getLoggedBefore() {
		return loggedBefore;
	}

	public void setLoggedBefore(Boolean loggedBefore) {
		this.loggedBefore = loggedBefore;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
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

	public CompanyDto getCompany() {
		return company;
	}

	public void setCompany(CompanyDto company) {
		this.company = company;
	}
	
}
