package com.accounting.api.domain.account.models;

import com.accounting.api.domain.account.exceptions.TransactionRefusedException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.Optional;

public class Account {
    private final Collection<Transaction> transactions = new ArrayList<>();
    private Balance balance = new Balance(0);

    public Collection<Transaction> getTransactions() {
        return transactions;
    }

    public Optional<Transaction> getTransactionById(String id) {
        return transactions.stream().filter(transaction -> Objects.equals(id, transaction.getId())).findFirst();
    }

    public void commitTransaction(Transaction transaction) {
        double newAmount = transaction.operate(balance.getAmount());
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
