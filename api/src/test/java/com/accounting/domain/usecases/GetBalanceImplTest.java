package com.accounting.domain.usecases;

import com.accounting.domain.account.AccountRepository;
import com.accounting.domain.account.models.Balance;
import com.accounting.domain.usecases.impl.GetBalanceImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class GetBalanceImplTest {

    @Test
    void givenEmptyAccountWhenNoTransactionsThenReturnZero() {

        AccountRepository accountRepository = mock(AccountRepository.class);

        GetBalanceImpl getBalance = new GetBalanceImpl(accountRepository);

        Balance balance = getBalance.get().orElseThrow();

        assertEquals(0, balance.getAmount());

    }
}