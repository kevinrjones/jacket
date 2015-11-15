package com.pluralsight.jacket.security.service;

public class EmailExistsException extends Exception {

	public EmailExistsException(String message) {
		super(message);
	}

}
