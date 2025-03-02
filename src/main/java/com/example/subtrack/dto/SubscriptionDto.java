package com.example.subtrack.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * DTO for {@link com.example.subtrack.entity.Subscription}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubscriptionDto {
    private UUID id;
    private String name;
    private String description;
    private BigDecimal price;
}