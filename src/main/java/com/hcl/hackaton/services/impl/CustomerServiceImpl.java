package com.hcl.hackaton.services.impl;

import com.hcl.hackaton.dto.CustomerDTO;
import com.hcl.hackaton.entity.Customer;
import com.hcl.hackaton.repository.CustomerRepository;
import com.hcl.hackaton.services.CustomerService;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService{


    private final CustomerRepository customerRepo;

    // Constructor Injection
    public CustomerServiceImpl(CustomerRepository customerRepo) {
        this.customerRepo = customerRepo;
    }

    @Override
    public Customer createCustomer(Customer customer) {
        Customer cust = customerRepo.save(customer);
        return cust;
    }

    @Override
    public Customer getCustomer(long customerId) {
        Optional<Customer> cust = customerRepo.findById(customerId);
        if (cust.isPresent()) {
            return cust.get();
        }
        return null;
    }

    @Override
    public void deleteCustomer(long customerId) {
        customerRepo.deleteById(customerId);
    }

}
