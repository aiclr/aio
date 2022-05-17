package org.bougainvilleas.spring.aop.annotation;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 通知类
 * @author renqiankun
 */
@Component
@Aspect //生产代理对象
@Order(1)
public class UserProxy {

    /**
     * 抽取公共切入点
     */
    @Pointcut(value = "execution(* org.bougainvilleas.spring.aop.annotation.UserDao.add(..))")
    public void pointdemo(){

    }

    /**
     * 前置
     */
    @Before(value = "pointdemo()")
    public void before(){
        System.out.println("UserDaoProxy before---");
    }

    /**
     * 后置
     */
    @AfterReturning(value = "execution(* org.bougainvilleas.spring.aop.annotation.UserDao.*(..))")
    public void afterReturning(){
        System.out.println("afterReturning---");
    }

    /**
     * 环绕
     */
    @Around(value = "pointdemo()")
    public void around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("环绕通知之前---");
        proceedingJoinPoint.proceed();
        System.out.println("环绕通知之后---");
    }

    /**
     * 异常
     */
    @AfterThrowing(value = "execution(* org.bougainvilleas.spring.aop.annotation.UserDao.update(..))")
    public void afterThrowing(){
        System.out.println("afterThrowing---");
    }

    /**
     * 最终通知 类似finally
     */
    @After(value = "execution(* org.bougainvilleas.spring.aop.annotation.UserDao.*(..))")
    public void after(){
        System.out.println("after---");
    }


}
