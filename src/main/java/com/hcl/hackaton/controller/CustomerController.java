package com.hcl.hackaton.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.hackaton.constants.Constants;
import com.hcl.hackaton.exception.ResourceNotFoundException;
import com.hcl.hackaton.dto.CustomerDTO;
import com.hcl.hackaton.services.CustomerService;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {

	private final CustomerService customerService;

    // Constructor Injection
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }
	
	@PostMapping("/create-customer")
    public ResponseEntity<CustomerDTO> createCustomer(@RequestBody CustomerDTO customer) {
		try {
		return new ResponseEntity<CustomerDTO>(customerService.createCustomer(customer), HttpStatus.OK);
		}
		catch (Exception e) {
			new Exception(Constants.CUSTOMER_CREATION_ERROR);
		}
		return null;
	}
   
	@GetMapping("/get-customer/{id}")
    public ResponseEntity<CustomerDTO> getCustomer(@PathVariable("customerId") Integer customerId) {
		try {
			
		return new ResponseEntity<CustomerDTO>( customerService.getCustomer(customerId), HttpStatus.OK);
		}
		catch (Exception e) {
			new ResourceNotFoundException(Constants.CUSTOMER_FETCH_ERROR);
		}
		return null;
	}
}