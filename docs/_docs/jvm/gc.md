---
layout: content
title: Garbage Collection
targets:
  - name: Top
    link: garbage-collection
  - name: Minor GC
    link: minor-gc
  - name: Major GC
    link: major-gc
  - name: Full GC
    link: full-gc
---

### Garbage Collection

![gc.svg](/assets/images/jvm/GC.svg)

> [java8 hotspot 官网文档](https://docs.oracle.com/javase/8/docs/technotes/guides/vm/gctuning/toc.html)\
> [java17 hotspot 官网文档](https://docs.oracle.com/en/java/javase/17/gctuning/introduction-garbage-collection-tuning.html)

> `GC`是**垃圾回收线程**，与之对应的是**用户线程**. 收集垃圾时`STW`<sub>Stop The World</sub>，进行检索垃圾，会导致**用户线程**暂停，影响应用\
> `JVM`在进行`GC`时，并非每次都对三个内存<sub>`Young、Old、method area`</sub>区域一起回收，大部分时候回收指**Young区**
>
> 针对`Hotspot JVM`实现，`GC`按照回收区域分为两大类型
> 1. **部分收集**<sub>`partial GC`</sub>：不是完整收集整个`heap area`的垃圾收集
>     - **新生代**收集 `Minor GC/Young GC`: 只是`Young区`<sub>`Eden区、s0或s1`</sub>的垃圾收集
>     - **老年代**收集 `Major GC/Old GC`: 只是`Old区`的垃圾收集
>         - 目前只有 **Concurrent<sub>并发</sub>Mark Sweep<sub>`CMS`</sub> Collector**会有单独收集老年代的行为
>         - 注意，很多时候`Major GC`会和`Full GC`混淆使用，需要具体分辨是`Old区`回收还是`整堆`回收
>     - **混合收集** `Mixed GC`：收集**整个新生代**以及**部分老年代**的垃圾收集
>         - 目前只有**Garbage-First<sub>`G1`</sub> Garbage Collector**.会有这种行为
> 2. **整堆收集**<sub>`Full GC`</sub>： 收集整个`heap area`和`method area`的垃圾收集
>
> 调优就是**减少GC**出现次数. 主要调优`Major GC`和`Full GC`

## Minor GC

- 当`Eden`区满触发
- `Survivor`区满**不会**引发`MinorGC`
- 每次`MinorGC`会清理`Young区`<sub>`Eden区、s0或s1`</sub>
- `MinorGC`非常频繁
- **回收速度快**
- 会引发`STW`<sub>Stop The World</sub>，暂停其他**用户线程**，等待垃圾回收结束，用户线程才恢复运行

## Major GC

- 对象从`Old区`消失时，则发生了`Major GC`或`Full GC`
- 出现`Major GC` 经常会伴随至少一次的`MinorGC`<sub>但非绝对，在`Parallel Scavenge`收集器的收集策略里直接进行`MajorGC`的策略选择过程</sub>
  - 当`Old区`不足时，会先尝试触发`MinorGC`，如果之后空间还不足，则触发`MajorGC`
- `Major GC`比`Minor GC`**慢10倍以上**，`STW`的时间更长
- `Major GC`后内存还不足，会抛出`OOM`异常

## Full GC

- 触发机制
    1. 调用`System.gc()`时，系统建议执行`Full GC`，但是**不必然执行**
    2. `Old区`空间不足
    3. `method area`空间不足
    4. 通过`Minor GC`后晋升`Old区`的对象平均大小，大于`Old区`的可用内存
    5. 由`Eden区`和`Survivor Form区`向 `Survivor To区`复制时，对象大小**大于**`Survivor To区`可用内存，则把该对象转存到`Old区`，且`Old区`的可用内存小于该对象大小
- 说明：`Full GC`是开发或调优中尽量要避免的，这样`STW`时间会短一些
