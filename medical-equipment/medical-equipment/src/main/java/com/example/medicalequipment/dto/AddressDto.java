package com.example.medicalequipment.dto;

import javax.persistence.Column;

import com.example.medicalequipment.model.Address;

public class AddressDto {
	
	private Long addressId;
	private String street;
	private String streetNumber;
	private String city;
	private String country;
	private Double longitude;
	private Double latitude;
	
	
	public AddressDto(Address address) {
		this.addressId=address.getAddressId();
		this.street=address.getStreet();
		this.streetNumber=address.getStreetNumber();
		this.city=address.getCity();
		this.country=address.getCountry();
		this.longitude=address.getLongitude();
		this.latitude=address.getLatitude();
	}


	@Override
	public String toString() {
		return "AddressDto [addressId=" + addressId + ", street=" + street + ", streetNumber=" + streetNumber
				+ ", city=" + city + ", country=" + country + ", longitude=" + longitude + ", latitude=" + latitude
				+ "]";
	}


	public Long getAddressId() {
		return addressId;
	}


	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}


	public String getStreet() {
		return street;
	}


	public void setStreet(String street) {
		this.street = street;
	}


	public String getStreetNumber() {
		return streetNumber;
	}


	public void setStreetNumber(String streetNumber) {
		this.streetNumber = streetNumber;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getCountry() {
		return country;
	}


	public void setCountry(String country) {
		this.country = country;
	}


	public Double getLongitude() {
		return longitude;
	}


	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}


	public Double getLatitude() {
		return latitude;
	}


	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	
}
