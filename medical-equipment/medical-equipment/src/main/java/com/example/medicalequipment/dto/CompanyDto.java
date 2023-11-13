package com.example.medicalequipment.dto;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.medicalequipment.model.Equipment;

import com.example.medicalequipment.model.Address;
import com.example.medicalequipment.model.Company;
import com.example.medicalequipment.model.CompanyAdmin;

public class CompanyDto {

	private Long company_id;
	private String name;	
	private double averageGrade;
	private AddressDto address;
	private Set<EquipmentDto> equipment=new HashSet<EquipmentDto>();

	
	public CompanyDto(Company company) {
		this.company_id=company.getId();
		this.name=company.getName();
		this.averageGrade=company.getAverageGrade();
		this.address=new AddressDto(company.getAddress());
		if(!company.getEquipment().isEmpty())
		{
			for(Equipment e:company.getEquipment()) {
				equipment.add(new EquipmentDto(e));
			}		
		}
		
	}
	
	public CompanyDto(Long company_id,String name,double averageGrade,AddressDto address) {
		this.company_id=company_id;
		this.name=name;
		this.averageGrade=averageGrade;
		this.address=address;
	}
	public CompanyDto() {}
	
	@Override
	public String toString() {
		return "CompanyDto [company_id=" + company_id + ", name=" + name + ", averageGrade=" + averageGrade
				+ ", address=" + address.toString() + "]";
	}

	public Long getCompany_id() {
		return company_id;
	}

	public void setCompany_id(Long company_id) {
		this.company_id = company_id;
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

	public AddressDto getAddress() {
		return address;
	}

	public Set<EquipmentDto> getEquipment() {
		return equipment;
	}

	public void setEquipment(Set<EquipmentDto> equipment) {
		this.equipment = equipment;
	}

	public void setAddress(AddressDto address) {
		this.address = address;
	}
	
}
