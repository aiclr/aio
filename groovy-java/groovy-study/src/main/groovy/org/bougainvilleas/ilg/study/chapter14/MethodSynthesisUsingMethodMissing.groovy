package org.bougainvilleas.ilg.study.chapter14

class Person {
    def work() { "working ..." }
    def plays = ['Tennis', 'VolleyBall', 'BasketBall']

    def methodMissing(String name, args) {
        System.err.println("methodMissing called for $name")
        //name.split('play')==>[,Tennis]
        //name.split('play')==>[,VolleyBall]
        //name.split('play')==>[,BasketBall]
        def methodInList = plays.find { it == name.split('play')[1] }
        if (methodInList) {
            "playing ${name.split('play')[1]} ..."
        } else {
            throw new MissingMethodException(name, Person.class, args)
        }
    }
}

System.err.println("aabbcc".split('aa'))
System.err.println("aabbcc".split('bb'))
System.err.println("aabbcc".split('cc'))

jack = new Person()
System.err.println(jack.work())
System.err.println(jack.playTennis())
System.err.println(jack.playVolleyBall())
System.err.println(jack.playBasketBall())
//重复调用 会有性能问题
System.err.println(jack.playTennis())


System.err.println("优化 重复调用")

class Person2 {
    def work() { "working ..." }
    def plays = ['Tennis', 'VolleyBall', 'BasketBall']

    def methodMissing(String name, args) {
        System.err.println("methodMissing called for $name")
        def methodInList = plays.find { it == name.split('play')[1] }
        if (methodInList) {
            def impl = { Object[] vargs ->
                "playing ${name.split('play')[1]} ..."
            }
            Person2 instance = this
            //添加方法 重复调用会调用新添加的方法
            instance.metaClass."$name" = impl
            impl(args)
        } else {
            throw new MissingMethodException(name, Person.class, args)
        }
    }
}

jack = new Person2()
System.err.println(jack.playTennis())
//重复调用 会调用metaclass 添加的新方法 不会在进入 methodMissing 方法 提高性能
System.err.println(jack.playTennis())


System.err.println("实现 GroovyInterceptable后 invokeMethod 和 methodMissing方法")
/**
 * 实现 GroovyInterceptable 的类，调用该对象的任何方法，不管是否存在此方法，如果有实现 invokeMethod()，都会先调用 invokeMethod
 * 只有调用的方法不存在时，才会调用 methodMissing
 */
class Person3 implements GroovyInterceptable {
    def work() { 'working ...' }
    def plays = ['Tennis', 'VolleyBall', 'BasketBall']

    def invokeMethod(String name, args) {
        System.err.println("invokeMethod called for $name")
        def method = metaClass.getMetaMethod(name, args)
        if (method) {
            method.invoke(this, args)
        } else {
            //调用 methodMissing
            metaClass.invokeMethod(this, name, args)
        }
    }

    def methodMissing(String name, args) {
        System.err.println("methodMissing called for $name")
        def methodInList = plays.find { it == name.split('play')[1] }
        if (methodInList) {
            def impl = { Object[] vargs ->
                "playing ${name.split('play')[1]} ..."
            }
            Person3 instance = this
            //添加方法 重复调用会调用新添加的方法
            instance.metaClass."$name" = impl
            impl(args)
        } else {
            throw new MissingMethodException(name, Person.class, args)
        }
    }
}

jack = new Person3()
System.err.println(jack.playTennis())
//重复调用 会调用metaclass 添加的新方法 不会在进入 methodMissing 方法 提高性能
System.err.println(jack.playTennis())