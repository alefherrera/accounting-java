package com.accounting.domain.usecases;

import com.accounting.domain.account.models.Transaction;

import java.util.Collection;
import java.util.function.Supplier;

public interface GetTransactions extends Supplier<Collection<Transaction>> {
}
