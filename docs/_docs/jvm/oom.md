---
title: OutOfMemoryError
targets:
  - name: Top
    link: javalangoutofmemoryerror
---

# java.lang.OutOfMemoryError

> 要解决`OOM`异常或者`heap space`异常，一般首先通过`Eclipse Memory Analyzer`<sub>内存映像分析工具</sub> 对`dump`出来的**堆转储快照**进行分析.重点是确认内存中的对象是否是必要的，也就是要先分清到底是`memory leak`<sub>内存泄漏</sub>还是`memory overflow`<sub>内存溢出</sub>
> > `memory leak`
> > > 可进一步通过工具查看**泄漏对象**到`GC Roots`的**引用链**。找到泄漏对象是通过怎样的路径与`GC Roots`相关联并导致**垃圾收集器**无法自动回收他们。\
> > > 掌握了泄漏对象的类型信息，以及`GC Roots`引用链的信息，就可以比较准确地定位泄漏代码的位置
> >
> > `memory overflow`
> > > 不存在`memory leak`，即内存中的对象确实都还必须存活，那就应当检查`jvm heap options`<sub>堆参数`-Xms`和`-Xmx`</sub>与机器**物理内存**对比看是否还可以调大，从代码上检查是否存在某些**对象生命周期过长**、**持有状态时间过长**的情况，尝试减少程序运行期的内存消耗。
