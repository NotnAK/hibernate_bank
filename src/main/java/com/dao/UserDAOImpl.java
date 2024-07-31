package com.dao;

import com.entity.Account;
import com.entity.Transaction;
import com.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class UserDAOImpl implements UserDAO{
    EntityManagerFactory entityManagerFactory;
    EntityManager entityManager;

    public UserDAOImpl() {
        entityManagerFactory = Persistence.createEntityManagerFactory("bankHibernate");
        entityManager = entityManagerFactory.createEntityManager();
    }

    @Override
    public void addUser(User user) {
        entityManager.getTransaction().begin();
        try{
            entityManager.persist(user);
            entityManager.getTransaction().commit();
        }
        catch (Exception e){
            entityManager.getTransaction().rollback();
        }
    }

    @Override
    public void deleteUser(User user) {
        entityManager.getTransaction().begin();
        try {
            if (user != null) {
                List<Account> accountList = user.getAccountList();
                for (Account account : accountList) {
                    entityManager.remove(account);
                }
                entityManager.remove(user);
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }
    }

    @Override
    public void updateUser(User user) {
        entityManager.getTransaction().begin();
        try {
            entityManager.merge(user);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }
    }
   /* @Override
    public void addUserWithAccounts(User user) {
        entityManager.getTransaction().begin();
        try {
            entityManager.persist(user);
            for (Account account : user.getAccountList()) {
                entityManager.persist(account);
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }*/
    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("SELECT u FROM User u", User.class).getResultList();
    }
    @Override
    public User getUserById(int id) {
        return entityManager.find(User.class, id);
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
