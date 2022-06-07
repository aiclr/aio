package org.bougainvilleas.ilg.study.chapter02

import groovy.transform.Canonical
import groovy.transform.Immutable

/**
 * 如果编写的toString() 方法只是简单地显示以逗号分隔的字段值,则可以使用 @Canonical
 * 默认生成的代码包含所有字段
 * 可以使用 excludes 去掉其他字段
 */
@Canonical(excludes = "lastName")
class Person {
    String firstName;
    String lastName;
    int age
}

def sara = new Person(firstName: "Sara", lastName: "Walker", age: 49)
println sara

class Worker {
    def work() { println 'get work done' }

    def analyze() { println 'analyze...' }

    def writeReport() { println 'get report written' }
}

class Expert {
    def analyze() { println "expert analysis..." }
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
class Manager {
    @Delegate
    Expert expert = new Expert()
    @Delegate
    Worker worker = new Worker()
}

def bernie = new Manager()
bernie.analyze()
bernie.work()
bernie.writeReport()


/**
 * 不可变对象是线程安全的
 * 将其字段标记为final是很好的实践选择
 * 如果 @Immutable 注解标记了一个类
 * Groovy会将其字段标记为final
 * 并且额外创建一些便捷方法
 *
 * 如下,groovy提供了一个构造器,其参数以类中字段定义的顺序依次列出
 * 此外还添加了 hashCode() equals() toString() 方法
 */
@Immutable
class CreditCard {
    String cardNumber
    int creditLimit
}

println new CreditCard("4000-1111-2222-3333", 1000)


/**
 * 使用 @Lazy
 * 把耗时对象的构建推迟到真正需要时,可以懒惰与高效并得,编写更少的代码,同时又能获得惰性初始化的所有好处
 * 如下 推迟创建 Heavy 实例,直到真正需要它时
 * 既可以在声明的地方直接初始化实例,也可以将创建逻辑包在一个闭包中
 *
 * groovy不仅推迟了创建,还将字段标记为 volatile 确保创建期间是线程安全的
 * 实例会在第一次访问这些字段的时候被创建
 *
 * 此外 @Lazy 注解提供一种轻松实现线程安全的虚拟代理模式 virtual proxy pattern 的方式
 */

class Heavy {
    def size = 10

    Heavy() { println "Creating Heavy with $size" }
}

class AsNeeded {
    def value
    @Lazy Heavy heavy1= new Heavy()
    @Lazy Heavy heavy2= {new Heavy(size:value)}()

    AsNeeded(){println "Created AsNeeded"}
}

def asNeeded = new AsNeeded(value:1000)
println asNeeded.heavy1.size
println asNeeded.heavy1.size
println asNeeded.heavy2.size


/**
 * 在groovy中,@Newify 注解帮助创建类似Ruby的构造器
 * 在此,new 是该类的一个方法
 * 该注解还可以用来创建类似 Python 的构造器,也类似Scala的applicator
 * 要创建类似Python 的构造器 必须向 @Newify 注解指明类型列表
 * 只有将 auto=false 这个值作为一个参数设置给 @Newify groovy才会创建 Ruby 风格的构造器
 * 在创建 DSL 时,@Newify 注解非常有用,它可以使得实例创建更像是一个隐式操作
 */
@Newify([Person,CreditCard])
def fluentCreate(){
    println Person.new(firstName: "John",lastName: "Doe",age: 20)
    println Person(firstName: "John",lastName: "Doe",age: 20)
    println CreditCard("1234-5678-1234-5678",2000)
}
fluentCreate()

/**
 * 单例模式 @Singleton
 * 单例模式 一般会创建一个静态字段,并创建一个静态方法来初始化该字段,然后返回单例
 * 必须确保静态方法是线程安全的,同时还要决定是否要惰性创建
 * 通过 @Singleton 可避免上述麻烦
 * 将代码 copy 到 groovyConsole 选择 Script 菜单下的 Inspect AST 菜单项可以看到生成的代码
 * 单例使用的双重检查 lazy = true
 */

@Singleton(lazy = true)
class TheUnique{
//    TheUnique(){println 'Instance created'}
    def hello(){println 'hello'}
}
println "Accessing TheUnique"
TheUnique.instance.hello()
TheUnique.instance.hello()