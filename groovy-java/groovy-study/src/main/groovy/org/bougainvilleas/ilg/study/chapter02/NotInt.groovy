package org.bougainvilleas.ilg.study.chapter02

/**
 * 尽管指定的是int,但创建的是 java.lang.Integer类的实例,而不是 int 的变量
 * groovy会根据该实例的使用方法来决定将其存储为 int 类型还是 Integer 类型
 * Java 自动装箱,自动拆箱会涉及类型之间转换
 * groovy是简单地将其当作对象,所以不需要反复地转换类型
 * 在groovy 2.0 版本之前,groovy中所有基本类型都被看作对象
 * 为了改进性能,为了能在基本类型的操作上使用更直接的字节码,
 * groovy 2.0 做了优化
 * 基本类型只在必要时才会被看作对象
 * 如,在其上调用了方法,或将其传给了对象引用.
 * 否则groovy会在字节码级别将其保留为基本类型
 */
int val=5
// 输出java.lang.Integer
println val.getClass().name