package com.hcl.hackaton.services;

import com.hcl.hackaton.entity.Account;
import com.hcl.hackaton.entity.AccountType;
import com.hcl.hackaton.repository.AccountRepository;
import com.hcl.hackaton.services.impl.AccountServiceImpl;
import com.hcl.hackaton.util.ErrorMessageUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private ErrorMessageUtil errorMessageUtil;

    @InjectMocks
    private AccountServiceImpl accountService;

    private Account account;

    @BeforeEach
    public void setUp() {
        account = new Account();
        account.setAccountId(1L);
        account.setCustomerId(1L);
        account.setAccountNumber("1234567890");
        account.setAccountType(AccountType.SAVINGS);
        account.setBalance(BigDecimal.valueOf(1000.0));
        account.setLastTransactionDate(null); 
    }

    @Test
    public void testGetAllAccounts() {
        when(accountRepository.findAll()).thenReturn(Arrays.asList(account));

        List<Account> accounts = accountService.getAllAccounts();

        assertNotNull(accounts);
        assertEquals(1, accounts.size());
        assertEquals(account.getAccountId(), accounts.get(0).getAccountId());
        verify(accountRepository, times(1)).findAll();
    }

    @Test
    public void testGetAccountById() {
        when(accountRepository.findById(anyLong())).thenReturn(Optional.of(account));

        Account foundAccount = accountService.getAccountById(1L);

        assertNotNull(foundAccount);
        assertEquals(account.getAccountId(), foundAccount.getAccountId());
        verify(accountRepository, times(1)).findById(1L);
    }

    @Test
    public void testGetAccountById_NotFound() {
        when(accountRepository.findById(anyLong())).thenReturn(Optional.empty());
        when(errorMessageUtil.getErrorMessage("ERR001")).thenReturn("Account not found with id: ");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            accountService.getAccountById(99L);
        });

        assertEquals("Account not found with id: 99", exception.getMessage());
        verify(accountRepository, times(1)).findById(99L);
        verify(errorMessageUtil, times(1)).getErrorMessage("ERR001");
    }

    @Test
    public void testCreateAccount() {
        when(accountRepository.save(any(Account.class))).thenReturn(account);

        Account createdAccount = accountService.createAccount(account);

        assertNotNull(createdAccount);
        assertEquals(account.getAccountId(), createdAccount.getAccountId());
        verify(accountRepository, times(1)).save(account);
    }

    @Test
    public void testUpdateAccount() {
        when(accountRepository.existsById(anyLong())).thenReturn(true);
        when(accountRepository.save(any(Account.class))).thenReturn(account);

        Account updatedAccount = accountService.updateAccount(1L, account);

        assertNotNull(updatedAccount);
        assertEquals(updatedAccount.getAccountId());
        verify(accountRepository, times(1)).existsById(1L);
        verify(accountRepository, times(1)).save(account);
    }

    @Test
    public void testUpdateAccount_NotFound() {
        when(accountRepository.existsById(anyLong())).thenReturn(false);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            accountService.updateAccount(99L, account);
        });

        assertEquals("Account not found with id: 99", exception.getMessage());
        verify(accountRepository, times(1)).existsById(99L);
    }

    @Test
    public void testDeleteAccount() {
        when(accountRepository.existsById(anyLong())).thenReturn(true);
        doNothing().when(accountRepository).deleteById(anyLong());

        accountService.deleteAccount(1L);

        verify(accountRepository, times(1)).existsById(1L);
        verify(accountRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testDeleteAccount_NotFound() {
        when(accountRepository.existsById(anyLong())).thenReturn(false);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            accountService.deleteAccount(99L);
        });

        assertEquals("Account not found with id: 99", exception.getMessage());
        verify(accountRepository, times(1)).existsById(99L);
    }
}
