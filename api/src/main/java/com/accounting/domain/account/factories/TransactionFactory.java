package com.accounting.domain.account.factories;

import com.accounting.domain.account.models.Transaction;
import com.accounting.domain.account.models.TransactionType;

import java.util.function.BiFunction;

public interface TransactionFactory extends BiFunction<TransactionType, Double, Transaction> {

}
