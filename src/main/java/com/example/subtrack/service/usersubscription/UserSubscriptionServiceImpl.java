package com.example.subtrack.service.usersubscription;

import com.example.subtrack.dto.NewUserSubscriptionRequest;
import com.example.subtrack.dto.SubscriptionDto;
import com.example.subtrack.dto.mappers.SubscriptionMapper;
import com.example.subtrack.entity.Subscription;
import com.example.subtrack.entity.User;
import com.example.subtrack.entity.UserSubscription;
import com.example.subtrack.entity.UserSubscriptionId;
import com.example.subtrack.error.exception.EntityNotFoundException;
import com.example.subtrack.error.exception.ValidationException;
import com.example.subtrack.repository.SubscriptionRepository;
import com.example.subtrack.repository.UserRepository;
import com.example.subtrack.repository.UserSubscriptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Сервис для управления подписками пользователей.
 * <p>
 * Позволяет добавлять, получать и удалять подписки пользователей.
 */
@RequiredArgsConstructor
@Service
@Transactional
public class UserSubscriptionServiceImpl implements UserSubscriptionService {
    private final UserSubscriptionRepository userSubscriptionRepository;
    private final SubscriptionRepository subscriptionRepository;
    private final UserRepository userRepository;
    private final SubscriptionMapper subscriptionMapper;

    /**
     * Добавляет подписку пользователю.
     * <p>
     * Если у пользователя уже есть подписка на этот сервис, проверяется её статус:
     * <ul>
     *     <li>Если подписка активна — выбрасывается исключение {@link ValidationException}.</li>
     *     <li>Если подписка неактивна — подписка активируется, а срок обновляется.</li>
     * </ul>
     *
     * @param userId                     UUID пользователя.
     * @param newUserSubscriptionRequest данные о подписке, включая ID подписки и длительность.
     * @throws EntityNotFoundException если пользователь или подписка не найдены.
     * @throws ValidationException     если подписка уже активна.
     */
    @Override
    public void addUserSubscription(UUID userId, NewUserSubscriptionRequest newUserSubscriptionRequest) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> userNotFound(userId));
        Optional<UserSubscription> existingSubscription = user.getUserSubscriptions().stream()
                .filter(us -> us.getSubscription().getId().equals(newUserSubscriptionRequest.getSubscriptionId()))
                .findFirst();

        if (existingSubscription.isPresent()) {
            boolean isActive = existingSubscription.get().getIsActive();
            if (isActive) {
                throw new ValidationException("User subscription already exists and is active");
            } else {
                UserSubscription subscription = existingSubscription.get();
                subscription.setIsActive(true);
                subscription.setStartDate(LocalDate.now());
                subscription.setEndDate(LocalDate.now().plusMonths(newUserSubscriptionRequest.getDurationMonths()));
                return;
            }

        }

        Subscription subscription = subscriptionRepository.findById(newUserSubscriptionRequest.getSubscriptionId())
                .orElseThrow(() -> new EntityNotFoundException("Subscription " + newUserSubscriptionRequest.getSubscriptionId() +
                        " not found"));

        UserSubscription userSubscription = new UserSubscription();
        userSubscription.setId(new UserSubscriptionId());
        userSubscription.setUser(user);
        userSubscription.setSubscription(subscription);
        userSubscription.setStartDate(LocalDate.now());
        userSubscription.setEndDate(LocalDate.now().plusMonths(newUserSubscriptionRequest.getDurationMonths()));
        userSubscription.setIsActive(Boolean.TRUE);

        userSubscriptionRepository.save(userSubscription);
    }

    /**
     * Получает список активных подписок пользователя.
     *
     * @param userId UUID пользователя.
     * @return список подписок пользователя в виде {@link SubscriptionDto}.
     * @throws EntityNotFoundException если пользователь не найден.
     */
    @Override
    public List<SubscriptionDto> getUserSubscriptions(UUID userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> userNotFound(userId));
        List<UUID> subscriptionIds = user.getUserSubscriptions().stream()
                .filter(UserSubscription::getIsActive)
                .map(us -> us.getSubscription().getId()).toList();
        List<Subscription> subscriptions = subscriptionRepository.findAllById(subscriptionIds);
        return subscriptions.stream().map(subscriptionMapper::toSubscriptionDto).toList();
    }

    /**
     * Удаляет подписку пользователя (деактивирует её).
     *
     * @param userId         UUID пользователя.
     * @param subscriptionId UUID подписки.
     * @throws EntityNotFoundException если подписка не найдена у пользователя.
     */
    @Override
    public void deleteUserSubscription(UUID userId, UUID subscriptionId) {
        boolean subscriptionExists = userSubscriptionRepository.existsByUserIdAndSubscriptionId(userId, subscriptionId);
        if (!subscriptionExists) {
            throw new EntityNotFoundException("Subscription with ID=" + subscriptionId +
                    " not found for user with ID=" + userId);
        }
        userSubscriptionRepository.deactiveUserSubscription(userId, subscriptionId);
    }

    /**
     * Генерирует исключение {@link EntityNotFoundException}, если пользователь не найден.
     *
     * @param userId UUID пользователя.
     * @throws EntityNotFoundException всегда.
     */
    private EntityNotFoundException userNotFound(UUID userId) {
        throw new EntityNotFoundException("User with id " + userId + " not found");
    }
}