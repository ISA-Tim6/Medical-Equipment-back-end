package com.example.medicalequipment.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Company {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "name", nullable = false)
	private String name;	
	@Column(name = "averageGrade", nullable = true)
	private double averageGrade;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "addressId", referencedColumnName = "addressId")
	private Address address;

	
	@OneToMany(mappedBy = "company", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<CompanyAdmin> admins;
	
	public Company() {
		this.admins=new HashSet<CompanyAdmin>();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getAverageGrade() {
		return averageGrade;
	}

	public void setAverageGrade(double averageGrade) {
		this.averageGrade = averageGrade;
	}

	/*public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}*/

	//public Set<CompanyAdmin> getAdmins() {
	//	return admins;
	//}

	public void setAdmins(Set<CompanyAdmin> admins) {
	//	this.admins = admins;
	}

	public Company(String name, double averageGrade, Address address,Set<CompanyAdmin> admins ) {
		super();
		this.name = name;
		this.averageGrade = averageGrade;
		this.address = address;
		this.admins = admins;
	}
	
	
	
	
}
