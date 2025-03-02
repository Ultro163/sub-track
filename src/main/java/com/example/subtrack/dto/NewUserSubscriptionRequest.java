package com.example.subtrack.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * DTO for {@link com.example.subtrack.entity.Subscription}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewUserSubscriptionRequest {
    @NotNull
    private UUID subscriptionId;
    @NotNull
    @Positive
    private Integer durationMonths;
}