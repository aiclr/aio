package org.bougainvilleas.ilg.study.chapter02

import groovy.transform.Canonical

@Canonical(excludes="lastName")
class Person{
    String firstName;
    String lastName;
    int age
}

def sara= new Person(firstName: "Sara",lastName:"Walker",age:49)
println sara

class Worker{
    def work(){println 'get work done'}
    def analyze() { println 'analyze...'}
    def writeReport() { println 'get report written'}
}

class Expert{
    def analyze(){ println "expert analysis..."}
}

/**
 * 编译时 groovy 会检查 Manager 类,如果该类中没有被委托类中的方法
 * 就把这些方法从被委托类中引入进来
 * 首先引入 Expert 类中的 analyze() 方法
 * 再引入 Worker 类中的 work() writeReport() 方法
 * 至于 Worker 类中的 analyze() 方法
 * 因为 已经出现在 Manager 类中(由 Expert 类引入的 Expert 类中的 analyze() 方法)
 * 所以 Worker 类中的 analyze() 方法会被忽略
 */
class Manager{
    @Delegate Expert expert = new Expert()
    @Delegate Worker worker = new Worker()
}

def bernie =new Manager()
bernie.analyze()
bernie.work()
bernie.writeReport()