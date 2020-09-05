package com.accounting.api.domain.account.models;

public class CreditTransaction extends Transaction {
    public CreditTransaction(Double amount) {
        super(amount);
    }

    public double operate(double amount) {
        return amount + this.amount;
    }
}
