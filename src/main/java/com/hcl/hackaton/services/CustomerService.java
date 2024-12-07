package com.hcl.hackaton.services;

import org.springframework.stereotype.Service;

import com.hcl.hackaton.dto.CustomerDTO;
import org.springframework.stereotype.Service;
import com.hcl.hackaton.entity.Customer;

@Service
public interface CustomerService {

	CustomerDTO createCustomer(Customer product);

	CustomerDTO getCustomer(Integer customerId);
	Customer getCustomer(long customerId);

	void deleteCustomer(long customerId);

}