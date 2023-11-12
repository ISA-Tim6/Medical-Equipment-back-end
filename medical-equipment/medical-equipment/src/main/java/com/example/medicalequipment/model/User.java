package com.example.medicalequipment.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import static javax.persistence.InheritanceType.JOINED;
@Entity
@Table(name = "`User`")
@Inheritance(strategy=JOINED)
public class User {

	@Id
	@SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
	private Long id;
	@Column(name = "name", nullable = false)
	private String name;
	@Column(name = "surname", nullable = false)
	private String surname;
	@Column(name = "username", nullable = false)
	private String username;
	@Column(name = "password", nullable = false)
	private String password;
	@Column(name = "email", nullable = false)
	private String email;
	@Column(name = "loggedBefore", nullable = false)
	private Boolean loggedBefore;
	@Column(name = "phoneNumber", nullable = false)
	private String phoneNumber;
	@Column(name = "city", nullable = false)
	private String city;
	@Column(name = "country", nullable = false)
	private String country;
	@Column(name = "employment", nullable = false)
	private Employment employment;
	@Column(name = "infoAboutInstitution", nullable = false)
	private String infoAboutInstitution;
	@Column(name = "penals", nullable = false)
	private int penals;
	@Column(name = "category", nullable = false)
	private Category category;
	public User() {
		this.penals = 0;
		this.category = Category.REGULAR;
	}

	
	public User(String name, String surname, String username, String password, String email, String phoneNumber,
			String city,String country, Employment employment, String infoAboutInstitution) {
		this.name=name;
		this.surname=surname;
		this.email=email;
		this.password=password;
		this.phoneNumber=phoneNumber;
		this.loggedBefore=false;
		this.city=city;
		this.country=country;
		this.employment=employment;	
		this.infoAboutInstitution=infoAboutInstitution;
		this.penals = 0;
		this.category = Category.REGULAR;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public int getPenals() {
		return penals;
	}


	public void setPenals(int penals) {
		this.penals = penals;
	}


	public Category getCategory() {
		return category;
	}


	public void setCategory(Category category) {
		this.category = category;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getSurname() {
		return surname;
	}


	public void setSurname(String surname) {
		this.surname = surname;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public Boolean getLoggedBefore() {
		return loggedBefore;
	}


	public void setLoggedBefore(Boolean loggedBefore) {
		this.loggedBefore = loggedBefore;
	}


	public String getPhoneNumber() {
		return phoneNumber;
	}


	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
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


	public Employment getEmployment() {
		return employment;
	}


	public void setEmployment(Employment employment) {
		this.employment = employment;
	}
	
	public String getInfoAboutInstitution() {
		return infoAboutInstitution;
	}


	public void setInfoAboutInstitution(String infoAboutInstitution) {
		this.infoAboutInstitution = infoAboutInstitution;
	}

}
