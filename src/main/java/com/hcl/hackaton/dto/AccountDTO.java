package com.hcl.hackaton.dto;

import lombok.Data;

@Data
public class AccountDTO {
    private Long accountId;
    private Long customerId;
    private String accountNumber;
    private String accountType;
    private Double balance;
    private String lastTransactionDate;
}
