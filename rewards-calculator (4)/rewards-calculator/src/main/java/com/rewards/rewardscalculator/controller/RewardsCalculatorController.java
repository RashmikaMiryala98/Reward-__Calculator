package com.rewards.rewardscalculator.controller;

import com.rewards.rewardscalculator.dto.RewardsDto;
import com.rewards.rewardscalculator.dto.Transaction;
import com.rewards.rewardscalculator.model.Customer;
import com.rewards.rewardscalculator.service.RewardsCalculatorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * REST controller for customer and rewards-related operations.
 * Exposes endpoints for calculating rewards for customers.
 */
@RestController
@RequestMapping("api")
@Validated
public class RewardsCalculatorController {

    private final RewardsCalculatorService rewardsCalculatorService;

    public RewardsCalculatorController(RewardsCalculatorService rewardsCalculatorService) {
        this.rewardsCalculatorService = rewardsCalculatorService;
    }

    /**
     * Returns the rewards for a given customer
     *
     * @param customerId     customer
     * @param numberOfMonths optional parameter to mention the number of months for which rewards need to be calculated
     * @return Rewards data
     */

    @Operation(summary = "Gets the rewards data as JSON for the given customer",
            description = "Returns the rewards data as JSON for the given customer and for last three months.",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Rewards retrieved successfully",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = RewardsDto.class)))
            })
    @GetMapping("/{customerId}")
    public ResponseEntity<RewardsDto> getRewardsForCustomerId(
            @PathVariable Long customerId,
            @RequestParam(value = "numberOfMonths", required = false) Integer numberOfMonths) {
        RewardsDto rewards = rewardsCalculatorService.calculateRewardsForCustomerId(customerId, numberOfMonths);
        return new ResponseEntity<>(rewards, HttpStatus.OK);
    }

    /**
     * End point for creating a transaction for a customer
     *
     * @param transaction customer transaction input
     * @return status of the operation
     */

    @Operation(summary = "Creates a transaction entry.",
            description = "Creates a transaction entry with the given input.",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Rewards retrieved successfully",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = RewardsDto.class)))
            })
    @PostMapping("/transaction")
    public ResponseEntity<String> createTransaction(@Valid @RequestBody Transaction transaction) {
        rewardsCalculatorService.createTransaction(transaction);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @Operation(summary = "Creates new Customer",
            description = "Creates a new Customer entry with the given input",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Customer created successfully",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = RewardsDto.class)))
            })
    @PostMapping("/customer")
    public ResponseEntity<Customer> addCustomer(@Valid @RequestBody Customer Customer) {
        Customer customer = rewardsCalculatorService.addCustomer(Customer);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }


    /**
     * Endpoint for calculating rewards for all customers.
     *
     * @return A map of customer IDs to their corresponding rewards data.
     */
    @Operation(summary = "Calculates Rewards for all customers",
            description = "Calculates Rewards for all customers present in the system",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Calculates Rewards calculated successfully",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Map.class)))
            })
    @GetMapping("/calculate-all")
    public ResponseEntity<Map<Long, RewardsDto>> calculateRewardsForAllCustomers() {
        // Retrieve all customer IDs
        List<Long> customerIds = rewardsCalculatorService.getAllCustomerIds();

        // Calculate rewards for each customer
        Map<Long, RewardsDto> rewardsForAllCustomers = customerIds.stream()
                .collect(Collectors.toMap(
                        customerId -> customerId,
                        customerId -> rewardsCalculatorService.calculateRewardsForCustomerId(customerId, null)
                ));

        return new ResponseEntity<>(rewardsForAllCustomers, HttpStatus.OK);
    }

}
