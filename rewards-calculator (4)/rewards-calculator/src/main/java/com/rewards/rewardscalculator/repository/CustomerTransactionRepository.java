package com.rewards.rewardscalculator.repository;

import com.rewards.rewardscalculator.model.CustomerTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerTransactionRepository extends JpaRepository<CustomerTransaction, Long> {
    @Query("SELECT T FROM CustomerTransaction T WHERE T.customer.customerId = :customerId and year(T.transactionDate) = :year AND month(T.transactionDate) = :month")
    List<CustomerTransaction> findAllByTransactionDate(@Param("customerId") Long customerId, @Param("year") Integer year, @Param("month") Integer month);
}
