package com.accounting.domain.usecases;

import com.accounting.domain.account.AccountRepository;
import com.accounting.domain.account.models.Balance;

public class GetBalanceImpl implements GetBalance {

    private final AccountRepository accountRepository;

    public GetBalanceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Balance get() {
        return null;
    }
}
