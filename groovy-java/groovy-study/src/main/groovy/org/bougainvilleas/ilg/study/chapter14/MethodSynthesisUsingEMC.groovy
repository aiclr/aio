package org.bougainvilleas.ilg.study.chapter14

class Person4 {
    def work() { 'working ...' }
}

System.err.println("为 MetaClass 提供 invokeMethod")

Person4.metaClass.invokeMethod = { String name, args ->
    System.err.println("invokeMethod called for $name")
    def method = Person4.metaClass.getMetaMethod(name, args)
    if (method) {
        method.invoke(delegate, args)
    } else {
        //调用 methodMissing
        Person4.metaClass.invokeMissingMethod(delegate, name, args)
    }
}
System.err.println("为 MetaClass 提供 methodMissing")
/**
 * 如果类中也提供了 methodMissing() 方法 MetaClass 的 methodMissing() 方法会优先被调用，覆盖掉类提供的 methodMissing()方法
 */
Person4.metaClass.methodMissing = { String name, args ->
    def plays = ['Tennis', 'VolleyBall', 'BasketBall']
    System.err.println("methodMissing called for $name")
    def methodInList = plays.find { it == name.split('play')[1] }
    if (methodInList) {
        def impl = { Object[] vargs ->
            "playing ${name.split('play')[1]} ..."
        }
        //添加方法 重复调用会调用新添加的方法
        Person4.metaClass."$name" = impl
        impl(args)
    } else {
        throw new MissingMethodException(name, Person.class, args)
    }
}


jack = new Person4()
System.err.println(jack.work())
System.err.println(jack.playTennis())
System.err.println(jack.playTennis())