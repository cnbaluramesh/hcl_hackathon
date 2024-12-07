package com.hcl.hackaton.util;

import com.hcl.hackaton.entity.Account;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommonValidationsUtil {

	@Autowired
	ErrorMessageUtil errorMessageUtil;
	
    public boolean isValidAccountType(String accountType) {
        return "SAVINGS".equals(accountType) || "MORTGAGE".equals(accountType);
    }

    public boolean hasSufficientBalance(Account account, BigDecimal amount) {
        return account.getBalance().compareTo(amount) > 0;
    }

    public void validateAccount(Account account) {
        if (account.getAccountNumber() == null || account.getAccountNumber().isEmpty()) {
            throw new IllegalArgumentException(ErrorMessageUtil.getErrorMessage("ERR001"));
        }

        if (!isValidAccountType(account.getAccountType().toString())) {
            throw new IllegalArgumentException(ErrorMessageUtil.getErrorMessage("ERR007"));
        }

    }
}
