package org.bougainvilleas.ilg.study.chapter13
/**
 * mixin 注入方法 mix in 混入
 * groovy 的 mixin 是一种运行时能力
 * 将多个类中的实现引入进来 或 混入
 *
 * 如果将一个类混入另一个类中,groovy 会在内存中把这些类的实例链接起来
 * 当调用一个方法时,groovy 首先将调用 路由到混入的类中
 * 如果该方法存在于这个类中,则由这个混入的类处理,
 * 否则由主类处理
 *
 * 可以将多个类混入到一个类中,最后加入的Mixin优先级最高
 *
 */
class Friend {
    def listen(){
        "$name is listening as a friend"
    }
}
println '注解@Mixin(Friend) 向类混入 Friend 已过时'
@Mixin(Friend)
class People{
    String firstName
    String lastName
    String getName(){"$firstName $lastName"}
}
john = new People(firstName: "John",lastName: "Smith")
println john.listen()


class Dog{
    String name
}
println 'Dog.mixin Friend 向类混入 Friend'
Dog.mixin Friend
buddy=new Dog(name: "Buddy")
println buddy.listen()

class Cat{
    String name
}
println '未混入 Friend'
try{
    rude=new Cat(name:"Rude")
    rude.listen()
}catch(ex)
{
    println ex.message
}
println '向实例混入 Friend'
socks = new Cat(name: "Socks")
socks.metaClass.mixin Friend
println socks.listen()

println '注解@Mixin已过时 trait 关键字替代' +
        'static mixins have been deprecated in favour of traits (trait keyword)'
trait Friend2{
    def listen(){
        "$name is listening as a friend2"
    }
}
trait Friend3{
    def listen(){
        "$name is listening as a friend3"
    }
}
class People2 implements Friend2,Friend3{
    String firstName
    String lastName
    String getName(){"$firstName $lastName"}
}
john = new People2(firstName: "John",lastName: "Smith")
println john.listen()
