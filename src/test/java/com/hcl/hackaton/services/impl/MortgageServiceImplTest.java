package com.hcl.hackaton.services.impl;

import com.hcl.hackaton.dto.MortgageDTO;
import com.hcl.hackaton.entity.Mortgage;
import com.hcl.hackaton.repository.MortgageRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MortgageServiceImplTest {

    @Mock
    private MortgageDTO mockMortgageDTO;
    @Mock
    private Mortgage mockMortgage;
    @Mock
    private MortgageRepository mockMortgageRepository;

    @InjectMocks
    private MortgageServiceImpl mortgageServiceImplUnderTest;

    @Test
    void testShowAccountBalance() {
        // Setup
        when(mockMortgageDTO.getMortgageBalance()).thenReturn(new BigDecimal("0.00"));

        // Run the test
        final BigDecimal result = mortgageServiceImplUnderTest.showAccountBalance();

        // Verify the results
        assertThat(result).isEqualTo(new BigDecimal("0.00"));
    }

    @Test
    void testRepay() {
        // Setup
        when(mockMortgageDTO.getMortgageBalance()).thenReturn(new BigDecimal("0.00"));
        when(mockMortgageDTO.getMortgageId()).thenReturn(0L);
        when(mockMortgageRepository.existsById(0L)).thenReturn(false);

        // Run the test
        final BigDecimal result = mortgageServiceImplUnderTest.repay(new BigDecimal("0.00"));

        // Verify the results
        assertThat(result).isEqualTo(new BigDecimal("0.00"));
    }

    @Test
    void testRepay_MortgageRepositoryExistsByIdReturnsTrue() {
        // Setup
        when(mockMortgageDTO.getMortgageBalance()).thenReturn(new BigDecimal("0.00"));
        when(mockMortgageDTO.getMortgageId()).thenReturn(0L);
        when(mockMortgageRepository.existsById(0L)).thenReturn(true);

        // Configure MortgageRepository.save(...).
        final Mortgage mortgage = new Mortgage();
        mortgage.setMortgageId(0L);
        mortgage.setAccountId(0L);
        mortgage.setPropertyCost(new BigDecimal("0.00"));
        mortgage.setDepositAmount(new BigDecimal("0.00"));
        mortgage.setMortgageBalance(new BigDecimal("0.00"));
        final Mortgage entity = new Mortgage();
        entity.setMortgageId(0L);
        entity.setAccountId(0L);
        entity.setPropertyCost(new BigDecimal("0.00"));
        entity.setDepositAmount(new BigDecimal("0.00"));
        entity.setMortgageBalance(new BigDecimal("0.00"));
        when(mockMortgageRepository.save(entity)).thenReturn(mortgage);

        // Run the test
        final BigDecimal result = mortgageServiceImplUnderTest.repay(new BigDecimal("0.00"));

        // Verify the results
        assertThat(result).isEqualTo(new BigDecimal("0.00"));
        verify(mockMortgage).setAccountId(0L);
        verify(mockMortgage).setMortgageBalance(new BigDecimal("0.00"));
    }

    @Test
    void testUpdateMortgageBalance() {
        // Setup
        final Mortgage expectedResult = new Mortgage();
        expectedResult.setMortgageId(0L);
        expectedResult.setAccountId(0L);
        expectedResult.setPropertyCost(new BigDecimal("0.00"));
        expectedResult.setDepositAmount(new BigDecimal("0.00"));
        expectedResult.setMortgageBalance(new BigDecimal("0.00"));

        when(mockMortgageRepository.existsById(0L)).thenReturn(true);

        // Configure MortgageRepository.save(...).
        final Mortgage mortgage = new Mortgage();
        mortgage.setMortgageId(0L);
        mortgage.setAccountId(0L);
        mortgage.setPropertyCost(new BigDecimal("0.00"));
        mortgage.setDepositAmount(new BigDecimal("0.00"));
        mortgage.setMortgageBalance(new BigDecimal("0.00"));
        final Mortgage entity = new Mortgage();
        entity.setMortgageId(0L);
        entity.setAccountId(0L);
        entity.setPropertyCost(new BigDecimal("0.00"));
        entity.setDepositAmount(new BigDecimal("0.00"));
        entity.setMortgageBalance(new BigDecimal("0.00"));
        when(mockMortgageRepository.save(entity)).thenReturn(mortgage);

        // Run the test
        final Mortgage result = mortgageServiceImplUnderTest.updateMortgageBalance(0L, new BigDecimal("0.00"));

        // Verify the results
       // assertThat(result).isEqualTo(expectedResult);
        verify(mockMortgage).setAccountId(0L);
        verify(mockMortgage).setMortgageBalance(new BigDecimal("0.00"));
    }

    @Test
    void testUpdateMortgageBalance_MortgageRepositoryExistsByIdReturnsFalse() {
        // Setup
        when(mockMortgageRepository.existsById(0L)).thenReturn(false);

        // Run the test
        assertThatThrownBy(
                () -> mortgageServiceImplUnderTest.updateMortgageBalance(0L, new BigDecimal("0.00")))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void testAuthenticate() {
        assertThat(mortgageServiceImplUnderTest.authenticate(new MortgageDTO())).isEqualTo("JWT_TOKEN");
    }

    @Test
    void testGetAllMortgageAccounts() {
        // Setup
        final Mortgage mortgage = new Mortgage();
        mortgage.setMortgageId(0L);
        mortgage.setAccountId(0L);
        mortgage.setPropertyCost(new BigDecimal("0.00"));
        mortgage.setDepositAmount(new BigDecimal("0.00"));
        mortgage.setMortgageBalance(new BigDecimal("0.00"));
        final List<Mortgage> expectedResult = List.of(mortgage);

        // Configure MortgageRepository.findAll(...).
        final Mortgage mortgage1 = new Mortgage();
        mortgage1.setMortgageId(0L);
        mortgage1.setAccountId(0L);
        mortgage1.setPropertyCost(new BigDecimal("0.00"));
        mortgage1.setDepositAmount(new BigDecimal("0.00"));
        mortgage1.setMortgageBalance(new BigDecimal("0.00"));
        final List<Mortgage> mortgages = List.of(mortgage1);
        when(mockMortgageRepository.findAll()).thenReturn(mortgages);

        // Run the test
        final List<Mortgage> result = mortgageServiceImplUnderTest.getAllMortgageAccounts();

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetAllMortgageAccounts_MortgageRepositoryReturnsNoItems() {
        // Setup
        when(mockMortgageRepository.findAll()).thenReturn(Collections.emptyList());

        // Run the test
        final List<Mortgage> result = mortgageServiceImplUnderTest.getAllMortgageAccounts();

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void testDeleteMortgageAccount() {
        // Setup
        when(mockMortgageRepository.existsById(0L)).thenReturn(false);

        // Run the test
        mortgageServiceImplUnderTest.deleteMortgageAccount(0L);

        // Verify the results
    }

    @Test
    void testDeleteMortgageAccount_MortgageRepositoryExistsByIdReturnsTrue() {
        // Setup
        when(mockMortgageRepository.existsById(0L)).thenReturn(true);

        // Run the test
        mortgageServiceImplUnderTest.deleteMortgageAccount(0L);

        // Verify the results
        verify(mockMortgageRepository).deleteById(0L);
    }
}
