package org.bougainvilleas.ilg.study.chapter10

/**
 * groovy在运行时创建方法，这些方法不能从java中直接调用
 * 因为在编译时这些代码，在字节码中还不存在
 * 在运行时产生，但是如果我们要从 Java 中调用他们 需要在编译时编写调用语句（或者使用反射）
 * 要调用动态方法，必须先通过java编译器的编译，然后运行时才能进行分派
 *
 * 本例这个类完全是动态的，除了methodMissing 没有实际的方法
 * 因为这个类可以接受任何方法调用，所以几乎可以在它上面调用任何方法
 * 在java端 调用invokeMethod()并将方法的名字和一个由参数组成的数组传给它
 */
class DynamicGroovyClass
{
    /**
     * 当某个不存在的方法被调用时，该方法回介入
     */
    def methodMissing(String name,args){
        println "You called $name with ${args.join(', ')}."
        args.size()
    }
}
