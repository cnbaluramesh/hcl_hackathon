package com.hcl.hackaton.controller;


import com.hcl.hackaton.entity.Account;
import com.hcl.hackaton.services.AccountService;
import com.hcl.hackaton.util.CommonValidationsUtil;
import com.hcl.hackaton.util.LoggerUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;
    
	@Autowired
	CommonValidationsUtil validationsUtil;
	
	@Autowired
	LoggerUtil loggerUtil;

	@GetMapping
	public List<Account> getAllAccounts() {
		return accountService.getAllAccounts();
	}

	@GetMapping("/{accountId}")
	public ResponseEntity<Account> getAccountById(@PathVariable Long accountId) {
		try {
			Account account = accountService.getAccountById(accountId);
			return ResponseEntity.ok(account);
		} catch (IllegalArgumentException e) {
			loggerUtil.logError(getClass(), null, e);
			return ResponseEntity.notFound().build();
		}}

	@PostMapping
	public Account createAccount(@RequestBody Account account) {
		validationsUtil.validateAccount(account);
		return accountService.createAccount(account);
	}
	
	@PutMapping("/{accountId}")
	public ResponseEntity<Account> updateAccount(@RequestBody Account account) {
		try {
			Account updatedAccount = accountService.updateAccount(account);
			return ResponseEntity.ok(updatedAccount);
		} catch (RuntimeException e) {
			loggerUtil.logError(getClass(), null, e);
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{accountId}")
	public ResponseEntity<Void> deleteAccount(@PathVariable Long accountId) {
		try {
			accountService.deleteAccount(accountId);
			return ResponseEntity.noContent().build();
		} catch (RuntimeException e) {
			loggerUtil.logError(getClass(), null, e);
			return ResponseEntity.notFound().build();
		}
	}
}