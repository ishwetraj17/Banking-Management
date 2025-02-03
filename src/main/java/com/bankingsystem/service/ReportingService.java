package com.bankingsystem.service;

import com.bankingsystem.model.Transaction;
import com.bankingsystem.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReportingService {

    @Autowired
    private TransactionRepository transactionRepository;

    public List<Transaction> getAllTransactionsForCustomer(Long customerId) {
        return transactionRepository.findByCustomerId(customerId);
    }

    public BigDecimal getTotalBalanceForCustomer(Long customerId) {
        List<Transaction> transactions = transactionRepository.findByCustomerId(customerId);

        return transactions.stream()
                .map(transaction -> {
                    if ("DEPOSIT".equalsIgnoreCase(transaction.getTransactionType())) {
                        return transaction.getAmount();
                    } else if ("WITHDRAWAL".equalsIgnoreCase(transaction.getTransactionType())) {
                        return transaction.getAmount().negate();
                    }
                    return BigDecimal.ZERO;
                })
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public List<Transaction> filterTransactionsByType(Long customerId, String type) {
        return transactionRepository.findByCustomerId(customerId).stream()
                .filter(transaction -> transaction.getTransactionType().equalsIgnoreCase(type))
                .collect(Collectors.toList());
    }
}
