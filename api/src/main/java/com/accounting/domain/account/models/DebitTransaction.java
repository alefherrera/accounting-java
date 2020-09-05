package com.accounting.domain.account.models;

public class DebitTransaction extends Transaction {
    public DebitTransaction(Double amount) {
        super(amount);
    }

    public double operate(double amount) {
        return amount - this.amount;
    }
}
