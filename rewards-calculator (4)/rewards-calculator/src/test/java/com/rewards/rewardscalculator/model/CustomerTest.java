package com.rewards.rewardscalculator.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CustomerTest {
    @Test
    public void test_gettersAndSetters() {
        // Create a CustomerTransaction object
        Customer customer = new Customer();

        // Set values using setter methods
        customer.setCustomerName("Thomas Anderson");
        customer.setCustomerId(1L);

        // Verify values using getter methods
        assertEquals("Thomas Anderson", customer.getCustomerName());
        assertEquals(1L, customer.getCustomerId());
    }
}