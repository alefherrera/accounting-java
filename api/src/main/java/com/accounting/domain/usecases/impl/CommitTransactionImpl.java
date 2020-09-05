package com.accounting.domain.usecases.impl;

import com.accounting.domain.account.AccountRepository;
import com.accounting.domain.account.exceptions.InvalidTransactionTypeException;
import com.accounting.domain.account.models.*;
import com.accounting.domain.usecases.CommitTransaction;

import java.util.Objects;
import java.util.Optional;

public class CommitTransactionImpl implements CommitTransaction {

    private final AccountRepository accountRepository;

    public CommitTransactionImpl(final AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Optional<CommitTransactionResult> apply(CommitTransactionModel model) {
        Optional<Account> optionalAccount = accountRepository.get();
        return optionalAccount.map(account -> getResult(account, model));
    }

    private CommitTransactionResult getResult(Account account, CommitTransactionModel model) {
        Transaction transaction = getTransaction(model.getType(), model.getAmount());

        account.commitTransaction(transaction);

        accountRepository.save(account);

        return new CommitTransactionResult(transaction.getId(), account.getBalance());
    }

    private Transaction getTransaction(TransactionType type, double amount) {
        if (Objects.isNull(type)) {
            throw new InvalidTransactionTypeException();
        }
        switch (type) {
            case CREDIT -> {
                return new CreditTransaction(amount);
            }
            case DEBIT -> {
                return new DebitTransaction(amount);
            }
            default -> throw new InvalidTransactionTypeException();
        }
    }
}
