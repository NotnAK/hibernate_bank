package com.dao;

import com.entity.Account;

import java.util.List;

public interface AccountDAO {
    void addAccount(Account account);
    void deleteAccount(Account account);
    void updateAccount(Account account);
    List<Account> getAllAccounts();
    Account getAccountById(int id);
    List<Account> getAccountsByUserId(int userId);
    void close();
}
