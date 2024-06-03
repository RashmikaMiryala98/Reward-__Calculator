package com.rewards.rewardscalculator.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;


/**
 * Entity class representing a customer transaction.
 * Contains transaction details and is mapped to the TRANSACTION table in the database.
 */
@Getter
@Setter
@Entity
@Table(name = "TRANSACTION")
@AllArgsConstructor
@NoArgsConstructor
public class CustomerTransaction {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "TRANSACTION_ID")
    private Long transactionId;

    @ManyToOne
    @JoinColumn(name = "CUSTOMER_ID", nullable = false)
    private Customer customer;

    @Column(name = "TRANSACTION_DATE")
    private Timestamp transactionDate;

    @Column(name = "AMOUNT")
    private double transactionAmount;
}