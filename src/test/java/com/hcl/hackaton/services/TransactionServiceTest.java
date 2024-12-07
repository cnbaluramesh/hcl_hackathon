package com.hcl.hackaton.services;

import com.hcl.hackaton.dto.TransferRequestDTO;
import com.hcl.hackaton.entity.Account;
import com.hcl.hackaton.entity.AccountType;
import com.hcl.hackaton.entity.Transaction;
import com.hcl.hackaton.repository.AccountRepository;
import com.hcl.hackaton.repository.TransactionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TransactionServiceTest {

    @Mock
    private AccountRepository mockAccountRepository;
    @Mock
    private TransactionRepository mockTransactionRepository;

    @InjectMocks
    private TransactionService transactionServiceUnderTest;

    @Test
    void testTransferFunds() {
        // Setup
        final TransferRequestDTO transferRequest = new TransferRequestDTO();
        transferRequest.setFromAccountId(1L);
        transferRequest.setToAccountId(2L);
        transferRequest.setAmount(BigDecimal.valueOf(500.0)); // Fixed
        transferRequest.setRemarks("Test Transfer");

        // Configure AccountRepository.findById(...).
        final Account fromAccount = new Account();
        fromAccount.setAccountId(1L);
        fromAccount.setCustomerId(1L);
        fromAccount.setAccountNumber("FROM123");
        fromAccount.setAccountType(AccountType.SAVINGS);
        fromAccount.setBalance(BigDecimal.valueOf(1000.0)); // Fixed

        final Account toAccount = new Account();
        toAccount.setAccountId(2L);
        toAccount.setCustomerId(2L);
        toAccount.setAccountNumber("TO456");
        toAccount.setAccountType(AccountType.SAVINGS);
        toAccount.setBalance(BigDecimal.valueOf(500.0)); // Fixed

        when(mockAccountRepository.findById(1L)).thenReturn(Optional.of(fromAccount));
        when(mockAccountRepository.findById(2L)).thenReturn(Optional.of(toAccount));

        // Run the test
        final String result = transactionServiceUnderTest.transferFunds(transferRequest);

        // Verify the results
        assertThat(result).isEqualTo("Transfer completed successfully.");

        // Verify AccountRepository.save() calls
        verify(mockAccountRepository, times(2)).save(any(Account.class));

        verify(mockAccountRepository).save(argThat(account ->
                account.getAccountId().equals(1L) &&
                        account.getBalance().compareTo(BigDecimal.valueOf(500.0)) == 0 // Fixed
        ));
        verify(mockAccountRepository).save(argThat(account ->
                account.getAccountId().equals(2L) &&
                        account.getBalance().compareTo(BigDecimal.valueOf(1000.0)) == 0 // Fixed
        ));

        // Verify TransactionRepository.save() calls
        verify(mockTransactionRepository, times(2)).save(any(Transaction.class));
        verify(mockTransactionRepository).save(argThat(transaction ->
                transaction.getAccountId().equals(1L) &&
                        transaction.getTransactionType().equals("DEBIT") &&
                        transaction.getAmount().compareTo(BigDecimal.valueOf(500.0)) == 0 // Fixed
        ));
        verify(mockTransactionRepository).save(argThat(transaction ->
                transaction.getAccountId().equals(2L) &&
                        transaction.getTransactionType().equals("CREDIT") &&
                        transaction.getAmount().compareTo(BigDecimal.valueOf(500.0)) == 0 // Fixed
        ));
    }

    @Test
    void testTransferFunds_AccountRepositoryFindByIdReturnsAbsent() {
        // Setup
        final TransferRequestDTO transferRequest = new TransferRequestDTO();
        transferRequest.setFromAccountId(1L);
        transferRequest.setToAccountId(2L);
        transferRequest.setAmount(BigDecimal.valueOf(500.0)); // Fixed
        transferRequest.setRemarks("Test Transfer");

        // Configure AccountRepository.findById(...) to return empty for "from account"
        when(mockAccountRepository.findById(1L)).thenReturn(Optional.empty());

        // Run the test
        assertThatThrownBy(() -> transactionServiceUnderTest.transferFunds(transferRequest))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Source account not found.");
    }

    @Test
    void testTransferFunds_InsufficientFunds() {
        // Setup
        final TransferRequestDTO transferRequest = new TransferRequestDTO();
        transferRequest.setFromAccountId(1L);
        transferRequest.setToAccountId(2L);
        transferRequest.setAmount(BigDecimal.valueOf(500.0)); // Fixed
        transferRequest.setRemarks("Test Transfer");

        // Configure AccountRepository.findById(...).
        final Account fromAccount = new Account();
        fromAccount.setAccountId(1L);
        fromAccount.setCustomerId(1L);
        fromAccount.setAccountNumber("FROM123");
        fromAccount.setAccountType(AccountType.SAVINGS);
        fromAccount.setBalance(BigDecimal.valueOf(1000.0)); // Fixed

        final Account toAccount = new Account();
        toAccount.setAccountId(2L);
        toAccount.setCustomerId(2L);
        toAccount.setAccountNumber("TO456");
        toAccount.setAccountType(AccountType.SAVINGS);
        toAccount.setBalance(BigDecimal.valueOf(500.0)); // Fixed

        when(mockAccountRepository.findById(1L)).thenReturn(Optional.of(fromAccount));
        when(mockAccountRepository.findById(2L)).thenReturn(Optional.of(toAccount));

        // Run the test
        assertThatThrownBy(() -> transactionServiceUnderTest.transferFunds(transferRequest))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Insufficient balance in the source account.");
    }
}
