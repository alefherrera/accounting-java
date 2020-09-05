package com.accounting.domain.usecases.impl;

import com.accounting.domain.account.AccountRepository;
import com.accounting.domain.account.models.Account;
import com.accounting.domain.account.models.Transaction;
import com.accounting.domain.usecases.GetTransactions;

import java.util.Collection;
import java.util.Collections;

public class GetTransactionsImpl implements GetTransactions {

    private final AccountRepository accountRepository;

    public GetTransactionsImpl(final AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Collection<Transaction> get() {
        return accountRepository.get().map(Account::getTransactions).orElse(Collections.emptyList());
    }
}
