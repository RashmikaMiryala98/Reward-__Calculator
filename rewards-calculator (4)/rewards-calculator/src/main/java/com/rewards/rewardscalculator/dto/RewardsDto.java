package com.rewards.rewardscalculator.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RewardsDto {
    private Long customerId;
    private Map<String, Long> monthlyRewards;
    private Long totalRewards;
}
