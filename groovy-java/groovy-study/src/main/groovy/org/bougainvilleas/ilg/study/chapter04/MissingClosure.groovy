package org.bougainvilleas.ilg.study.chapter04

/**
 * 动态闭包
 *
 */
def doSomeThing(closure) {

    if (closure) {
        //未提供闭包 closure
        closure()
    } else {
        //提供闭包 closure
        println "Using default implementation"
    }
}

doSomeThing() {
    println "Use specialized implementation"
}
doSomeThing()


def completeOrder(amount, taxComputer) {
    tax = 0
    //maximumNumberOfParameters:
    //      the maximum number of parameters a doCall method of this closure can take
    if (taxComputer.maximumNumberOfParameters == 2) {
        //期望传入税率
        tax = taxComputer(amount, 6.05)
    } else {
        //使用默认税率
        tax = taxComputer(amount)
    }
    println "Sales tax is ${tax}"
}

completeOrder(100){it*0.0825}
completeOrder(100){amount,rate->amount * (rate/100)}


/**
 * 闭包 没有任何形参 像 {} {it},其实也会接受一个参数,名字默认 it
 * 如果调用者没有向闭包提供任何值,则第一个形参it 为null
 * 如果希望闭包完全不接受任何参数,必须使用  {->}这种语法
 *      在 -> 前没有形参,说明该闭包不接受任何参数
 */
def examine(closure){
    println "$closure.maximumNumberOfParameters parameter(s) given: "
    for(aParameter in closure.parameterTypes){println aParameter.name}
    println '--'
}

examine(){}
examine(){it}
examine(){->}
examine(){val1->}
examine(){Date val1->}
examine(){Date val1,val2->}
examine(){Date val1,String val2->}