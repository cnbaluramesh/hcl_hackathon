package com.hcl.hackaton.services;

<<<<<<< HEAD
import com.hcl.hackaton.dto.CustomerDTO;
import org.springframework.stereotype.Service;
=======
import com.hcl.hackaton.entity.Customer;
>>>>>>> e21f1c6b619111e9a3703de0c0b89fbf34cf840f

@Service
public interface CustomerService {

	CustomerDTO createCustomer(CustomerDTO product);

<<<<<<< HEAD
	CustomerDTO getCustomer(Integer customerId);
=======
	Customer getCustomer(long customerId);

	void deleteCustomer(long customerId);

>>>>>>> e21f1c6b619111e9a3703de0c0b89fbf34cf840f
}