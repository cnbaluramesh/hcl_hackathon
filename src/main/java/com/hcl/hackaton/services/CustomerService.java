package com.hcl.hackaton.services;

import com.hcl.hackaton.model.Customer;

public interface CustomerService {

	Customer createCustomer(Customer product);

	Customer getCustomer(Integer customerId);
}