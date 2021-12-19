package com.openpayd.exchange.integration.configuration.aspect;

import org.aspectj.lang.annotation.Pointcut;

public class CommonJoinPointConfig {

    @Pointcut("execution(* com.openpayd.exchange..*.*(..))")
    public void applicationExecution() {

    }
}
