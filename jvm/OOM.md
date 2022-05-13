# java.lang.OutOfMemoryError
```text
1. 要解决OOM异常或者heap space异常，一般首先通过内存映像分析工具（Eclipse Memory Analyzer）对dump出来的堆转储快照进行分析
   重点是确认内存中的对象是否是必要的，也就是要先分清楚到底是内存泄漏（memory leak）还是内存溢出（memory overflow）
2. memory leak：可进一步通过工具查看泄漏对象到GC Roots的引用链。
   找到泄漏对象是通过怎样的路径与GC Roots相关联并导致垃圾收集器无法自动回收他们。
   掌握了泄漏对象的类型信息，以及GC Roots引用链的信息，就可以比较准确地定位处泄漏代码的位置
3. memory overflow：不存在memory leak，即内存中的对象确实都还必须存活着，那就应当检查虚拟机的堆参数（-Xms和-Xmx）
   与机器物理内存对比看是否还可以调大，从代码上检查是否存在某些对象生命周期过长、持有状态时间过长的情况，尝试减少程序运行期的内存消耗。   
```