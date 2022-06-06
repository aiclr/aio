package org.bougainvilleas.ilg.study.chapter02

/**
 * groovy 实现静态导入
 * groovy 可以为静态方法和类名定义别名
 * 要定义别名 需要在 import 语句中使用 as 操作符
 */
import static java.lang.Math.random as rand
import groovy.lang.ExpandoMetaClass as EMC

double value = rand()
println value
def metaClass = new EMC(Integer)
assert metaClass.getClass().name == 'groovy.lang.ExpandoMetaClass'
println metaClass.getClass().name