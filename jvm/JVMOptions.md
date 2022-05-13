# JVM Options
## 注意
- 参数-server 启动server模式，64位系统默认是Server模式，在server模式下才可以启用逃逸分析
```shell
C:\>java -version
java version "1.8.0_191"
Java(TM) SE Runtime Environment (build 1.8.0_191-b12)
Java HotSpot(TM) 64-Bit Server VM (build 25.191-b12, mixed mode) 'Server模式'
```

## 查看所有参数的默认初始值
   ```shell
   -XX:+PrintFlagsInitial
   ```
## 查看所有参数的最终值（修改后的值）
   ```shell
   -XX:+PrintFlagsFinal
   ```
## 初始heap size 只影响新生区和养老区 默认大小为物理机内存大小除以64
   ```shell
   -Xms10M '或者' -XX:InitialHeapSize=10M '通常将-Xms -Xmx设置为相同的值,目的是为了能够在Java垃圾回收机制清理完堆区后不需要重新分割计算堆区的大小从而提高性能'
   ```
## 最大heap size 只影响新生区和养老区 默认大小为物理机内存大小除以4
   ```shell
   -Xmx10M '或者' -XX:MaxHeapSize=10M '通常将-Xms -Xmx设置为相同的值,目的是为了能够在Java垃圾回收机制清理完堆区后不需要重新分割计算堆区的大小从而提高性能'
   ```
## 设置Young和Old的比
   ```shell
   -XX:NewRatio=2 '默认2，表示Young占1份，Old占2份'
   -XX:NewRatio=4 '表示Young占1份，Old占4份'
   ```
## （一般不使用）设置新生代Young的大小，
   ```shell
   -Xmn100M '当与-XX:NewRatio=2冲突时，以-Xmn设置的值来分配Young区大小，剩余的区域分给Old区'
   ```
## 设置Young区下的Eden区和Young区下survivor0区、survivor1区的比例
   ```shell
   -XX:SurvivorRatio=8 '默认值8：1：1由于自适应的内存分配策略，查看时可能是6：1：1，显式地设置为8才是8：1：1'
   ```
## 开启/关闭自适应的内存分配策略
   ```shell
   -XX:+UseAdaptiveSizePolicy '开启'
   -XX:-UseAdaptiveSizePolicy '关闭'
   ```
## Promotion晋升到老年区的阈值
   ```shell
   -XX:MaxTenuringThreshold=15 '默认值15'
   ```
## TLAB
   ```shell
   -XX:+UseTLAB '默认：开启'
   -XX:-UseTLAB '关闭'
   ```
## 查看所有参数的最终值（修改后的值）
   ```shell
   -XX:+printFlagsFinal
   ```
## 设置TLAB空间大小
   ```shell
   -XX:TLABSize=512k 'If this option is set to 0, then the JVM chooses the initial size automatically.'
   ```
## 输出详细的GC处理日志
   ```shell
   -XX:+PrintGCDetails
   ```
## 输出GC简要信息
   ```shell
   -XX:+PrintGC
   ```
## 设置空间分配担保（JDK6_24后失效）
   ```shell
   -XX:HandlePromotionFailure=true 'JDK6 Update24之后该参数失效'
   ```
## 逃逸分析（JDK6u23后默认开启）
   ```shell
   -XX:+DoEscapeAnalysis '默认开启逃逸分析'
   -XX:-DoEscapeAnalysis '关闭逃逸分析'
   -XX:+PrintEscapeAnalysis '查看逃逸分析的筛选结果'
   ```
## 标量替换
   ```shell
   -XX:+EliminateAllocations '默认开启标量替换'
   -XX:-EliminateAllocations '关闭标量替换'
   ```
## 设置 Method area size
   ```shell
   -XX:PermSize=20.75M 'JDK7及以前设置PermanentGenerationSpace 初始值 默认20.75M'
   -XX:MaxPermSize=82M 'JDK7及以前设置PermanentGenerationSpace 最大可分配空间，32位机器默认是64M，64位机器默认是82M'
   
   -XX:MetaspaceSize=21M 'JDK8及以后，设置元空间初始值，平台不同默认值不同，windows下默认约为21M'
   -XX:MaxMetaspaceSize=-1 'JDK8及以后，设置元空间最大可分配空间，-1表示没有限制'
   ```