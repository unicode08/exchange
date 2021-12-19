package com.openpayd.exchange.integration.configuration.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;

@Configuration
@Aspect
@Slf4j
public class ExceptionLoggerAspect {

    @AfterThrowing(pointcut = "com.openpayd.exchange.integration.configuration.aspect.CommonJoinPointConfig.applicationExecution()", throwing = "ex")
    public void logApplicationException(Exception ex) {
        log.error("Exception handled " + ex.getMessage());
    }
}
