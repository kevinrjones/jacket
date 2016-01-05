package com.pluralsight.jacket.security.service;

public class EmailExistsException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4072047721454465349L;

	public EmailExistsException(String message) {
		super(message);
	}

}
