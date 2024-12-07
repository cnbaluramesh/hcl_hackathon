package com.hcl.hackaton.services;

import com.hcl.hackaton.dto.MortgageDTO;
import org.springframework.stereotype.Service;

@Service
public interface MortgageService {

    public abstract long showAccountBalance();

    long repay(long amt);

    public String authenticate(MortgageDTO mortgageDTO);
}