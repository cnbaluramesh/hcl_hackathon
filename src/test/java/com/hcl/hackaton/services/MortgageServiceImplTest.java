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





@ExtendWith(MockitoExtension.class)
public class MortgageServiceImplTest {

    @Mock
    private MortgageRepositoryImpl mortgageRepositoryImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetUserById_UserExists() {

    }
}
