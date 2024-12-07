package com.hcl.hackaton.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data // TODO:: as lombak is not working, adding manual getters and setters
@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;

    @Column(nullable = false)
    private Long customerId;

    @Column(nullable = false, unique = true)
    private String accountNumber;

    @Column(nullable = false)
    private String accountType; // "SAVINGS" or "MORTGAGE"

    @Column(nullable = false)
    private Double balance;

    @Column(nullable = false)
    private LocalDateTime lastTransactionDate;

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public LocalDateTime getLastTransactionDate() {
		return lastTransactionDate;
	}

	public void setLastTransactionDate(LocalDateTime lastTransactionDate) {
		this.lastTransactionDate = lastTransactionDate;
	}
    
    
}

