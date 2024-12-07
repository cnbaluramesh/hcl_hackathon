package com.hcl.hackaton.services;

import org.springframework.stereotype.Service;

@Service
public interface MortgageService {

    public abstract long showAccountBalance();

    long repay(long amt);
}