package org.bougainvilleas.spring.aop.annotation;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 通知类 优先级
 * @author renqiankun
 */
@Component
@Aspect //生产代理对象
@Order(2)
public class UserProxy2 {

    /**
     * 抽取公共切入点
     */
    @Pointcut(value = "execution(* org.bougainvilleas.spring.aop.annotation.UserDao.add(..))&& args(age)")
    public void pointdemo(int age){

    }

    /**
     * 前置
     */
    @Before(value = "pointdemo(age)")
    public void before(int age){

        System.out.println("UserDaoProxy2 before---"+age);
    }

}
