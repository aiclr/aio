package org.bougainvilleas.ilg.study.chapter05

/**
 * JDK java.util.regex 包含了使用正则表达式进行模式匹配的API
 * ~ 操作符可以方便创建 regex模式 这个操作符映射到String类的negate()方法
 *
 * 总结：
 *      1. 从字符串创建一个模式，使用 ~ 操作符
 *      2. 要定义一个RegEx，使用正斜杠 /[G/g]roovy/
 *      3. =~ 执行RegEx部分匹配
 *      4. ==~ 执行RegEx精确匹配
 *
 */
obj = ~"hello"
println obj.getClass().name

/**
 * 使用正斜杠、单引号、或双引号创建 RegEx
 * 正斜杠优势： 不必对反斜杠进行转义
 * 为了方便匹配正则表达式 groovy 提供一对操作符 =~和==~
 *
 * =~ 执行RegEx部分匹配
 * ==~ 执行RegEx精确匹配
 *
 * =~ 操作符会返回一个 Matcher 对象 是 java.util.regex.Matcher 实例
 * groovy 对Matcher 的布尔求值处理不同于java
 * 只要至少有一个匹配 就会返回true
 * 如果有多个匹配 则 matcher 会包含一个匹配的数据
 * 有助于快速获得匹配给定RegEx的文本中的部分内容
 */
regex = /\d*\w*/
regex = "\\d*\\w*"

pattern = ~"(G|g)roovy"
text = 'Groovy is Hip'
if(text =~ pattern)
    println "match"
else
    println "no match"

if(text ==~ pattern)
    println "match"
else
    println "no match"


matcher1 = 'Groovy is groovy' =~ /(G|g)roovy/
print "Size of matcher1 is ${matcher1.size()} "
println "With elements ${matcher1[0]} and ${matcher1[1]}."

matcher2 = 'Groovy is groovy' =~ /[G|g]roovy/
print "Size of matcher2 is ${matcher2.size()} "
println "With elements ${matcher2[0]} and ${matcher2[1]}."

matcher3 = 'Groovy is groovy' =~ /([Gg])roovy/
print "Size of matcher3 is ${matcher3.size()} "
println "With elements ${matcher3[0]} and ${matcher3[1]}."

/**
 * 使用 replaceFirst() 或 replaceAll() 方法方便地替换匹配的文本
 */
str = 'Groovy is groovy, really groovy'
println str
result = (str =~ /groovy/).replaceAll('hip')
println result