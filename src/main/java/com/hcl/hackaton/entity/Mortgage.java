package com.hcl.hackaton.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "mortgages")
public class Mortgage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mortgageId;

    @Column(nullable = false)
    private Long accountId;

    @Column(nullable = false)
    private BigDecimal propertyCost;

    @Column(nullable = false)
    private BigDecimal depositAmount;

    @Column(nullable = false)
    private String mortgageType;

    @Column(nullable = false)
    private BigDecimal mortgageBalance;


}
