package com.hcl.hackaton.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Map<String, String>> handleResourceNotFoundException(ResourceNotFoundException ex) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("status", "error");
        errorResponse.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(InvalidInputException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Map<String, String>> handleInvalidInputException(InvalidInputException ex) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("status", "error");
        errorResponse.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Map<String, String>> handleGeneralException(Exception ex) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("status", "error");
        errorResponse.put("message", "An unexpected error occurred: " + ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }
}
/*
        MortgageApplication application = mortgageService.findById(id);
        if (application == null) {
            throw new ResourceNotFoundException("Mortgage application not found for id: " + id);
 */
/*
  if (request.getLoanAmount() <= 0) {
            throw new InvalidInputException("Loan amount must be greater than zero.");
        }
        // Process application...
        return ResponseEntity.status(HttpStatus.CREATED).body(newApplication);
 */