package com.dao;

import com.entity.Transaction;

import java.util.List;

public interface TransactionDAO {
    void addTransaction(Transaction transaction);
    void deleteTransaction(Transaction transaction);
    void updateTransaction(Transaction transaction);
    List<Transaction> getAllTransactions();
    Transaction getTransactionById(int id);
    void close();
}
