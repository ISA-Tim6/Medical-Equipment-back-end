package com.example.medicalequipment.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "CompanyAdmin")
public class CompanyAdmin extends User{


	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "company_id", referencedColumnName="company_id",nullable=false)
	private Company company;
	
	
	public CompanyAdmin(String name, String surname, String username, String password, String email, String phoneNumber,
			String city,String country, Employment employment,Company company,String info,boolean isActive ) {
		super(name,surname,username,password,email,phoneNumber,city,country,employment,info,isActive);
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
