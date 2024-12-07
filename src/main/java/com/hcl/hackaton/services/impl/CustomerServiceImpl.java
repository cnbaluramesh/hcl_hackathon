package com.hcl.hackaton.services.impl;

<<<<<<< HEAD
import com.hcl.hackaton.dto.CustomerDTO;
=======
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.hcl.hackaton.entity.Customer;
import com.hcl.hackaton.repository.CustomerRepository;
>>>>>>> e21f1c6b619111e9a3703de0c0b89fbf34cf840f
import com.hcl.hackaton.services.CustomerService;

public class CustomerServiceImpl implements CustomerService  {

	
	private final CustomerRepository customerRepo;

    // Constructor Injection
    public CustomerServiceImpl(CustomerRepository customerRepo) {
        this.customerRepo = customerRepo;
    }
    
	@Override
<<<<<<< HEAD
	public CustomerDTO createCustomer(CustomerDTO product) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CustomerDTO getCustomer(Integer customerId) {
		// TODO Auto-generated method stub
=======
	public com.hcl.hackaton.entity.Customer createCustomer(Customer customer) {
		Customer cust = customerRepo.save(customer);
		return cust;
	}

	@Override
	public Customer getCustomer(long customerId) {
		Optional<Customer> cust = customerRepo.findById(customerId);
		if(cust.isPresent()) {
			return cust.get();
		}
>>>>>>> e21f1c6b619111e9a3703de0c0b89fbf34cf840f
		return null;
		}

	@Override
	public void deleteCustomer(long customerId) {
		customerRepo.deleteById(customerId);
	}

}
