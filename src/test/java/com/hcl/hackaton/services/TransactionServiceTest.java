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
import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TransactionServiceTest {

    @Mock
    private AccountRepository accountRepository;
    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private TransactionService transactionServiceUnderTest;

    public String transferFunds(TransferRequestDTO transferRequest) {
        Account fromAccount = accountRepository.findById(transferRequest.getFromAccountId())
                .orElseThrow(() -> new IllegalArgumentException("Source account not found."));
        Account toAccount = accountRepository.findById(transferRequest.getToAccountId())
                .orElseThrow(() -> new IllegalArgumentException("Destination account not found."));

        // Check for insufficient balance
        if (fromAccount.getBalance().compareTo(transferRequest.getAmount()) < 0) {
            throw new IllegalArgumentException("Insufficient balance in the source account.");
        }

        // Deduct from 'fromAccount' and add to 'toAccount'
        fromAccount.setBalance(fromAccount.getBalance().subtract(transferRequest.getAmount()));
        toAccount.setBalance(toAccount.getBalance().add(transferRequest.getAmount()));

        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);

        // Create transactions
        Transaction debitTransaction = new Transaction(fromAccount.getAccountId(), "DEBIT",
                transferRequest.getAmount(), LocalDateTime.now(), transferRequest.getRemarks());
        transactionRepository.save(debitTransaction);

        Transaction creditTransaction = new Transaction(toAccount.getAccountId(), "CREDIT",
                transferRequest.getAmount(), LocalDateTime.now(), transferRequest.getRemarks());
        transactionRepository.save(creditTransaction);

        return "Transfer completed successfully.";
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
        when(accountRepository.findById(1L)).thenReturn(Optional.empty());

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
        transferRequest.setAmount(BigDecimal.valueOf(1500.0)); // Transfer amount
        transferRequest.setRemarks("Test Transfer");

        // Configure AccountRepository.findById(...).
        final Account fromAccount = new Account();
        fromAccount.setAccountId(1L);
        fromAccount.setCustomerId(1L);
        fromAccount.setAccountNumber("FROM123");
        fromAccount.setAccountType(AccountType.SAVINGS);
        fromAccount.setBalance(BigDecimal.valueOf(1000.0)); // Insufficient balance (less than transfer amount)

        final Account toAccount = new Account();
        toAccount.setAccountId(2L);
        toAccount.setCustomerId(2L);
        toAccount.setAccountNumber("TO456");
        toAccount.setAccountType(AccountType.SAVINGS);
        toAccount.setBalance(BigDecimal.valueOf(1500.0));

        when(accountRepository.findById(1L)).thenReturn(Optional.of(fromAccount));
        when(accountRepository.findById(2L)).thenReturn(Optional.of(toAccount));

        // Run the test
        assertThatThrownBy(() -> transactionServiceUnderTest.transferFunds(transferRequest))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Insufficient balance in the source account.");
    }
}
