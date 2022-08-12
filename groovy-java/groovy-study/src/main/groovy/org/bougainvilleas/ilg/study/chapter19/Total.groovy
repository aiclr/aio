package org.bougainvilleas.ilg.study.chapter19

value = 0
def clear(){value=0}
def add(number){value+=number}
def total(){println "Total is $value"}
clear()
add 2
add 5
add 7
total()

try{
    /**
     * groovy 认为 对total 的调用引用了一个不存在的属性
     * 通过 get 方法 实现属性 total
     */
    total
}catch(Exception exception)
{
    //groovy.lang.MissingPropertyException: No such property: total for class: org.bougainvilleas.ilg.study.chapter19.Total
    println exception
}
/**
 * 通过 get 方法 实现属性 total clear
 * 即可去掉括号，消除 DSL 括号
 */
def getTotal(){println "Total is $value"}
def getClear(){value=0}

clear
add 2
add 5
add 7
total
