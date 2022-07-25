package org.bougainvilleas.ilg.study.chapter13


/**
 * 创建领域特定语言 DSL 需要能够向不同的类、甚至类的层次结构中加入任意方法
 * 为了保持一定流畅性，需要注入实例方法和静态方法
 * 操纵构造器 以及将实例方法转变为属性
 * 在创建代替协作者的模拟对象时，需要这些能力
 *
 * 通过向类的 metaClass 添加方法
 *
 */
Integer.metaClass.daysFromNow={->

    println "this is $this"
    println "owner is $owner"
    println "delegate is $delegate"

    Calendar today=Calendar.instance
    //delegate 引用的是 下例的 Integer 5
    today.add(Calendar.DAY_OF_MONTH, delegate as int)
    today.time
}

println "5天后日期是："+ 5.daysFromNow()
