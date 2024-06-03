package com.rewards.rewardscalculator.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


/**
 * Entity class representing a customer.
 * Contains customer information and is mapped to the CUSTOMER table in the database.
 */
@Getter
@Setter
@Entity
@Table(name = "CUSTOMER")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CUSTOMER_ID")
    @Schema(hidden = true)
    private Long customerId;

    @Column(name = "CUSTOMER_NAME", unique = true)
    @NotBlank(message = "Customer name must not be blank")
    @Size(min = 3, message = "Customer name must be at least 3 characters long")
    private String customerName;
}
