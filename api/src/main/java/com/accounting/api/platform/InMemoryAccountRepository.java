package com.accounting.api.platform;

import com.accounting.api.domain.account.AccountRepository;
import com.accounting.api.domain.account.models.Account;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Component
public class InMemoryAccountRepository implements AccountRepository {

    ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    private Account account;

    public Optional<Account> get() {
        Lock readLock = readWriteLock.readLock();
        try {
            readLock.lock();
            return Optional.of(account);
        } finally {
            readLock.unlock();
        }
    }

    public void save(Account account) {
        Lock writeLock = readWriteLock.writeLock();
        try {
            writeLock.lock();
            this.account = account;
        } finally {
            writeLock.unlock();
        }
    }
}
