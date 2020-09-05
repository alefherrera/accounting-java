package com.accounting.api.domain.account;

import com.accounting.api.domain.account.models.Account;

import java.util.Optional;

public interface AccountRepository {

    Optional<Account> get();

    void save(Account account);

}
