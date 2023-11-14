package com.example.medicalequipment.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "CompanyAdmin")
public class CompanyAdmin extends User{


	@ManyToOne()
	@JoinColumn(name = "company_id", referencedColumnName="company_id",nullable=false)
	private Company company;
	
	
	public CompanyAdmin(String name, String surname, String username, String password, String email, String phoneNumber,
			String city,String country, Employment employment,Company company,String info) {
		super(name,surname,username,password,email,phoneNumber,city,country,employment,info);
		this.company=company;
	}
	public CompanyAdmin() {}

	public CompanyAdmin(User user) 
	{
		this.setPassword(user.getPassword());
		this.setCity(user.getCity());
	 	this.setCountry(user.getCountry());
	 	this.setEmail(user.getEmail());
	 	this.setEmployment(user.getEmployment());
	 	this.setInfoAboutInstitution(user.getInfoAboutInstitution());
	 	this.setUser_id(user.getUser_id());
	 	this.setLoggedBefore(user.getLoggedBefore());
	 	this.setUsername(user.getUsername());
	 	this.setPhoneNumber(user.getPhoneNumber());
	 	this.setName(user.getName());
	 	this.setSurname(user.getSurname());
	}
	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}
	
	@Override
	public int hashCode() {
		return 1337;
	}
	

	
}
