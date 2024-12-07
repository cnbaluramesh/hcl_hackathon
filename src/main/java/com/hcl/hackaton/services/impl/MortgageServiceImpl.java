package com.hcl.hackaton.services.impl;

import com.hcl.hackaton.dto.MortgageDTO;
import com.hcl.hackaton.entity.Account;
import com.hcl.hackaton.entity.Mortgage;
import com.hcl.hackaton.repository.MortgageRepository;
import com.hcl.hackaton.repository.impl.MortgageRepositoryImpl;
import com.hcl.hackaton.services.MortgageService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class MortgageServiceImpl implements MortgageService {

    @Autowired
    private MortgageDTO mortgageDTO;

    @Autowired
    private Mortgage mortgage;
    @Autowired
            private MortgageRepository mortgageRepository;

    @Autowired
    private MortgageRepositoryImpl mortgageRepositoryImpl;
    Long mortgageBalance;

    @Override
    public long showAccountBalance() {
        return mortgageDTO.getMortgageBalance();
    }

    @Override
    public long repay(long amt) {
        mortgageBalance=mortgageDTO.getMortgageBalance();
        try {
            mortgageBalance = mortgageBalance + amt;
            updateMortgageBalance(mortgageDTO.getMortgageId(),mortgageBalance);
            System.out.println("Balance after Repaid: " + mortgageBalance);
        }catch(Exception e){
            e.getMessage();
        }
        return mortgageBalance;
    }



    @Override
    public Mortgage updateMortgageBalance(Long mortgageId,long mortgageBalance) {
        if (mortgageRepository.existsById(mortgageId)) {
            mortgage.setAccountId(mortgageId);
            mortgage.setMortgageBalance(mortgageBalance);
            return mortgageRepository.save(mortgage);
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
            e.getMessage();
        }
    }

}
