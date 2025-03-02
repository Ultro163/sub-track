package com.example.subtrack.scheduling;

import com.example.subtrack.util.annotations.MeasureExecutionTime;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Планировщик для автоматической обработки подписок пользователей.
 * Этот компонент выполняет задачу по деактивации истекших подписок.
 * <p>
 * Выполнение задачи происходит только при включенной настройке {@code app.scheduler.enabled=true}.
 */
@Slf4j
@Component
@ConditionalOnProperty(name = "app.scheduler.enabled", havingValue = "true")
public class SubscriptionScheduler {

    @PersistenceContext
    private EntityManager entityManager;

    public SubscriptionScheduler() {
        log.info("SubscriptionScheduler created");
    }

    /**
     * Планируемая задача по деактивации подписок, срок которых истек.
     * <p>
     * Выполняется ежедневно в 00:00 (по расписанию cron: {@code 0 0 0 * * *}).
     * <p>
     * Метод обернут в {@link Transactional} для обеспечения атомарности обновления данных.
     * Аннотация {@link MeasureExecutionTime} используется для логирования времени выполнения метода.
     */
    @MeasureExecutionTime
    @Transactional
    @Scheduled(cron = "0 0 0 * * *")
    public void deactivateExpiredSubscriptions() {
        entityManager.createQuery(
                "UPDATE UserSubscription us SET us.isActive = false WHERE us.endDate < CURRENT_DATE"
        ).executeUpdate();

        log.info("Проверка подписок завершена: истёкшие подписки деактивированы.");
    }
}