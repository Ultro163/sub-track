package com.example.subtrack.service.subscription;

import com.example.subtrack.dto.SubscriptionDto;
import com.example.subtrack.dto.mappers.SubscriptionMapper;
import com.example.subtrack.repository.SubscriptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Реализация сервиса для работы с подписками пользователей.
 * <p>
 * Предоставляет методы для получения информации о подписках.
 */
@RequiredArgsConstructor
@Service
@Transactional
public class SubscriptionServiceImpl implements SubscriptionService {
    private final SubscriptionRepository subscriptionRepository;
    private final SubscriptionMapper subscriptionMapper;

    /**
     * Получает три самых популярных подписки.
     *
     * @return список из трех {@link SubscriptionDto}, представляющих топовые подписки.
     */
    @Override
    public List<SubscriptionDto> getTopThreeSubscriptions() {
        return subscriptionRepository.findTopThreeSubscriptions(PageRequest.of(0, 3)).stream()
                .map(subscriptionMapper::toSubscriptionDto).toList();
    }
}