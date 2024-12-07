package com.hcl.hackaton.services;


import com.hcl.hackaton.entity.Account;

import java.util.List;

public interface AccountService {

    List<Account> getAllAccounts();

    Account getAccountById(Long accountId);

    Account createAccount(Account account);

    Account updateAccount(Account account);

    void deleteAccount(Long accountId);
}

