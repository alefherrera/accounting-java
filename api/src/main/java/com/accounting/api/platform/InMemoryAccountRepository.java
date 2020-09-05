package com.accounting.api.platform;

import com.accounting.api.domain.account.AccountRepository;
import com.accounting.api.domain.account.models.Account;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class InMemoryAccountRepository implements AccountRepository {

    private Account account;

    public Optional<Account> get() {
        return Optional.of(account);
    }

    public void save(Account account) {
        account = account;
    }
}
