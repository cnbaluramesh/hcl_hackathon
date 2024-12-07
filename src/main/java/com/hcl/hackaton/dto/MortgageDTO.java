package com.hcl.hackaton.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class MortgageDTO {
    private Long mortgageId;
    private Long accountId;
    private BigDecimal propertyCost;
    private BigDecimal depositAmount;
    private String mortgageType;
    private BigDecimal mortgageBalance;
}
