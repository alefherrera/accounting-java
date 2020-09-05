package com.accounting.domain.account;

import com.accounting.domain.account.models.Account;

import java.util.function.Supplier;

public interface AccountRepository extends Supplier<Account> {
}
