package org.bougainvilleas.module.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)//模块注释 只有 RUNTIME 才有效
@Target(value = {ElementType.PACKAGE,ElementType.MODULE})
public @interface MyModuleAnnotation
{
}
