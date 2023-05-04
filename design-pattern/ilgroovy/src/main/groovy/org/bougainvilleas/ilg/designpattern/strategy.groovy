package org.bougainvilleas.ilg.designpattern

interface Calc2 {
    def execute(n, m)
}

class CalcByMult implements Calc2 {
    def execute(n, m) { n * m }
}

class CalcByManyAdds implements Calc2 {
    def execute(n, m) {
        def result = 0
        n.times {
            result += m
        }
        result
    }
}

def sampleData = [
        [3, 4, 12],
        [5, -5, -25]
]

Calc2[] multiplicationStrategies = [
        new CalcByMult(),
        new CalcByManyAdds()
]

sampleData.each{ data ->
    multiplicationStrategies.each { calc ->
        assert data[2] == calc.execute(data[0], data[1])
    }
}
/**
 * Here is the Groovier way to achieve the same thing using Closures:
 * 以下是Groovier使用Closures实现相同功能的方法
 */
multiplicationStrategies = [
        { n, m -> n * m },
        { n, m ->
            def result = 0
            n.times {
                result += m
            }
            result
        }
]

sampleData.each{ data ->
    multiplicationStrategies.each { calc ->
        assert data[2] == calc.execute(data[0], data[1])
    }
}