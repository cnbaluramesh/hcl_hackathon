package com.hcl.hackaton.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.hackaton.constants.Constants;
import com.hcl.hackaton.dto.CustomerDTO;
import com.hcl.hackaton.exception.ResourceNotFoundException;
import com.hcl.hackaton.entity.Customer;
import com.hcl.hackaton.services.CustomerService;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {

	/*
	 * private final CustomerServiceImpl customerService;
	 * 
	 * // Constructor Injection public CustomerController(CustomerServiceImpl
	 * customerService) { this.customerService = customerService; }
	 */
	
    @Autowired
    private CustomerService customerService;
    
	@PostMapping("/createCustomer")
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) throws Exception {
		try {
		return ResponseEntity.ok(customerService.createCustomer(customer)); 
		}
		catch (Exception e) {
			 throw new Exception(Constants.CUSTOMER_CREATION_ERROR);
		}
	}
   
	@GetMapping("/getCustomer/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable("customerId") Integer customerId) {
		try {
			
		return ResponseEntity.ok( customerService.getCustomer(customerId));
		}
		catch (Exception e) {
			throw new ResourceNotFoundException(Constants.CUSTOMER_FETCH_ERROR);
		}
		//return null;
	}
	
	@GetMapping("/deleteCustomer/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable("customerId") Integer customerId) {
		try {
			 customerService.deleteCustomer(customerId);
		return new ResponseEntity<String>( "Customer Deleted Succcessfully", HttpStatus.OK);
		}
		catch (Exception e) {
			throw new ResourceNotFoundException(Constants.CUSTOMER_FETCH_ERROR);
		}
		//return null;
	}
}