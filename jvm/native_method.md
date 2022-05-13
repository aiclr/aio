# Native Method

- 概述

```text
Native Method是java调用非java代码实现的接口
该方法的实现由非java语言实现，c或c++这个特征非java所特有，很多编程语言都有这一机制
例如：c++中可以使用extern "C" 告知c++编译器去调用一个c的函数

A native method is a java method whose implementation is provided by non-java code

在定义一个native method时，并不提供实现体（有些像定义一个java interface） 因为其实现体是由非java语言在外面实现的
本地接口的作用是融合不同的编程语言为Java所用，初衷是融合c/c++程序

使用native标识符修饰，不能与abstract修饰符连用
```

- 例子
    - java.lang.Object.public native int hashCode();
    - java.lang.Thread
        - private native void start0();
        - private native void setPriority0(int newPriority);
        - private native void stop0(Object o);
        - private native void suspend0();
        - private native void resume0();
        - private native void interrupt0();
        - private native void setNativeName(String name);
- 使用原因

```text
1. 主要原因是java应用需要与java外面的环境交互。
    有些层次的任务使用java实现起来不容易，或者对程序的效率有影响
    当java需要与一些底层系统，如操作系统或某些硬件交换信息时的情况，native method正是这样一种交流机制
    通过native method提供一个非常简洁的接口，无需去了解java应用之外的繁琐的细节
2. 与操作系统交互
    JVM支持java语言本身和运行时库，它是java程序赖以生存的平台，由一个解释器（解释字节码）和一些连接到本地代码的库组成
    JVM不是一个完整的系统，经常依赖于一些底层系统的支持。这些底层系统常常是强大的操作系统
    通过使用native method得以用java实现了jre与底层系统的交互，甚至JVM的一些部分都是C写的
    如果使用一些java语言本身没有提供封装的操作系统的特性时，我们也需要使用本地方法
3. sun`s java
    Sun的解释器是用C实现的，这使得它能像一些普通的C一样与外部交互，jre大部分是Java实现的，也有通过一些native method与外界交互
    例如java.lang.Thread的setPriority()方法是由java实现的，但是它实现调用的是该类的native method setPriority0()
    setPriority0()方法是由C实现的，并被植入JVM内部，在Windows95的平台上，这个本地方法最终将调用Win32 setPriority() API
    这是一个native method的具体实现由JVM直接提供，更多的情况是native method由外部的动态链接库(external dynamic link library)提供，然后被JVM调用
```

- 现状

```text
目前该方法使用的越来越少，除非是与硬件有关的应用
比如通过java程序驱动打印机或者Java系统管理生产设备
在企业级应用中已经比较少见因为现在的异构领域间通信很发达，
比如可以使用socket通信，也可以使用Web Service等
```
## Native Method Interface（JNI） 本地方法接口
## Native Method Library 本地方法库
## Native Method Stack 本地方法栈(调用c语言，与本地方法库、本地方法接口交互)
- 与stack相似
- 管理本地方法的调用
- 线程私有
- 允许被实现成固定或可动态扩展的内存大小
    - 如果线程请求分配的stack容量超过本地方法栈允许的最大容量，JVM会抛出StackOverFlowError异常
    - 如果本地方法栈可以动态扩展，并且在尝试扩展时无法申请到足够的内存，或者在创建新的线程时没有足够的内存去创建对应的本地方法栈那么JVM将抛出OutOfMemoryError异常
- 本地方法时使用c语言实现的
- 具体做法是native method stack中登记native方法在Execution engine执行时加载native method library
- 当某个线程调用一个native method时他就进入了一个全新的并且不再受虚拟机限制的世界，它和虚拟机拥有同样的权限
    1. native method可以通过native method interface来访问JVM内部的runtime data area
    2. 它甚至可以直接使用本地处理器中的寄存器
    3. 直接从本地内存的堆中分配任意数量的内存
- 并不是所有JVM都支持native method，JVM规范并没有明确要求本地方法栈的使用语言、具体实现方式、数据结构等，如果JVM不打算支持native method也可以不是实现native method stack
- HotspotJVM中直接将native method stack 与JVM stack合二为一