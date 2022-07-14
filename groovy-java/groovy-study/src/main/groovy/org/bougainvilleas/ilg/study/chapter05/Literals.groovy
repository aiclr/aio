package org.bougainvilleas.ilg.study.chapter05

/**
 * groovy 可以使用单引号创建字符串字面常量
 * 想显示创建一个 字符 char 需要 as char
 * 如果有任何方法调用需要，groovy 可能会隐式地创建 Character 对象
 */
println 'He said,"Thar is Groovy"'

str = 'A string'
println str.getClass().name

char a_char='a' as char
println a_char.getClass().name
/**
 * groovy 单引号创建的String 是一个纯粹的字面常量
 * 不会计算内部表达式
 * 双引号才会计算String中的表达式
 */
value =25
println 'The value is ${value}'
println "The value is ${value}"

/**
 * Java String 是不可变 Groovy 也不可变，一旦创建String实例
 * 不能通过调用更改器等方法修改 实例内容
 * 可以使用 [] 操作符读取 字符
 * 但是不能修改
 */

str='hello'
println str[2]
try{
    str[2]='!'
}catch(MissingMethodException ex)
{
    println ex
}

/**
 * 使用 双引号 “”或 正斜杠 // 创建表达式
 * 双引号经常用于定义字符串表达式
 * 正斜杠经常用于正则表达式（使用正斜刚将字符串内容包围在内）
 *
 * 如果表达式 是一个像value这样的简单变量名
 * 或者是一个简单的属性存取器 accessor 则包围表达式的{} 是可选的
 */
value=12
println "He said \$${value} for that."
println "He said \$$value for that."
println(/He said $${value} for that./)
println(/He said $$value for that./)


/**
 * groovy 惰性求值 lazy evaluation
 * 把一个表达式保存在一个字符串中 稍后再打印
 */
what = new StringBuilder('fence')
text = "The cowjumped over the $what"
println text
what.replace(0,5,"moon")
println text
println text.getClass().name
/**
 * GString  Groovy String
 * 以下为不同语法创建的对象类型
 * groovy 并不会简单地因为使用了双引号或正斜杠就创建 GString 实例
 * groovy 会智能地分析字符串 以确定该字符串是否可以使用一个简单的普通String蒙混过关
 *
 */
def printClassInfo(obj){
    println "class: ${obj.getClass().name}"
    println "supperclass: ${obj.getClass().superclass.name}"
}

val=125
printClassInfo("The Stock closed at ${val}")
printClassInfo(/The Stock closed at ${val}/)
printClassInfo("The is a simple String")
