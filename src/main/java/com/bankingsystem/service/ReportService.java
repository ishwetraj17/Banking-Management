package com.bankingsystem.service;

import com.bankingsystem.model.Report;
import com.bankingsystem.model.Transaction;
import com.bankingsystem.repository.CustomerRepository;
import com.bankingsystem.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ReportService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private CustomerRepository customerRepository;

    // Generate transaction report for a customer
    public List<Report> generateTransactionReport(Long customerId) {
        List<Transaction> transactions = transactionRepository.findByCustomerId(customerId);
        List<Report> reportList = new ArrayList<>();

        transactions.forEach(transaction -> {
            Report report = new Report(
                    customerId,
                    customerRepository.findById(customerId).get().getName(),
                    transaction.getType(),
                    transaction.getAmount(),
                    transaction.getDate()
            );
            reportList.add(report);
        });

        return reportList;
    }

    // Generate a summary report based on transaction type
    public List<Report> generateTransactionSummaryReport(String type) {
        List<Transaction> transactions = transactionRepository.findAll();
        List<Report> summaryReport = new ArrayList<>();

        transactions.stream()
                .filter(transaction -> transaction.getType().equalsIgnoreCase(type))
                .forEach(transaction -> {
                    Report report = new Report(
                            transaction.getCustomerId(),
                            customerRepository.findById(transaction.getCustomerId()).get().getName(),
                            transaction.getType(),
                            transaction.getAmount(),
                            transaction.getDate()
                    );
                    summaryReport.add(report);
                });

        return summaryReport;
    }
}
