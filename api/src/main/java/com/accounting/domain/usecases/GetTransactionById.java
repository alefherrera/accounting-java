package com.accounting.domain.usecases;

import com.accounting.domain.account.models.Transaction;

import java.util.Optional;
import java.util.function.Function;

public interface GetTransactionById extends Function<String, Optional<Transaction>> {
}
