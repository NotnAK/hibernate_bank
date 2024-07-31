package com.dao;

import com.entity.Account;
import com.entity.Currency;
import junit.framework.TestCase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AccountDAOImplTest extends TestCase {
    private AccountDAOImpl accountDAO;
    private Account account;

    @BeforeEach
    public void setUp() {
        accountDAO = new AccountDAOImpl();
        account = new Account();
        account.setBalance(100.00);
        account.setCurrency(Currency.EUR);
    }
    @AfterEach
    public void tearDown() {
        accountDAO.close();


    }
    @Test
    public void testAddAccount() {
        accountDAO.addAccount(account);
        Account retrievedAccount = accountDAO.getAccountById(account.getId());
        assertNotNull(retrievedAccount);
        assertEquals(100.00, retrievedAccount.getBalance());
        assertEquals(Currency.EUR, retrievedAccount.getCurrency());
    }
    @Test
    public void testDeleteAccount() {
        accountDAO.addAccount(account);
        assertNotNull(accountDAO.getAccountById(account.getId()));
        accountDAO.deleteAccount(account);
        assertNull(accountDAO.getAccountById(account.getId()));
    }
    @Test
    public void testUpdateAccount() {
        accountDAO.addAccount(account);
        assertNotNull(accountDAO.getAccountById(account.getId()));

        account.setCurrency(Currency.USD);
        accountDAO.updateAccount(account);
        Account retrievedAccount = accountDAO.getAccountById(account.getId());
        assertEquals(Currency.USD, retrievedAccount.getCurrency());

    }
}