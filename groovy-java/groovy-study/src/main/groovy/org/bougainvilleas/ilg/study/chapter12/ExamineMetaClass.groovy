package org.bougainvilleas.ilg.study.chapter12

/**
 * groovy.lang.ExpandoMetaClass 是 MetaClass 接口的一个实现
 * 也是 Groovy中负责实现动态行为的关键类之一
 * 通过向该类添加方法 可以实现向类中注入行为
 * 甚至可以使用该类特化单个对象
 *
 * 陷阱：
 *  groovy.lang.ExpandoMetaClass 是 MetaClass 接口的众多不同实现之一
 *  默认情况下 groovy 目前并没有使用 groovy.lang.ExpandoMetaClass
 *  当我们向 metaClass 添加一个方法时，默认的 metaClass 会被用一个 groovy.lang.ExpandoMetaClass 实例替换掉
 */
Integer.metaClass.invokeMethod{String name,args->/* */}
println Integer.metaClass.getClass().name