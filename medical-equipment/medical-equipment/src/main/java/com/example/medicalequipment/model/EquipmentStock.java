package com.example.medicalequipment.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class EquipmentStock {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long equipment_stock_id;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "equipment_id", referencedColumnName = "equipment_id")
	private Equipment equipment;
	@Column(name = "quantity", nullable = false)
	private int quantity;
	public EquipmentStock() {
		super();
		// TODO Auto-generated constructor stub
	}
	public EquipmentStock(Equipment equipment, int quantity) {
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
