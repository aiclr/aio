package org.bougainvilleas.ilg.study.chapter16.eam

import org.codehaus.groovy.transform.GroovyASTTransformationClass

import java.lang.annotation.ElementType
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import java.lang.annotation.Target


@Retention(RetentionPolicy.SOURCE)//
@Target(ElementType.TYPE)//只能用于类
@GroovyASTTransformationClass("org.bougainvilleas.ilg.study.chapter16.eam.EAMTransformation")
public @interface EAM {
}
