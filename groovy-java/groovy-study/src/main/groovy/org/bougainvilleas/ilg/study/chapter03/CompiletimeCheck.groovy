package org.bougainvilleas.ilg.study.chapter03

import groovy.transform.TypeChecked
import groovy.transform.TypeCheckingMode

/**
 * 静态类型检查会限制使用动态方法
 *      并没有阻止 groovy 向JDK中的类添加方法
 * 静态类型检查器
 *      1. 会检查类中的方法和属性
 *      2. 还会检查特殊的 DefaultGroovyMethods 类 .包含一些有用的,优雅的扩展方法
 *      3. 此外还会检查开发者能够添加的定制扩展
 *
 * 静态类型检查 旨在帮助在编译时识别出一些错误
 * 如果没有错误,编译器为类型检查版本和无类型检查版本生成的字节码是类似的
 * 使用静态编译,可以生成高效的字节码
 *
 * @TypeChecked 注解让Groovy 编译时进行类型检查
 *
 * 检查方法和访问的属性在该类型上是合法的
 * 可用于类或单个方法上
 * 用于类,则类型检查会在该类中所有的方法,闭包,内部类上执行
 * 如果用于一个方法则类型检查仅在目标方法的成员上执行
 *
 * @TypeChecked 要利用静态类型检查,必须指明方法和闭包的形参类型
 * 能在形参上调用的方法,被限制为该类型在编译时已知支持的方法
 * groovy 会推断闭包的返回类型,并相应地执行类型检查,所以不必担心此类细节
 */
@TypeChecked
def shout(String str)
{
    println 'Printing in uppercase'
    println str.toUpperCase()
    println 'Printing again in uppercase'
    /**
     * 故意拼写错误,
     * 编译时不会检查
     * 运行时抛出 groovy.lang.MissingMethodException
     *
     * @TypeChecked 加入此注解后,编译时就会报错
     */
//    println str.toUppercase()
}
try
{
    shout 'hello'
}catch(ex){
    println "$ex"
}


/**
 * Groovy默认可以向类中注入方法
 *
 * 如下: 向 String 类的实例动态添加 shout() 方法
 * 从而能从 shoutString() 方法中调用
 *
 * 使用 @TypeChecked 注解标记的代码
 * 在编译时,编译器会验证方法或属性是否从属于该类( 此处shout() 不属于String类 )
 *
 * 类型检查 会阻止groovy使用元编程的能力,
 */
//@TypeChecked
def shoutString(String str){
    println str.shout()
}

str = 'hi'
str.metaClass.shout={->toUpperCase()}
shoutString(str)


/**
 * 静态类型检查器
 *      1. 会检查类中的方法和属性
 *      2. 还会检查特殊的 DefaultGroovyMethods 类 .包含一些有用的,优雅的扩展方法
 *      3. 此外还会检查开发者能够添加的定制扩展
 * DefaultGroovyMethods 类提供的优雅的方法
 */
@TypeChecked
def printInReverse(String str)
{
    println str.reverse()//DefaultGroovyMethods 类提供,静态检查不会报错
}
printInReverse 'oh'

/**
 * 与java相比,groovy类型检查优势
 * 如果使用 instanceOf 检查类型 在使用该类型特定的方法或属性是,并不需要执行强制转换
 */
@TypeChecked
def use(Object instance){
    if(instance instanceof String)
        println instance.length()//不用强制转换
    else
        println instance
}
use 'hello'
use 4

/**
 * @TypeChecked 注解一个完整的类
 * 可以使用 @TypeChecked(TypeCheckingMode.SKIP)
 * 去掉一些具体的方法,不对他们进行静态类型检查
 */
@TypeChecked
class Sample{
    def method1()
    {}
    @TypeChecked(TypeCheckingMode.SKIP)
    def methdo2(String str){
        str.shout()
    }
}