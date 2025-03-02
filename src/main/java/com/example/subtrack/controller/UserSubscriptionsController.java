package com.example.subtrack.controller;

import com.example.subtrack.dto.NewUserSubscriptionRequest;
import com.example.subtrack.dto.ResponseSubscriptionDto;
import com.example.subtrack.dto.mappers.SubscriptionMapper;
import com.example.subtrack.service.usersubscription.UserSubscriptionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

/**
 * Контроллер для управления подписками пользователей.
 * Обрабатывает запросы, связанные с добавлением, получением и удалением подписок пользователя.
 */
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserSubscriptionsController {
    private final UserSubscriptionService userSubscriptionService;
    private final SubscriptionMapper subscriptionMapper;

    /**
     * Добавляет подписку пользователю.
     *
     * @param userId                     идентификатор пользователя.
     * @param newUserSubscriptionRequest объект, содержащий данные новой подписки.
     */
    @PostMapping("/{userId}/subscriptions")
    public void addUserSubscription(@PathVariable UUID userId,
                                    @RequestBody @Valid NewUserSubscriptionRequest newUserSubscriptionRequest) {
        userSubscriptionService.addUserSubscription(userId, newUserSubscriptionRequest);
    }

    /**
     * Получает список подписок пользователя.
     *
     * @param userId идентификатор пользователя.
     * @return список подписок пользователя в формате {@link ResponseSubscriptionDto}.
     */
    @GetMapping("/{userId}/subscriptions")
    public List<ResponseSubscriptionDto> getUserSubscriptions(@PathVariable UUID userId) {
        return userSubscriptionService.getUserSubscriptions(userId).stream()
                .map(subscriptionMapper::toResponseSubscriptionDto).toList();
    }

    /**
     * Удаляет подписку пользователя.
     *
     * @param userId идентификатор пользователя.
     * @param subId  идентификатор подписки.
     */
    @DeleteMapping("/{userId}/subscriptions/{subId}")
    public void deleteUserSubscription(@PathVariable UUID userId, @PathVariable UUID subId) {
        userSubscriptionService.deleteUserSubscription(userId, subId);
    }
}