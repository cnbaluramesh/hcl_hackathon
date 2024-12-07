package com.hcl.hackaton.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;

    @Enumerated(EnumType.STRING) // Or EnumType.ORDINAL if appropriate
    @Column(name = "account_type")
    private AccountType accountType;

    @Column(nullable = false)
    private Long customerId;

    @Column(nullable = false, unique = true)
    private String accountNumber;

    /*@Column(nullable = false)
    private String accountType; // "SAVINGS" or "MORTGAGE"*/

    @Column(nullable = false)
    private Double balance;

    @Column(nullable = false)
    private LocalDateTime lastTransactionDate;

}
