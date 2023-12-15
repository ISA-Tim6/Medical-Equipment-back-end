package com.example.medicalequipment.model;

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
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Equipment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long equipment_id;
	@Column(name = "name", nullable = false)
	@NotNull @NotEmpty
	private String name;
	@Column(name = "description", nullable = false)
	@NotNull @NotEmpty
	private String description;
	@NotNull @NotEmpty
	@Column(name = "type", nullable = false)
	private String type;
	@NotNull
	@Column(name = "price", nullable = false)
	private Double price;
	@NotNull 
	@Column(name = "quantity", nullable = false)
	private Integer quantity;
	
	@ManyToMany(mappedBy = "equipment",fetch=FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnore
	//@JoinTable(name = "company_equipment", joinColumns = @JoinColumn(name = "company_id", referencedColumnName = "company_id"),
	//inverseJoinColumns = @JoinColumn(name = "equipment_id", referencedColumnName = "equipment_id"))
	private Set<Company> companies=new HashSet<Company>();
	
	public Equipment() {}
	
	public Equipment(String description,String name,String type,Double price,Integer quantity)
	{
		this.description=description;
		this.type=type;
		this.name=name;
		this.price=price;
		this.quantity=quantity;
	}


	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
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

	public Set<Company> getCompanies() {
		return this.companies;
	}

	public void setCompanies(Set<Company> companies) {
		this.companies = companies;
	}


}
