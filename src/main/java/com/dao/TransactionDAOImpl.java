package com.dao;

import com.entity.Account;
import com.entity.Transaction;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class TransactionDAOImpl implements TransactionDAO{
    EntityManagerFactory entityManagerFactory;
    EntityManager entityManager;

    public TransactionDAOImpl() {
        entityManagerFactory = Persistence.createEntityManagerFactory("bankHibernate");
        entityManager = entityManagerFactory.createEntityManager();
    }

    @Override
    public void addTransaction(Transaction transaction) {
        entityManager.getTransaction().begin();
        try{
            entityManager.persist(transaction);
            entityManager.getTransaction().commit();
        }
        catch (Exception e){
            entityManager.getTransaction().rollback();
        }
    }

    @Override
    public void deleteTransaction(Transaction transaction) {
        entityManager.getTransaction().begin();
        try {
            entityManager.remove(transaction);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }
    }

    @Override
    public void updateTransaction(Transaction transaction) {
        entityManager.getTransaction().begin();
        try {
            entityManager.merge(transaction);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }
    }

    @Override
    public List<Transaction> getAllTransactions() {
        return entityManager.createQuery("SELECT t FROM Transaction t", Transaction.class).getResultList();
    }
    @Override
    public Transaction getTransactionById(int id) {
        return entityManager.find(Transaction.class, id);
    }

    @Override
    public void close() {
        if (entityManager != null && entityManager.isOpen()) {
            entityManager.close();
        }
        if (entityManagerFactory != null && entityManagerFactory.isOpen()) {
            entityManagerFactory.close();
        }
    }
}
