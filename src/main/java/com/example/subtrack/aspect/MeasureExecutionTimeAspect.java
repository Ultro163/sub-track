package com.example.subtrack.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * Аспект для измерения времени выполнения методов, помеченных аннотацией {@code @MeasureExecutionTime}.
 * Логирует время выполнения метода в минутах и секундах.
 */
@Slf4j
@Aspect
@Component
public class MeasureExecutionTimeAspect {

    @Around("@annotation(com.example.subtrack.util.annotations.MeasureExecutionTime)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();

        Object result = joinPoint.proceed();

        long elapsedTime = System.currentTimeMillis() - start;

        long minutes = TimeUnit.MILLISECONDS.toMinutes(elapsedTime);
        long seconds = TimeUnit.MILLISECONDS.toSeconds(elapsedTime) % 60;


        String formattedTime = String.format("%d мин %d сек", minutes, seconds);
        log.info("Метод {} выполнен за {}", joinPoint.getSignature(), formattedTime);

        return result;
    }
}