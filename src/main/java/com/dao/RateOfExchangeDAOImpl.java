package com.dao;

import com.entity.Account;
import com.entity.Currency;
import com.entity.RateOfExchange;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class RateOfExchangeDAOImpl implements RateOfExchangeDAO {
    EntityManagerFactory entityManagerFactory;
    EntityManager entityManager;

    public RateOfExchangeDAOImpl() {
        entityManagerFactory = Persistence.createEntityManagerFactory("bankHibernate");
        entityManager = entityManagerFactory.createEntityManager();
    }

    @Override
    public void addRateOfExchange(RateOfExchange rateOfExchange) {
        entityManager.getTransaction().begin();
        try {
            entityManager.persist(rateOfExchange);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }

    }

    @Override
    public void deleteRateOfExchange(RateOfExchange rateOfExchange) {
        entityManager.getTransaction().begin();
        try {
            entityManager.remove(rateOfExchange);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }
    }

    @Override
    public void updateRateOfExchange(RateOfExchange rateOfExchange) {
        entityManager.getTransaction().begin();
        try {
            entityManager.merge(rateOfExchange);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }
    }

    @Override
    public List<RateOfExchange> getAllRatesOfExchange() {
        return entityManager.createQuery("SELECT r FROM RateOfExchange r", RateOfExchange.class).getResultList();
    }
    @Override
    public RateOfExchange getRateByCurrency(Currency currency) {
        return entityManager.find(RateOfExchange.class, currency);
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
