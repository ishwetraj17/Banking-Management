package com.bankingsystem.model;

import java.util.Date;

public class Report {

    private Long customerId;
    private String customerName;
    private String transactionType;
    private Double amount;
    private Date transactionDate;

    // Constructor, Getters, and Setters
    public Report(Long customerId, String customerName, String transactionType, Double amount, Date transactionDate) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.transactionType = transactionType;
        this.amount = amount;
        this.transactionDate = transactionDate;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }
}
