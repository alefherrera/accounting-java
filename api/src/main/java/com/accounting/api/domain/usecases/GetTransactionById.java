package com.accounting.api.domain.usecases;

import com.accounting.api.domain.account.models.Transaction;

import java.util.Optional;
import java.util.function.Function;

public interface GetTransactionById extends Function<String, Optional<Transaction>> {
}
