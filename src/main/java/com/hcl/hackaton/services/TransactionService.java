package com.hcl.hackaton.services;

import com.hcl.hackaton.dto.TransferRequestDTO;
import com.hcl.hackaton.entity.Account;
import com.hcl.hackaton.entity.Transaction;
import com.hcl.hackaton.repository.AccountRepository;
import com.hcl.hackaton.repository.TransactionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Service
public class TransactionService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Transactional
    public String transferFunds(TransferRequestDTO transferRequest) {
        // Validate transfer amount
        if (transferRequest.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Transfer amount must be greater than zero.");
        }

        // Fetch source and destination accounts
        Account fromAccount = accountRepository.findById(transferRequest.getFromAccountId())
                .orElseThrow(() -> new IllegalArgumentException("Source account not found."));
        Account toAccount = accountRepository.findById(transferRequest.getToAccountId())
                .orElseThrow(() -> new IllegalArgumentException("Destination account not found."));

        // Validate sufficient balance in source account
        if (fromAccount.getBalance().compareTo(transferRequest.getAmount()) < 0) {
            throw new IllegalArgumentException("Insufficient balance in the source account.");
        }

        // Timestamp for consistency across all operations
        LocalDateTime now = LocalDateTime.now();

        // Perform the transfer
        // 1. Debit from source account
        fromAccount.setBalance(fromAccount.getBalance().subtract(transferRequest.getAmount()));
        accountRepository.save(fromAccount);

        Transaction debitTransaction = new Transaction();
        debitTransaction.setAccountId(fromAccount.getAccountId());
        debitTransaction.setTransactionType("DEBIT");
        debitTransaction.setAmount(transferRequest.getAmount());
        debitTransaction.setTransactionDate(now);
        debitTransaction.setRemarks("Transfer to account: " + toAccount.getAccountNumber());
        transactionRepository.save(debitTransaction);

        // 2. Credit to destination account
        toAccount.setBalance(toAccount.getBalance().add(transferRequest.getAmount()));
        accountRepository.save(toAccount);

        Transaction creditTransaction = new Transaction();
        creditTransaction.setAccountId(toAccount.getAccountId());
        creditTransaction.setTransactionType("CREDIT");
        creditTransaction.setAmount(transferRequest.getAmount());
        creditTransaction.setTransactionDate(now);
        creditTransaction.setRemarks("Transfer from account: " + fromAccount.getAccountNumber());
        transactionRepository.save(creditTransaction);

        // Return success message
        return "Transfer completed successfully.";
    }
}
