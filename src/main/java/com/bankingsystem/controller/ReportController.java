package com.bankingsystem.controller;

import com.bankingsystem.model.Report;
import com.bankingsystem.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    @Autowired
    private ReportService reportService;

    // Endpoint to generate transaction report for a customer
    @GetMapping("/transactions/{customerId}")
    public ResponseEntity<List<Report>> getTransactionReport(@PathVariable Long customerId) {
        List<Report> report = reportService.generateTransactionReport(customerId);
        return new ResponseEntity<>(report, HttpStatus.OK);
    }

    // Endpoint to generate transaction summary report based on type (e.g., DEPOSIT, WITHDRAWAL)
    @GetMapping("/summary/{type}")
    public ResponseEntity<List<Report>> getTransactionSummaryReport(@PathVariable String type) {
        List<Report> summaryReport = reportService.generateTransactionSummaryReport(type);
        return new ResponseEntity<>(summaryReport, HttpStatus.OK);
    }
}
