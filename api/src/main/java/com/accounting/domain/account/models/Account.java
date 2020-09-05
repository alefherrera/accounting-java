package com.accounting.domain.account.models;

import java.util.Collection;

public class Account {
    private Collection<Transaction> transactions;

    public Collection<Transaction> getTransactions() {
        return transactions;
    }

    public void commitTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public Balance getBalance() {
        return new Balance(0);
    }
}
