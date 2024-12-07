package com.hcl.hackaton.services;

import com.hcl.hackaton.dto.TransferRequestDTO;
import com.hcl.hackaton.entity.Account;
import com.hcl.hackaton.entity.Transaction;
import com.hcl.hackaton.repository.AccountRepository;
import com.hcl.hackaton.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
@Service
public class TransactionService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    public String transferFunds(TransferRequestDTO transferRequest) {
        // Fetch source and destination accounts
        Account fromAccount = accountRepository.findById(transferRequest.getFromAccountId())
                .orElseThrow(() -> new IllegalArgumentException("Source account not found."));
        Account toAccount = accountRepository.findById(transferRequest.getToAccountId())
                .orElseThrow(() -> new IllegalArgumentException("Destination account not found."));

        // Validate sufficient balance
        if (fromAccount.getBalance().compareTo(transferRequest.getAmount()) < 0) {
            throw new IllegalArgumentException("Insufficient balance in the source account.");
        }

        // Perform transfer
        // Debit from source account
        fromAccount.setBalance(fromAccount.getBalance().subtract(transferRequest.getAmount()));
        accountRepository.save(fromAccount);

        Transaction debitTransaction = new Transaction();
        debitTransaction.setAccountId(fromAccount.getAccountId());
        debitTransaction.setTransactionType("DEBIT");
        debitTransaction.setAmount(transferRequest.getAmount());
        debitTransaction.setTransactionDate(LocalDateTime.now());
        debitTransaction.setRemarks("Transfer to account: " + toAccount.getAccountNumber());
        transactionRepository.save(debitTransaction);

        // Credit to destination account
        toAccount.setBalance(toAccount.getBalance().add(transferRequest.getAmount()));
        accountRepository.save(toAccount);

        Transaction creditTransaction = new Transaction();
        creditTransaction.setAccountId(toAccount.getAccountId());
        creditTransaction.setTransactionType("CREDIT");
        creditTransaction.setAmount(transferRequest.getAmount());
        creditTransaction.setTransactionDate(LocalDateTime.now());
        creditTransaction.setRemarks("Transfer from account: " + fromAccount.getAccountNumber());
        transactionRepository.save(creditTransaction);

        return "Transfer completed successfully.";
    }
}
