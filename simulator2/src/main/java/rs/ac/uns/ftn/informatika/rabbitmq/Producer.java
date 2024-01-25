package rs.ac.uns.ftn.informatika.rabbitmq;


import java.io.Serializable;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Component
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id", scope = Producer.class)
public class Producer implements Serializable{
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
