package org.bougainvilleas.ilg.designpattern

/**
 * Singleton Pattern
 */
println('The Classic Java Singleton')

class VoteCollector {
    def votes = 0
    private static final INSTANCE = new VoteCollector()

    static getInstance() { return INSTANCE }

    private VoteCollector() {}

    def display() { println "Collector:${hashCode()},Votes:$votes" }
}

def collector = VoteCollector.instance
collector.display()
collector.votes++
collector = null
Thread.start {
    def collector2 = VoteCollector.instance
    collector2.display()
    collector2.votes++
    collector2 = null
}.join()
def collector3 = VoteCollector.instance
collector3.display()

println '\nSingleton via MetaProgramming'

class Calculator {
    private total = 0

    def add(a, b) { total++; a + b }

    def getTotalCalculations() { 'Total Calculations: ' + total }

    String toString() { 'Calc: ' + hashCode() }
}

class Client {
    def calc = new Calculator()

    def executeCalc(a, b) { calc.add(a, b) }

    String toString() { 'Client: ' + hashCode() }
}

class CalculatorMetaClass extends MetaClassImpl {
    private static final INSTANCE = new Calculator()

    CalculatorMetaClass() { super(Calculator) }

    def invokeConstructor(Object[] arguments) { return INSTANCE }
}

def registry = GroovySystem.metaClassRegistry
registry.setMetaClass(Calculator, new CalculatorMetaClass())

def client = new Client()
assert 3 == client.executeCalc(1, 2)
println "$client,$client.calc,$client.calc.totalCalculations"

client = new Client()
assert 4 == client.executeCalc(2, 2)
println "$client,$client.calc,$client.calc.totalCalculations"