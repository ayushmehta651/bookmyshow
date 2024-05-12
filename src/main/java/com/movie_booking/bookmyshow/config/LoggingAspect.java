package com.movie_booking.bookmyshow.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    // Aspect-oriented programming
    // modularization of cross-cutting concern
    // separate concerns like logging, security and transactions from your core application logic

    // Advice
    // pointcut
    // Join point

//    @Before("execution(* com.movie_booking.bookmyshow.controller.*.*(..))")
//    public void logBeforeControllerMethods() {
//        System.out.println("Before Advice ");
//    }
//
//    @AfterReturning(pointcut = "execution(* com.movie_booking.bookmyshow.controller.*.*(..))", returning = "result")
//    public void logAfterControllerMethods(Object result) {
//        System.out.println("Before Advice " + result);
//    }
//
//    @AfterThrowing(pointcut = "execution(* com.movie_booking.bookmyshow.controller.*.*(..))", throwing = "exception")
//    public void logAfterThrowingControllerMethods(Exception exception) {
//        System.out.println("Before Advice " + exception);
//    }

    @Around("execution(* com.movie_booking.bookmyshow.controller.*.*(..))")
    public Object logAroundControllerMethods(ProceedingJoinPoint joinPoint)  throws Throwable{
        long start = System.currentTimeMillis();

        Object proceed = joinPoint.proceed();

        long executionTime  = System.currentTimeMillis() - start;

        System.out.println(joinPoint.getSignature() + " executed in " + executionTime + "ms");

        return proceed;
    }

}
