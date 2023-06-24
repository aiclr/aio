---
layout: content
title: JVM
targets:
  - name: Top
    link: jvm
  - name: 线程
    link: 线程
  - name: 后台系统线程
    link: 后台系统线程
---

# jvm

## 备注

> 内存
> > 是非常重要的系统资源，是硬盘和cpu的中间仓库及桥梁，承载着操作系统和应用程序的实时运行。
> > JVM内存布局规定了java在运行过程中内存申请、分配、管理的策略，保证了JVM的高效稳定运行。
> > 不同的JVM对于内存的划分方式和管理机制存在着部分差异。

> JVM 定义了若干种程序运行期间会使用到的`run-time data areas`
> > 生命周期与JVM一致的内存区域
> > - `heap area`
> > - `non-heap`
> >    - `method area`jdk8采用`metaspace`<sub>元空间</sub>作为`method area`的落地实现
> >    - **代码缓存**<sub>`JIT`编译产物</sub>
>
> > 线程私有区域
> > - [program counter registers](../pcregister)
> > - [jvm stacks](../jvmstacks)
> > - [native stacks](../nativemethodstacks)
>
> `public class Runtime extends Object`
> > 每个JVM只有一个`Runtime`实例。即为**运行时环境**\
> > Every Java application has a single instance of class `Runtime` that allows the application to interface with the environment in which the application is running. The current runtime can be obtained<sub>获得</sub> from the `getRuntime` method.

## 线程

> 线程是一个程序里的运行单元。JVM允许一个应用有多个线程并发的执行\
> `HotspotJVM`里，每个线程都与操作系统的本地线程直接映射
> > 当一个`java线程`准备好执行以后，一个`操作系统的本地线程`也同时创建，`java线程`执行终止后`本地线程`也会回收\
> > `操作系统`负责所有线程的安排调度到任何一个可用的`cpu`上，一旦`本地线程`初始化成功，会调用`java线程`中的`run()`方法
>
> 线程出现异常
> > 捕获处理异常也相当于`java线程`正常终止\
> > 未捕获处理异常，`java线程`肯定终止，此时操作系统还要判断一下是否要终止`JVM`
> >   - `deamon`<sub>守护线程</sub>，如果`JVM`中只剩`demon`则`JVM`可以退出
> >   - 非守护线程 当前线程为最后一个非守护线程则终止`JVM`

### 后台系统线程

> `hotspot JVM`的后台系统线程
> 使用`jconsole`或者其他调试工具，能看到后台有许多线程在运行，不包括`main`方法的`main`线程以及所有这个`main`线程自己创建的线程
>
> **虚拟机线程**
> > 这种线程的操作是需要`JVM`达到**安全点**才会出现。\
> > 这些操作必须在不同线程中发生的原因是他们都需要`JVM`达到**安全点**，这样`heap area`才不会变化。
> > 这种线程的执行类型包括`STW`<sub>stop-the-world</sub>的**垃圾收集**、**线程栈收集**、**线程挂起**、**偏向锁撤销**。
>
> **周期任务线程**
> > 这种线程是时间周期事件的体现<sub>比如中断</sub>，他们一般用于周期性操作的调度执行
>
> **GC线程**
> > 这种线程对在`JVM`里不同种类的垃圾收集行为提供了支持
>
> **编译线程**
> > 这种线程在运行时会将**字节码**编译成**本地代码**
>
> **信号调度线程**
> > 这种线程**接收信号**并发送给`JVM`,在它内部通过调用适当的方法进行处理
