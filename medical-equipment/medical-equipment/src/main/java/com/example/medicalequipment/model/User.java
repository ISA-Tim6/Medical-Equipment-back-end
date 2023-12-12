package com.example.medicalequipment.model;

import static javax.persistence.InheritanceType.JOINED;

import java.security.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
@Table(name = "`User`")
@Inheritance(strategy=JOINED)
public class User implements UserDetails{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long user_id;
	@Column(name = "name", nullable = false)
	@NotNull
	private String name;
	@Column(name = "surname", nullable = false)
	@NotNull
	private String surname;
	@Column(name = "username", nullable = false, unique=true)
	@NotNull
	private String username;
	@Column(name = "password", nullable = false)
	@NotNull
	private String password;
	@Column(name = "email", nullable = false, unique=true)
	@Email(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")
	private String email;
	@Column(name = "loggedBefore", nullable = false)
	private Boolean loggedBefore;
	@Column(name = "phoneNumber", nullable = false)
	@Size(min = 9, max = 10, message = "Must contain 9 or 10 digits.")
    @Pattern(regexp = "\\d+", message = "Only digits are allowed.")
	private String phoneNumber;
	@Column(name = "city", nullable = false)
	private String city;
	@Column(name = "country", nullable = false)
	private String country;
	@Column(name = "employment", nullable = false)
	private Employment employment;
	@Column(name = "infoAboutInstitution", nullable = false)
	private String infoAboutInstitution;
	@Column(name = "isActive", nullable = false)
	private boolean isActive;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "role_id"))
    private List<Role> roles;
	public User() {
	}

	
	public User(String name, String surname, String username, String password, String email, String phoneNumber,
			String city,String country, Employment employment, String infoAboutInstitution,boolean isActive) {
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
		this.isActive=isActive;
	}
	
	public User(User user) {
		this.name=user.name;
		this.surname=user.surname;
		this.email=user.email;
		this.password=user.password;
		this.phoneNumber=user.phoneNumber;
		this.loggedBefore=false;
		this.city=user.city;
		this.country=user.country;
		this.employment=user.employment;	
		this.infoAboutInstitution=user.infoAboutInstitution;
		this.isActive=user.isActive;
	}



	

	public Long getUser_id() {
		return user_id;
	}


	public void setUser_id(Long user_id) {
		this.user_id = user_id;
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


	public boolean isActive() {
		return isActive;
	}


	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}



	public List<Role> getRoles() {
		return roles;
	}


	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}



	@JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles;
    }



	@JsonIgnore
	@Override
	public boolean isAccountNonExpired() {
	    return true;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonLocked() {
	    return true;
	}

	@JsonIgnore
	@Override
	public boolean isCredentialsNonExpired() {
	    return true;
	}


	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return isActive;
	}

}
