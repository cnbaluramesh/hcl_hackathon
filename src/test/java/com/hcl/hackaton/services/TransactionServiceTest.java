package com.hcl.hackaton.services;

import com.hcl.hackaton.dto.TransferRequest;
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
        final TransferRequest transferRequest = new TransferRequest();
        transferRequest.setFromAccountId(1L);
        transferRequest.setToAccountId(2L);
        transferRequest.setAmount(500.0);
        transferRequest.setRemarks("Test Transfer");

        // Configure AccountRepository.findById(...).
        final Account fromAccount = new Account();
        fromAccount.setAccountId(1L);
        fromAccount.setCustomerId(1L);
        fromAccount.setAccountNumber("FROM123");
        fromAccount.setAccountType(String.valueOf(AccountType.valueOf("SAVINGS")));
        fromAccount.setBalance(1000.0);

        final Account toAccount = new Account();
        toAccount.setAccountId(2L);
        toAccount.setCustomerId(2L);
        toAccount.setAccountNumber("TO456");
        toAccount.setAccountType(String.valueOf(AccountType.valueOf("SAVINGS")));
        toAccount.setBalance(500.0);

        when(mockAccountRepository.findById(1L)).thenReturn(Optional.of(fromAccount));
        when(mockAccountRepository.findById(2L)).thenReturn(Optional.of(toAccount));

        // Run the test
        final String result = transactionServiceUnderTest.transferFunds(transferRequest);

        // Verify the results
        assertThat(result).isEqualTo("Transfer completed successfully.");

        // Verify AccountRepository.save() calls
        verify(mockAccountRepository, times(2)).save(any(Account.class));

        // Verify specific save calls for updated balances
        verify(mockAccountRepository).save(argThat(account ->
                account.getAccountId().equals(1L) && account.getBalance() == 500.0
        ));
        verify(mockAccountRepository).save(argThat(account ->
                account.getAccountId().equals(2L) && account.getBalance() == 1000.0
        ));

        // Verify TransactionRepository.save() calls
        verify(mockTransactionRepository, times(2)).save(any(Transaction.class));
        verify(mockTransactionRepository).save(argThat(transaction ->
                transaction.getAccountId().equals(1L) &&
                        transaction.getTransactionType().equals("DEBIT") &&
                        transaction.getAmount() == 500.0
        ));
        verify(mockTransactionRepository).save(argThat(transaction ->
                transaction.getAccountId().equals(2L) &&
                        transaction.getTransactionType().equals("CREDIT") &&
                        transaction.getAmount() == 500.0
        ));
    }

    @Test
    void testTransferFunds_AccountRepositoryFindByIdReturnsAbsent() {
        // Setup
        final TransferRequest transferRequest = new TransferRequest();
        transferRequest.setFromAccountId(1L);
        transferRequest.setToAccountId(2L);
        transferRequest.setAmount(500.0);
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
        final TransferRequest transferRequest = new TransferRequest();
        transferRequest.setFromAccountId(1L);
        transferRequest.setToAccountId(2L);
        transferRequest.setAmount(1500.0); // More than available balance
        transferRequest.setRemarks("Test Transfer");

        // Configure AccountRepository.findById(...).
        final Account fromAccount = new Account();
        fromAccount.setAccountId(1L);
        fromAccount.setCustomerId(1L);
        fromAccount.setAccountNumber("FROM123");
        fromAccount.setAccountType(String.valueOf(AccountType.valueOf("SAVINGS")));
        fromAccount.setBalance(1000.0);

        final Account toAccount = new Account();
        toAccount.setAccountId(2L);
        toAccount.setCustomerId(2L);
        toAccount.setAccountNumber("TO456");
        toAccount.setAccountType(String.valueOf(AccountType.valueOf("SAVINGS")));
        toAccount.setBalance(500.0);

        when(mockAccountRepository.findById(1L)).thenReturn(Optional.of(fromAccount));
        when(mockAccountRepository.findById(2L)).thenReturn(Optional.of(toAccount));

        // Run the test
        assertThatThrownBy(() -> transactionServiceUnderTest.transferFunds(transferRequest))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Insufficient balance in the source account.");
    }
}
