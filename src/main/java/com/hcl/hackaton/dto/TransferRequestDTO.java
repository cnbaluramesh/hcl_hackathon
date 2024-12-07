package com.hcl.hackaton.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransferRequestDTO {
    private Long fromAccountId; // Source account ID
    private Long toAccountId;   // Destination account ID
    private BigDecimal amount;      // Amount to transfer
    private String remarks;     // Optional transfer comments

}
