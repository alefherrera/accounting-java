package com.accounting.api.domain.usecases;

import com.accounting.api.domain.account.models.Balance;
import com.accounting.api.domain.account.models.TransactionType;

import java.util.Optional;
import java.util.function.Function;

public interface CommitTransaction extends Function<CommitTransaction.CommitTransactionModel, Optional<CommitTransaction.CommitTransactionResult>> {

    class CommitTransactionModel {
        private final TransactionType type;
        private final double amount;

        public CommitTransactionModel(TransactionType type, double amount) {
            this.type = type;
            this.amount = amount;
        }

        public TransactionType getType() {
            return type;
        }

        public double getAmount() {
            return amount;
        }
    }

    class CommitTransactionResult {

        private final String id;
        private final Balance balance;

        public CommitTransactionResult(String transactionId, Balance balance) {
            this.id = transactionId;
            this.balance = balance;
        }

        public String getId() {
            return id;
        }

        public Balance getBalance() {
            return balance;
        }
    }

}
