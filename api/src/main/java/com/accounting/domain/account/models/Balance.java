package com.accounting.domain.account.models;

public class Balance {

    private final double amount;

    public Balance(double amount) {
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }
}
