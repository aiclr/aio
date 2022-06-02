package org.bougainvilleas.ilg.study.chapter02

/**
 * 2.5 使用多赋值
 * 从方法返回多个结果,并将他们一次性赋给多个变量
 * 返回一个数组,
 * 然后将多个变量用逗号分隔,放在圆括号中,置于赋值表达式左侧即可
 */


def splitName(fullName)
{
    fullName.split(' ')
}

def (firstName, lastName)= splitName('James Bond')

println "$lastName,$firstName $lastName"

/**
 * 利用这一特性交换变量
 */

def name1="Thomson"
def name2="Thompson"
println "$name1 and $name2"
//交换 name1 name2
(name1,name2)=[name2,name1]

println "$name1 and $name2"

/**
 * 赋值表达式左侧变量与右侧的值数目相同时,一一对应进行赋值
 * 当赋值表达式左侧变量比右侧的值数目多时,groovy会将左侧多余的变量设置为null
 * 当赋值表达式左侧变量比右侧的数目少时,groovy会将右侧多余的值丢弃
 */

//'Spike','Tyke' 会被丢弃
def (String cat,String mouse)=['Tom','Jerry','Spike','Tyke']
println "$cat and $mouse"

//third 会被赋null
def (first,second,third)=['Tom','Jerry']
println "$first , $second and $third"

/**
 * 如果多余的变量是不能设置为null的基本类型
 * groovy将抛出异常
 */
