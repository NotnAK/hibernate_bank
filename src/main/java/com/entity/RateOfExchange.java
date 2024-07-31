package com.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Data
@Entity
@Table(name = "rates_of_exchange")
public class RateOfExchange {
    @Id
    @Enumerated(EnumType.STRING)
    private Currency currency;

    private double rateToUAH;

    public RateOfExchange() {
    }

    public RateOfExchange(Currency currency, double rateToUAH) {
        this.currency = currency;
        this.rateToUAH = rateToUAH;
    }

}
