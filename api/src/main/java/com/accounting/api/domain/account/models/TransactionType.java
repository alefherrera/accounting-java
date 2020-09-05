package com.accounting.api.domain.account.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum TransactionType {
    @JsonProperty("credit")
    CREDIT,
    @JsonProperty("debit")
    DEBIT
}
