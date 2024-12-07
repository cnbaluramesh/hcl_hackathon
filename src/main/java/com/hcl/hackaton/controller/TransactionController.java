package com.hcl.hackaton.controller;

import com.hcl.hackaton.dto.TransferRequest;
import com.hcl.hackaton.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/transfer")
    public ResponseEntity<String> transferFunds(@RequestBody TransferRequest transferRequest) {
        String result = transactionService.transferFunds(transferRequest);
        return ResponseEntity.ok(result);
    }
}