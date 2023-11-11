package com.example.medicalequipment.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


@Entity
public class Company {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long company_id;
	@Column(name = "name", nullable = false)
	private String name;	
	@Column(name = "averageGrade", nullable = true)
	private double averageGrade;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "addressId", referencedColumnName = "addressId")
	private Address address;

	
	@OneToMany(mappedBy = "company", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<CompanyAdmin> admins= new HashSet<CompanyAdmin>();
	
	@ManyToMany( cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH})
	@JoinTable(name = "company_equipment", joinColumns = @JoinColumn(name = "equipment_id", referencedColumnName = "equipment_id"),
	inverseJoinColumns = @JoinColumn(name = "company_id", referencedColumnName = "company_id"))
	private Set<Equipment> equipment=new HashSet<Equipment>();
	
	
	
	public void addAdmin(CompanyAdmin ca) {
		admins.add(ca);
		ca.setCompany(this);
	}

	public void removeAdmin(CompanyAdmin ca) {
		admins.remove(ca);
		ca.setCompany(null);
	}
	
	public Company() {
		super();
	}

	public Long getId() {
		return company_id;
	}

	public void setId(Long id) {
		this.company_id = id;
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

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Set<CompanyAdmin> getAdmins() {
		return admins;
	}

	public void setAdmins(Set<CompanyAdmin> admins) {
		this.admins = admins;
	}

	public Company(String name, double averageGrade, Address address ) {
		super();
		this.name = name;
		this.averageGrade = averageGrade;
		this.address = address;
	}
	
	
	public Set<Equipment> getEquipment() {
		return equipment;
	}

	public void setEquipment(Set<Equipment> equipment) {
		this.equipment = equipment;
	}

	@Override	
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Company c = (Company) o;
		if (c.name == null || name == null) {
			return false;
		}
		return Objects.equals(name, c.name);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(name);
	}
	
}
