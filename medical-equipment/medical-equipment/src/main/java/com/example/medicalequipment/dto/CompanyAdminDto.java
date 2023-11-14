package com.example.medicalequipment.dto;

import com.example.medicalequipment.model.CompanyAdmin;
import com.example.medicalequipment.model.Employment;

public class CompanyAdminDto {
	private Long user_id;
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
	private Long company_id;
	private String infoAboutInstitution;
	
	@Override
	public String toString() {
		return "CompanyAdminDto [id=" + user_id + ", name=" + name + ", surname=" + surname + ", username=" + username
				+ ", password=" + password + ", email=" + email + ", loggedBefore=" + loggedBefore + ", phoneNumber="
				+ phoneNumber + ", city=" + city + ", country=" + country + ", employment=" + employment + ", company="
				+ company_id + "]";
	}

	public String getInfoAboutInstitution() {
		return infoAboutInstitution;
	}

	public void setInfoAboutInstitution(String infoAboutInstitution) {
		this.infoAboutInstitution = infoAboutInstitution;
	}
	
	public CompanyAdminDto() {
		
	}


	public CompanyAdminDto(CompanyAdmin ca){
		this.user_id=ca.getUser_id();
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
		this.infoAboutInstitution=ca.getInfoAboutInstitution();
		this.company_id=ca.getCompany().getId();
		//this.company=new CompanyDto(ca.getCompany());
	}

	public long getId() {
		return user_id;
	}

	public void setId(long id) {
		this.user_id = id;
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

	public Long getCompany_id() {
		return company_id;
	}

	public void setCompany_id(Long company_id) {
		this.company_id = company_id;
	}
	
}
