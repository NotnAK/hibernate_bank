package com.dao;


import com.entity.Currency;
import com.entity.RateOfExchange;

import java.util.List;

public interface RateOfExchangeDAO {
    void addRateOfExchange(RateOfExchange rateOfExchange);
    void deleteRateOfExchange(RateOfExchange rateOfExchange);
    void updateRateOfExchange(RateOfExchange rateOfExchange);
    List<RateOfExchange> getAllRatesOfExchange();
    RateOfExchange getRateByCurrency(Currency currency);
    void close();
}
