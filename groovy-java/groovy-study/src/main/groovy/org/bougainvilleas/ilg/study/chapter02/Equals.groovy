package org.bougainvilleas.ilg.study.chapter02

/**
 * Groovy 的 == 等价于 java 的 equals()
 * groovy 的 is() 是 比较 内存地址
 *
 */
str1 = 'hello'
str2 = str1
str3=new String('hello')
str4='hello'

println "str1 == str2 : ${str1==str2}"
println "str1 == str3 : ${str1==str3}"
println "str1 == str4 : ${str1==str4}"

println "str1.is(str2) : ${str1.is(str2)}"
println "str1.is(str3) : ${str1.is(str3)}"
println "str1.is(str4) : ${str1.is(str4)}"