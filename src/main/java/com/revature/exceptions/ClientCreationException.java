package com.revature.exceptions;

public class ClientCreationException extends Exception {

	public ClientCreationException() {

	}

	public ClientCreationException(String message) {
		super(message);
		
	}

	public ClientCreationException(Throwable cause) {
		super(cause);
		
	}

	public ClientCreationException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public ClientCreationException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		
	}

}
