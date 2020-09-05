package com.accounting.domain.account.models;

import java.util.UUID;

public class Transaction {

    protected final double amount;
    private final String id;

    protected Transaction(double amount) {
        id = UUID.randomUUID().toString();
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }

    public String getId() {
        return id;
    }
}
