package com.example.medicalequipment.model;

public class Contract {
	private Integer dayInMonth;
	private String equipment;
	private Integer quantity;
	private String company;

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Ugovor{" +
        "dan='" + dayInMonth + '\'' +
        ", oprema='" + equipment + '\'' +", kolicina= "+quantity+
        '}';
	}

	public Integer getDayInMonth() {
		return dayInMonth;
	}

	public void setDayInMonth(Integer dayInMonth) {
		this.dayInMonth = dayInMonth;
	}

	public String getEquipment() {
		return equipment;
	}

	public void setEquipment(String equipment) {
		this.equipment = equipment;
	}



	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}
}
