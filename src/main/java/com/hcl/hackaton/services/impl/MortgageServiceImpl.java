package com.hcl.hackaton.services.impl;

import com.hcl.hackaton.services.MortgageService;

public class MortgageServiceImpl implements MortgageService {

    Long mortgageId;
    Long accountId;
    Long propertyCost;
    Long depositAmount;
    String mortgageType="";
    Long mortgageBalance;

    @Override
    public long showAccountBalance() {
        return mortgageBalance;
    }

    @Override
    public long repay(long amt) {
        System.out.println("Balance Before Payment: " + mortgageBalance);
        amt=10;
        mortgageBalance = mortgageBalance + amt;
        System.out.println("Balance after Repaid: " + mortgageBalance);
        return mortgageBalance;
    }
}
