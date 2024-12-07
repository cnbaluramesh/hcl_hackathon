package com.hcl.hackaton.services;

import com.hcl.hackaton.dto.CustomerDTO;
import org.springframework.stereotype.Service;

@Service
public interface CustomerService {

	CustomerDTO createCustomer(CustomerDTO product);

	CustomerDTO getCustomer(Integer customerId);
}