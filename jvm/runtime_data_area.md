# runtime data area

## 备注

- 内存
    ```text
    是非常重要的系统资源，是硬盘和cpu的中间仓库及桥梁，承载着操作系统和应用程序的实时运行。
    JVM内存布局规定了java在运行过程中内存申请、分配、管理的策略，保证了JVM的高效稳定运行。
    不同的JVM对于内存的划分方式和管理机制存在着部分差异。
    ```

- JVM 定义了若干种程序运行期间会使用到的运行时数据区
    - 一些随着JVM启动而创建，随着JVM退出而销毁
        1. heap 堆内存
        2. 堆外内存
            1. method area「jdk8采用metaspace元空间/永久代，为method area的落地实现」
            2. 代码缓存「JIT编译产物」
    - 一些与线程一一对应，与线程对应的数据区会随着线程开始和结束而创建和销毁
        1. program counter registers pc寄存器
        2. stack 栈
        3. native stack 本地方法栈
- public class Runtime extends Object
    ```text
    每个JVM只有一个Runtime实例。即为运行时环境
    Every Java application has a single instance of class Runtime that allows the application to interface with the
    environment in which the application is running. The current runtime can be obtained from the getRuntime method.
    ```

## 线程

- 线程是一个程序里的运行单元。JVM允许一个应用有多个线程并行的执行
- HotspotJVM里，每个线程都与操作系统的本地线程直接映射
    - 当一个java线程准备好执行以后，一个操作系统的本地线程也同时创建，java线程执行终止后本地线程也会回收
    - 操作系统负责所有线程的安排调度到任何一个可用的cpu上，一旦本地线程初始化成功，会调用java线程中的run()方法
    - 线程出现异常
        - 捕获处理异常也相当于java线程正常终止
        - 未捕获处理异常，java线程肯定终止，此时操作系统还要判断一下是否要终止JVM
            - 守护线程 demon线程，如果JVM中只剩demon线程则JVM可以退出
            - 非守护线程 当前线程为最后一个非守护线程则终止

### hotspotJVM的后台系统线程

- 使用jconsole或者其他调试工具，能看到后台有许多线程在运行，不包括main方法的main线程以及所有这个main线程自己创建的线程

1. 虚拟机线程

    ```
    这种线程的操作是需要JVM达到安全点才会出现。
    这些操作必须在不同线程中发生的原因是他们都需要JVM达到安全点，这样heap才不会变化。
    这种线程的执行类型包括“stop-the-world”的垃圾收集、线程栈收集、线程挂起、偏向锁撤销。
    ```

2. 周期任务线程
    ```
    这种线程是时间周期事件的体现（比如中断），他们一般用于周期性操作的调度执行
    ```
3. GC线程
    ```
    这种线程对在JVM里不同种类的垃圾收集行为提供了支持
    ```
4. 编译线程
    ```
    这种线程在运行时会将字节码编译成本地代码
    ```
5. 信号调度线程
    ```
    这种线程接收信号并发送给JVM,在它内部通过调用适当的方法进行处理
    ```