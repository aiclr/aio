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

## chapter03 动态类型(groovy属于强类型语言)

> 动态类型语言中的类型是在**运行时**推断的 \
> 方法及其实参也是在运行时检查 \
> 通过这种能力可以在运行时向类中注入行为,从而使代码比严格的静态类型具有更好的扩展性
> > 借助动态类型,可以用比Java更少的代码创建灵活的设计 \
> > 将实参的类型验证推迟到**运行时**这一特性为Groovy中的多态注入了活力 \
> > 利用**多方法**(multimethods)这一工具,可以为与实参的运行时类型相关的操作提供替换行为

## 闭包三属性 this owner delegate [参考：ThisOwnerDelegate.groovy](src/main/groovy/org/bougainvilleas/ilg/study/chapter04/ThisOwnerDelegate.groovy)

> 用于确定哪个对象处理该闭包内的方法调用 \
> 闭包内引用的变量和方法都会绑定到 this \
> this负责处理任何方法调用,以及任何属性和变量的访问 \
> 如果 this 无法处理,则转向 owner 最后是 delegate

- this 上下文 脚本实例
- owner
- delegate 
  - delegate 会设置为 owner
  - with函数会修改 delegate 以执行动态路由

## java 和 groovy 混合

![混合使用java类、groovy类和脚本](src/main/resources/Image00008.jpg)

> 在groovy代码中使用groovy类，无需额外操作，直接可以工作
> > 需要确保所依赖的类在类路径 classpath 下；是字节码、源代码
>
> java类中使用groovy脚本
> > JSR 223 提供的 ScriptEngine API
>
> Java中使用groovy类 或者 groovy中使用java类
> > 利用groovy 联合编译工具（joint-compilation）

## Groovy 对象

> 带有附加功能的Java对象 \
> Groovy使用的三类对象：
> > POJO (plain old java object 普通java对象)扩展了java.lang.Object\
> > POGO (plain old groovy object 普通groovy对象) 扩展了java.lang.Object 同时实现了groovy.lang.GroovyObject \
> > Groovy 拦截器，扩展了 GroovyInterceptable 的groovy对象，具有方法拦截功能

### Groovy 源代码 GroovyObject

> invokeMethod()、getProperty()、setProperty()使Java对象具有高度动态性，可以使用他们来处理运行中创建的方法和属性 \
> getMetaClass()、setMetaClass() 使创建代理拦截POGO上的方法调用、在POGO上注入方法变得非常容易 \
> 一旦一个类被加载到JVM中，就不能修改它的元对象Class，可以通过setMetaClass() 修改它的MetaClass，好像对象在运行时修改了它的类

```groovy
package groovy.lang;

import groovy.transform.Internal;

public interface GroovyObject {

    /**
     * Invokes the given method.
     *
     * @param name the name of the method to call
     * @param args the arguments to use for the method call
     * @return the result of invoking the method
     */
    @Internal
    // marked as internal just for backward compatibility, e.g. AbstractCallSite.createGroovyObjectGetPropertySite will check `isMarkedInternal`
    default Object invokeMethod(String name, Object args) {
        return getMetaClass().invokeMethod(this, name, args);
    }

    /**
     * Retrieves a property value.
     *
     * @param propertyName the name of the property of interest
     * @return the given property
     */
    @Internal
    // marked as internal just for backward compatibility, e.g. AbstractCallSite.createGroovyObjectGetPropertySite will check `isMarkedInternal`
    default Object getProperty(String propertyName) {
        return getMetaClass().getProperty(this, propertyName);
    }

    /**
     * Sets the given property to the new value.
     *
     * @param propertyName the name of the property of interest
     * @param newValue the new value for the property
     */
    @Internal
    // marked as internal just for backward compatibility, e.g. AbstractCallSite.createGroovyObjectGetPropertySite will check `isMarkedInternal`
    default void setProperty(String propertyName, Object newValue) {
        getMetaClass().setProperty(this, propertyName, newValue);
    }

    /**
     * Returns the metaclass for a given class.
     *
     * @return the metaClass of this instance
     */
    MetaClass getMetaClass();

    /**
     * Allows the MetaClass to be replaced with a derived implementation.
     *
     * @param metaClass the new metaclass
     */
    void setMetaClass(MetaClass metaClass);
}

```

### Groovy 源代码 GroovyInterceptable

> 扩展了GroovyObject的标记接口，对于实现了该接口的对象而言，其上所有方法调用，不管是否存在，都会被invokeMethod() 方法拦截

```groovy
package groovy.lang;

/**
 * Marker interface used to notify that all methods should be intercepted through the <code>invokeMethod</code> mechanism
 * of <code>GroovyObject</code>.
 */
public interface GroovyInterceptable extends GroovyObject {
}
```

## Groovy 支持对POJO和POGO进行元编程

> 当调用一个方法时Groovy会检查目标对象时一个POJO还是POGO，不同类型，groovy方法处理不同 \
> 对于POJO Groovy 维护了MetaClass的一个 MetaClassRegistry
> > 调用方法时，Groovy 会去应用类的 MetaClassRegistry 取它的MetaClass，将方法调用委托给它\
> > 因此在它的MetaClass上定义的任何拦截器或方法，都优先于 POJO原来的方法
>
> 对于POGO POGO有一个到其MetaClass的直接引用

![POJO POGO 及其 MetaClass](src/main/resources/Image00009.jpg)

> POGO 调用方法时，groovy 会采取一些额外的步骤，如下图：
> > 如果对象实现了GroovyInterceptable,那么所有的调用都会被路由到它的invokeMethod(),在此拦截器内，可以再把调用路由给实际的方法，使类AOP的操作成为可能 \
> > 如果对象未实现GroovyInterceptable,会先查找其MetaClass中的方法
> > > 如果没有则查找POGO自身上的方法，如果该POGO没有这样的方法，Groovy会以方法名查找属性或字段 \
> > > 如果属性或字段使Closure类型的（闭包），Groovy会调用它，代替方法调用 \
> > > 如果没有找到这样的属性或字段，会做最后两次尝试
> > > > 如果POGO有一个名为methodMissing()的方法，则调用该方法 \
> > > > 否则调用POGO的invokeMethod()
> > > > > 如果POGO实现了invokeMethod(),会被调用 \
> > > > > invokeMethod()默认实现会抛出 MissingMethodException异常，说明调用失败

![Groovy处理POGO上的方法调用流程](src/main/resources/Image00010.jpg)

## MetaClass

> groovy.lang.ExpandoMetaClass 是 MetaClass 接口的众多不同实现之一 \
> 默认情况下 groovy 目前并没有使用 groovy.lang.ExpandoMetaClass \
> 当我们向 metaClass 添加一个方法时，默认的 metaClass 会被用一个 groovy.lang.ExpandoMetaClass 实例替换掉 \
> [eg](src/main/groovy/org/bougainvilleas/ilg/study/chapter12/MetaClassUsed.groovy)

## MOP

- Groovy 的 MOP 支持以下3种技术注入行为种的任何一种
	- 分类category
	- ExpandoMetaClass
	- Mixin
	
## 元编程

> 与某些只有运行时能力的动态类型语言不同，groovy同时提供了运行时和编译时元编程 \
> 将与类和实例上方法的拦截、注入甚至合成相关的决策推迟到运行时。就元编程而言，这些就够了 \
> 编译时元编程时一种高级特性，主要是框架或工具的编写者使用\
> 借助Groovy可以在编译时分析和修改程序的结构。可以为应用带来高度的可扩展性\
> 同时支持添加新的横切特性
> > 无需修改源代码，即可为线程安全、日志消息和在代码的不同部分执行前置和后置的检验操作等，对类进行检查

### groovyConsole工具 可以显示代码的AST，Script菜单下 Inspect AST菜单项

### canVote() 方法的 AST(抽象语法树)

> canVote() 方法很短，只是返回三元操作符的结果，但是其AST却异常丰富，包含很多细粒度的细节信息（参见下图）

![canVote() 方法的 AST(抽象语法树)](src/main/resources/Image00013.jpg)

> groovy 编译器允许我们进入其编译阶段，一窥其所处理的AST（抽象语法树）\
> AST描述了程序中的表达式和语句，使用节点表示 \
> 随着编译过程的进行，AST会被变换,包括节点的插入、删除和重新排列 \
> 在编译过程中，可以随着AST的演进对他进行检查，以及命令编译器去标记警告或错误
 
## 单元测试

> 通过 extends 重写方法 模拟 groovy 和 java 代码，使用现有的单元测试和模拟框架来测试 
> 
> 对 groovy 代码进行单元测试，可以使用分类和ExpandoMetaClass。 二者都支持通过拦截方法调用，实现模拟。\
> 如果使用 ExpandoMetaClass 不必创建额外的类，测试会很简洁
> 
> 对与参数对象的简单模拟可以使用 Map 或 Expando
> 
> 如果想对多个方法设置测试预期，以及想模拟被测方法内部的依赖，可以使用 StubFor
> 
> 要测试状态以及行为，可以使用MockFor

## DSL (Domain-Special Language 领域特定语言)

> 特点：
> > 上下文驱动
> > > 语境 生命周期
> > >
> > 非常流畅
> > > 改进代码的可读性，同时使代码自然地流动\
> > > 要在设计上实现流畅并不容易，应该让用户使用起来很流畅
> 
> DSL 针对的是“某一特定类型的问题”。其语法聚焦于指定的领域或问题。\
> 不会像使用 Java、Groovy或C++等语言那样将其应用于一般性编程任务，因为DSL的应用领域和能力都非常有限。

### 设计一门 DSL

> 必须确定要设计 外部 DSL 还是 内部 DSL
> 
> 外部DSL 例如：Ant 使用的是XML，是外部 DSL
> > 外部DSL 定义了一门新语言。\
> > 可以灵活地选择语法，之后是解析新语言中的命令并执行动作\
> > 解析 DSL 可以使用诸如 C++ 和 Java 等语言，使用它们提供的解析功能及库，\
> > 以及利用他们提供大量解析功能和库来处理繁重的任务
> >
> 内部DSL 例如 Gant 使用 Groovy 来解决 Ant 同样的问题，但它是内部 DSL
> > 也称做嵌入式 DSL 同样定义了一门语言，但是语法受到现有语言的约束\
> > 不必使用任何解析器，但必须巧妙地将语法映射到底层语言中的方法和属性等构造。\
> > 内部 DSL 用户可能意识不到自己正在使用的是一门更广的语言的语法。\
> > 为了让底层语言工作，创建内部 DSL 需要在设计上付出巨大的努力，而且还需要一些聪明的技巧。
> > > 设计内部 DSL
> > > > 动态语言很适合设计和实现内部的 DSL。\
> 这些语言提供了很好的 元编程能力 和 灵活的语法，便于轻松地加载和执行代码片段\
> 并非所有的动态语言都是一样的。\
> 如 Ruby 动态类型、括号可选、可以用冒号代替双引号引用字符串，等等。 为创建内部 DSL 提供极大支持\
> 如 Python 中空白是有意义的，可能会成为创建 DSL 的障碍\
> Groovy 的动态类型和元编程能力对于创建内部的 DSL 帮助很大。\
> 但是groovy 对括号的处理，以及它没有 Ruby 提供的优雅的冒号符号，都会有一些限制。

### Groovy 与 DSL

- 动态类型与可选类型
- 动态加载、操纵和执行脚本的灵活性
- 分类和 ExpandoMetaClass 可以打开类
- 闭包为执行提供了很好的上下文
- 操作符重载有助于自由地定义操作符
- 生成器支持
- 灵活的括号
  - 调用需要参数的方法时，不要求括号
  - 调用没有参数的方法时，必须带括号