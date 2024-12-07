package com.hcl.hackaton.services;

import com.hcl.hackaton.entity.Mortgage;
import com.hcl.hackaton.repository.impl.MortgageRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;


@ExtendWith(MockitoExtension.class)
public class MortgageServiceImplTest {

    @Mock
    double testAccountBalance=100L;
    @Mock
    private MortgageRepositoryImpl mortgageRepositoryImpl;

    @Mock
    private Mortgage testMortgage;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testShowAccountBalance() {
        assertNotNull(testMortgage.getDepositAmount());
    }

    @Test
    void repay() {
        assertNotNull(testMortgage.getDepositAmount());
    }

    @Test
    void authenticate() {
        assertNotNull(testMortgage.getDepositAmount());
    }



}
