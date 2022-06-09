## Groovy 自动导入的包

- java.lang
- java.util
- java.io
- java.net
- java.math.BigDecimal
- java.math.BigInteger
- groovy.lang
- groovy.util

## chapter02

1. `return`语句几乎总是可选的
2. 尽管可以使用分号分隔语句,但它几乎总是可选的
3. 方法和类默认是`public`的
4. `?.`操作符只有对象引用不为空时才会分派调用
    1. [Ease.groovy](src/main/groovy/org/bougainvilleas/ilg/study/chapter02/Ease.groovy)
5. 可以使用具名参数初始化`JavaBean`
6. `Groovy`不强迫我们捕获自己不关心的异常,这些异常会被传递给代码的调用者
    1. [ExceptionHandling.groovy](src/main/groovy/org/bougainvilleas/ilg/study/chapter02/ExceptionHandling.groovy)
7. `静态方法`内可以使用`this`来引用`CLass`对象
    1. [Wizard.groovy](src/main/groovy/org/bougainvilleas/ilg/study/chapter02/Wizard.groovy)

### JavaBean

> groovy 自动创建 getter setter\
> groovy 默认public 且不区分 public private protected\
> `groovyc` 会忽略 `@Override`

### boolean

|类型|为true条件|
|:---|:---|
|Boolean|值为true|
|Collection|集合不为空|
|Character|值不为0|
|CharSequence|长度大于0|
|Enumeration|hasMoreElements() 为 true|
|Iterator|hasNext() 为true|
|Number|Double值不为0|
|Map|该映射不为空|
|Matcher|至少有一个匹配|
|Object[]|长度大于0|
|其他任何类型|引用不为null|

### 注意

> groovy ==
> > Groovy 的 == 映射到 equals() 方法 \
> > 当实现 Comparable 接口时 会将 == 映射到 compareTo() 方法 \
> > groovy 的 is() 是 比较 内存地址
>
> 类型检查默认关闭
> > groovy 中 x=y 在语义上等价于 x=(ClassOfx)y 强制类型转换 \
> > groovy编译器不会验证类型,只是进行强制类型转换,然后将其留给运行时处理 可能出现 GroovyCastException 异常 \
> > 通过 javap -c CLassFileName 分析字节码验证上述内容 \
> > 如果调用一个不存在的方法,也不会出现编译错误,运行时会出现 MissingMethodException \
> > 利用上诉内容,可以在代码编译时和执行时动态注入缺失的方法


> groovy 方法内不能有任何代码块 如下
> > java的代码块,groovy编译器会错误地认为要定义一个闭包,并给出编译错误

```jshelllanguage
public void method()
{
    System.out.println("hello");
    //groovy编译器会错误地认为 下面的代码块 是要定义一个闭包,并给出编译错误
    {
        System.out.println("hello");
    }
}
```



### groovy 新关键字

> `def` 用于定义方法,属性,局部变量
>
> `in` 用于在for循环中指定循环的区间
>
> `it` 虽然不是关键字,但是 闭包内默认使用此变量名

