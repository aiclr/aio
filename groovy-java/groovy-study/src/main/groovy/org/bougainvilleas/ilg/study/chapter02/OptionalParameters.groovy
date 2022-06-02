package org.bougainvilleas.ilg.study.chapter02

/**
 * 2.4 可选参数
 * groovy 可以把方法和构造器的形参设为可选的
 * 想设置多少就可以设置多少
 * 但是这些形参必须位于形参列表的末尾
 * 可以在演进式设计中向已有方法添加新的形参
 * 要定义可选形参,只需要在形参列表中给他赋上一个值
 */

def log(x,base=10)
{
    Math.log(x)/Math.log(base)
}
println log(1024)
println log(1024,10)
println log(1024,2)


/**
 * groovy 会把末尾数组形参视作可选的
 */

def task(name,String[] details)
{
    println "$name-$details"
}

task 'Call','123-456-7890'
task 'Call','123-456-7890','231-546-0987'
task 'Check Mail'