package com.example.subtrack.controller;

import com.example.subtrack.dto.ResponseSubscriptionDto;
import com.example.subtrack.dto.mappers.SubscriptionMapper;
import com.example.subtrack.service.subscription.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Контроллер для управления подписками.
 * Предоставляет эндпоинты для получения информации о подписках.
 */
@RestController
@RequestMapping("/api/v1/subscriptions")
@RequiredArgsConstructor
public class SubscriptionController {
    private final SubscriptionService subscriptionService;
    private final SubscriptionMapper subscriptionMapper;

    /**
     * Получает топ-3 подписки.
     *
     * @return список из трех самых популярных подписок в формате {@link ResponseSubscriptionDto}
     */
    @GetMapping("/top")
    public List<ResponseSubscriptionDto> getTopThreeSubscriptions() {
        return subscriptionService.getTopThreeSubscriptions().stream()
                .map(subscriptionMapper::toResponseSubscriptionDto).toList();
    }
}