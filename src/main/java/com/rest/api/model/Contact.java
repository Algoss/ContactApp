package com.rest.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="CONTACT")
public class Contact {
	
	@Id
	@Column(name="email")
	String email;
	
	@Column(name="name")
	String name;

	
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Contact(String email, String name) {
		super();
		this.email = email;
		this.name = name;
	}

	public Contact() {
		super();
		
	}	
	
	

}
