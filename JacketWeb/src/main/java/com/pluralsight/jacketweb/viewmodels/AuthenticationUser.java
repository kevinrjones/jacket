package com.pluralsight.jacketweb.viewmodels;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class AuthenticationUser {
	
	@NotEmpty(message="Enter an email")
	@Email(message="Invalid email address")
	private String email;
	
	@NotEmpty(message="Enter a password")
	private String password;
	
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

}
