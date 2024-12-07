package com.hcl.hackaton.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.hackaton.constants.Constants;
import com.hcl.hackaton.exception.ResourceNotFoundException;
import com.hcl.hackaton.entity.Customer;
import com.hcl.hackaton.services.CustomerService;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {

	private final CustomerService customerService;

    // Constructor Injection
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }
	
	@PostMapping("/createCustomer")
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
		try {
		return new ResponseEntity<Customer>(customerService.createCustomer(customer), HttpStatus.OK); 
		}
		catch (Exception e) {
			new Exception(Constants.CUSTOMER_CREATION_ERROR);
		}
		return null;
	}
   
	@GetMapping("/getCustomer/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable("customerId") long customerId) {
		try {
			
		return new ResponseEntity<Customer>( customerService.getCustomer(customerId), HttpStatus.OK);
		}
		catch (Exception e) {
			new ResourceNotFoundException(Constants.CUSTOMER_FETCH_ERROR);
		}
		return null;
	}
	
	@DeleteMapping("deleteCustomer/{customerId}")
	public ResponseEntity<String> deleteCustomer(@PathVariable("customerId") long customerId){
		
		try {
			customerService.deleteCustomer(customerId);		
			return new ResponseEntity<String>(Constants.CUSTOMER_DELETION_SUCCESS,HttpStatus.OK);		
			}
			catch (Exception e) {
				new ResourceNotFoundException(Constants.CUSTOMER_DELETION_ERROR);
			}
		return null;
	}
}