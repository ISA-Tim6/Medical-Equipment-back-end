package com.example.medicalequipment.dto;

import java.util.HashSet;
import java.util.Set;

import com.example.medicalequipment.model.Company;
import com.example.medicalequipment.model.Equipment;

public class CompanySearchDto {
	private Long company_id;
	private String name;	
	private double averageGrade;
	private String city;
	
	public CompanySearchDto(Company company) {
		this.company_id=company.getId();
		this.name=company.getName();
		this.averageGrade=company.getAverageGrade();
		this.city=company.getAddress().getCity();		
	}
	
	public CompanySearchDto(Long company_id,String name,double averageGrade,AddressDto address) {
		this.company_id=company_id;
		this.name=name;
		this.averageGrade=averageGrade;
		this.city=address.getCity();
	}
	public CompanySearchDto() {}
	
	
	
}
