package com.rewards.rewardscalculator.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class RewardsCalculatorAppError {
    private int statusCode;
    private Date timestamp;
    private String message;
    private String description;
}
