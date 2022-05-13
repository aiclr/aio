# GC
```text
GC是垃圾回收线程，与之对应的是用户线程
收集垃圾时STW（Stop The World），进行检索垃圾，会导致用户线程暂停，影响应用

JVM在进行GC时，并非每次都对三个内存（新生代、老年代、方法区）区域一起回收，大部分时候回收指新生代

针对Hotspot VM实现，GC按照回收区域分为两大类型
1. 部分收集（partial GC）：不是完整收集整个Java 堆的垃圾收集
    1. 新生代收集 Minor GC/Young GC:只是新生代（Eden\S0、S1）的垃圾收集
    2. 老年代收集 Major GC/Old GC:只是老年代的垃圾收集
        1. 目前只有CMS GC（多线程）会有单独收集老年代的行为
        2. 注意，很多时候Major GC会和Full GC混淆使用，需要具体分辨时老年代回收还是整堆回收
    3. 混合收集 Mixed GC：收集整个新生代以及部分老年代的垃圾收集
        1. 目前只有G1 GC（Garbage First GC）会有这种行为
    
2. 整堆收集（Full GC）：收集整个java堆和方法区的垃圾收集
```
- 调优就是减少GC出现次数
- 主要调优Major GC和Full GC
## Minor GC
- 当Eden区满触发
- Survivor区满不会引发MinorGC
- 每次MinorGC会清理年轻代内存
- MinorGC非常频繁
- 回收速度快
- 会引发STW（Stop The World），暂停其他用户线程，等待垃圾回收结束，用户线程才恢复运行
## Major GC 
- 对象从Old区消失时，则发生了Major GC或Full GC
- 出现MajorGC 经常会伴随至少一次的MinorGC（但非绝对，在Parallel Scavenge收集器的收集策略里直接进行MajorGC的策略选择过程）
    - 当Old区不足时，会先尝试触发MinorGC，如果之后空间还不足，则触发MajorGC
- Major GC比Minor GC慢10倍以上，STW的时间更长
- Major GC后内存还不足，就报OOM
## Full GC（待补充） 
- 触发机制
    1. 调用System.gc()时，系统建议执行Full GC，但是不必然执行
    2. Old区空间不足
    3. 方法区空间不足
    4. 通过Minor GC后晋升Old区的对象平均大小，大于Old区的可用内存
    5. 由Eden区、Form区向To区复制时，对象大小大于To区可用内存，则把该对象转存到Old区，且Old区的可用内存小于该对象大小
- 说明：Full GC是开发或调优中尽量要避免的，这样暂停时间会短一些    
    