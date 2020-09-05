package com.accounting.domain.account.models;

import com.accounting.domain.account.exceptions.TransactionRefusedException;

import java.util.ArrayList;
import java.util.Collection;

public class Account {
    private final Collection<Transaction> transactions = new ArrayList<>();
    private Balance balance = new Balance(0);

    public Collection<Transaction> getTransactions() {
        return transactions;
    }

    public void commitTransaction(Transaction transaction) {
        Double newAmount = transaction.operate(balance.getAmount());
        if (newAmount < 0) {
            throw new TransactionRefusedException();
        }
        transactions.add(transaction);
        this.balance = new Balance(newAmount);
    }

    public Balance getBalance() {
        return balance;
    }
}
