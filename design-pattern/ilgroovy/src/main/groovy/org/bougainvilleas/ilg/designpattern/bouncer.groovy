package org.bougainvilleas.ilg.designpattern

//Null Checking example
class NullChecker {
    static check(name, arg) {
        if (arg == null) {
            throw new IllegalArgumentException(name + ' is null')
        }
    }
}

void doStuff(String name, Object value) {
    NullChecker.check('name', name)
    NullChecker.check('value', value)
}

def value
//doStuff('',value)

/**
 * groovy way to do
 */
void doStuff2(String name, Object value) {
    assert name != null, 'name should not be null'
    assert value != null, 'value should not be null'
}

//doStuff2('',value)

/**
 * validation Example
 */
class NumberChecker {
    static final String NUMBER_PATTERN = "\\\\d+(\\\\.\\\\d+(E-?\\\\d+)?)?"

    static isNumber(str) {
        if (!str ==~ NUMBER_PATTERN) {
            throw new IllegalArgumentException("Argument '$str' must be a number")
        }
    }

    static isNotZero(number) {
        if (number == 0) {
            throw new IllegalArgumentException('Argument must not be 0')
        }
    }
}

def stringDivide(String dividendStr, String divisorStr) {
    NumberChecker.isNumber(dividendStr)
    NumberChecker.isNumber(divisorStr)
    def dividend = dividendStr.toDouble()
    def divisor = divisorStr.toDouble()
    NumberChecker.isNotZero(divisor)
    dividend / divisor
}
// 1.2E2 = 1.2x10^2 = 120
println stringDivide('1.2E2', '3.0')

// groovy
def stringDivide2(String dividendStr,String divisorStr){

    def a=dividendStr ==~ NumberChecker.NUMBER_PATTERN
    println(a.class)
    println(a)

    def b=dividendStr =~ NumberChecker.NUMBER_PATTERN
    println(b.class)
    println(b)
    println(b.matches())

    assert !(dividendStr ==~ NumberChecker.NUMBER_PATTERN),"dividendStr must be a number"
    assert !(divisorStr ==~ NumberChecker.NUMBER_PATTERN),"dividendStr must be a number"
    def dividend = dividendStr.toDouble()
    def divisor= divisorStr.toDouble()
    assert divisor != 0,'Divisor must not be 0'
    dividend / divisor
}

println stringDivide2('1.2E2', '3.0')
