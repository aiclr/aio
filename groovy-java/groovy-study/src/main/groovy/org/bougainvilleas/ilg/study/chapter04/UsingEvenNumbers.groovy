package org.bougainvilleas.ilg.study.chapter04

/**
 * 求1到n之间所有偶数的和
 */
def sum(n){
    total = 0
    for(int i=2;i<=n;i+=2)
    {
        total+=i
    }
    total
}
println "Sum of even numbers from 1 to 10 is ${sum(10)}"

/**
 * 求1到n之间所有偶数的积
 */
def product(n){
    prod = 1
    for(int i=2;i<=n;i+=2)
    {
        prod*=i
    }
    prod
}
println "Product of even numbers from 1 to 10 is ${product(10)}"

/**
 * 获取1到n之间所有偶数的平方组成的集合
 */
def sqr(n){
    squared = []
    for(int i=2;i<=n;i+=2)
    {
        // ** 次方
        squared << i ** 2
    }
    squared
}
println "Squared of even numbers from 1 to 10 is ${sqr(10)}"

/**
 * 闭包 实现上面三个方法
 */
def pickEven(n,block)
{
    for(int i=2;i<=n;i+=2)
    {
        block i
    }
}

pickEven(10,{println it})
pickEven(10,{i->println i})

total=0
pickEven(10) {total+=it}
println "Sum of even numbers from 1 to 10 is ${total}"

prod=1
pickEven(10) {prod *= it}
println "Product of even numbers from 1 to 10 is ${prod}"

squared = []
pickEven(10) {squared<<it**2}
println "Squared of even numbers from 1 to 10 is ${squared}"
