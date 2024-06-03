package com.rewards.rewardscalculator.util;

import com.rewards.rewardscalculator.model.Customer;
import com.rewards.rewardscalculator.model.CustomerTransaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RewardCalculatorUtilTest {

    private RewardCalculatorUtil rewardCalculatorUtil;

    @BeforeEach
    public void setUp() {
        rewardCalculatorUtil = new RewardCalculatorUtil();
    }

    @Test
    public void testCalculateRewards_NoTransactions() {
        List<CustomerTransaction> transactions = new ArrayList<>();
        assertEquals(0L, rewardCalculatorUtil.calculateRewards(transactions));
    }

    @Test
    public void testCalculateRewards_OneTransactionFirstLevel() {
        List<CustomerTransaction> transactions = new ArrayList<>();
        Customer c1 = new Customer();
        c1.setCustomerId(1L);
        c1.setCustomerName("John Wick");
        transactions.add(
                new CustomerTransaction(1L, c1, new Timestamp(System.currentTimeMillis()),
                        60.0)); // Transaction amount exceeds FIRST_LEVEL_SPENT
        assertEquals(10L, rewardCalculatorUtil.calculateRewards(transactions));
    }

    @Test
    public void testCalculateRewards_OneTransactionSecondLevel() {
        List<CustomerTransaction> transactions = new ArrayList<>();

        Customer c1 = new Customer();
        c1.setCustomerId(1L);
        c1.setCustomerName("John Wick");
        transactions.add(new CustomerTransaction(1L, c1, new Timestamp(System.currentTimeMillis()),
                120.0)); // Transaction amount exceeds SECOND_LEVEL_SPENT
        assertEquals(90L, rewardCalculatorUtil.calculateRewards(transactions));
    }

    @Test
    public void testCalculateRewards_MultipleTransactions() {

        Customer c1 = new Customer();
        c1.setCustomerId(1L);
        c1.setCustomerName("John Wick");

        List<CustomerTransaction> transactions = new ArrayList<>();
        transactions.add(new CustomerTransaction(1L, c1, new Timestamp(System.currentTimeMillis()),
                30.0)); // Below FIRST_LEVEL_SPENT, no rewards
        transactions.add(new CustomerTransaction(1L, c1, new Timestamp(System.currentTimeMillis()),
                60.0)); // Transaction amount exceeds FIRST_LEVEL_SPENT
        transactions.add(new CustomerTransaction(1L, c1, new Timestamp(System.currentTimeMillis()),
                110.0)); // Transaction amount exceeds SECOND_LEVEL_SPENT
        assertEquals(80L, rewardCalculatorUtil.calculateRewards(transactions));
    }
}
