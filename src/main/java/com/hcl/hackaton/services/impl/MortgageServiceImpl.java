package com.hcl.hackaton.services.impl;

import com.hcl.hackaton.dto.MortgageDTO;
import com.hcl.hackaton.services.MortgageService;
import org.springframework.beans.factory.annotation.Autowired;

public class MortgageServiceImpl implements MortgageService {

    @Autowired
    private MortgageDTO mortgageDTO;
    Long mortgageId;
    Long accountId;
    Long propertyCost;
    Long depositAmount;
    String mortgageType="";
    Long mortgageBalance;

    @Override
    public long showAccountBalance() {
        return mortgageDTO.getMortgageBalance();
    }

    @Override
    public long repay(long amt) {
        try {
            mortgageBalance = mortgageBalance + amt;
            System.out.println("Balance after Repaid: " + mortgageBalance);
        }catch(Exception e){
            e.getMessage();
        }
        return mortgageBalance;
    }

    public String authenticate(MortgageDTO loginDTO) {
        // Authentication logic (JWT, etc.)
        return "JWT_TOKEN";
    }
}
