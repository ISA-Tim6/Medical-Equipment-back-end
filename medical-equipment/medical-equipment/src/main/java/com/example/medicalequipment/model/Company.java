package com.example.medicalequipment.model;

import java.time.LocalTime;
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
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class Company {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long company_id;
	@NotNull @NotEmpty
	@Column(name = "name", nullable = false)
	private String name;	
	@Min(value=0)
	@Column(name = "averageGrade", nullable = true)
	private double averageGrade;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "addressId", referencedColumnName = "addressId")
	@NotNull
	private Address address;

	
	@OneToMany(mappedBy = "company", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	//@JoinColumn(name = "company_id", nullable=false)
	@JsonIgnore
	private Set<CompanyAdmin> admins= new HashSet<CompanyAdmin>();
	
	@ManyToMany(fetch=FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "company_equipment", joinColumns = @JoinColumn(name = "company_id", referencedColumnName = "company_id"),
	inverseJoinColumns = @JoinColumn(name = "equipment_id", referencedColumnName = "equipment_id"))
	private Set<Equipment> equipment=new HashSet<Equipment>();
	
	
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "workingTimeCalendar_id")
	private WorkingTimeCalendar workingTimeCalendar;
	

	@Column(name = "openingHours")
	@NotNull
	private LocalTime openingHours;

	@Column(name = "closingHours")
	@NotNull
	private LocalTime closingHours;
	
	
	  public Long getCompany_id() {
		return company_id;
	}

	public void setCompany_id(Long company_id) {
		this.company_id = company_id;
	}

	public LocalTime getOpeningHours() {
		return openingHours;
	}

	public void setOpeningHours(LocalTime openingHours) {
		this.openingHours = openingHours;
	}

	public LocalTime getClosingHours() {
		return closingHours;
	}

	public void setClosingHours(LocalTime closingHours) {
		this.closingHours = closingHours;
	}

	public void add(CompanyAdmin ca) {
		    if (ca.getCompany() != null)
		     ca.getCompany().getAdmins().remove(ca);
		    ca.setCompany(this);
		    this.getAdmins().add(ca);
		  }
	
	public void addEquipment(Equipment e) {
		if(e!=null)
		e.getCompanies().add(this);
		e.setCompanies(e.getCompanies());
		this.equipment.add(e);
	}
	
	public void removeEquipment(Equipment e) {
		this.equipment.remove(e);
	}
	
	public Company() {
		super();
		this.workingTimeCalendar=new WorkingTimeCalendar();
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
		this.workingTimeCalendar=new WorkingTimeCalendar();
	}
	
	
	public Set<Equipment> getEquipment() {
		return equipment;
	}

	@Override
	public String toString() {
		return "Company [company_id=" + company_id + ", name=" + name + ", averageGrade=" + averageGrade + ", address="
				+ address + ", admins=" + admins + ", equipment=" + equipment + "]";
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


	public WorkingTimeCalendar getWorkingTimeCalendar() {
		return workingTimeCalendar;
	}

	public void setWorkingTimeCalendar(WorkingTimeCalendar workingTimeCalendar) {
		this.workingTimeCalendar = workingTimeCalendar;
	}
	
}
