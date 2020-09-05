package com.accounting.domain.usecases.impl;

import com.accounting.domain.account.AccountRepository;
import com.accounting.domain.account.exceptions.InvalidTransactionTypeException;
import com.accounting.domain.account.exceptions.TransactionRefusedException;
import com.accounting.domain.account.models.Account;
import com.accounting.domain.account.models.CreditTransaction;
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
        when(accountRepository.get()).thenReturn(Optional.of(getEmptyAccount()));

        CommitTransaction commitTransaction = new CommitTransactionImpl(accountRepository);

        double expectedAmount = 100;
        CommitTransaction.CommitTransactionModel model = new CommitTransaction.CommitTransactionModel(TransactionType.CREDIT, expectedAmount);
        CommitTransaction.CommitTransactionResult result = commitTransaction.apply(model).orElseThrow();

        assertEquals(expectedAmount, result.getBalance().getAmount());
    }

    @Test
    void acceptDebitTransactionWhenBalanceIsMoreThanZero() {
        AccountRepository accountRepository = mock(AccountRepository.class);
        when(accountRepository.get()).thenReturn(Optional.of(getAccountWithBalance(101)));

        CommitTransaction commitTransaction = new CommitTransactionImpl(accountRepository);

        CommitTransaction.CommitTransactionModel model = new CommitTransaction.CommitTransactionModel(TransactionType.DEBIT, 100);
        CommitTransaction.CommitTransactionResult result = commitTransaction.apply(model).orElseThrow();

        assertEquals(1, result.getBalance().getAmount());
    }

    @Test
    void acceptDebitTransactionWhenBalanceIsZero() {
        AccountRepository accountRepository = mock(AccountRepository.class);
        when(accountRepository.get()).thenReturn(Optional.of(getAccountWithBalance(100)));

        CommitTransaction commitTransaction = new CommitTransactionImpl(accountRepository);

        CommitTransaction.CommitTransactionModel model = new CommitTransaction.CommitTransactionModel(TransactionType.DEBIT, 100);
        CommitTransaction.CommitTransactionResult result = commitTransaction.apply(model).orElseThrow();

        assertEquals(0, result.getBalance().getAmount());
    }

    @Test
    void refuseNegativeAmountOnAccount() {
        AccountRepository accountRepository = mock(AccountRepository.class);
        when(accountRepository.get()).thenReturn(Optional.of(getAccountWithBalance(50)));

        CommitTransaction commitTransaction = new CommitTransactionImpl(accountRepository);

        CommitTransaction.CommitTransactionModel model = new CommitTransaction.CommitTransactionModel(TransactionType.DEBIT, 100);

        assertThrows(TransactionRefusedException.class, () -> commitTransaction.apply(model));
    }

    @Test
    void refuseTransactionWhenUnableToGetBalance() {
        AccountRepository accountRepository = mock(AccountRepository.class);
        when(accountRepository.get()).thenThrow(new RuntimeException());

        CommitTransaction commitTransaction = new CommitTransactionImpl(accountRepository);

        CommitTransaction.CommitTransactionModel model = new CommitTransaction.CommitTransactionModel(TransactionType.DEBIT, 100);

        assertThrows(RuntimeException.class, () -> commitTransaction.apply(model));
    }

    @Test
    void refuseTransactionWhenAccountDoesNotExists() {
        AccountRepository accountRepository = mock(AccountRepository.class);
        when(accountRepository.get()).thenReturn(Optional.empty());

        CommitTransaction commitTransaction = new CommitTransactionImpl(accountRepository);

        CommitTransaction.CommitTransactionModel model = new CommitTransaction.CommitTransactionModel(TransactionType.DEBIT, 100);

        Optional<CommitTransaction.CommitTransactionResult> result = commitTransaction.apply(model);

        assertTrue(result.isEmpty());
    }

    @Test
    void errorSavingAccount() {
        AccountRepository accountRepository = mock(AccountRepository.class);
        when(accountRepository.get()).thenReturn(Optional.of(getEmptyAccount()));
        doThrow(new RuntimeException()).when(accountRepository).save(any());

        CommitTransaction commitTransaction = new CommitTransactionImpl(accountRepository);

        CommitTransaction.CommitTransactionModel model = new CommitTransaction.CommitTransactionModel(TransactionType.CREDIT, 100);

        assertThrows(RuntimeException.class, () -> commitTransaction.apply(model));
    }

    @Test
    void commitUnknownTransactionType() {
        AccountRepository accountRepository = mock(AccountRepository.class);
        when(accountRepository.get()).thenReturn(Optional.of(getEmptyAccount()));
        doThrow(new RuntimeException()).when(accountRepository).save(any());

        CommitTransaction commitTransaction = new CommitTransactionImpl(accountRepository);

        CommitTransaction.CommitTransactionModel model = new CommitTransaction.CommitTransactionModel(null, 100);

        assertThrows(InvalidTransactionTypeException.class, () -> commitTransaction.apply(model));
    }

    private Account getEmptyAccount() {
        return new Account();
    }

    private Account getAccountWithBalance(double amount) {
        Account result = new Account();
        result.commitTransaction(new CreditTransaction(amount));
        return result;
    }
}