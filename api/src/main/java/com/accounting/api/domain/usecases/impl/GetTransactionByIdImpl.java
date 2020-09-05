package com.accounting.api.domain.usecases.impl;

import com.accounting.api.domain.account.AccountRepository;
import com.accounting.api.domain.account.models.Transaction;
import com.accounting.api.domain.usecases.GetTransactionById;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class GetTransactionByIdImpl implements GetTransactionById {

    private final AccountRepository accountRepository;

    public GetTransactionByIdImpl(final AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Optional<Transaction> apply(String id) {
        return accountRepository.get().flatMap(account -> account.getTransactionById(id));
    }
}
