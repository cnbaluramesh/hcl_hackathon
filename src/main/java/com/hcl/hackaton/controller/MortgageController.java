package com.hcl.hackaton.controller;


import com.hcl.hackaton.services.MortgageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MortgageController {

  @Autowired
  private MortgageService mortgageService;

    @GetMapping("/{mortgageAccountId}")
    public ResponseEntity<Long> getAccountDetails(@PathVariable Long accountId) {
        long account = mortgageService.showAccountBalance();
        return ResponseEntity.ok(account);
    }


    @PostMapping("/repay/{amt}")
    public ResponseEntity<Long> repayDetails(long amt) {
        long token = mortgageService.repay(amt);
        return ResponseEntity.ok(token);
    }

}