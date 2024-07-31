package com.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Currency currency;
    private Double balance;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @OneToMany(mappedBy = "senderAccount", cascade = CascadeType.PERSIST)
    private List<Transaction> sentTransactions = new ArrayList<>();

    @OneToMany(mappedBy = "beneficiaryAccount", cascade = CascadeType.PERSIST)
    private List<Transaction> receivedTransactions = new ArrayList<>();

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", currency=" + currency +
                ", balance=" + balance +
                ", user=" + user +
                '}';
    }
}
