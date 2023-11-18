package com.example.medicalequipment.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
@Entity
public class ActivationToken {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long token_id;
	@Column(name = "token", nullable = false)
	private String token;
	@OneToOne(targetEntity=User.class,fetch=FetchType.EAGER)
	@JoinColumn(name="user_id",nullable=false,referencedColumnName = "user_id")
	private User user;
	public ActivationToken() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ActivationToken(User user) {
		super();
		this.user = user;
		token=UUID.randomUUID().toString();
	}
	public ActivationToken(Long token_id, String token, User user) {
		super();
		this.token_id = token_id;
		this.token = token;
		this.user = user;
	}
	public ActivationToken(String token, User user) {
		super();
		this.token = token;
		this.user = user;
	}
	public Long getToken_id() {
		return token_id;
	}
	public void setToken_id(Long token_id) {
		this.token_id = token_id;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
}
