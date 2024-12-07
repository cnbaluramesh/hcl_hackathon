package com.hcl.hackaton.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class MortgageDTO {
    private Long mortgageId;
    private Long accountId;
    private Long propertyCost;
    private BigDecimal depositAmount;
    private String mortgageType;
    private BigDecimal mortgageBalance;
}
