package com.hcl.hackaton.services;

import org.springframework.stereotype.Service;

import com.hcl.hackaton.entity.Customer;

public interface CustomerService {

	Customer createCustomer(Customer product);

	Customer getCustomer(long customerId);

	void deleteCustomer(long customerId);

}