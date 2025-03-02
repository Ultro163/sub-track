package com.example.subtrack.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * Аспект для логирования выполнения методов сервисных классов.
 * Отслеживает вызовы методов в сервисах пользователей, подписок и подписок пользователей.
 */
@Slf4j
@Aspect
@Component
public class LoggingAspect {

    @Around("execution(* com.example.subtrack.service.user.*.*(..))")
    public Object logUserService(ProceedingJoinPoint joinPoint) throws Throwable {
        return logExecution(joinPoint, "UserServiceImpl");
    }

    @Around("execution(* com.example.subtrack.service.subscription.*.*(..))")
    public Object logSubscriptionService(ProceedingJoinPoint joinPoint) throws Throwable {
        return logExecution(joinPoint, "SubscriptionServiceImpl");
    }

    @Around("execution(* com.example.subtrack.service.usersubscription.*.*(..))")
    public Object logUserSubscriptionService(ProceedingJoinPoint joinPoint) throws Throwable {
        return logExecution(joinPoint, "UserSubscriptionServiceImpl");
    }

    private Object logExecution(ProceedingJoinPoint joinPoint, String serviceName) throws Throwable {
        String operation = defineOperation(joinPoint.getSignature().getName());
        log.info("[{}] {} началось. Аргументы: {}", serviceName, operation, joinPoint.getArgs());

        try {
            Object result = joinPoint.proceed();
            log.info("[{}] {} успешно завершено. Результат: {}", serviceName, operation, result);
            return result;
        } catch (Exception ex) {
            log.error("[{}] Ошибка при {}. Исключение: {}", serviceName, operation, ex.getMessage(), ex);
            throw ex;
        }
    }

    private String defineOperation(String methodName) {
        if (methodName.startsWith("save")) return "Сохранение";
        if (methodName.startsWith("update")) return "Обновление";
        if (methodName.startsWith("delete")) return "Удаление";
        if (methodName.startsWith("get")) return "Получение данных";
        return "Вызов метода " + methodName;
    }
}