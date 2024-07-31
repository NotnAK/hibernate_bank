package com.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Data
@Entity()
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "sender_account_id")
    private Account senderAccount;

    @ManyToOne
    @JoinColumn(name = "beneficiary_account_id")
    private Account beneficiaryAccount;

    private Double amount;

}
