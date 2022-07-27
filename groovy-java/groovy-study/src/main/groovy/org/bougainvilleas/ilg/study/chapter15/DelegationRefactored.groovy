package org.bougainvilleas.ilg.study.chapter15

class Manager2 {
    {
        delegateCallsTo Worker, Expert, GregorianCalendar
    }

    def schedule() { println "Scheduling ..." }
}

Object.metaClass.delegateCallsTo = { Class... klassOFDelegates ->
    def objectOfDelegates = klassOFDelegates.collect { it.newInstance() }
    delegate.metaClass.methodMissing = { String name, args ->
        println "methodMissing call to $name"
        def delegateTo = objectOfDelegates.find {
            it.metaClass.respondsTo(it, name, args)
        }
        if (delegateTo) {
            delegate.metaClass."$name" = { Object[] varArgs ->
                delegateTo.invokeMethod(name, varArgs)
            }
            delegateTo.invokeMethod(name, args)
        } else {
            throw new MissingMethodException(name, delegate.getClass(), args)
        }
    }
}

peter = new Manager2()
peter.schedule()
peter.simpleWork1('fast')
peter.simpleWork1('quality')//调用 metaClass 内合成的新方法，不再调用 methodMissing
peter.simpleWork2()
peter.simpleWork2()
peter.advancedWork1('fast')
peter.advancedWork1('quality')
peter.advancedWork2('prototype', 'fast')
peter.advancedWork2('product', 'quality')
/**
 * {@link GregorianCalendar#isLeapYear(int)}
 */
println "Is 2020 a leap year? " + peter.isLeapYear(2020)
GregorianCalendar
try {
    peter.simpleWork3()
} catch (ex) {
    println ex
}