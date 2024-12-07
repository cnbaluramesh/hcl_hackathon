package com.hcl.hackaton.exception;

public class ResourceNotFoundException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException(String message) {
        super(message);
    }
}

 class InvalidInputException extends RuntimeException {
    public InvalidInputException(String message) {
        super(message);
    }
}