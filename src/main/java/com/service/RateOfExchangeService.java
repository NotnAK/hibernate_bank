package com.service;

import com.dao.RateOfExchangeDAO;
import com.entity.Currency;
import com.entity.RateOfExchange;

import java.util.List;
import java.util.Scanner;

public class RateOfExchangeService {
    private final RateOfExchangeDAO rateOfExchangeDAO;

    public RateOfExchangeService(RateOfExchangeDAO rateOfExchangeDAO) {
        this.rateOfExchangeDAO = rateOfExchangeDAO;
    }

    public void addRateOfExchange(Scanner sc) {
        try {
            System.out.print("Enter currency: ");
            String currencyStr = sc.nextLine();
            Currency currency = Currency.valueOf(currencyStr.toUpperCase());
            System.out.print("Enter rate to UAH: ");
            double rateToUAH = Double.parseDouble(sc.nextLine());

            RateOfExchange rateOfExchange = new RateOfExchange();
            rateOfExchange.setCurrency(currency);
            rateOfExchange.setRateToUAH(rateToUAH);

            rateOfExchangeDAO.addRateOfExchange(rateOfExchange);
            System.out.println("Rate of exchange added successfully.");
        } catch (Exception e) {
            System.out.println("Error adding rate of exchange: " + e.getMessage());
        }
    }

    public void deleteRateOfExchange(Scanner sc) {
        try {
            System.out.print("Enter currency to delete: ");
            String currencyStr = sc.nextLine();
            Currency currency = Currency.valueOf(currencyStr.toUpperCase());
            RateOfExchange rateOfExchange = rateOfExchangeDAO.getRateByCurrency(currency);
            if (rateOfExchange != null) {
                rateOfExchangeDAO.deleteRateOfExchange(rateOfExchange);
                System.out.println("Rate of exchange deleted successfully.");
            } else {
                System.out.println("Rate of exchange not found!");
            }
        } catch (Exception e) {
            System.out.println("Error deleting rate of exchange: " + e.getMessage());
        }
    }

    public void updateRateOfExchange(Scanner sc) {
        try {
            System.out.print("Enter currency to update: ");
            String currencyStr = sc.nextLine();
            Currency currency = Currency.valueOf(currencyStr.toUpperCase());
            RateOfExchange rateOfExchange = rateOfExchangeDAO.getRateByCurrency(currency);
            if (rateOfExchange != null) {
                System.out.print("Enter new rate to UAH: ");
                rateOfExchange.setRateToUAH(Double.parseDouble(sc.nextLine()));
                rateOfExchangeDAO.updateRateOfExchange(rateOfExchange);
                System.out.println("Rate of exchange updated successfully.");
            } else {
                System.out.println("Rate of exchange not found!");
            }
        } catch (Exception e) {
            System.out.println("Error updating rate of exchange: " + e.getMessage());
        }
    }

    public void viewAllRatesOfExchange() {
        try {
            List<RateOfExchange> rates = rateOfExchangeDAO.getAllRatesOfExchange();
            rates.forEach(System.out::println);
        } catch (Exception e) {
            System.out.println("Error viewing rates of exchange: " + e.getMessage());
        }
    }

    public RateOfExchange getRateByCurrency(Currency currency) {
        return rateOfExchangeDAO.getRateByCurrency(currency);
    }

    public void close() {
        rateOfExchangeDAO.close();
    }
}
