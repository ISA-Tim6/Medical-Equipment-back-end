package com.example.medicalequipment.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class CompanyAdmin extends User{

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "company_id",nullable=false)

	private Company company;
	
	public CompanyAdmin(String name, String surname, String username, String password, String email, String phoneNumber,
			String city,String country, Employment employment,Company company) {
		super(name,surname,username,password,email,phoneNumber,city,country,employment);
		this.company=company;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}
	
	
}
