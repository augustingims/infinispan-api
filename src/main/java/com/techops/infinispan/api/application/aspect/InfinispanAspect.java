package com.techops.infinispan.api.application.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;

@Aspect
@Component
@Slf4j
public class InfinispanAspect {

    public static final int NANOS_TO_MICROS = 1000;
    public static final int MICROS_TO_MILLIS = 1000;

    @Pointcut(value = "execution(* org.infinispan.spring.remote.session.InfinispanRemoteSessionRepository.*(..)) ")
    public void logInfinispanOperations() {
    }
    @Pointcut(value = "execution(* org.infinispan.spring.common.session.AbstractInfinispanSessionRepository.*(..)) ")
    public void logInterfaceOperations() {
    }

    @Around(value = "logInfinispanOperations() || logInterfaceOperations()")
    public Object timeCacheCalls(ProceedingJoinPoint joinPoint) throws Throwable {
        String targetMethod = joinPoint.getSignature().getName();

        Instant startTime = Instant.now();

        Object retVal = joinPoint.proceed();

        Instant endTime = Instant.now();

        Duration duration = Duration.between(startTime, endTime);
        log.info("Method {} with args {} took {}μs", targetMethod, joinPoint.getArgs(), duration.getNano() / NANOS_TO_MICROS);
        return retVal;
    }

}
