package com.hcl.hackaton.repository;


import org.springframework.data.repository.CrudRepository;

import com.hcl.hackaton.entity.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {


}
