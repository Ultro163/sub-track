package com.example.subtrack.repository;

import com.example.subtrack.entity.Subscription;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface SubscriptionRepository extends JpaRepository<Subscription, UUID> {

    @Query("""
                SELECT s FROM Subscription s
                JOIN UserSubscription us ON s.id = us.subscription.id
                GROUP BY s.id
                ORDER BY COUNT(us.user.id) DESC
            """)
    List<Subscription> findTopThreeSubscriptions(Pageable pageable);

}