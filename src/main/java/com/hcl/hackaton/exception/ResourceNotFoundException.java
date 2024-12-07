package com.hcl.hackaton.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}

 class InvalidInputException extends RuntimeException {
    public InvalidInputException(String message) {
        super(message);
    }
}