package com.hcl.hackaton.services;

import com.hcl.hackaton.dto.MortgageDTO;
import com.hcl.hackaton.entity.Mortgage;
import java.math.BigDecimal;
import java.util.List;

public interface MortgageService {

    public abstract BigDecimal showAccountBalance();

    public BigDecimal repay(BigDecimal amt);

    public Mortgage updateMortgageBalance(Long mortgageId, BigDecimal mortgageBalance);

    public String authenticate(MortgageDTO mortgageDTO);

    abstract void deleteMortgageAccount(Long accountId);

    public List<Mortgage> getAllMortgageAccounts();
}