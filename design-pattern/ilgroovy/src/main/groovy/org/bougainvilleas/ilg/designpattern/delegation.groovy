package org.bougainvilleas.ilg.designpattern
/**
 * Delegation Pattern
 * 委托模式
 */
class Delegator {
    private targetClass
    private delegate

    Delegator(targetClass, delegate) {
        this.targetClass = targetClass
        this.delegate = delegate
    }

    def delegate(String methodName) {
        delegate(methodName, methodName)
    }

    def delegate(String methodName, String asMethodName) {
        // “.&”运算符是用来引用一个方法
        targetClass.metaClass."$asMethodName" = delegate.&"$methodName"
    }

    def delegateAll(String[] names) {
        names.each { delegate(it) }
    }

    def delegateAll(Map names) {
        names.each { k, v -> delegate(k, v) }
    }

    def delegateAll() {
        delegate.class.methods*.name.each { delegate(it) }
    }
}

class Person {
    String name
}

class MortgageLender {
    def borrowAmount(amount) {
        "borrow \$$amount"
    }

    def borrowFor(thing) {
        "buy $thing"
    }
}

def lender = new MortgageLender()
def delegator = new Delegator(Person, lender)

// Person#borrowFor -> MortgageLender#borrowFor
delegator.delegate 'borrowFor'

// Person#getMoney -> MortgageLender#borrowAmount
delegator.delegate 'borrowAmount', 'getMoney'

def p = new Person()

println p.borrowFor('persent')
println p.getMoney(50)