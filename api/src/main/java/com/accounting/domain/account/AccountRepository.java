package com.accounting.domain.account;

import com.accounting.domain.account.models.Account;

import java.util.Optional;

public interface AccountRepository {

    Optional<Account> get();

    void save(Account account);

}
