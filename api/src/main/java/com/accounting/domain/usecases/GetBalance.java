package com.accounting.domain.usecases;

import com.accounting.domain.account.models.Balance;

import java.util.function.Supplier;

public interface GetBalance extends Supplier<Balance> {
}
