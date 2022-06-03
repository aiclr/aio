package org.bougainvilleas.ilg.study.chapter02

/**
 * Groovy 支持操作符重载
 * 利用这点可以创建DSL(领域特定语言)
 * Java不支持操作符重载
 * Groovy将每个操作符映射到一个标准方法
 * 在java中可以调用这些标准方法,在groovy中 既可以使用操作符又可以使用标准方法
 *
 */


//++操作符 映射的是String类的next()方法
for(ch='a';ch<'d';ch++)
{
    println ch
}

//for-each 语法 也用到String 的next()方法
for(ch in 'a'..'d')
{
    println ch
}

//集合类也重载了一些操作符
//<< 操作符,被转换为Groovy在Collection上添加的leftShift()方法
lst=['hello']
lst<<'there'
println lst

/**
 * 重载+操作符,计算复数相加,实部+实部 虚部+虚部
 */
class ComplexNumber
{
    def real,imaginary
    def plus(other)
    {
        new ComplexNumber(real: real+other.real,imaginary: imaginary+other.imaginary)
    }
    String toString()
    {
        "$real ${imaginary>0?'+':''} ${imaginary}i"
    }
}

c1=new ComplexNumber(real:1,imaginary: 2)
c2=new ComplexNumber(real:4,imaginary:1)
println c1+c2

