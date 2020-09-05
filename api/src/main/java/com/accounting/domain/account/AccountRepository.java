package com.accounting.domain.account;

import com.accounting.domain.account.models.Account;

public interface AccountRepository {

    Account get();

    void save(Account account);

}
