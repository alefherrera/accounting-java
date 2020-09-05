package com.accounting.domain.account.models;

import java.util.ArrayList;
import java.util.Collection;

public class Account {
    private Collection<Transaction> transactions = new ArrayList<>();

    private static Double calculateAccum(Double accum, Transaction transaction) {
        return transaction.operate(accum);
    }

    private static Double merge(Double aDouble, Double aDouble2) {
        return aDouble;
    }

    public Collection<Transaction> getTransactions() {
        return transactions;
    }

    public void commitTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public Balance getBalance() {
        Double amount = transactions.stream().reduce(0D, Account::calculateAccum, Account::merge);
        return new Balance(amount);
    }
}
