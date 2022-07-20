package org.bougainvilleas.ilg.study.chapter10

class AGroovyClass {
    def useClosure(closure){
        println "Calling closure"
        closure()
    }
    def passToClosure(int value,closure){
        println "Simple passing $value to the given closure"
        closure(value)
    }
}
