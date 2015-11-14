package com.pluralsight.jacketweb.viewmodels;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class RegistrationUser {
	
	@NotEmpty(message="Enter an email")
	@Email(message="Invalid email address")
	private String email;
	
	@NotEmpty(message="Enter a first name")
	private String firstName;
	
	@NotEmpty(message="Enter a last name")
	private String lastName;
	
	@NotEmpty(message="Enter a password")
	private String password;
	
	@NotEmpty(message="Enter a repeat password")
	private String repeatPassword;

	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
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
	public String getRepeatPassword() {
		return repeatPassword;
	}
	public void setRepeatPassword(String repeatPassword) {
		this.repeatPassword = repeatPassword;
	}

}
