package com.accounting.api.domain.account.models;

import java.util.UUID;

public abstract class Transaction {

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

    public abstract double operate(double amount);

    public abstract String getType();
}
