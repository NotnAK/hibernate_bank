package com.service;

import com.dao.AccountDAO;
import com.dao.TransactionDAO;
import com.entity.Account;
import com.entity.Transaction;

import java.util.List;
import java.util.Scanner;

public class TransactionService {
    private final TransactionDAO transactionDAO;
    private final AccountDAO accountDAO;

    public TransactionService(TransactionDAO transactionDAO, AccountDAO accountDAO) {
        this.transactionDAO = transactionDAO;
        this.accountDAO = accountDAO;
    }

    public void addTransaction(Scanner sc) {
        try {
            System.out.print("Enter sender account ID: ");
            int senderAccountId = Integer.parseInt(sc.nextLine());
            System.out.print("Enter beneficiary account ID: ");
            int beneficiaryAccountId = Integer.parseInt(sc.nextLine());
            System.out.print("Enter amount: ");
            double amount = Double.parseDouble(sc.nextLine());

            Account senderAccount = accountDAO.getAccountById(senderAccountId);
            Account beneficiaryAccount = accountDAO.getAccountById(beneficiaryAccountId);

            if (senderAccount == null) {
                System.out.println("Sender account not found.");
                return;
            }

            if (beneficiaryAccount == null) {
                System.out.println("Beneficiary account not found.");
                return;
            }

            Transaction transaction = new Transaction();
            transaction.setSenderAccount(senderAccount);
            transaction.setBeneficiaryAccount(beneficiaryAccount);
            transaction.setAmount(amount);

            transactionDAO.addTransaction(transaction);
            System.out.println("Transaction added successfully.");
        } catch (Exception e) {
            System.out.println("Error adding transaction: " + e.getMessage());
        }
    }

    public void deleteTransaction(Scanner sc) {
        try {
            System.out.print("Enter transaction ID to delete: ");
            int id = Integer.parseInt(sc.nextLine());
            Transaction transaction = transactionDAO.getTransactionById(id);
            if (transaction != null) {
                transactionDAO.deleteTransaction(transaction);
                System.out.println("Transaction deleted successfully.");
            } else {
                System.out.println("Transaction not found!");
            }
        } catch (Exception e) {
            System.out.println("Error deleting transaction: " + e.getMessage());
        }
    }

    public void updateTransaction(Scanner sc) {
        try {
            System.out.print("Enter transaction ID to update: ");
            int id = Integer.parseInt(sc.nextLine());
            Transaction transaction = transactionDAO.getTransactionById(id);
            if (transaction != null) {
                System.out.print("Enter new amount: ");
                transaction.setAmount(Double.parseDouble(sc.nextLine()));
                transactionDAO.updateTransaction(transaction);
                System.out.println("Transaction updated successfully.");
            } else {
                System.out.println("Transaction not found!");
            }
        } catch (Exception e) {
            System.out.println("Error updating transaction: " + e.getMessage());
        }
    }

    public void viewAllTransactions() {
        try {
            List<Transaction> transactions = transactionDAO.getAllTransactions();
            transactions.forEach(System.out::println);
        } catch (Exception e) {
            System.out.println("Error viewing transactions: " + e.getMessage());
        }
    }

    public Transaction getTransactionById(int id) {
        return transactionDAO.getTransactionById(id);
    }

    public void close() {
        transactionDAO.close();
    }
}
