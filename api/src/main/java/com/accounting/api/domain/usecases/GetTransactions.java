package com.accounting.api.domain.usecases;

import com.accounting.api.domain.account.models.Transaction;

import java.util.Collection;
import java.util.function.Supplier;

public interface GetTransactions extends Supplier<Collection<Transaction>> {
}
