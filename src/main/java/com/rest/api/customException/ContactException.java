package com.rest.api.customException;

public class ContactException extends Exception{

	private static final long serialVersionUID = 1L;
	private String errorMessage;
	
	public String getErrorMessage() {
		return errorMessage;
	}
	
	public ContactException() {
		super();
	}
	
	public ContactException(String message) {
		super(message);
		this.errorMessage = message;
	}
}
