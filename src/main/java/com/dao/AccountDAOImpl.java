package com.dao;

import com.entity.Account;
import com.entity.Transaction;
import com.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class AccountDAOImpl implements AccountDAO{
    EntityManagerFactory entityManagerFactory;
    EntityManager entityManager;

    public AccountDAOImpl() {
        entityManagerFactory = Persistence.createEntityManagerFactory("bankHibernate");
        entityManager = entityManagerFactory.createEntityManager();
    }

    @Override
    public void addAccount(Account account) {
        entityManager.getTransaction().begin();
        try {
            entityManager.persist(account);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }

    @Override
    public void deleteAccount(Account account) {
        entityManager.getTransaction().begin();
        try {
            User user = entityManager.find(User.class, account.getUser().getId());
            user.getAccountList().remove(account);
            entityManager.merge(user);
            entityManager.remove(account);

            entityManager.getTransaction().commit();
            entityManager.clear();

        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }
    }

    @Override
    public void updateAccount(Account account) {
        entityManager.getTransaction().begin();
        try {
            entityManager.merge(account);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }
    }

    @Override
    public List<Account> getAllAccounts() {
        return entityManager.createQuery("SELECT a FROM Account a", Account.class).getResultList();
    }

    @Override
    public Account getAccountById(int id) {
        return entityManager.find(Account.class, id);
    }
    @Override
    public List<Account> getAccountsByUserId(int userId) {
        return entityManager.createQuery("SELECT a FROM Account a WHERE a.user.id = :userId", Account.class)
                .setParameter("userId", userId)
                .getResultList();
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
