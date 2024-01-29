package com.example.medicalequipment.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ContractCompany {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long contract_id;
	@Column(name = "equipment", nullable = false)
	private String equipment;
	@Column(name = "dayInMonth", nullable = false)
	private Integer dayInMonth;
	@Column(name = "companyName", nullable = false)
	private String companyName;
	@Column(name = "quantity", nullable = false)
	private Integer quantity;
	public Long getContract_id() {
		return contract_id;
	}
	public void setContract_id(Long contract_id) {
		this.contract_id = contract_id;
	}
	public String getEquipment() {
		return equipment;
	}
	public void setEquipment(String equipment) {
		this.equipment = equipment;
	}
	public Integer getDayInMonth() {
		return dayInMonth;
	}
	public void setDayInMonth(Integer dayInMonth) {
		this.dayInMonth = dayInMonth;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public ContractCompany(String equipment, Integer dayInMonth, String companyName,
			Integer quantity) {
		super();
		this.equipment = equipment;
		this.dayInMonth = dayInMonth;
		this.companyName = companyName;
		this.quantity = quantity;
	}
	public ContractCompany() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	
}
