package com.example.medicalequipment.dto;

import java.util.ArrayList;
import java.util.List;

import com.example.medicalequipment.model.Company;
import com.example.medicalequipment.model.Equipment;

public class EquipmentCompaniesDto {
	private Long equipment_id;
	private String name;
	private String description;
	private String type;
	private List<CompanyDto> companies = new ArrayList<CompanyDto>();
	
	public EquipmentCompaniesDto(Equipment equipment) {
		this.equipment_id=equipment.getEquipment_id();
		this.name=equipment.getName();
		this.description=equipment.getDescription();
		this.type=equipment.getType();
		for(Company c: equipment.getCompanies())
		{
			this.companies.add(new CompanyDto(c));
		}
	}

	public List<CompanyDto> getCompanies() {
		return companies;
	}

	public void setCompanies(List<CompanyDto> companies) {
		this.companies = companies;
	}

	public Long getEquipment_id() {
		return equipment_id;
	}

	public void setEquipment_id(Long equipment_id) {
		this.equipment_id = equipment_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "EquipmentDto [equipment_id=" + equipment_id + ", name=" + name + ", description=" + description
				+ ", type=" + type + "]";
	}
}
