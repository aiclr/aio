package org.bougainvilleas.spring.aop.annotation.mya;

import java.lang.annotation.*;

@Target({ElementType.TYPE})//类注解
@Retention(RetentionPolicy.RUNTIME)//运行时注解
@Inherited//允许类或接口继承
@Documented//生成javadoc时包含注解
public @interface MyTable {
    String value();
}