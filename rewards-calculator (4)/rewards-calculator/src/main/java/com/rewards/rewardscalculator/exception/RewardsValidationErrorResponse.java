package com.rewards.rewardscalculator.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
public class RewardsValidationErrorResponse {

    private String message;
    private List<String> errors;
}
