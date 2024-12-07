package com.hcl.hackaton.services.impl;


import com.hcl.hackaton.entity.Account;
import com.hcl.hackaton.repository.AccountRepository;
import com.hcl.hackaton.services.AccountService;
import com.hcl.hackaton.util.ErrorMessageUtil;
import com.hcl.hackaton.util.LoggerUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService{

	@Autowired
	ErrorMessageUtil errorMessageUtil;
	
	@Autowired
	LoggerUtil loggerUtil;
	
    private AccountRepository accountRepository;
	
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    @Override
	public Account getAccountById(Long accountId) {
		return accountRepository.findById(accountId)
				.orElseThrow(() -> new IllegalArgumentException(ErrorMessageUtil.getErrorMessage("ERR001") + accountId));
	}

    @Override
    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    @Override
	public Account updateAccount(Account account) {
		if (accountRepository.existsById(account.getAccountId())) {
			account.setAccountId(account.getAccountId());
			return accountRepository.save(account);
		} else {
			throw new IllegalArgumentException(ErrorMessageUtil.getErrorMessage("ERR001") + account.getAccountId());
		}
	}

    @Override
	public void deleteAccount(Long accountId) {
		if (accountRepository.existsById(accountId)) {
			accountRepository.deleteById(accountId);
		} else {
			throw new IllegalArgumentException(ErrorMessageUtil.getErrorMessage("ERR001") + accountId);
		}
	}
}