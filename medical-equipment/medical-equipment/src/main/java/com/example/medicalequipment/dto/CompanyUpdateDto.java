package com.example.medicalequipment.dto;

import com.example.medicalequipment.model.Address;

public class CompanyUpdateDto {
	private String name;
	private Address address;
	private double averageGrade;
	private Long company_id;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public double getAverageGrade() {
		return averageGrade;
	}
	public void setAverageGrade(double averageGrade) {
		this.averageGrade = averageGrade;
	}
	public Long getId() {
		return company_id;
	}
	public void setId(Long id) {
		this.company_id = id;
	}
	public CompanyUpdateDto() {}
	public CompanyUpdateDto(String name, Address address, double averageGrade, Long company_id) {
		super();
		this.name = name;
		this.address = address;
		this.averageGrade = averageGrade;
		this.company_id = company_id;
	}
	
}
