package com.rewards.rewardscalculator.util;

import com.rewards.rewardscalculator.model.CustomerTransaction;

import java.util.List;


public class RewardCalculatorUtil {

    public static final int FIRST_LEVEL_SPENT = 50;
    public static final int SECOND_LEVEL_SPENT = 100;
    public static final int FIRST_LEVEL_SPENT_POINT = 1;
    public static final int SECOND_LEVEL_SPENT_POINT = 2;


    public Long calculateRewards(List<CustomerTransaction> transactions) {
        return transactions.stream().map(this::computeRewardsOfTransaction).mapToLong(r -> r).sum();
    }

    private Long computeRewardsOfTransaction(CustomerTransaction txn) {
        double transactionAmount = txn.getTransactionAmount();
        if (transactionAmount > FIRST_LEVEL_SPENT && transactionAmount < SECOND_LEVEL_SPENT)
            return Math.round(transactionAmount - FIRST_LEVEL_SPENT) * FIRST_LEVEL_SPENT_POINT;
        else if (transactionAmount > SECOND_LEVEL_SPENT)
            return Math.round(transactionAmount - SECOND_LEVEL_SPENT) * SECOND_LEVEL_SPENT_POINT
                    + (SECOND_LEVEL_SPENT - FIRST_LEVEL_SPENT) * FIRST_LEVEL_SPENT_POINT;
        else
            return 0L;
    }
}
