package org.bougainvilleas.spring.aop.annotation.AOPAnnotation;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author renqiankun
 * 2022-03-31 14:53:02 星期四
 */
@Component
@Aspect
@Order(1)
public class PKAspect
{
    /**
     * 切入点 带有 @PK 注解的方法
     */
    @Pointcut(value = "@annotation(org.bougainvilleas.spring.aop.annotation.AOPAnnotation.PK)")
    public void yunPKPoint()
    {
    }

    /**
     * 新增数据时的 主键表 `t_sys_snogeneral` opertype=3000
     */
    @Around(value = "yunPKPoint()")
    public void around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable
    {
        //获取目标类
        Class<?> targetClass = proceedingJoinPoint.getTarget().getClass();
        System.err.println(targetClass);
        MethodSignature methodSignature = (MethodSignature)proceedingJoinPoint.getSignature();
        System.err.println(methodSignature);
        Method targetMethod = targetClass.getDeclaredMethod(methodSignature.getName(), methodSignature.getParameterTypes());
        System.err.println(targetMethod);
        PK pk;
        pk = targetMethod.getAnnotation(PK.class);
        int opertype = pk.value();
        System.err.println(opertype);
    }

}
