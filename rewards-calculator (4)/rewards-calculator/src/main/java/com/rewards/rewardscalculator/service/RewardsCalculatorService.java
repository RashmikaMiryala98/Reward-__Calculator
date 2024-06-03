package com.rewards.rewardscalculator.service;


import com.rewards.rewardscalculator.dto.RewardsDto;
import com.rewards.rewardscalculator.dto.Transaction;
import com.rewards.rewardscalculator.exception.RewardsApplicationBadRequestGenericError;
import com.rewards.rewardscalculator.model.Customer;
import com.rewards.rewardscalculator.model.CustomerTransaction;
import com.rewards.rewardscalculator.repository.CustomerRepository;
import com.rewards.rewardscalculator.repository.CustomerTransactionRepository;
import com.rewards.rewardscalculator.util.RewardCalculatorUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Service responsible for handling the business logic related to rewards calculation and customer transactions.
 * This class interacts with the repositories to perform CRUD operations and calculations.
 */
@Service
public class RewardsCalculatorService {

    private final CustomerTransactionRepository transactionRepository;
    private final CustomerRepository customerRepository;

    @Value("${rewards.calculation.months}")
    private int rewardsCalculationNumMonths;

    public RewardsCalculatorService(CustomerTransactionRepository transactionRepository,
                                    CustomerRepository customerRepository) {
        this.transactionRepository = transactionRepository;
        this.customerRepository = customerRepository;
    }


    /**
     * Adds a new customer and saves it to the repository.
     * Also checks if the given customer is already present.
     *
     * @param customer customer to be added.
     * @return saved customer.
     */
    public Customer addCustomer(Customer customer) {
        try {
            return customerRepository.save(customer);
        } catch (DataIntegrityViolationException ex) {
            throw new RewardsApplicationBadRequestGenericError("Customer name already exists: " + customer.getCustomerName());
        }
    }


    /**
     * Calculates rewards for a given customer ID.
     *
     * @param customerId ID of the customer to calculate rewards.
     * @return calculated rewards data.
     */
    public RewardsDto calculateRewardsForCustomerId(Long customerId, Integer numMonths) {

        if (!customerRepository.existsById(customerId)) {
            throw new RewardsApplicationBadRequestGenericError("Customer with ID " + customerId + " not found");
        }


        if (numMonths == null || numMonths <= 0) {
            numMonths = rewardsCalculationNumMonths;
        }
        LocalDateTime now = LocalDateTime.now();
        RewardCalculatorUtil rewardCalculatorUtil = new RewardCalculatorUtil();
        Map<String, Long> monthlyRewards = new HashMap<>();
        Long totalRewardsPoints = 0L;

        for (int i = 0; i < numMonths; i++) {
            LocalDateTime targetMonth = now.minusMonths(i);
            List<CustomerTransaction> transactions = transactionRepository.findAllByTransactionDate(customerId, targetMonth.getYear(), targetMonth.getMonthValue());
            Long monthlyRewardPoints = rewardCalculatorUtil.calculateRewards(transactions);
            totalRewardsPoints += monthlyRewardPoints;
            monthlyRewards.put(targetMonth.getMonth().name(), monthlyRewardPoints);
        }

        return new RewardsDto(customerId, monthlyRewards, totalRewardsPoints);
    }


    /**
     * Creates a new transaction and saves it to the repository.
     * checks if customer is present before creating a transaction
     *
     * @param transaction The transaction to be created.
     */
    public void createTransaction(Transaction transaction) {
        LocalDateTime now = LocalDateTime.now();
        Timestamp timestamp = Timestamp.valueOf(now);

        // Retrieve the customer by ID
        Customer customer = customerRepository.findById(transaction.getCustomerId())
                .orElseThrow(() -> new RewardsApplicationBadRequestGenericError("Customer not found"));

        CustomerTransaction customerTransaction = new CustomerTransaction();
        customerTransaction.setCustomer(customer);
        customerTransaction.setTransactionAmount(transaction.getTransactionAmount());
        customerTransaction.setTransactionDate(timestamp);
        transactionRepository.save(customerTransaction);
    }

    public List<Long> getAllCustomerIds() {
        return customerRepository.getAllCustomerIds();
    }
}
