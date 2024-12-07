package com.hcl.hackaton.controller;


import com.hcl.hackaton.dto.MortgageDTO;
import com.hcl.hackaton.repository.MortgageRepository;
import com.hcl.hackaton.services.MortgageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MortgageController {

  @Autowired
  private MortgageService mortgageService;

  @Autowired
  private MortgageRepository mortgageRepository;


    @PostMapping("/login")
    public <LoginDTO> ResponseEntity<String> login(@RequestBody MortgageDTO mortgageDTO) {
        String token = mortgageService.authenticate(mortgageDTO);
        return ResponseEntity.ok(token);
    }
    @GetMapping("/{mortgageAccountId}")
    public void getAccountDetails(@PathVariable Long accountId) {
      // return mortgageRepository.findById(accountId).orElseThrow(() -> new RuntimeException("Account not found"));

    }


    @PostMapping("/repay/{amt}")
    public ResponseEntity<Long> repayDetails(long amt) {
        long token = mortgageService.repay(amt);
        return ResponseEntity.ok(token);
    }

}