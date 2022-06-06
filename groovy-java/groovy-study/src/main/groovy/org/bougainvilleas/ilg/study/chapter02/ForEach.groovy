package org.bougainvilleas.ilg.study.chapter02

/**
 * 其他 fori 循环
 * {@link LightGreetings}
 */
String[] greetings = ["Hello", "Hi", "Howdy"]

println 'for-i'
for (int i = 0; i < greetings.size(); i++) {
    println greetings[i]
}

println '\nfor-i-in'
for (i in 0..greetings.size()-1) {
    println greetings[i]
}

println '\nupto'
0.upto(greetings.size()-1) {
    println greetings[it]
}

println '\ntimes'
greetings.size().times {
    println greetings[it]
}

println '\nstep'
0.step(greetings.size(), 1) {
    println greetings[it]
}


/**
 * groovy 指定类型可以使用类型 String 或者使用 def
 */
println '\nfor-each'

for (String greet : greetings) {
    println greet
}
println '\nfor-each-def'

for (def greet : greetings) {
    println greet
}

/**
 * groovy 不想指定类型,使用 in 关键字代替冒号
 */
println '\nfor-in'

for (greet in greetings) {
    println greet
}

/**
 * each() 内部迭代器
 */
println '\neach()'
greetings.each { println it }