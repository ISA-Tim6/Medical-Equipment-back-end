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
	
	@ManyToMany(mappedBy = "equipment",fetch=FetchType.LAZY,cascade = CascadeType.PERSIST)
	private Set<Company> companies=new HashSet<Company>();
	
	private Equipment() {}
	
	public Equipment(String description,String name,String type)
	{
		this.description=description;
		this.type=type;
		this.name=name;
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

	@Override
	public String toString() {
		return "Equipment [equipment_id=" + equipment_id + ", name=" + name + ", description=" + description + ", type="
				+ type + ", equipment=" + companies + "]";
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Equipment e = (Equipment) o;
		return equipment_id != null && equipment_id.equals(e.getEquipment_id());
	}

	@Override
	public int hashCode() {
	
		return 1337;
	}
}
