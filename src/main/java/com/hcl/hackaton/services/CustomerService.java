package com.hcl.hackaton.services;

import com.hcl.hackaton.dto.CustomerDTO;
import com.hcl.hackaton.entity.Customer;


public interface CustomerService {

	CustomerDTO createCustomer(Customer product);

	CustomerDTO getCustomer(Integer customerId);
	Customer getCustomer(long customerId);

	void deleteCustomer(long customerId);

}