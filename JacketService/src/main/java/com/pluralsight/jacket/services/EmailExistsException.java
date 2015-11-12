package com.pluralsight.jacket.services;

public class EmailExistsException extends Exception {

	public EmailExistsException(String message) {
		super(message);
	}

}
