package com.example.medicalequipment.dto;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;

import com.example.medicalequipment.model.Equipment;
import com.example.medicalequipment.model.Item;

public class ItemDto {

	private Long item_id;
	private EquipmentDto equipment;
	private int quantity;
	
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public ItemDto(Item item) {
		this.item_id = item.getItemId();
		this.quantity = item.getQuantity();
		this.equipment = new EquipmentDto(item.getEquipment());
	}
	public Long getItem_id() {
		return item_id;
	}
	public void setItem_id(Long item_id) {
		this.item_id = item_id;
	}
	public EquipmentDto getEquipment() {
		return equipment;
	}
	public void setEquipment(EquipmentDto equipment) {
		this.equipment = equipment;
	}
	
}
