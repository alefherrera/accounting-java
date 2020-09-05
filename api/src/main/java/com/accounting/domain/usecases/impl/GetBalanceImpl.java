package com.accounting.domain.usecases.impl;

import com.accounting.domain.account.AccountRepository;
import com.accounting.domain.account.models.Account;
import com.accounting.domain.account.models.Balance;
import com.accounting.domain.usecases.GetBalance;

import java.util.Optional;

public class GetBalanceImpl implements GetBalance {

    private final AccountRepository accountRepository;

    public GetBalanceImpl(final AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Optional<Balance> get() {
        Optional<Account> optionalAccount = accountRepository.get();
        return optionalAccount.map(Account::getBalance);
    }
}
