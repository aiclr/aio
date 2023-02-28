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
   * @target 匹配的是 执行方法 的对象的class上是否有注解
   * @within 匹配的是 定义方法 的对象的class上是否有注解
   * 由于运行时多态的特性，执行方法的对象和定义方法的对象可能不是同一个对象
   * 定义方法的类是可以提前知道的，这个是静态的，但是执行方法的类是无法提前知道的，是运行时动态的，
   * 因此，在@target的场景下，spring aop给所有的bean都生成了代理类，如果无法生成代理就会报错。
   * 比如：cglib遇到final的时候
   * @target一定要慎用
   */
//  @Pointcut(value = "@target(org.bougainvilleas.spring.aop.annotation.AOPAnnotation.TypeAOP) && args(vo)")
  @Pointcut(value = "@within(org.bougainvilleas.spring.aop.annotation.AOPAnnotation.TypeAOP) && args(vo)")
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
