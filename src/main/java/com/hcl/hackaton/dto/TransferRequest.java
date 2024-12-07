package com.hcl.hackaton.dto;

import lombok.Data;

@Data
public class TransferRequest {
    private Long fromAccountId; // Source account ID
    private Long toAccountId;   // Destination account ID
    private Double amount;      // Amount to transfer
    private String remarks;     // Optional transfer comments

}
