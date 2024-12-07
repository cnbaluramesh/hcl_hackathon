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

}
