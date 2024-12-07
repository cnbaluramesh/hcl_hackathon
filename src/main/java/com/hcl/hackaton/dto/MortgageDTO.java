package com.hcl.hackaton.dto;

import lombok.Data;

@Data
public class MortgageDTO {
    private Long mortgageId;
    private Long accountId;
    private Long propertyCost;
    private Long depositAmount;
    private String mortgageType;
    private Long mortgageBalance;
}
