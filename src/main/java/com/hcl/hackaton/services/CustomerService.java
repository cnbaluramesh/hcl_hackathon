package com.hcl.hackaton.services;

import com.hcl.hackaton.dto.CustomerDTO;
import com.hcl.hackaton.entity.Customer;


public interface CustomerService {

	Customer createCustomer(Customer product);

	Customer getCustomer(long customerId);

	void deleteCustomer(long customerId);

}