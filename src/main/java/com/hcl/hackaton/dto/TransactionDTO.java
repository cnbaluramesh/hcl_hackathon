package com.hcl.hackaton.dto;

import lombok.Data;

@Data
public class TransactionDTO {
    private Long transactionId;
    private Long accountId;
    private String transactionType; // "CREDIT" or "DEBIT"
    private Double amount;
    private String transactionDate;
    private String remarks;
}
