package com.accounting.domain.usecases;

import com.accounting.domain.account.AccountRepository;
import com.accounting.domain.account.models.Balance;

import java.util.Optional;

public class GetBalanceImpl implements GetBalance {

    private final AccountRepository accountRepository;

    public GetBalanceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Optional<Balance> get() {
        return Optional.of(new Balance(0));
    }
}
