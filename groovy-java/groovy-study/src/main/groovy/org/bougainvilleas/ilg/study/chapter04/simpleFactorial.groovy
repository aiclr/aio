package org.bougainvilleas.ilg.study.chapter04

//普通递归 方法栈可能溢出  java.lang.StackOverflowError
def factorial(BigInteger number) {
    if (1 == number) 1 else number * factorial(number - 1)
}

try {
    println "factorial of 5 is ${factorial(5)}"
    //java.lang.StackOverflowError
    println "Number of bits in the result is ${factorial(5000).bitCount()}"
} catch (Throwable ex) {
    println "Caught ${ex.class.name}"
}

//尾递归 更高效使用内存
def factorial2
factorial2 = { int number, BigInteger theFactorial ->
    number == 1 ? theFactorial :
            factorial2.trampoline(number - 1, number * theFactorial)
}.trampoline()
println "factorial2 of 5 is ${factorial2(5, 1)}"
println "Number of bits in the rsult is ${factorial2(5000, 1).bitCount()}"

//尾递归 优化 减少参数 提供 默认参数 BigInteger theFactorial = 1
//调用者可以省略 factorial3 第二个参数

def factorial3(int factorialFor) {
    def tailFactorial
    tailFactorial = { int number, BigInteger theFactorial = 1 ->
        number == 1 ? theFactorial :
                tailFactorial.trampoline(number - 1, number * theFactorial)
    }.trampoline()
    tailFactorial(factorialFor)
}

println "factorial3 of 5 is ${factorial3(5)}"
println "Number of bits in the result is ${factorial3(5000).bitCount()}"

/**
 * 某些语言通过编译技术 实现尾递归优化
 * groovy 使用 闭包上的一个便捷方法 trampoline() 特性实现尾递归
 * 性能比简单递归或纯粹的迭代差一些
 * 要使用 trampoline() 适用于较大的输入值
 */