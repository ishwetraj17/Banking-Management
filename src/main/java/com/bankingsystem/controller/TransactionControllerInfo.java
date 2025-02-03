package com.bankingsystem.controller;

import com.bankingsystem.model.Transaction;
import com.bankingsystem.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping("/{customerId}")
    public List<Transaction> getTransactionsByCustomerId(@PathVariable Long customerId) {
        return transactionService.getTransactionsByCustomerId(customerId);
    }

    @PostMapping("/{customerId}")
    public Transaction createTransaction(@PathVariable Long customerId, @RequestBody Transaction transactionDetails) {
        return transactionService.createTransaction(customerId, transactionDetails);
    }
}
