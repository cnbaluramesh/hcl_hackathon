package com.hcl.hackaton.controller;


import com.hcl.hackaton.entity.Account;
import com.hcl.hackaton.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

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
			return ResponseEntity.notFound().build();
		}}

	@PostMapping
	public Account createAccount(@RequestBody Account account) {
		return accountService.createAccount(account);
	}
	
	@PutMapping("/{accountId}")
	public ResponseEntity<Account> updateAccount(@PathVariable Long accountId, @RequestBody Account account) {
		try {
			Account updatedAccount = accountService.updateAccount(accountId, account);
			return ResponseEntity.ok(updatedAccount);
		} catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{accountId}")
	public ResponseEntity<Void> deleteAccount(@PathVariable Long accountId) {
		try {
			accountService.deleteAccount(accountId);
			return ResponseEntity.noContent().build();
		} catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}
}