package com.hcl.hackaton.entity;

import jakarta.persistence.*;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;


@Data
@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private long id;
    
    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private boolean email;
    
    @Column(name = "gender")
    private String gender;
    
    @Column(name = "phone")
    private String phone;
    
    @Column(name = "dob")
    private Date dob;
    

    public Customer() {

    }
}