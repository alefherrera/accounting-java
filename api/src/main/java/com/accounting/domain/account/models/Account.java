package com.accounting.domain.account.models;

import java.util.Collection;

public class Account {
    private Collection<Transaction> transactions;

    public Collection<Transaction> getTransactions() {
        return transactions;
    }
}
