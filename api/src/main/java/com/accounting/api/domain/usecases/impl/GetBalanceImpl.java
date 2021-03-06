package com.accounting.api.domain.usecases.impl;

import com.accounting.api.domain.account.AccountRepository;
import com.accounting.api.domain.account.models.Account;
import com.accounting.api.domain.account.models.Balance;
import com.accounting.api.domain.usecases.GetBalance;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
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
