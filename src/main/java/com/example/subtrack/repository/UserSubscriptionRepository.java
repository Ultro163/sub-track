package com.example.subtrack.repository;

import com.example.subtrack.entity.UserSubscription;
import com.example.subtrack.entity.UserSubscriptionId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface UserSubscriptionRepository extends JpaRepository<UserSubscription, UserSubscriptionId> {
    @Modifying
    @Query("update UserSubscription us set us.isActive = false where us.user.id = :userId")
    void deactiveSubscriptions(@Param("userId") UUID userId);

    @Modifying
    @Query("""
            update UserSubscription us
            set us.isActive = false
            where us.user.id = :userId
            and us.subscription.id = :subscriptionId
            """)
    void deactiveUserSubscription(@Param("userId") UUID userId, @Param("subscriptionId") UUID subscriptionId);

    @Query("""
            SELECT COUNT(us) > 0
            FROM UserSubscription us
            WHERE us.user.id = :userId
            AND us.subscription.id = :subscriptionId
            """)
    boolean existsByUserIdAndSubscriptionId(@Param("userId") UUID userId, @Param("subscriptionId") UUID subscriptionId);

}