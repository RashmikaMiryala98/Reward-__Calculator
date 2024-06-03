package com.rewards.rewardscalculator.model;

import org.junit.jupiter.api.Test;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTransactionTest {

    @Test
    public void test_gettersAndSetters() {
        // Create a CustomerTransaction object
        CustomerTransaction transaction = new CustomerTransaction();

        // Set values using setter methods
        transaction.setTransactionId(1L);

        Customer c1 = new Customer();
        c1.setCustomerId(1L);
        c1.setCustomerName("John Wick");

        transaction.setCustomer(c1);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        transaction.setTransactionDate(timestamp);
        transaction.setTransactionAmount(120.0);

        // Verify values using getter methods
        assertEquals(1L, transaction.getTransactionId());
        assertEquals(1L, transaction.getCustomer().getCustomerId());
        assertEquals(timestamp, transaction.getTransactionDate());
        assertEquals(120.0, transaction.getTransactionAmount());
    }

}