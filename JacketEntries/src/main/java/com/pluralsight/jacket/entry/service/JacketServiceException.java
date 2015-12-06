package com.pluralsight.jacket.entry.service;

public class JacketServiceException extends RuntimeException {

	public JacketServiceException() {
		super();
	}

	public JacketServiceException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public JacketServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public JacketServiceException(String message) {
		super(message);
	}

	public JacketServiceException(Throwable cause) {
		super(cause);
	}
}
