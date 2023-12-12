package com.example.medicalequipment.dto;


import com.example.medicalequipment.model.Equipment;

public class EquipmentDto {
	private Long equipment_id;
	private String name;
	private String description;
	private String type;
	private Double price;
	private Integer quantity;
	
	public EquipmentDto(Equipment equipment) {
		this.equipment_id=equipment.getEquipment_id();
		this.name=equipment.getName();
		this.description=equipment.getDescription();
		this.type=equipment.getType();
		this.price=equipment.getPrice();
		this.quantity=equipment.getQuantity();
	}
	public EquipmentDto() {}

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

	@Override
	public String toString() {
		return "EquipmentDto [equipment_id=" + equipment_id + ", name=" + name + ", description=" + description
				+ ", type=" + type + "]";
	}
	
}
