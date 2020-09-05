package com.accounting.domain.usecases.impl;

import com.accounting.domain.account.AccountRepository;
import com.accounting.domain.account.factories.TransactionFactory;
import com.accounting.domain.account.models.Account;
import com.accounting.domain.account.models.Transaction;
import com.accounting.domain.usecases.CommitTransaction;

public class CommitTransactionImpl implements CommitTransaction {

    private final AccountRepository accountRepository;
    private final TransactionFactory transactionFactory;

    public CommitTransactionImpl(AccountRepository accountRepository, TransactionFactory transactionFactory) {
        this.accountRepository = accountRepository;
        this.transactionFactory = transactionFactory;
    }

    public CommitTransactionResult apply(CommitTransactionModel model) {

        Account account = accountRepository.get();

        Transaction transaction = transactionFactory.apply(model.getType(), model.getAmount());

        account.commitTransaction(transaction);

        accountRepository.save(account);

        return new CommitTransactionResult(transaction.getId(), account.getBalance());
    }
}
