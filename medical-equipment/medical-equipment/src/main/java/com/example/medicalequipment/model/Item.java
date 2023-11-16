package com.example.medicalequipment.model;

public class Item {
	private Equipment equipment;
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
