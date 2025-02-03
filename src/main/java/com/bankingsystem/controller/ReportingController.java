package com.bankingsystem.controller;

import com.bankingsystem.model.Transaction;
import com.bankingsystem.service.ReportingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/reports")
public class ReportingController {

    @Autowired
    private ReportingService reportingService;

    @GetMapping("/transactions/{customerId}")
    public List<Transaction> getAllTransactions(@PathVariable Long customerId) {
        return reportingService.getAllTransactionsForCustomer(customerId);
    }

    @GetMapping("/balance/{customerId}")
    public BigDecimal getTotalBalance(@PathVariable Long customerId) {
        return reportingService.getTotalBalanceForCustomer(customerId);
    }

    @GetMapping("/filter/{customerId}")
    public List<Transaction> filterTransactionsByType(
            @PathVariable Long customerId,
            @RequestParam String type) {
        return reportingService.filterTransactionsByType(customerId, type);
    }
}
