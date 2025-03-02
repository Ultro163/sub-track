package com.example.subtrack.service.usersubscription;

import com.example.subtrack.dto.NewUserSubscriptionRequest;
import com.example.subtrack.dto.SubscriptionDto;

import java.util.List;
import java.util.UUID;

public interface UserSubscriptionService {

    void addUserSubscription(UUID userId, NewUserSubscriptionRequest newUserSubscriptionRequest);

    List<SubscriptionDto> getUserSubscriptions(UUID userId);

    void deleteUserSubscription(UUID userId, UUID subscriptionId);
}