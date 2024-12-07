package com.hcl.hackaton.services.impl;

import com.hcl.hackaton.entity.Account;
import com.hcl.hackaton.entity.AccountType;
import com.hcl.hackaton.repository.AccountRepository;
import com.hcl.hackaton.util.ErrorMessageUtil;
import com.hcl.hackaton.util.LoggerUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccountServiceImplTest {

    @Mock
    private AccountRepository mockAccountRepository;
    @Mock
    private ErrorMessageUtil mockErrorMessageUtil;
    @Mock
    private LoggerUtil mockLoggerUtil;

    private AccountServiceImpl accountServiceImplUnderTest;

    @BeforeEach
    void setUp() {
        accountServiceImplUnderTest = new AccountServiceImpl(mockAccountRepository);
        accountServiceImplUnderTest.errorMessageUtil = mockErrorMessageUtil;
        accountServiceImplUnderTest.loggerUtil = mockLoggerUtil;
    }

    @Test
    void testGetAllAccounts() {
        // Setup
        final Account account = new Account();
        account.setAccountId(0L);
        account.setAccountType(AccountType.SAVINGS);
        account.setCustomerId(0L);
        account.setAccountNumber("accountNumber");
        account.setBalance(new BigDecimal("0.00"));
        final List<Account> expectedResult = List.of(account);

        // Configure AccountRepository.findAll(...).
        final Account account1 = new Account();
        account1.setAccountId(0L);
        account1.setAccountType(AccountType.SAVINGS);
        account1.setCustomerId(0L);
        account1.setAccountNumber("accountNumber");
        account1.setBalance(new BigDecimal("0.00"));
        final List<Account> accounts = List.of(account1);
        when(mockAccountRepository.findAll()).thenReturn(accounts);

        // Run the test
        final List<Account> result = accountServiceImplUnderTest.getAllAccounts();

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetAllAccounts_AccountRepositoryReturnsNoItems() {
        // Setup
        when(mockAccountRepository.findAll()).thenReturn(Collections.emptyList());

        // Run the test
        final List<Account> result = accountServiceImplUnderTest.getAllAccounts();

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void testGetAccountById() {
        // Setup
        final Account expectedResult = new Account();
        expectedResult.setAccountId(0L);
        expectedResult.setAccountType(AccountType.SAVINGS);
        expectedResult.setCustomerId(0L);
        expectedResult.setAccountNumber("accountNumber");
        expectedResult.setBalance(new BigDecimal("0.00"));

        // Configure AccountRepository.findById(...).
        final Account account1 = new Account();
        account1.setAccountId(0L);
        account1.setAccountType(AccountType.SAVINGS);
        account1.setCustomerId(0L);
        account1.setAccountNumber("accountNumber");
        account1.setBalance(new BigDecimal("0.00"));
        final Optional<Account> account = Optional.of(account1);
        when(mockAccountRepository.findById(0L)).thenReturn(account);

        // Run the test
        final Account result = accountServiceImplUnderTest.getAccountById(0L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetAccountById_AccountRepositoryReturnsAbsent() {
        // Setup
        when(mockAccountRepository.findById(0L)).thenReturn(Optional.empty());

        // Run the test
        assertThatThrownBy(() -> accountServiceImplUnderTest.getAccountById(0L))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void testCreateAccount() {
        // Setup
        final Account account = new Account();
        account.setAccountId(0L);
        account.setAccountType(AccountType.SAVINGS);
        account.setCustomerId(0L);
        account.setAccountNumber("accountNumber");
        account.setBalance(new BigDecimal("0.00"));

        final Account expectedResult = new Account();
        expectedResult.setAccountId(0L);
        expectedResult.setAccountType(AccountType.SAVINGS);
        expectedResult.setCustomerId(0L);
        expectedResult.setAccountNumber("accountNumber");
        expectedResult.setBalance(new BigDecimal("0.00"));

        // Configure AccountRepository.save(...).
        final Account account1 = new Account();
        account1.setAccountId(0L);
        account1.setAccountType(AccountType.SAVINGS);
        account1.setCustomerId(0L);
        account1.setAccountNumber("accountNumber");
        account1.setBalance(new BigDecimal("0.00"));
        final Account entity = new Account();
        entity.setAccountId(0L);
        entity.setAccountType(AccountType.SAVINGS);
        entity.setCustomerId(0L);
        entity.setAccountNumber("accountNumber");
        entity.setBalance(new BigDecimal("0.00"));
        when(mockAccountRepository.save(entity)).thenReturn(account1);

        // Run the test
        final Account result = accountServiceImplUnderTest.createAccount(account);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testUpdateAccount() {
        // Setup
        final Account account = new Account();
        account.setAccountId(0L);
        account.setAccountType(AccountType.SAVINGS);
        account.setCustomerId(0L);
        account.setAccountNumber("accountNumber");
        account.setBalance(new BigDecimal("0.00"));

        final Account expectedResult = new Account();
        expectedResult.setAccountId(0L);
        expectedResult.setAccountType(AccountType.SAVINGS);
        expectedResult.setCustomerId(0L);
        expectedResult.setAccountNumber("accountNumber");
        expectedResult.setBalance(new BigDecimal("0.00"));

        when(mockAccountRepository.existsById(0L)).thenReturn(true);

        // Configure AccountRepository.save(...).
        final Account account1 = new Account();
        account1.setAccountId(0L);
        account1.setAccountType(AccountType.SAVINGS);
        account1.setCustomerId(0L);
        account1.setAccountNumber("accountNumber");
        account1.setBalance(new BigDecimal("0.00"));
        final Account entity = new Account();
        entity.setAccountId(0L);
        entity.setAccountType(AccountType.SAVINGS);
        entity.setCustomerId(0L);
        entity.setAccountNumber("accountNumber");
        entity.setBalance(new BigDecimal("0.00"));
        when(mockAccountRepository.save(entity)).thenReturn(account1);

        // Run the test
        final Account result = accountServiceImplUnderTest.updateAccount(account);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testUpdateAccount_AccountRepositoryExistsByIdReturnsFalse() {
        // Setup
        final Account account = new Account();
        account.setAccountId(0L);
        account.setAccountType(AccountType.SAVINGS);
        account.setCustomerId(0L);
        account.setAccountNumber("accountNumber");
        account.setBalance(new BigDecimal("0.00"));

        when(mockAccountRepository.existsById(0L)).thenReturn(false);

        // Run the test
        assertThatThrownBy(() -> accountServiceImplUnderTest.updateAccount(account))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void testDeleteAccount() {
        // Setup
        when(mockAccountRepository.existsById(0L)).thenReturn(true);

        // Run the test
        accountServiceImplUnderTest.deleteAccount(0L);

        // Verify the results
        verify(mockAccountRepository).deleteById(0L);
    }

    @Test
    void testDeleteAccount_AccountRepositoryExistsByIdReturnsFalse() {
        // Setup
        when(mockAccountRepository.existsById(0L)).thenReturn(false);

        // Run the test
        assertThatThrownBy(() -> accountServiceImplUnderTest.deleteAccount(0L))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
