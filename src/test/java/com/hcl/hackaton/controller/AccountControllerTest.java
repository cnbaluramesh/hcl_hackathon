package com.hcl.hackaton.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import com.hcl.hackaton.entity.Account;
import com.hcl.hackaton.services.AccountService;
import java.time.LocalDateTime;
import java.util.Arrays;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AccountController.class)
public class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountService accountService;

    private Account account;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        account = new Account();
        account.setAccountId(1L);
        account.setCustomerId(1L);
        account.setAccountNumber("1234567890");
        account.setAccountType("SAVINGS");
        account.setBalance(1000.0);
        account.setLastTransactionDate(LocalDateTime.now());
    }

    @Test
    public void testGetAllAccounts() throws Exception {
        when(accountService.getAllAccounts()).thenReturn(Arrays.asList(account));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/accounts")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].accountId").value(account.getAccountId()));
    }

    @Test
    public void testGetAccountById() throws Exception {
        when(accountService.getAccountById(anyLong())).thenReturn(account);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/accounts/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accountId").value(account.getAccountId()));
    }

    @Test
    public void testGetAccountById_NotFound() throws Exception {
        when(accountService.getAccountById(anyLong())).thenThrow(new IllegalArgumentException("Account not found with id: 99"));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/accounts/99")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testCreateAccount() throws Exception {
        when(accountService.createAccount(account)).thenReturn(account);

        String accountJson = "{ \"customerId\": 1, \"accountNumber\": \"1234567890\", \"accountType\": \"SAVINGS\", \"balance\": 1000.0, \"lastTransactionDate\": \"" + account.getLastTransactionDate() + "\" }";

        mockMvc.perform(MockMvcRequestBuilders.post("/api/accounts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(accountJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accountId").value(account.getAccountId()));
    }

    @Test
    public void testUpdateAccount() throws Exception {
        when(accountService.updateAccount(anyLong(), account)).thenReturn(account);

        String accountJson = "{ \"customerId\": 1, \"accountNumber\": \"1234567890\", \"accountType\": \"SAVINGS\", \"balance\": 1000.0, \"lastTransactionDate\": \"" + account.getLastTransactionDate() + "\" }";

        mockMvc.perform(MockMvcRequestBuilders.put("/api/accounts/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(accountJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accountId").value(account.getAccountId()));
    }

    @Test
    public void testDeleteAccount() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/accounts/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}
