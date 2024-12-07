package com.hcl.hackaton.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "mortgage")
public class Mortgage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mortgageId;

    @Column(nullable = false)
    private Long accountId;

    @Column(nullable = false)
    private Long propertyCost;

    @Column(nullable = false)
    private Long depositAmount;

    @Column(nullable = false)
    private String mortgageType;

    @Column(nullable = false)
    private Long mortgageBalance;


    public double getMortgageId() {
        return mortgageId;
    }

    public void setMortgageId(long mortgageId) {
        this.mortgageId = mortgageId;
    }

    public double getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public double getPropertyCost() {
        return propertyCost;
    }

    public void setPropertyCost(long propertyCost) {
        this.propertyCost = propertyCost;
    }

    public double getDepositAmount() {
        return depositAmount;
    }

    public void setDepositAmount(long depositAmount) {
        this.depositAmount = depositAmount;
    }

    public String getMortgageType() {
        return mortgageType;
    }

    public void setMortgageType(String mortgageType) {
        this.mortgageType = mortgageType;
    }

    public double getMortgageBalance() {
        return mortgageBalance;
    }

    public void setMortgageBalance(long mortgageBalance) {
        this.mortgageBalance = mortgageBalance;
    }
}
