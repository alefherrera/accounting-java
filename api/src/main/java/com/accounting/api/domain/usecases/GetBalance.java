package com.accounting.api.domain.usecases;

import com.accounting.api.domain.account.models.Balance;

import java.util.Optional;
import java.util.function.Supplier;

public interface GetBalance extends Supplier<Optional<Balance>> {
}
