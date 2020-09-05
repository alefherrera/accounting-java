package com.accounting.api.domain.account.exceptions;

public class TransactionRefusedException extends RuntimeException {

    public TransactionRefusedException() {
        super("transaction refused");
    }
}
