package com.accounting.api.domain.usecases.impl;

import com.accounting.api.domain.account.AccountRepository;
import com.accounting.api.domain.account.models.Account;
import com.accounting.api.domain.account.models.Transaction;
import com.accounting.api.domain.usecases.GetTransactions;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;

@Component
public class GetTransactionsImpl implements GetTransactions {

    private final AccountRepository accountRepository;

    public GetTransactionsImpl(final AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Collection<Transaction> get() {
        return accountRepository.get().map(Account::getTransactions).orElse(Collections.emptyList());
    }
}
