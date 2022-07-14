package org.bougainvilleas.ilg.study.chapter07

/**
 * 间接访问属性
 * [] 操作符动态地访问属性 映射 groovy添加的getAt()
 */
class Car{
    int miles,fuelLevel
}
car=new Car(fuelLevel: 80,miles: 25)

properties = ['miles','fuelLevel']

properties.each {name->
    println "$name = ${car[name]}"
}
car[properties[1]]=100
println "fuelLevel now is ${car.fuelLevel}"

/**
 * 间接调用方法
 * 如果编写代码时不知道方法名，而在运行时获得，那就可以使用几行代码将其变为实例上的动态调用
 * groovy 还提供了 getMetaClass() 方法 用于获得元类 metaclass 对象
 * 这是在Groovy 中利用动态能力的一个关键对象
 */
class Person{
    def walk() {println "Walking..."}
    def walk(int miles){println "Walking $miles miles..."}
    def walk(int miles,String where){println "Walking $miles miles $where..."}
}
peter = new Person()

peter.invokeMethod("walk",null)
peter.invokeMethod("walk",10)
peter.invokeMethod("walk",[2,'uphill']as Object[])