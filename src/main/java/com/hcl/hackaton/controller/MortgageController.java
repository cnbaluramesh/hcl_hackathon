package com.hcl.hackaton.controller;


import com.hcl.hackaton.dto.MortgageDTO;
import com.hcl.hackaton.entity.Mortgage;
import com.hcl.hackaton.repository.MortgageRepository;
import com.hcl.hackaton.services.MortgageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class MortgageController {

  @Autowired
  private MortgageService mortgageService;

  @Autowired
  private MortgageRepository mortgageRepository;


    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody MortgageDTO mortgageDTO) {
        String token = mortgageService.authenticate(mortgageDTO);
        return ResponseEntity.ok(token);
    }
    @GetMapping("/{mortgageAccountId}")
    public Mortgage getMortgageAccountDetails(@PathVariable Long accountId) {
       return mortgageRepository.findById(accountId).orElseThrow(() -> new RuntimeException("Account not found"));
    }
    @PostMapping("/repay/{amt}")
    public ResponseEntity<BigDecimal> repayDetails(BigDecimal amt) {
        BigDecimal token = mortgageService.repay(amt);
        return ResponseEntity.ok(token);
    }

    @GetMapping("/accountBalance")
    public Mortgage showAccountBalance(@PathVariable Long accountId) {
        return mortgageRepository.findById(accountId).orElseThrow(() -> new RuntimeException("Account not found"));
    }

}