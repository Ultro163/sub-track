package com.example.subtrack.service.subscription;

import com.example.subtrack.dto.SubscriptionDto;

import java.util.List;

public interface SubscriptionService {

    List<SubscriptionDto> getTopThreeSubscriptions();
}