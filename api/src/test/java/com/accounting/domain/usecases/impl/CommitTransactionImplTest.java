package com.accounting.domain.usecases.impl;

import com.accounting.domain.account.AccountRepository;
import com.accounting.domain.account.models.Account;
import com.accounting.domain.account.models.TransactionType;
import com.accounting.domain.usecases.CommitTransaction;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CommitTransactionImplTest {

    @Test
    void mustAcceptCreditTransaction() {
        AccountRepository accountRepository = mock(AccountRepository.class);
        when(accountRepository.get()).thenReturn(Optional.of(new Account()));

        CommitTransaction commitTransaction = new CommitTransactionImpl(accountRepository);

        CommitTransaction.CommitTransactionModel model = new CommitTransaction.CommitTransactionModel(TransactionType.CREDIT, 100);
        CommitTransaction.CommitTransactionResult result = commitTransaction.apply(model).orElseThrow();

        assertEquals(100, result.getBalance().getAmount());
    }

    @Test
    void errorSavingAccount() {
        AccountRepository accountRepository = mock(AccountRepository.class);
        when(accountRepository.get()).thenReturn(Optional.of(new Account()));
        doThrow(new RuntimeException()).when(accountRepository).save(any());

        CommitTransaction commitTransaction = new CommitTransactionImpl(accountRepository);

        CommitTransaction.CommitTransactionModel model = new CommitTransaction.CommitTransactionModel(TransactionType.CREDIT, 100);

        assertThrows(RuntimeException.class, () -> commitTransaction.apply(model));
    }

}