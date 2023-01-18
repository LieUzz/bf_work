package com.example.shoppingapp.AOP;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class PointCuts {

    @Pointcut("within(com.example.shoppingapp.controller.*)")
    public void inControllerLayer(){}

    @Pointcut("bean(*Service)")
    public void inService(){}

    @Pointcut("execution(* com.example.shoppingapp.dao.OrderDao.*Order(..))")
//    @Pointcut("execution(* *(..))")
    public void inDAOLayer(){}

}

