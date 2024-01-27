package rs.ac.uns.ftn.informatika.rabbitmq;


import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Component
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id", scope = Producer.class)
public class Producer implements Serializable{
	private double longitude;
	private double latitude;
	public Producer() {
    }

    public Producer(double longitude, double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Location{" +
        "longitude='" + longitude + '\'' +
        ", latitude='" + latitude + '\'' +
        '}';
	}
	
}
