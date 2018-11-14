package com.plumdo.jdbc.common.exception;

public class ObjectNotFoundException extends BaseException{
	private static final long serialVersionUID = 1L;

	public ObjectNotFoundException(String message) {
		super(message);
	}
	
	public ObjectNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}
