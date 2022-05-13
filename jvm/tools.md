# 常用调优工具

## JDK命令

### 注意

- 如果java进程关闭了默认开启的UsePerfData参数（-XX:-UsePerfData），那么jps命令和jstat命令无法探知该Java进程

### jps

- java process status
- 显示指定系统内所有的hotspot进程，用于查询正在运行的虚拟机进程
- 对于本地虚拟机进程来说，进程的本地虚拟机id与操作系统的进程id是一致的，是唯一的

```text
[caddy@blackbox ~]$ jps -help
usage: jps [-help]
jps [-q] [-mlvV] [<hostid>]

Definitions:
<hostid>:      <hostname>[:<port>]
```

- jps -q 只显示LVMID（local virtual machine id）即本地虚拟机唯一id,不显示主类的名称，只有进程号
- jps -l 输出应用程序主类的全类名 如果进程执行的是jar包，则输出jar完整路径
- jps -m 输出虚拟机进程启动时传递给主类main()的参数
- jps -v 列出虚拟机进程启动时的jvm参数
- jps -V 列出虚拟机进程启动时的jvm参数
- jps -v > jps.txt 将信息内容写入到当前目录下的jps.txt内
- 除了-q，-mlvV可以混合使用 jps -lv，jps -lvm

### jstat

- JVM statistics monitoring tool 查看jvm统计信息
- 相对于监控某一时刻的信息jmap，jstat可以持续监控
- 监视jvm各种运行状态信息，可以显示本地或远程jvm进程中的类装载、内存、垃圾收集、JIT编译等运行数据
- 常用于检测GC以及memory leak
- 经验：
    1. -t参数：
        - 比较java进程的启动时间以及总GC时间（GCT列），或者两次测量的间隔时间以及总GC时间的增量，来得出GC时间占运行时间的比例，
        - 如果该比例超过20%说明堆压力较大，超过90%则说明堆里几乎没有可用空间，随时都可能OOM
    2. memory leak
        1. 长时间运行的java程序中，可以运行jstat命令连续获取多行性能数据，并取这几行数据中OU列（已占用的老年代内存）的最小值
        2. 每个一段较长的时间重复一次1操作，获取多组OU最小值，如果这些值呈现上涨趋势，则说明该java程序的老年代内存已使用量在不断上涨，这意味着无法回收的对象在不断增加，因此很可能存在内存泄漏
- <option>
    1. 类装载相关的
        - -class 显示ClassLoader的相关信息，类的装载、卸载数量、总空间、类装载所消耗的时间等
    2. GC相关
        - -gc 显示与GC相关的堆信息，包括Eden区、s0、s1、Old区、永久代等的容量、已用空间、GC时间合计等信息
        - -gccapacity 显示内容与-gc基本相同，但输出主要关注java堆各个区域使用到的最大、最小空间
        - -gcutil 显示内容与-gc基本相同，但输出主要关注已使用空间占总空间的百分比
        - -gccause 与-gcutil功能一样但是会额外输出导致最后一次或当前正在发生的GC产生的原因
        - -gcnew 显示新生代GC状况
        - -gcnewcapacity 显示内容与-gcnew基本相同，输出主要关注使用到的最大、最小空间
        - -gcold 显示老年代GC状况
        - -gcoldcapacity 显示内容与-gcold基本相同，输出主要关注使用到的最大、最小空间
        - -gcpermcapacity 显示永久代使用到的最大、最小空间
    3. JIT相关
        - -compiler 显示JIT编译器编译过的方法、耗时等信息
        - -printcompilation 输出已经被JIT编译的方法
- GC信息输出指标解释 jstat -gc
    1. 新生代相关
        - SOC s0区总大小 字节Byte
        - S1C s1区总大小 字节Byte
        - S0U s0区已使用大小 字节Byte
        - S1U s1区已使用大小
        - EC Eden区总大小
        - EU Eden区已使用大小
    2. 老年代相关
        - OC Old区总大小
        - OU Old区已使用大小
    3. 方法区相关
        - MC 方法区总大小
        - MU 方法区已使用大小
        - CCSC 压缩类空间的大小
        - CCSU 压缩类空间已使用的大小
    4. 其他
        - YGC 从应用程序启动到采样时youngGC次数
        - YGCT 从应用程序启动到采样时youngGC消耗的时间 秒
        - FGC 从应用程序启动到采样时fullGC次数
        - FGCT 从应用程序启动到采样时fullGC消耗的时间
        - GCT 从应用程序启动到采样时GC总时间

```text
[caddy@blackbox ~]$ jstat -help
Usage: jstat -help|-options
       jstat -<option> [-t] [-h<lines>] <vmid> [<interval> [<count>]]
        [-t] 显示程序的运行时间单位秒
        [-h<lines>] 周期性数据输出时，输出多少行数据后输出一个表头信息
        <vmid> jvm进程id
        <interval> 查询间隔 单位默认ms 查询一次
        <count> 查询的总次数
Definitions:
  <option>      An option reported by the -options option 
  <vmid>        Virtual Machine Identifier. A vmid takes the following form:
                     <lvmid>[@<hostname>[:<port>]]
                Where <lvmid> is the local vm identifier for the target
                Java virtual machine, typically a process id; <hostname> is
                the name of the host running the target Java virtual machine;
                and <port> is the port number for the rmiregistry on the
                target host. See the jvmstat documentation for a more complete
                description of the Virtual Machine Identifier.
  <lines>       Number of samples between header lines.
  <interval>    Sampling interval. The following forms are allowed:    
                    <n>["ms"|"s"]
                Where <n> is an integer and the suffix specifies the units as 
                milliseconds("ms") or seconds("s"). The default units are "ms".
  <count>       Number of samples to take before terminating. 
  -J<flag>      Pass <flag> directly to the runtime system.
[caddy@blackbox ~]$ jstat -class -t -h 3 2304 1s 10
Timestamp       Loaded  Bytes  Unloaded  Bytes     Time   
         3358.8  61697 126063.3     1952  2050.0      30.14
         3359.8  61697 126063.3     1952  2050.0      30.14
         3360.8  61697 126063.3     1952  2050.0      30.14
Timestamp       Loaded  Bytes  Unloaded  Bytes     Time   
         3361.8  61697 126063.3     1952  2050.0      30.14
[caddy@blackbox ~]$ jstat -compiler 2304
Compiled Failed Invalid   Time   FailedType FailedMethod
   66470      5       0   226.91          1 com/intellij/psi/util/PsiTreeUtilKt walkUpToCommonParent
[caddy@blackbox ~]$ jstat -printcompilation 2304
Compiled  Size  Type Method
   66470      5    1 com/intellij/remoteServer/configuration/deployment/DeploymentSourceType getId
[caddy@blackbox ~]$ jstat -gc 2304
 S0C    S1C    S0U    S1U      EC       EU        OC         OU       MC     MU    CCSC   CCSU   YGC     YGCT    FGC    FGCT     GCT   
85184.0 85184.0  0.0   2291.8 681600.0 672369.2 1245184.0   358924.7  407152.0 385765.5 55076.0 47651.3    107    1.022   0      0.000    1.022
```

### jinfo

- configuration info for java 查看jvm配置参数信息，也可以调整虚拟机配置参数
- 查看
    1. jinfo -sysprops PID 可以查看由System.getProperties()取得的参数
    2. jinfo -flags PID 查看曾赋过值的一些参数
    3. jinfo -flag 具体参数 PID 查看某个java进程的具体参数的值
- 修改 只有被标记为manageable的参数可以使用jinfo -flag 修改
    - 查看被标记为manageable的参数 java -XX:+PrintFlagsFinal -version | grep manageable
    - boolean类型
        1. jinfo -flag +/-具体参数 PID
    - 非boolean类型
        1. jinfo -flag 具体参数=具体参数值 PID
- 拓展
    1. java -XX:+PrintFlagsInitial 查看所有JVM参数启动的初始值
    2. java -XX:+PrintFlagsFinal 查看所有JVM参数的最终值
    3. java -XX:+PrintCommandLineFlags 查看那些已经被用户或者JVM设置过的详细的XX参数的名称和值

```text
[caddy@blackbox ~]$ jinfo -help
Usage:
    jinfo [option] <pid>
        (to connect to running process)
    jinfo [option] <executable <core>
        (to connect to a core file)
    jinfo [option] [server_id@]<remote server IP or hostname>
        (to connect to remote debug server)

where <option> is one of:
    -flag <name>         to print the value of the named VM flag
    -flag [+|-]<name>    to enable or disable the named VM flag
    -flag <name>=<value> to set the named VM flag to the given value
    -flags               to print VM flags
    -sysprops            to print Java system properties
    <no option>          to print both of the above
    -h | -help           to print this help message
[caddy@blackbox ~]$ sudo jinfo -sysprops 7379
Attaching to process ID 7379, please wait...
Debugger attached successfully.
Server compiler detected.
JVM version is 25.241-b07
java.runtime.name = Java(TM) SE Runtime Environment
java.vm.version = 25.241-b07......
[caddy@blackbox ~]$ sudo jinfo -flags 7880
Attaching to process ID 7880, please wait...
Debugger attached successfully.
Server compiler detected.
JVM version is 25.241-b07
Non-default VM flags: -XX:CICompilerCount=4 -XX:InitialHeapSize=629145600 -XX:MaxHeapSize=629145600 -XX:MaxNewSize=209715200 -XX:MinHeapDeltaBytes=524288 -XX:NewSize=209715200 -XX:OldSize=419430400 -XX:SurvivorRatio=8 -XX:+UseCompressedClassPointers -XX:+UseCompressedOops -XX:+UseFastUnorderedTimeStamps -XX:+UseParallelGC 
Command line:  -XX:SurvivorRatio=8 -XX:SurvivorRatio=8 -Xms600M -Xmx600M -Dfile.encoding=UTF-8 -Duser.country=US -Duser.language=en -Duser.variant
[caddy@blackbox ~]$ jinfo -flag PrintGCDetails 8320
-XX:-PrintGCDetails
[caddy@blackbox ~]$ jinfo -flag +PrintGCDetails 8320
[caddy@blackbox ~]$ jinfo -flag PrintGCDetails 8320
-XX:+PrintGCDetails
[caddy@blackbox ~]$ jinfo -flag MaxHeapFreeRatio 8320
-XX:MaxHeapFreeRatio=100
[caddy@blackbox ~]$ jinfo -flag MaxHeapFreeRatio=10 8320
[caddy@blackbox ~]$ jinfo -flag MaxHeapFreeRatio 8320
-XX:MaxHeapFreeRatio=10
[caddy@blackbox ~]$ java -XX:+PrintFlagsFinal -version | grep manageable
     intx CMSAbortablePrecleanWaitMillis            = 100                                 {manageable}
     intx CMSTriggerInterval                        = -1                                  {manageable}
     intx CMSWaitDuration                           = 2000                                {manageable}
     bool HeapDumpAfterFullGC                       = false                               {manageable}
     bool HeapDumpBeforeFullGC                      = false                               {manageable}
     bool HeapDumpOnOutOfMemoryError                = false                               {manageable}
    ccstr HeapDumpPath                              =                                     {manageable}
    uintx MaxHeapFreeRatio                          = 100                                 {manageable}
    uintx MinHeapFreeRatio                          = 0                                   {manageable}
     bool PrintClassHistogram                       = false                               {manageable}
     bool PrintClassHistogramAfterFullGC            = false                               {manageable}
     bool PrintClassHistogramBeforeFullGC           = false                               {manageable}
     bool PrintConcurrentLocks                      = false                               {manageable}
     bool PrintGC                                   = false                               {manageable}
     bool PrintGCDateStamps                         = false                               {manageable}
     bool PrintGCDetails                            = false                               {manageable}
     bool PrintGCID                                 = false                               {manageable}
     bool PrintGCTimeStamps                         = false                               {manageable}
java version "1.8.0_241"
Java(TM) SE Runtime Environment (build 1.8.0_241-b07)
Java HotSpot(TM) 64-Bit Server VM (build 25.241-b07, mixed mode)
C:\>jps
4448
2164 Jps
8324 GradleDaemon
3164 EdenSurvivortest

C:\>jinfo -flag UseTLAB 3164
-XX:+UseTLAB

C:\>jinfo -flag NewRatio 3164
-XX:NewRatio=2

C:\>jinfo -flag SurvivorRatio 3164
-XX:SurvivorRatio=8
```

### jmap

- JVM memory map
- 相较于jstat可以连续监控,jmap只是某一时刻的监控信息
- 获取dump文件（堆转储快照文件，二进制文件）
- 获取目标java进程内存相关信息，包括java堆各个区域的使用情况、堆中对象的统计信息、类加载信息等
- 基本option
    1. -dump 生成dump文件 1.1. -dump:live 只保存heap中存活的对象（dump文件会小一些，推荐使用加live）
    2. -heap 输出整个堆空间的详细信息，包括GC的使用、堆配置信息，以及内存的使用信息等
    3. -histo 输出堆中对象的统计信息，包括类、实例数量和合计容量 3.1 -histo:live 只统计堆中的存活对象
    4. -permstat 仅限linux和solaris平台，以ClassLoader为统计口径输出永久代的内存状态信息
    5. -finalizerinfo 仅限linux和solaris平台，显示在F-Queue中等待Finalizer线程执行finalize方法的对象
    6. -F 仅限linux和solaris平台，当虚拟机进程堆-dump选项没有任何响应时，使用此选项强制执行生成dump文件
- heap dump堆转储文件，指一个java进程在某个时间点的内存快照,保存信息如下
    - All Objects
    - All Classes
    - GCRoots
    - Thread Stack & Local variables
- 使用自动生成dump文件时，通常在写heap dump前会出发一次FUllGC 所以heap dump文件里保存的都是FullGC后留下的对象信息
- 生成dump文件比较耗时

- jmap -dump:format=b,file=/home/caddy/a.hprof PID（format=b 使导出文件符合dump读取规范）
- jmap -dump:live,format=b,file=/home/caddy/b.hprof PID
- 自动生成dump文件，jvm命令行参数(当OOM时,导出应用程序的当前heap dump)
    - -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/home/caddy/c.hprof
- sudo jmap -heap 10603
- sudo jmap -heap 10603 > /home/caddy/a.txt
- jmap -histo 10603
- jmap -histo 10603 > /home/caddy/b.txt
- 小结

```text
由于jmap将访问堆中的所有对象，为了保证在此过程中不被应用线程干扰，jmap需要借助安全点机制，让所有线程停留在不改变堆中数据的状态，
由jmap导出的堆快照必定是安全点位置的，这可能导致基于该heap dump的分析结果存在偏差
比如：某些对象的生命周期在两个安全点之间，那么:live 选项将无法探知到这些对象
此外，如果某个线程长时间无法跑到安全点，jmap将一直等下去，
jstat 则不同，GC会主动将jstat所需要的摘要数据保存在固定位置中，而jstat只需直接读取即可，实时读取
```

```text
[caddy@blackbox ~]$ jmap -h
Usage:
    jmap [option] <pid>
        (to connect to running process)
    jmap [option] <executable <core>
        (to connect to a core file)
    jmap [option] [server_id@]<remote server IP or hostname>
        (to connect to remote debug server)

where <option> is one of:
    <none>               to print same info as Solaris pmap
    -heap                to print java heap summary
    -histo[:live]        to print histogram of java object heap; if the "live"
                         suboption is specified, only count live objects
    -clstats             to print class loader statistics
    -finalizerinfo       to print information on objects awaiting finalization
    -dump:<dump-options> to dump java heap in hprof binary format
                         dump-options:
                           live         dump only live objects; if not specified,
                                        all objects in the heap are dumped.
                           format=b     binary format
                           file=<file>  dump heap to <file>
                         Example: jmap -dump:live,format=b,file=heap.bin <pid>
    -F                   force. Use with -dump:<dump-options> <pid> or -histo
                         to force a heap dump or histogram when <pid> does not
                         respond. The "live" suboption is not supported
                         in this mode.
    -h | -help           to print this help message
    -J<flag>             to pass <flag> directly to the runtime system
[caddy@blackbox ~]$ jmap -dump:live,format=b,file=/home/caddy/b.hprof 10390
Dumping heap to /home/caddy/b.hprof ...
Heap dump file created
[caddy@blackbox ~]$ sudo jmap -heap 10603
Attaching to process ID 10603, please wait...
Debugger attached successfully.
Server compiler detected.
JVM version is 25.241-b07

using thread-local object allocation.
Parallel GC with 10 thread(s)

Heap Configuration:
   MinHeapFreeRatio         = 0
   MaxHeapFreeRatio         = 100
   MaxHeapSize              = 8417968128 (8028.0MB)
   NewSize                  = 175112192 (167.0MB)
   MaxNewSize               = 2805989376 (2676.0MB)
   OldSize                  = 351272960 (335.0MB)
   NewRatio                 = 2
   SurvivorRatio            = 8
   MetaspaceSize            = 21807104 (20.796875MB)
   CompressedClassSpaceSize = 1073741824 (1024.0MB)
   MaxMetaspaceSize         = 17592186044415 MB
   G1HeapRegionSize         = 0 (0.0MB)

Heap Usage:
PS Young Generation
Eden Space:
   capacity = 132120576 (126.0MB)
   used     = 2642424 (2.5200119018554688MB)
   free     = 129478152 (123.47998809814453MB)
   2.0000094459170388% used
From Space:
   capacity = 21495808 (20.5MB)
   used     = 0 (0.0MB)
   free     = 21495808 (20.5MB)
   0.0% used
To Space:
   capacity = 21495808 (20.5MB)
   used     = 0 (0.0MB)
   free     = 21495808 (20.5MB)
   0.0% used
PS Old Generation
   capacity = 351272960 (335.0MB)
   used     = 0 (0.0MB)
   free     = 351272960 (335.0MB)
   0.0% used
[caddy@blackbox ~]$ jmap -histo 10946

 num     #instances         #bytes  class name
----------------------------------------------
   1:           401        4437040  [I
   2:          1715         188168  [C
   3:          4652         148864  java.util.Random
   4:          4656         111744  java.util.concurrent.atomic.AtomicLong
   5:           534         101664  [Ljava.lang.Object;
   6:          4652          74432  org.bougainvillea.java.jvm.runtime.heap.Picture

```

### jhat

- JVM heap analysis tool
- 与jmap配合使用，分析heap dump文件
- jhat 内置一个微型的http/html服务器，端口默认7000，生成dump文件的分析结果后，用户可以在浏览器中查看分析结果
- 支持OQL查询 http://127.0.0.1:7000/oql/
    - select s from java.lang.String s where s.value.length >1000
- jhat在jdk9 jdk10已经被删除，官方推荐使用JVisualVM代替
- option参数
    1. -port 6565 设置jhat http server 端口

```text
[caddy@blackbox ~]$ jhat -help
Usage:  jhat [-stack <bool>] [-refs <bool>] [-port <port>] [-baseline <file>] [-debug <int>] [-version] [-h|-help] <file>

	-J<flag>          Pass <flag> directly to the runtime system. For
			  example, -J-mx512m to use a maximum heap size of 512MB
	-stack false:     Turn off tracking object allocation call stack.
	-refs false:      Turn off tracking of references to objects
	-port <port>:     Set the port for the HTTP server.  Defaults to 7000
	-exclude <file>:  Specify a file that lists data members that should
			  be excluded from the reachableFrom query.
	-baseline <file>: Specify a baseline object dump.  Objects in
			  both heap dumps with the same ID and same class will
			  be marked as not being "new".
	-debug <int>:     Set debug level.
			    0:  No debug output
			    1:  Debug hprof file parsing
			    2:  Debug hprof file parsing, no server
	-version          Report version number
	-h|-help          Print this help and exit
	<file>            The file to read

For a dump file that contains multiple heap dumps,
you may specify which dump in the file
by appending "#<number>" to the file name, i.e. "foo.hprof#3".

All boolean options default to "true"
[caddy@blackbox ~]$ jhat c.hprof 
Reading from c.hprof...
Dump file created Sun Feb 28 16:34:20 CST 2021
Snapshot read, resolving...
Resolving 4169 objects...
Chasing references, expect 0 dots
Eliminating duplicate references
Snapshot resolved.
Started HTTP server on port 7000
Server is ready.
[caddy@blackbox ~]$ jhat -port 6565 c.hprof 
Reading from c.hprof...
Dump file created Sun Feb 28 16:34:20 CST 2021
Snapshot read, resolving...
Resolving 4169 objects...
Chasing references, expect 0 dots
Eliminating duplicate references
Snapshot resolved.
Started HTTP server on port 6565
Server is ready.
```

### jstack

- JVM stack trace
- 虚拟机堆栈跟踪（生成JVM某一线程当前时刻的线程快照）
- 作用
    - 定位线程出现长时间停顿的原因：线程间死锁、死循环、请求外部资源导致的长时间等待等问题
    - 当线程出现停顿时，就可以使用jstack现实各个线程调用的堆栈情况
- 在thread dump中留意下面几种状态
    - Deadlock 死锁 (重点关注)
    - Waiting on condition 等待资源 (重点关注)
    - Waiting on monitor entry 等待获取同步监视器 (重点关注)
    - Blocked 阻塞 (重点关注)
    - Runnable 执行中
    - Suspended 暂停
    - Object.wait() 或 TIMED_WAITING
    - 停止 Parked
```text
[caddy@blackbox ~]$ jstack -help
Usage:
    jstack [-l] <pid>
        (to connect to running process)
    jstack -F [-m] [-l] <pid>
        (to connect to a hung process)
    jstack [-m] [-l] <executable> <core>
        (to connect to a core file)
    jstack [-m] [-l] [server_id@]<remote server IP or hostname>
        (to connect to a remote debug server)

Options:
    -F  to force a thread dump. Use when jstack <pid> does not respond (process is hung) 当正常输出的请求不被响应时，强制输出线程堆栈
    -m  to print both java and native frames (mixed mode)                                如果调用到本地方法的话，可以现实c/c++的堆栈
    -l  long listing. Prints additional information about locks                          除堆栈外，显示关于锁的附加信息
    -h or -help to print this help message
[caddy@blackbox ~]$ jstack 12393
2021-02-28 17:20:04
Full thread dump Java HotSpot(TM) 64-Bit Server VM (25.241-b07 mixed mode):

"Attach Listener" #10 daemon prio=9 os_prio=0 tid=0x00007f06dc001000 nid=0x30b3 waiting on condition [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE

"Service Thread" #9 daemon prio=9 os_prio=0 tid=0x00007f073c0d8000 nid=0x3082 runnable [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE

"C1 CompilerThread3" #8 daemon prio=9 os_prio=0 tid=0x00007f073c0cd000 nid=0x3081 waiting on condition [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE

"C2 CompilerThread2" #7 daemon prio=9 os_prio=0 tid=0x00007f073c0cb000 nid=0x3080 waiting on condition [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE

"C2 CompilerThread1" #6 daemon prio=9 os_prio=0 tid=0x00007f073c0c9800 nid=0x307f waiting on condition [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE

"C2 CompilerThread0" #5 daemon prio=9 os_prio=0 tid=0x00007f073c0c6800 nid=0x307e waiting on condition [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE

"Signal Dispatcher" #4 daemon prio=9 os_prio=0 tid=0x00007f073c0c5000 nid=0x307d runnable [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE

"Finalizer" #3 daemon prio=8 os_prio=0 tid=0x00007f073c094000 nid=0x307c in Object.wait() [0x00007f06fd4e6000]
   java.lang.Thread.State: WAITING (on object monitor)
	at java.lang.Object.wait(Native Method)
	- waiting on <0x0000000718c08ee0> (a java.lang.ref.ReferenceQueue$Lock)
	at java.lang.ref.ReferenceQueue.remove(ReferenceQueue.java:144)
	- locked <0x0000000718c08ee0> (a java.lang.ref.ReferenceQueue$Lock)
	at java.lang.ref.ReferenceQueue.remove(ReferenceQueue.java:165)
	at java.lang.ref.Finalizer$FinalizerThread.run(Finalizer.java:216)

"Reference Handler" #2 daemon prio=10 os_prio=0 tid=0x00007f073c08f800 nid=0x307b in Object.wait() [0x00007f06fd5e7000]
   java.lang.Thread.State: WAITING (on object monitor)
	at java.lang.Object.wait(Native Method)
	- waiting on <0x0000000718c06c00> (a java.lang.ref.Reference$Lock)
	at java.lang.Object.wait(Object.java:502)
	at java.lang.ref.Reference.tryHandlePending(Reference.java:191)
	- locked <0x0000000718c06c00> (a java.lang.ref.Reference$Lock)
	at java.lang.ref.Reference$ReferenceHandler.run(Reference.java:153)

"main" #1 prio=5 os_prio=0 tid=0x00007f073c00c000 nid=0x306f waiting on condition [0x00007f0742f2a000]
   java.lang.Thread.State: TIMED_WAITING (sleeping)
	at java.lang.Thread.sleep(Native Method)
	at org.bougainvillea.java.jvm.runtime.heap.OOMTest.main(OOMTest.java:19)

"VM Thread" os_prio=0 tid=0x00007f073c086000 nid=0x307a runnable 

"GC task thread#0 (ParallelGC)" os_prio=0 tid=0x00007f073c021000 nid=0x3070 runnable 

"GC task thread#1 (ParallelGC)" os_prio=0 tid=0x00007f073c023000 nid=0x3071 runnable 

"GC task thread#2 (ParallelGC)" os_prio=0 tid=0x00007f073c024800 nid=0x3072 runnable 

"GC task thread#3 (ParallelGC)" os_prio=0 tid=0x00007f073c026800 nid=0x3073 runnable 

"GC task thread#4 (ParallelGC)" os_prio=0 tid=0x00007f073c028000 nid=0x3074 runnable 

"GC task thread#5 (ParallelGC)" os_prio=0 tid=0x00007f073c02a000 nid=0x3075 runnable 

"GC task thread#6 (ParallelGC)" os_prio=0 tid=0x00007f073c02b800 nid=0x3076 runnable 

"GC task thread#7 (ParallelGC)" os_prio=0 tid=0x00007f073c02d800 nid=0x3077 runnable 

"GC task thread#8 (ParallelGC)" os_prio=0 tid=0x00007f073c02f000 nid=0x3078 runnable 

"GC task thread#9 (ParallelGC)" os_prio=0 tid=0x00007f073c031000 nid=0x3079 runnable 

"VM Periodic Task Thread" os_prio=0 tid=0x00007f073c0db000 nid=0x3083 waiting on condition 

JNI global references: 5

```    
### jcmd

- jdk7以后，新增命令行工具jcmd 
- 多功能命令行工具，可以用来实现除jstat之外所有命令的功能
- jcmd拥有jmap的大部分功能，并且Oracle推荐使用jcmd替代jmap
- jcmd == jps -lm
- jcmd PID help 查看该PID支持哪些jcmd命令
- jcmd PID 指令 使用指令
```text
[caddy@blackbox ~]$ jcmd -h
Usage: jcmd <pid | main class> <command ...|PerfCounter.print|-f file>
   or: jcmd -l                                                    
   or: jcmd -h                                                    
                                                                  
  command must be a valid jcmd command for the selected jvm.      
  Use the command "help" to see which commands are available.   
  If the pid is 0, commands will be sent to all Java processes.   
  The main class argument will be used to match (either partially 
  or fully) the class used to start Java.                         
  If no options are given, lists Java processes (same as -p).     
                                                                  
  PerfCounter.print display the counters exposed by this process  
  -f  read and execute commands from the file                     
  -l  list JVM processes on the local machine  列出所有JVM进程                 
  -h  this help
[caddy@blackbox ~]$ jps -lm
2304 com.intellij.idea.Main
8245 org.gradle.launcher.daemon.bootstrap.GradleDaemon 6.7
12726 sun.tools.jps.Jps -lm
[caddy@blackbox ~]$ jcmd
2304 com.intellij.idea.Main
8245 org.gradle.launcher.daemon.bootstrap.GradleDaemon 6.7
12748 sun.tools.jcmd.JCmd
[caddy@blackbox ~]$ jcmd -l
2304 com.intellij.idea.Main
12866 sun.tools.jcmd.JCmd -l
8245 org.gradle.launcher.daemon.bootstrap.GradleDaemon 6.7
[caddy@blackbox ~]$ jcmd 2304 help
2304:
The following commands are available:
Compiler.CodeHeap_Analytics
Compiler.codecache
Compiler.codelist
Compiler.directives_add
Compiler.directives_clear
Compiler.directives_print
Compiler.directives_remove
Compiler.queue
GC.class_histogram
GC.class_stats
GC.finalizer_info
GC.heap_dump
GC.heap_info
GC.run
GC.run_finalization
JFR.check
JFR.configure
JFR.dump
JFR.start
JFR.stop
JVMTI.agent_load
JVMTI.data_dump
ManagementAgent.start
ManagementAgent.start_local
ManagementAgent.status
ManagementAgent.stop
Thread.print
VM.class_hierarchy
VM.classloader_stats
VM.classloaders
VM.command_line
VM.dynlibs
VM.flags
VM.info
VM.log
VM.metaspace
VM.native_memory
VM.print_touched_methods
VM.set_flag
VM.stringtable
VM.symboltable
VM.system_properties
VM.systemdictionary
VM.uptime
VM.version
help

For more information about a specific command use 'help <command>'.
[caddy@blackbox ~]$ jcmd 2304 VM.flags
2304:
-XX:CICompilerCount=2 -XX:ErrorFile=/home/caddy/java_error_in_idea_%p.log -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/home/caddy/java_error_in_idea_.hprof -XX:InitialHeapSize=2147483648 -XX:MaxHeapSize=4294967296 -XX:MaxNewSize=872415232 -XX:MaxTenuringThreshold=6 -XX:MinHeapDeltaBytes=196608 -XX:NewSize=872415232 -XX:NonNMethodCodeHeapSize=5825164 -XX:NonProfiledCodeHeapSize=259231418 -XX:OldSize=1275068416 -XX:-OmitStackTraceInFastThrow -XX:ProfiledCodeHeapSize=259231418 -XX:ReservedCodeCacheSize=524288000 -XX:+SegmentedCodeCache -XX:SoftRefLRUPolicyMSPerMB=50 -XX:+UseCompressedClassPointers -XX:+UseCompressedOops -XX:+UseConcMarkSweepGC 


```

### jstatd

- 远程主机信息收集
- jps、jstat等支持远程计算机的监控，为了启用远程监控，需要配合使用jstatd工具
- jstatd是一个RMI服务端程序，它的作用相当于代理服务器，建立本地计算机与远程监控工具的通信
- jstatd服务器将本机的java应用程序信息传递到远程计算机

### javap

- javap -v -p MetaspaceOOMTest.class > test.txt 将内容写入到当前目录下的test.txt文件内 -p表示输出private权限修饰符的属性

### 示例

#### -XX:+PrintGCDetails 

- 运行时加命令行参数-XX:+PrintGCDetails 运行时打印内存详细信息

```text
Heap
 PSYoungGen      total 179200K, used 6144K [0x00000000f3800000, 0x0000000100000000, 0x0000000100000000)
  eden space 153600K, 4% used [0x00000000f3800000,0x00000000f3e00188,0x00000000fce00000)
  from space 25600K, 0% used [0x00000000fe700000,0x00000000fe700000,0x0000000100000000)
  to   space 25600K, 0% used [0x00000000fce00000,0x00000000fce00000,0x00000000fe700000)
 ParOldGen       total 409600K, used 0K [0x00000000da800000, 0x00000000f3800000, 0x00000000f3800000)
  object space 409600K, 0% used [0x00000000da800000,0x00000000da800000,0x00000000f3800000)
 Metaspace       used 2637K, capacity 4486K, committed 4864K, reserved 1056768K
  class space    used 281K, capacity 386K, committed 512K, reserved 1048576K
```

## Jconsole
## jvisualvm
- C:\Program Files\Java\jdk1.8.0_191\bin\jvisualvm.exe
    - 当配置Path java环境变量可以直接输入jvisualvm启动
    - 工具-插件-Virtual GC
## Eclipse: Memory Analyzer Tool
- http://www.eclipse.org/mat/downloads.php
- wget https://mirrors.bfsu.edu.cn/eclipse/mat/1.11.0/rcp/MemoryAnalyzer-1.11.0.20201202-linux.gtk.x86_64.zip
- ln -s /opt/mat/MemoryAnalyzer /usr/local/bin/mat
## Jprofiler
## Arthas
- https://github.com/alibaba/arthas
- https://arthas.aliyun.com/zh-cn/
- https://arthas.aliyun.com/doc/quick-start.html

## Java Flight Recorder（JMC下的工具）
- 取样对象需要启动参数
    1. -XX:+UnlockCommercialFeatures
    2. -XX:+FlightRecorder
## GCViewer

## GC Easy

## jhsdb(JDK9之后添加的工具)
## jmeter
- wget https://mirrors.bfsu.edu.cn/apache/jmeter/binaries/apache-jmeter-5.4.1.tgz
- ln -s /opt/apache-jmeter-5.4.1/bin/jmeter /usr/local/bin/jmeter