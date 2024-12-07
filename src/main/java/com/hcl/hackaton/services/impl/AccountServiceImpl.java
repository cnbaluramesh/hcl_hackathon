package com.hcl.hackaton.services.impl;


import com.hcl.hackaton.entity.Account;
import com.hcl.hackaton.repository.AccountRepository;
import com.hcl.hackaton.services.AccountService;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService{

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    private AccountRepository accountRepository;

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

	@Override
	public Account getAccountById(Long accountId) {
		return accountRepository.findById(accountId)
				.orElseThrow(() -> new IllegalArgumentException("Account not found with id: " + accountId));
	}

    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

	@Override
	public Account updateAccount(Long accountId, Account account) {
		if (accountRepository.existsById(accountId)) {
			account.setAccountId(accountId);
			return accountRepository.save(account);
		} else {
			throw new IllegalArgumentException("Account not found with id: " + accountId);
		}
	}

	@Override
	public void deleteAccount(Long accountId) {
		if (accountRepository.existsById(accountId)) {
			accountRepository.deleteById(accountId);
		} else {
			throw new IllegalArgumentException("Account not found with id: " + accountId);
		}
	}
}