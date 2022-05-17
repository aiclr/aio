package org.bougainvilleas.spring.aop.xml;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * 通知类
 * @author renqiankun
 */
public class UserProxy {

    /**
     * 前置
     */
    public void before(){
        System.out.println("UserDaoProxy before---");
    }

    /**
     * 后置
     */
    public void afterReturning(){
        System.out.println("afterReturning---");
    }

    /**
     * 环绕
     */
    public void around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("环绕通知之前---");
        proceedingJoinPoint.proceed();
        System.out.println("环绕通知之后---");
    }

    /**
     * 异常
     */
    public void afterThrowing(Throwable e){
        System.out.println("afterThrowing---");
    }

    /**
     * 最终通知 类似finally
     */
    public void after(){
        System.out.println("after---");
    }


}
