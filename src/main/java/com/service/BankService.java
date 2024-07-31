package com.service;


import com.dao.*;
import com.entity.*;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class BankService {
    private final AccountService accountService;
    private final RateOfExchangeService rateOfExchangeService;
    private final TransactionService transactionService;
    private final UserService userService;

    private final AccountDAO accountDAO;
    private final RateOfExchangeDAO rateOfExchangeDAO;
    private final TransactionDAO transactionDAO;
    private final UserDAO userDAO;
    public BankService() {
        // Initialize DAO implementations
        this.accountDAO = new AccountDAOImpl();
        this.rateOfExchangeDAO = new RateOfExchangeDAOImpl();
        this.transactionDAO = new TransactionDAOImpl();
        this.userDAO = new UserDAOImpl();

        // Initialize services
        this.accountService = new AccountService(accountDAO, userDAO);
        this.rateOfExchangeService = new RateOfExchangeService(rateOfExchangeDAO);
        this.transactionService = new TransactionService(transactionDAO, accountDAO);
        this.userService = new UserService(userDAO);
    }
    public void addUser(Scanner sc) {
        userService.addUser(sc);
    }

    public void deleteUser(Scanner sc) {
        userService.deleteUser(sc);
    }

    public void updateUser(Scanner sc) {
        userService.updateUser(sc);
    }

    public void viewAllUsers() {
        userService.viewAllUsers();
    }
    public void addAccount(Scanner sc) {
        accountService.addAccount(sc);
    }

    public void deleteAccount(Scanner sc) {
        accountService.deleteAccount(sc);
    }

    public void updateAccount(Scanner sc) {
        accountService.updateAccount(sc);
    }

    public void viewAllAccounts() {
        accountService.viewAllAccounts();
    }

    public void addRateOfExchange(Scanner sc) {
        rateOfExchangeService.addRateOfExchange(sc);
    }

    public void deleteRateOfExchange(Scanner sc) {
        rateOfExchangeService.deleteRateOfExchange(sc);
    }

    public void updateRateOfExchange(Scanner sc) {
        rateOfExchangeService.updateRateOfExchange(sc);
    }

    public void viewAllRatesOfExchange() {
        rateOfExchangeService.viewAllRatesOfExchange();
    }

    public void addTransaction(Scanner sc) {
        transactionService.addTransaction(sc);
    }

    public void deleteTransaction(Scanner sc) {
        transactionService.deleteTransaction(sc);
    }

    public void updateTransaction(Scanner sc) {
        transactionService.updateTransaction(sc);
    }

    public void viewAllTransactions() {
        transactionService.viewAllTransactions();
    }

    public void depositFunds(Scanner sc) {
        try {
            System.out.print("Enter account ID: ");
            int accountId = Integer.parseInt(sc.nextLine());
            System.out.print("Enter amount to deposit: ");
            double amount = Double.parseDouble(sc.nextLine());

            Account account = accountDAO.getAccountById(accountId);
            if (account != null) {
                account.setBalance(account.getBalance() + amount);
                accountDAO.updateAccount(account);
                System.out.println("Funds deposited successfully.");
            } else {
                System.out.println("Account not found!");
            }
        } catch (Exception e) {
            System.out.println("Error depositing funds: " + e.getMessage());
        }
    }
    public void transferFunds(Scanner sc) {
        try {
            System.out.print("Enter source account ID: ");
            int sourceAccountId = Integer.parseInt(sc.nextLine());
            System.out.print("Enter destination account ID: ");
            int destinationAccountId = Integer.parseInt(sc.nextLine());
            System.out.print("Enter amount to transfer: ");
            double amount = Double.parseDouble(sc.nextLine());

            Account sourceAccount = accountDAO.getAccountById(sourceAccountId);
            Account destinationAccount = accountDAO.getAccountById(destinationAccountId);

            if (sourceAccount != null && destinationAccount != null && sourceAccount.getBalance() >= amount) {
                RateOfExchange sourceRate = rateOfExchangeDAO.getRateByCurrency(sourceAccount.getCurrency());
                RateOfExchange destinationRate = rateOfExchangeDAO.getRateByCurrency(destinationAccount.getCurrency());

                if (sourceRate != null && destinationRate != null) {
                    double amountInBaseCurrency = amount * sourceRate.getRateToUAH();
                    double convertedAmount = amountInBaseCurrency / destinationRate.getRateToUAH();

                    sourceAccount.setBalance(sourceAccount.getBalance() - amount);
                    destinationAccount.setBalance(destinationAccount.getBalance() + convertedAmount);



                    Transaction transaction = new Transaction();
                    transaction.setSenderAccount(sourceAccount);
                    transaction.setBeneficiaryAccount(destinationAccount);
                    transaction.setAmount(amount);

                    sourceAccount.getSentTransactions().add(transaction);
                    destinationAccount.getReceivedTransactions().add(transaction);
                    accountDAO.updateAccount(sourceAccount);
                    accountDAO.updateAccount(destinationAccount);

                    /*transactionDAO.addTransaction(transaction);*/
                    System.out.println("Funds transferred successfully.");
                } else {
                    System.out.println("Currency conversion rates not found.");
                }
            } else {
                System.out.println("Transfer failed. Please check account details and balance.");
            }
        } catch (Exception e) {
            System.out.println("Error transferring funds: " + e.getMessage());
        }
    }
    public void convertCurrency(Scanner sc) {
        try {
            System.out.print("Enter user ID: ");
            int userId = Integer.parseInt(sc.nextLine());
            System.out.print("Enter source account ID: ");
            int sourceAccountId = Integer.parseInt(sc.nextLine());
            System.out.print("Enter target account ID: ");
            int targetAccountId = Integer.parseInt(sc.nextLine());
            System.out.print("Enter amount to convert: ");
            double amount = Double.parseDouble(sc.nextLine());

            Account sourceAccount = accountDAO.getAccountById(sourceAccountId);
            Account targetAccount = accountDAO.getAccountById(targetAccountId);

            if (sourceAccount != null && targetAccount != null && sourceAccount.getUser().getId() == userId && targetAccount.getUser().getId() == userId) {
                RateOfExchange sourceRate = rateOfExchangeDAO.getRateByCurrency(sourceAccount.getCurrency());
                RateOfExchange targetRate = rateOfExchangeDAO.getRateByCurrency(targetAccount.getCurrency());

                if (sourceRate != null && targetRate != null) {
                    double amountInBaseCurrency = amount * sourceRate.getRateToUAH();
                    double convertedAmount = amountInBaseCurrency / targetRate.getRateToUAH();

                    sourceAccount.setBalance(sourceAccount.getBalance() - amount);
                    targetAccount.setBalance(targetAccount.getBalance() + convertedAmount);

                    accountDAO.updateAccount(sourceAccount);
                    accountDAO.updateAccount(targetAccount);

                    System.out.println("Currency converted successfully.");
                } else {
                    System.out.println("Currency conversion rates not found.");
                }
            } else {
                System.out.println("Conversion failed. Please check account details and ensure both accounts belong to the same user");
            }
        } catch (Exception e) {
            System.out.println("Error converting currency: " + e.getMessage());
        }
    }
    public void getTotalFundsInUAH(Scanner sc) {
        try {
            System.out.print("Enter user ID: ");
            int userId = Integer.parseInt(sc.nextLine());

            List<Account> userAccounts = accountDAO.getAccountsByUserId(userId);
            RateOfExchange uahRate = rateOfExchangeDAO.getRateByCurrency(Currency.UAH);

            if (userAccounts != null && uahRate != null) {
                double totalFundsInUAH = 0;
                for (Account account : userAccounts) {
                    RateOfExchange accountRate = rateOfExchangeDAO.getRateByCurrency(account.getCurrency());
                    if (accountRate != null) {
                        double balanceInUAH = account.getBalance() * accountRate.getRateToUAH() / uahRate.getRateToUAH();
                        totalFundsInUAH += balanceInUAH;
                    }
                }
                System.out.println("Total funds in UAH: " + totalFundsInUAH);
            } else {
                System.out.println("Failed to calculate total funds. Please check user and rate details.");
            }
        } catch (Exception e) {
            System.out.println("Error calculating total funds in UAH: " + e.getMessage());
        }
    }
    public void autoFillUsers(int numUsers) {
        Random random = new Random();
        for (int i = 0; i < numUsers; i++) {
            User user = new User();
            user.setName("User" + (i + 1));
            user.setAddress("Address" + (i + 1));

            int numAccounts = random.nextInt(3) + 1;
            for (int j = 0; j < numAccounts; j++) {
                Account account = new Account();
                account.setCurrency(Currency.values()[random.nextInt(Currency.values().length)]);
                account.setBalance(random.nextDouble() * 1000);
                account.setUser(user);
                user.getAccountList().add(account);
            }

            userDAO.addUser(user);
        }
        System.out.println("Auto-filled " + numUsers + " users with random accounts.");
    }

    public void autoFillRatesOfExchange() {
        rateOfExchangeDAO.addRateOfExchange(new RateOfExchange(Currency.USD, 38.5));
        rateOfExchangeDAO.addRateOfExchange(new RateOfExchange(Currency.EUR, 42.0));
        rateOfExchangeDAO.addRateOfExchange(new RateOfExchange(Currency.UAH, 1.0));
        System.out.println("Auto-filled exchange rates.");
    }
    public void closeAll() {
        accountDAO.close();
        rateOfExchangeDAO.close();
        transactionDAO.close();
        userDAO.close();
    }
}
