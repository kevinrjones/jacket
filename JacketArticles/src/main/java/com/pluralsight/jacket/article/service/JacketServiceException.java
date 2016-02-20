package com.pluralsight.jacket.article.service;

public class JacketServiceException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -45039187260255302L;

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
