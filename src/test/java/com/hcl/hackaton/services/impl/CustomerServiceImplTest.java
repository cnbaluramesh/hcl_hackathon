package com.hcl.hackaton.services.impl;

import com.hcl.hackaton.entity.Customer;
import com.hcl.hackaton.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {

    @Mock
    private CustomerRepository mockCustomerRepo;

    private CustomerServiceImpl customerServiceImplUnderTest;

    @BeforeEach
    void setUp() {
        customerServiceImplUnderTest = new CustomerServiceImpl(mockCustomerRepo);
    }

    @Test
    void testCreateCustomer() {
        // Setup
        final Customer customer = new Customer();
        customer.setId(0L);
        customer.setFirstName("firstName");
        customer.setLastName("lastName");
        customer.setEmail("email");
        customer.setGender("gender");

        final Customer expectedResult = new Customer();
        expectedResult.setId(0L);
        expectedResult.setFirstName("firstName");
        expectedResult.setLastName("lastName");
        expectedResult.setEmail("email");
        expectedResult.setGender("gender");

        // Configure CustomerRepository.save(...).
        final Customer customer1 = new Customer();
        customer1.setId(0L);
        customer1.setFirstName("firstName");
        customer1.setLastName("lastName");
        customer1.setEmail("email");
        customer1.setGender("gender");
        final Customer entity = new Customer();
        entity.setId(0L);
        entity.setFirstName("firstName");
        entity.setLastName("lastName");
        entity.setEmail("email");
        entity.setGender("gender");
        when(mockCustomerRepo.save(entity)).thenReturn(customer1);

        // Run the test
        final Customer result = customerServiceImplUnderTest.createCustomer(customer);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetCustomer() {
        // Setup
        final Customer expectedResult = new Customer();
        expectedResult.setId(0L);
        expectedResult.setFirstName("firstName");
        expectedResult.setLastName("lastName");
        expectedResult.setEmail("email");
        expectedResult.setGender("gender");

        // Configure CustomerRepository.findById(...).
        final Customer customer1 = new Customer();
        customer1.setId(0L);
        customer1.setFirstName("firstName");
        customer1.setLastName("lastName");
        customer1.setEmail("email");
        customer1.setGender("gender");
        final Optional<Customer> customer = Optional.of(customer1);
        when(mockCustomerRepo.findById(0L)).thenReturn(customer);

        // Run the test
        final Customer result = customerServiceImplUnderTest.getCustomer(0L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetCustomer_CustomerRepositoryReturnsAbsent() {
        // Setup
        when(mockCustomerRepo.findById(0L)).thenReturn(Optional.empty());

        // Run the test
        final Customer result = customerServiceImplUnderTest.getCustomer(0L);

        // Verify the results
        assertThat(result).isNull();
    }

    @Test
    void testDeleteCustomer() {
        // Setup
        // Run the test
        customerServiceImplUnderTest.deleteCustomer(0L);

        // Verify the results
        verify(mockCustomerRepo).deleteById(0L);
    }
}
