package com.bankingsystem.repository;

import com.bankingsystem.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    // Custom query to find transactions by customerId
    List<Transaction> findByCustomerId(Long customerId);
}
