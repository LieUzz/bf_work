package com.example.shoppingapp.AOP;

import com.example.shoppingapp.entity.Order;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Aspect
@Component
@Slf4j
public class LoggingAspect {

    private final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Around("com.example.shoppingapp.AOP.PointCuts.inControllerLayer()")
    public Object logEndpointPerformance(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
        long startTime = System.currentTimeMillis();

        Object result = proceedingJoinPoint.proceed();

        long duration = System.currentTimeMillis() - startTime;

        logger.info("This endpoint took " + duration + " ms");

        return result;
    }

//    @Before("com.example.shoppingapp.AOP.PointCuts.inControllerLayer()")
//    public void logStartTime(){
//        logger.info("From LoggingAspect.logStartTime in controller: " + System.currentTimeMillis()); // advice
//    }
//
//    @After("com.example.shoppingapp.AOP.PointCuts.inService()")
//    public void logEndTime(JoinPoint joinPoint){
//        logger.info("From LoggingAspect.logEndTime in service: " + System.currentTimeMillis()  + ": " + joinPoint.getSignature());
//    }
//
//    @AfterReturning(value = "com.example.shoppingapp.AOP.PointCuts.inDAOLayer()", returning = "res")
//    public void logReturnObject(JoinPoint joinPoint, Object res){
//        logger.info("From LoggingAspect.logReturnObject in DAO: " + res + ": " + joinPoint.getSignature());
//    }
//
//    @AfterThrowing(value = "com.example.shoppingapp.AOP.PointCuts.inControllerLayer()", throwing = "ex")
//    public void logThrownException(JoinPoint joinPoint, Throwable ex){
//        logger.error("From LoggingAspect.logThrownException in controller: " + ex.getMessage() + ": " + joinPoint.getSignature());
//    }

    @Around("com.example.shoppingapp.AOP.PointCuts.inDAOLayer()")
    public Order logStartAndEndTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
        // before
//        logger.info("From LoggingAspect.logStartAndEndTime: " + proceedingJoinPoint.getSignature());
//        logger.info("Order Modify Start time: " + System.currentTimeMillis());

        //Invoke the actual object
        Order demo = (Order) proceedingJoinPoint.proceed();

        // after
        logger.info("Order Modify Time: " + System.currentTimeMillis());
        return demo;
    }
}

