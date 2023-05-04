package org.bougainvilleas.ilg.designpattern

/**
 * Template Method Pattern
 * 模板方法模式
 */
abstract class Accumulator2 {
    protected initial

    abstract doAccumulate(total, v)

    def accumulate(values) {
        def total = initial
        values.each { v -> total = doAccumulate(total, v) }
        total
    }
}

class Sum extends Accumulator2 {
    def Sum() { initial = 0 }

    def doAccumulate(total, v) { total + v }
}

class Product extends Accumulator2 {
    def Product() { initial = 0 }

    def doAccumulate(total, v) { total * v }
}

println new Sum().accumulate([1, 2, 3, 4])
println new Product().accumulate([1, 2, 3, 4])

/**
 * inject method to achieve a similar result using Closures
 */
Closure addAll = { total, item -> total += item }
def accumulated = [1, 2, 3, 4].inject(0, addAll)
println accumulated

/**
 * using Closures
 */
accumulated = ["1", "2", "3", "4"].inject("", addAll)
println accumulated

/**
 * multiplication case
 */
Closure mulAll = { total, item -> total *= item }
accumulated = [1, 2, 3, 4].inject(1, mulAll)
println accumulated