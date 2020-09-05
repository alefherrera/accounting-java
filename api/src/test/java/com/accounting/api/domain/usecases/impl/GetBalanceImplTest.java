package com.accounting.api.domain.usecases.impl;

import com.accounting.api.domain.account.AccountRepository;
import com.accounting.api.domain.account.models.Account;
import com.accounting.api.domain.account.models.Balance;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GetBalanceImplTest {

    @Test
    void givenEmptyAccountWhenNoTransactionsThenReturnZero() {

        AccountRepository accountRepository = mock(AccountRepository.class);
        when(accountRepository.get()).thenReturn(Optional.of(new Account()));

        GetBalanceImpl getBalance = new GetBalanceImpl(accountRepository);

        Balance balance = getBalance.get().orElseThrow();

        assertEquals(0, balance.getAmount());

    }
}