package com.service;

import com.dao.AccountDAO;
import com.dao.UserDAO;
import com.entity.Account;
import com.entity.Currency;
import com.entity.User;

import java.util.List;
import java.util.Scanner;

public class AccountService {
    private final AccountDAO accountDAO;
    private final UserDAO userDAO;

    public AccountService(AccountDAO accountDAO, UserDAO userDAO) {
        this.accountDAO = accountDAO;
        this.userDAO = userDAO;
    }
    public void addAccount(Scanner sc) {
        try {
            System.out.print("Enter user ID: ");
            int userId = Integer.parseInt(sc.nextLine());
            System.out.print("Enter currency (USD, EUR, UAN): ");
            String currencyStr = sc.nextLine();
            Currency currency = Currency.valueOf(currencyStr.toUpperCase());
            System.out.print("Enter initial balance: ");
            double balance = Double.parseDouble(sc.nextLine());

            Account account = new Account();
            User user = userDAO.getUserById(userId);
            if(user == null){
                System.out.println("User not found");
                return;
            }
            user.getAccountList().add(account);
            account.setUser(user);
            account.setCurrency(currency);
            account.setBalance(balance);
            userDAO.updateUser(user);
            System.out.println("Account added successfully.");

        } catch (Exception e) {
            System.out.println("Error adding account: " + e.getMessage());
        }
    }

    public void deleteAccount(Scanner sc) {
        try {
            System.out.print("Enter account ID to delete: ");
            int accountId = Integer.parseInt(sc.nextLine());
            Account account = accountDAO.getAccountById(accountId);
            if (account != null) {
                accountDAO.deleteAccount(account);
                System.out.println("Account deleted successfully.");
            } else {
                System.out.println("Account not found!");
            }
        } catch (Exception e) {
            System.out.println("Error deleting account: " + e.getMessage());
        }
    }

    public void updateAccount(Scanner sc) {
        try {
            System.out.print("Enter account ID to update: ");
            int accountId = Integer.parseInt(sc.nextLine());
            Account account = accountDAO.getAccountById(accountId);
            if (account != null) {
                System.out.print("Enter new balance: ");
                account.setBalance(Double.parseDouble(sc.nextLine()));
                accountDAO.updateAccount(account);
                System.out.println("Account updated successfully.");
            } else {
                System.out.println("Account not found!");
            }
        } catch (Exception e) {
            System.out.println("Error updating account: " + e.getMessage());
        }
    }

    public void viewAllAccounts() {
        try {
            List<Account> accounts = accountDAO.getAllAccounts();
            accounts.forEach(System.out::println);
        } catch (Exception e) {
            System.out.println("Error viewing accounts: " + e.getMessage());
        }
    }

    public Account getAccountById(int id) {
        return accountDAO.getAccountById(id);
    }


    public void close() {
        accountDAO.close();
    }
}
