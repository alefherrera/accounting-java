package com.accounting.domain.usecases.impl;

import com.accounting.domain.account.AccountRepository;
import com.accounting.domain.account.models.Transaction;
import com.accounting.domain.usecases.GetTransactionById;

import java.util.Optional;

public class GetTransactionByIdImpl implements GetTransactionById {

    private final AccountRepository accountRepository;

    public GetTransactionByIdImpl(final AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Optional<Transaction> apply(String id) {
        return accountRepository.get().flatMap(account -> account.getTransactionById(id));
    }
}
