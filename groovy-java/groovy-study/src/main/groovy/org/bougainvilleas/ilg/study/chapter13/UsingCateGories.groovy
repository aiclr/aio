package org.bougainvilleas.ilg.study.chapter13
/**
 * use() 定义了一个新作用域
 * 包括栈上的一个新属性/方法列表 用于目标对象的 MetaClass
 * 之后检查给定分类中的每个静态方法，并将静态方法及参数(至少有一个)加入到属性/方法列表中
 * 最后 会调用 use 后面的闭包
 * 在该闭包内的任何方法调用都会被拦截，然后发送给由分类提供的实现（如果存在的话）
 * 对于我们加入的新方法，以及拦截的已有方法均是如此
 * 最后一旦等到从闭包返回 use() 就结束掉前面创建的作用域，丢弃分类中注入的方法
 *
 * 可以同时使用多个分类，带入多组方法 use() 接受一个分类列表;多个分类 方法名冲突时 列表中最后一个分类优先级最高
 *
 * groovy 分类
 * 要求注入方法是 static 静态 至少接受一个参数
 * 第一个参数指向 方法调用的目标
 *
 * 要注入的方法所需的参数都放到后面
 * 参数可以是任何合法的 groovy 参数 包括对象和闭包
 *
 * 内置分类
 * groovy 提供很多方便使用的分类
 * DOMCategory 可以帮助处理DOM对象 还支持 GPath
 * ServletCategory 使用JavaBean惯例提供 Servlet API 对象的属性
 *
 * 多次进入退出use 块时由代价的
 * 每次进入use groovy 都必须检查静态方法，并将其加入到新作用域的一个方法列表 最后还要清理作用域
 *
 *
 * 分类应该用于方法注入 而不是方法拦截
 *
 */
class StringUtil {
    //如果想将参数限制为 String 类型 则使用
    //def static toSSN(String self)
    def static toSSN(self) {
        if (self.size() == 9) {
            "${self[0..2]}-${self[3..4]}-${self[5..8]}"
        }
    }
}

/**
 * StringUtil 注入方法 toSSN 仅在 use块内有效
 * 在外部调用 会出现 MissingMethodException 异常
 * 在 use 块内调用 会被路由到 分类 StringUtil 中的静态方法
 * toSSN 的self 参数被指派为目标实例 "123456789" 和 new StringBuilder("987654321")
 *
 */
use(StringUtil) {
    println "123456789".toSSN()
    println new StringBuilder("987654321").toSSN()
}

try {
    println "123456789".toSSN()
} catch (MissingMethodException e) {
    println e.message
}

println '@Category 注解'

/**
 * @Category 注解会根据我们传入的String 参数将新定义的StringUtilAnnotated类的 toSSN1() 转变为与 上面的例子一样的代码
 * public static toSSN1(String self){...}
 *
 * 注解式语法减少了一些程序化的东西 不必将分类的方法声明为静态的
 * 也不必再额外传递第一个参数
 *
 * 但是方法会被限制为只能使用参数中指定的类型 @Category(String) String
 * 对于其他类型 比如  StringBuilder 是不可复用的
 */
@Category(String)
class StringUtilAnnotated{
    def toSSN2(){
        if(size() ==9){
            "${this[0..2]}-${this[3..4]}-${this[5..8]}"
        }
    }
}

use(StringUtilAnnotated){
    println "123456789".toSSN2()
    try {
        println new StringBuilder("987654321").toSSN2()
    } catch (MissingMethodException e) {
        println e.message
    }
}

try {
    println "123456789".toSSN2()
} catch (MissingMethodException e) {
    println e.message
}

println ''

/**
 * 分类传递 闭包 Closure
 */
class FindUtil{
    def static extractOnly(String self,closure){
        def result=''
        self.each{
            if(closure(it)){result+=it}
        }
        result
    }
}

use(FindUtil){
    println "121254123".extractOnly {it=='4'||it=='5'}
}

println '可以同时使用多个分类，带入多组方法 use() 可以接受一个分类列表，可以使用逗号分割的类名列表'
/**
 * 类一旦定义 groovy 会将类名转换为一个对该类的class元对象的引用
 * String 等价于 String.class 即 String==String.class
 * 所以可以使用 String 类名代替 String.class
 *
 * 多个分类 方法名冲突时 列表中最后一个分类优先级最高
 */
use(StringUtil,FindUtil){
    str="123487651"
    println str.toSSN()
    println str.extractOnly{it=='8'||it=='1'}
}

println '嵌套use 内部的分类优先于外部的分类 '

/**
 * use 可以嵌套 内部的分类优先于外部的分类
 */

class StringUtil2 {
    //如果想将参数限制为 String 类型 则使用
    //def static toSSN(String self)
    def static toSSN(self) {
        if (self.size() == 9) {
            "toSSN2 ${self[0..2]}-${self[3..4]}-${self[5..8]}"
        }
    }
}
use(StringUtil){

    str="123487651"
    println str.toSSN()

    use(StringUtil2){
        str="123487651"
        println str.toSSN()
    }
}



println '使用分类拦截方法'
/**
 * 使用分类拦截方法，不能使用分类过滤一个实例上的所有方法调用
 * 只能为每个想拦截的方法编写单独的方法，
 * 当存在嵌套分类时，内部的分类优先级更高，对于同样的方法，无法利用顶层的分类进行拦截
 * 因此 分类应该用于方法注入，而不是方法拦截
 */
class Helper{
    def static toString(String self){
        def method =self.metaClass.metaMethods.find{it.name=='toString'}
        '!!'+method.invoke(self,null)+'!!'
    }
}
use(Helper){
    println 'hello'.toString()
}