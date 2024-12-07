package com.hcl.hackaton.services.impl;

import com.hcl.hackaton.dto.MortgageDTO;
import com.hcl.hackaton.entity.Mortgage;
import com.hcl.hackaton.repository.MortgageRepository;
import com.hcl.hackaton.services.MortgageService;
import com.hcl.hackaton.util.LoggerUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class MortgageServiceImpl implements MortgageService {

    //private MortgageDTO mortgageDTO;

    private Mortgage mortgage;
    
    @Autowired
    private MortgageRepository mortgageRepository;
    
	@Autowired
	LoggerUtil loggerUtil;

    BigDecimal mortgageBalance;

    @Override
    public BigDecimal showAccountBalance() {
        return mortgage.getMortgageBalance();
    }

    @Override
    public BigDecimal repay(BigDecimal amt) {
        mortgageBalance=mortgage.getMortgageBalance();
        try {
            mortgageBalance = mortgageBalance.add(amt);
            updateMortgageBalance(mortgage.getMortgageId(),mortgageBalance);
            System.out.println("Balance after Repaid: " + mortgageBalance);
        }catch(Exception e){
        	loggerUtil.logError(getClass(), null, e);
        }
        return mortgageBalance;
    }

    @Override
    public Mortgage updateMortgageBalance(Long mortgageId, BigDecimal mortgageBalance) {
    	Mortgage copiedMortgage = new Mortgage();
        if (mortgageRepository.existsById(mortgageId)) {
        	copiedMortgage.setAccountId(mortgageId);
        	copiedMortgage.setMortgageBalance(mortgageBalance);
            return copiedMortgage;
        } else {
            throw new IllegalArgumentException("No such account found: " + mortgageId);
        }

    }
    public String authenticate(MortgageDTO loginDTO) {
        // Authentication logic (JWT, etc.)
        return "JWT_TOKEN";
    }

    public List<Mortgage> getAllMortgageAccounts() {

        return mortgageRepository.findAll();
    }

    @Override
    public void deleteMortgageAccount(Long accountId) {
        try {
            if (mortgageRepository.existsById(accountId)) {
                mortgageRepository.deleteById(accountId);
            }
        }catch(Exception e){
        	loggerUtil.logError(getClass(), null, e);
        }
    }

}
