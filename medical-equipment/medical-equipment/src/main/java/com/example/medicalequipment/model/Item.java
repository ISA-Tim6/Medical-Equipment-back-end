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
import javax.persistence.ManyToOne;

@Entity
public class Item {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long item_id;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinTable(name = "equipment_item", joinColumns = @JoinColumn(name = "item_id", referencedColumnName = "item_id"),
	inverseJoinColumns = @JoinColumn(name = "equipment_id", referencedColumnName = "equipment_id"))
	private Equipment equipment;
	
	@Column(name="quantity")
	private int quantity;
	
	public Item() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Item(Equipment equipment, int quantity) {
		super();
		this.equipment = equipment;
		this.quantity = quantity;
	}
	public Equipment getEquipment() {
		return equipment;
	}
	public void setEquipment(Equipment equipment) {
		this.equipment = equipment;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
}
