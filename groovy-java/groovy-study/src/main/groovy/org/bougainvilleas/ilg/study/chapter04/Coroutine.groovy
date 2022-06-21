package org.bougainvilleas.ilg.study.chapter04

/**
 * 闭包与协程
 * 调用一个函数或方法会在程序的执行序列中创建一个新的作用域
 * 在入口点进入函数
 * 方法完成后,回到调用者作用域
 *
 * 协程 Coroutine 则支持多个入口点,
 * 每个入口点的都是上次挂起调用的位置
 * 可以进入一个函数,执行部分代码,挂起
 * 在回到调用这的上下文或作用域内执行一些代码.
 * 之后在挂起的地方恢复该函数的执行
 *
 * 与主例程和子例程之间的不对称关系不同,协程之间是完全对称的,可以互相调用
 *
 * 协程对于实现某些特殊的逻辑或算法非常方便
 *      如: 生产者-消费者问题,
 *      生产者会接收一些输入,对输入做一些初始处理,通知消费者拿走处理过的值做进一步计算,并输出或存储结果
 *      消费者处理它的那部分工作,完成之后通知生产者以获取更多输入
 *  在Java中,wait() notify() 与多线程结合使用,可以协助实现协程
 *  闭包会让人产生"协程是在一个线程中执行"的错觉
 *
 * 如下 每次调用闭包,都会从上一次调用中获取返回的total值
 */
def iterate(n, closure) {
    1.upto(n) {
        println "In iterate with value ${it}"
        closure(it)
    }
}

println "Calling iterate"
total = 0
iterate(4) {
    total += it
    println " In closure total so far is ${total}"
}
println 'Done'