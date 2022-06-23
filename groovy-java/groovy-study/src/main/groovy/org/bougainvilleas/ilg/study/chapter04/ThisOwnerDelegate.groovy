package org.bougainvilleas.ilg.study.chapter04

/**
 * 闭包方法委托 方法分派
 * 像 JavaScript 对原型集成 prototypal inheritance
 *
 * this owner delegate 闭包三个属性
 * 用于确定哪个对象处理该闭包内的方法调用
 * delegate 会设置为 owner
 *
 */

def examiningClosure(closure){
    closure()
}
/**
 * 第一个闭包内,取到该闭包的一些具体信息,确定了this,owner,delegate
 * 都是 org.bougainvilleas.ilg.study.chapter04.ThisOwnerDelegate@42039326
 * 在第一个闭包内调用 examiningClosure() 传递第二个闭包
 * 所以第一个闭包 成了第二个闭包的 owner
 *
 * 第二个闭包
 *      this 是 org.bougainvilleas.ilg.study.chapter04.ThisOwnerDelegate@42039326
 *      owner 是 org.bougainvilleas.ilg.study.chapter04.ThisOwnerDelegate$_run_closure1@3d1848cc
 *      delegate 是 org.bougainvilleas.ilg.study.chapter04.ThisOwnerDelegate$_run_closure1@3d1848cc
 *
 */
examiningClosure(){
    println 'In First Closure'
    println 'class is '+getClass().name
    println 'this is '+this+', supper:'+owner.getClass().getSuperclass().name
    println 'owner is '+owner+', supper:'+owner.getClass().superclass.name
    println 'delegate is '+delegate+', supper:'+delegate.getClass().superclass.name

    examiningClosure(){
        println 'In Closure within the First Closure:'
        println 'class is '+getClass().name
        println 'this is '+this+', supper:'+owner.getClass().getSuperclass().name
        println 'owner is '+owner+', supper:'+owner.getClass().superclass.name
        println 'delegate is '+delegate+', supper:'+delegate.getClass().superclass.name
    }
}

/**
 * 上例 delegate被设置为owner
 * 某些Groovy 函数会修改 delegate,以执行动态路由
 * 比如with()函数
 * 函数内this 指向该闭包所绑定对象(正在执行的上下文 比如此例子中的上下文 是 ThisOwnerDelegate.groovy 的实例)
 * 在闭包内引用的变量和方法都会绑定到 this ,this负责处理任何方法调用,以及任何属性和变量的访问
 * 如果 this 无法处理,则转向 owner 最后是 delegate
 *
 * 先调用 this 实例的方法,如果无法处理
 * 路由到 owner 实例的方法,如果无法处理
 * 路由到 delegate 实例的方法
 *
 */
class Handler{
    def f1(){println 'f1 of Handler called ...'}
    def f2(){println 'f2 of Handler called ...'}
}
class Example{
    def f1(){println 'f1 of Example called ...'}
    def f2(){println 'f2 of Example called ...'}

    def foo(closure){
        closure.delegate=new Handler()
        closure()
    }
    /**
     * 如果完全肯定该闭包不会用在别的地方,可以手动设置 delegate
     * 如果用在其他地方,为避免副作用,需要复制该闭包,在副本上设置 delegate 并使用该副本
     */
    def foo2(closure){
        def clone = closure.clone()
        clone.delegate = new Handler()
        clone()
    }
    //借助特殊的 with() 方法 设置 delegate
    def foo3(closure){
        def handler=new Handler()
        /**
         * with 类似 Kotlin 的apply
         * with 作用域内调用的任何方法,都会被路由到 handler 对象上
         * 且 闭包内可以使用 handler 对象的任意 public 属性
         */
        handler.with closure
    }
}
def f1(){println 'f1 of Script called ...'}

new Example().foo {

    /**
     * this 是 org.bougainvilleas.ilg.study.chapter04.ThisOwnerDelegate@3f270e0a
     * owner 是 org.bougainvilleas.ilg.study.chapter04.ThisOwnerDelegate@3f270e0a
     * delegate 是 org.bougainvilleas.ilg.study.chapter04.Handler@63798ca7
     */
    f1()
    f2()
    println 'class is '+getClass().name
    println 'this is '+this+', supper:'+owner.getClass().getSuperclass().name
    println 'owner is '+owner+', supper:'+owner.getClass().superclass.name
    println 'delegate is '+delegate+', supper:'+delegate.getClass().superclass.name
}

new Example().foo2 {

    /**
     * this 是 org.bougainvilleas.ilg.study.chapter04.ThisOwnerDelegate@3f270e0a
     * owner 是 org.bougainvilleas.ilg.study.chapter04.ThisOwnerDelegate@3f270e0a
     * delegate 是 org.bougainvilleas.ilg.study.chapter04.Handler@63798ca7
     */
    f1()
    f2()
    println 'class is '+getClass().name
    println 'this is '+this+', supper:'+owner.getClass().getSuperclass().name
    println 'owner is '+owner+', supper:'+owner.getClass().superclass.name
    println 'delegate is '+delegate+', supper:'+delegate.getClass().superclass.name
}

new Example().foo3 {


    /**
     * this 是 org.bougainvilleas.ilg.study.chapter04.ThisOwnerDelegate@3f270e0a
     * owner 是 org.bougainvilleas.ilg.study.chapter04.ThisOwnerDelegate@3f270e0a
     * delegate 是 org.bougainvilleas.ilg.study.chapter04.Handler@63798ca7
     */
    f1()
    f2()
    println 'class is '+getClass().name
    println 'this is '+this+', supper:'+owner.getClass().getSuperclass().name
    println 'owner is '+owner+', supper:'+owner.getClass().superclass.name
    println 'delegate is '+delegate+', supper:'+delegate.getClass().superclass.name
}

