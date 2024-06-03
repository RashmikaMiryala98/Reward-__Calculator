package com.rewards.rewardscalculator.repository;

import com.rewards.rewardscalculator.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query("SELECT c.customerId FROM Customer c")
    List<Long> getAllCustomerIds();
}
