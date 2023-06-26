---
title: JVM Options
targets:
  - name: Top
    link: jvm-options
---

# JVM Options

> 注意
> > `-server` 启动`server模式`，`64位`系统默认是Server模式，在server模式下才可以启用**逃逸分析**
> > ```
> > mi :: ~ » java -version
> > openjdk version "17.0.7" 2023-04-18
> > OpenJDK Runtime Environment (build 17.0.7+7)
> > OpenJDK 64-Bit Server VM (build 17.0.7+7, mixed mode)
> > 
> > mi :: ~ » java -version
> > openjdk version "1.8.0_372"
> > OpenJDK Runtime Environment (build 1.8.0_372-b07)
> > OpenJDK 64-Bit Server VM (build 25.372-b07, mixed mode) 
> > ```

> `-XX:+PrintFlagsInitial` 查看所有参数的默认初始值 \
> `-XX:+PrintFlagsFinal` 查看所有参数的最终值<sub>修改后的值</sub> 

> `-Xms10M`或者`-XX:InitialHeapSize=10M` 设置初始`heap size`，只影响**新生区**和**养老区**， 默认大小为物理机内存大小除以64。\
> `-Xmx10M`或者`-XX:MaxHeapSize=10M` 设置最大`heap size`，只影响**新生区**和**养老区** 默认大小为物理机内存大小除以4。
> > 通常将`-Xms`和`-Xmx`设置为相同的值,目的是为了能够在Java垃圾回收机制清理完**堆区**后不需要重新分割计算堆区的大小从而提高性能

> 设置`Young区`和`Old区`的比例
> > `-XX:NewRatio=2` '默认2，表示`Young`占1份，`Old`占2份'\
> > `-XX:NewRatio=4` '表示`Young占`1份，`Old`占4份'
>
> `-Xmn100M`设置`Young区`的大小<sub>**一般不使用**</sub>。当与`-XX:NewRatio=2`一起配置冲突时，以`-Xmn`设置的值来分配Young区大小，剩余的区域分给Old区

> `-XX:SurvivorRatio=8` 设置`Young区`下的`Eden区`、`survivor0`区、`survivor1`区的比例
> > 默认值`8：1：1`。由于**自适应的内存分配策略**，查看时可能是`6：1：1`，显式地设置为`8`查看时才是`8：1：1`。

> 开启 or 关闭**自适应的内存分配策略**
> > 开启 `-XX:+UseAdaptiveSizePolicy`\
> > 关闭 `-XX:-UseAdaptiveSizePolicy`

> `-XX:MaxTenuringThreshold=15` 设置`Promotion`<sub>晋升Old区阈值</sub>。默认值`15`。

> 开启 or 关闭 [TLAB](tlab)<sub>`Thread Local Allocation Buffer`</sub>。
> > `-XX:+UseTLAB` 默认开启
> > `-XX:-UseTLAB` 关闭'
>
> `-XX:TLABSize=512k` 设置`TLAB`空间大小。`If this option is set to 0, then the JVM chooses the initial size automatically.`

> `-XX:+PrintGCDetails` 输出详细的GC处理日志\
> `-XX:+PrintGC` 输出GC简要信息

> **逃逸分析**<sub>JDK6_23后默认开启</sub>
> > `-XX:+DoEscapeAnalysis` 默认开启逃逸分析\
> > `-XX:-DoEscapeAnalysis` 关闭逃逸分析\
> > `-XX:+PrintEscapeAnalysis` 查看逃逸分析的筛选结果

> 开启 or 关闭 **标量替换**
> > `-XX:+EliminateAllocations` 默认开启标量替换
> > `-XX:-EliminateAllocations` 关闭标量替换

> `Method area size` 方法区 
> > `-XX:PermSize=20.75M` **JDK7**及以前设置 `PermanentGenerationSpace` **初始值** 默认20.75M\
> > `-XX:MaxPermSize=82M` **JDK7**及以前设置 `PermanentGenerationSpace` **最大可分配空间**。32位机器默认是64M，64位机器默认是82M
> >
> > `-XX:MetaspaceSize=21M` **JDK8**及以后，设置**元空间初始值**，平台不同默认值不同，windows下默认约为21M\
> > `-XX:MaxMetaspaceSize=-1` **JDK8**及以后，设置**元空间最大可分配空间**，`-1`表示**没有限制**

> `-XX:HandlePromotionFailure=true` *设置空间分配担保*<sub>`JDK6_24`后过时</sub>
