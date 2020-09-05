package com.accounting.api.domain.account.models;

public class DebitTransaction extends Transaction {
    public DebitTransaction(Double amount) {
        super(amount);
    }

    public double operate(double amount) {
        return amount - this.amount;
    }

    @Override
    public String getType() {
        return "debit";
    }
}
