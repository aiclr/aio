package org.bougainvilleas.spring.aop.annotation.AOPAnnotation;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
@Aspect
@Order(2)
public class TypeAopAspect {

  /**
   * 切入点 带有 @PK 注解的方法
   */
  @Pointcut(value = "@target(org.bougainvilleas.spring.aop.annotation.AOPAnnotation.TypeAOP) && args(vo)")
  public <T extends Number> void yunPKPoint(T vo)
  {
  }

  /**
   * 新增数据时的 主键表 `t_sys_snogeneral` opertype=3000
   */
  @Around(value = "yunPKPoint(vo)")
  public <T extends Number>void around(ProceedingJoinPoint proceedingJoinPoint,T vo) throws Throwable
  {
    //获取目标类
    Class<?> targetClass = proceedingJoinPoint.getTarget().getClass();
    System.err.println(targetClass);
    MethodSignature methodSignature = (MethodSignature)proceedingJoinPoint.getSignature();
    System.err.println(methodSignature);
    Method targetMethod = targetClass.getDeclaredMethod(methodSignature.getName(), methodSignature.getParameterTypes());
    System.err.println(targetMethod);
    TypeAOP type;
    type = targetClass.getAnnotation(TypeAOP.class);
    int opertype = type.value();
    System.err.println(opertype);
  }

}
