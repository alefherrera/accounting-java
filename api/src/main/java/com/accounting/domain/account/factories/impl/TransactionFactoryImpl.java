package com.accounting.domain.account.factories.impl;

import com.accounting.domain.account.exceptions.InvalidTransactionTypeException;
import com.accounting.domain.account.factories.TransactionFactory;
import com.accounting.domain.account.models.CreditTransaction;
import com.accounting.domain.account.models.DebitTransaction;
import com.accounting.domain.account.models.Transaction;
import com.accounting.domain.account.models.TransactionType;

public class TransactionFactoryImpl implements TransactionFactory {
    @Override
    public Transaction apply(TransactionType type, Double amount) {
        switch (type) {
            case CREDIT -> {
                return new CreditTransaction(amount);
            }
            case DEBIT -> {
                return new DebitTransaction(amount);
            }
        }
        throw new InvalidTransactionTypeException();
    }
}
