package com.bankingsystem.service;

import com.bankingsystem.exception.InvalidTransactionException;
import com.bankingsystem.exception.ResourceNotFoundException;
import com.bankingsystem.model.Customer;
import com.bankingsystem.model.Transaction;
import com.bankingsystem.repository.CustomerRepository;
import com.bankingsystem.repository.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TransactionServiceTest {

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private TransactionService transactionService;

    private Customer customer;
    private Transaction transaction;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        customer = new Customer(1L, "John Doe", "john.doe@example.com", "1234567890");
        transaction = new Transaction(1L, "DEPOSIT", BigDecimal.valueOf(100.0), customer);
    }

    @Test
    void createTransaction_ShouldSaveTransaction_WhenValid() {
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
        when(transactionRepository.save(transaction)).thenReturn(transaction);

        Transaction createdTransaction = transactionService.createTransaction(1L, transaction);

        assertNotNull(createdTransaction);
        assertEquals("DEPOSIT", createdTransaction.getTransactionType());
    }

    @Test
    void createTransaction_ShouldThrowException_WhenTransactionAmountIsZero() {
        transaction.setAmount(BigDecimal.ZERO);

        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));

        assertThrows(InvalidTransactionException.class, () -> transactionService.createTransaction(1L, transaction));
    }

    @Test
    void createTransaction_ShouldThrowException_WhenCustomerNotFound() {
        when(customerRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> transactionService.createTransaction(1L, transaction));
    }
}
