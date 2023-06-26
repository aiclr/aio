---
title: Method Area
targets:
  - name: Top
    link: method-area
  - name: 设置 method area 大小与 oom
    link: 设置method-area大小与oom
  - name: method area 内部结构
    link: method-area内部结构
  - name: 字节码文件
    link: 字节码文件
  - name: 运行时常量池vs常量池
    link: 运行时常量池vs常量池
  - name: methad area 演进细节
    link: methad-area演进细节
  - name: stringtable为什么要调整
    link: stringtable为什么要调整
  - name: non-final-static变量放在哪里
    link: non-final-static变量放在哪里
  - name: methad area gc
    link: methad-area-gc
  - name: 面试题
    link: 面试题
---

# Method Area

> [Oracle 官方文档](https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-2.html#jvms-2.5.4)
> > The Java Virtual Machine has a method area that is shared among all Java Virtual Machine threads. The method area is analogous<sub>相似的</sub> to the storage<sub>存储</sub> area for compiled<sub>编译的</sub> code of a conventional<sub>传统的</sub> language or analogous<sub>相似的</sub> to the "text" segment<sub>片;段;</sub> in an operating system process. It stores per-class structures<sub>结构</sub> such as the run-time constant<sub>常量</sub> pool, field<sub>字段</sub> and method data, and the code<sub>字节码</sub> for methods and constructors<sub>构造器</sub>, including the special<sub>特殊的</sub> methods ([§2.9](https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-2.html#jvms-2.9)) used in class and instance<sub>实例</sub> initialization<sub>初始化</sub> and interface initialization.
> >
> > The method area is created on virtual machine start-up. Although the method area is logically<sub>逻辑上的</sub> part of the heap, simple implementations<sub>实现</sub> may choose not to either garbage collect or compact<sub>压缩</sub> it. This specification<sub>规范</sub> does not mandate<sub>强制执行</sub> the location of the method area or the policies<sub>策略</sub> used to manage compiled code. The method area may be of a fixed<sub>固定的</sub> size or may be expanded<sub>扩展</sub> as required by the computation<sub>计算</sub> and may be contracted<sub>收缩</sub> if a larger method area becomes unnecessary. The memory for the method area does not need to be contiguous<sub>连续的</sub>.
> >
> > *A Java Virtual Machine implementation may provide the programmer or the user control over the initial size of the method area, as well as, in the case of a varying-size<sub>大小动态变化的</sub> method area, control over the maximum and minimum method area size.*
> >
> > The following exceptional<sub>异常的</sub> condition<sub>情况</sub> is associated<sub>相关的</sub> with the method area:
> > - If memory in the method area cannot be made available to satisfy<sub>满足</sub> an allocation<sub>分配</sub> request, the Java Virtual Machine throws an `OutOfMemoryError`.

---

> Method area 方法区
>
> `stack area`、`heap area`、`method area`交互关系
> > `Person p=new Person();`
> > - `Person` **类信息** 保存在 `method area`
> > - `new Person()` **对象实例**保存在 `heap area`
> > - 如果此行代码在**方法内部**，则`p`是一个`reference`<sub>引用类型</sub>保存在`stack area` 的 `local variables`
>
> 特点
> > `Method area` 有一个别名叫`Non-Heap`<sub>非堆</sub>\
> > `Method area` 是一块独立于`heap area`之外的内存空间\
> > `Method area` 保存**类信息**\
> > `Method area` 线程共享\
> > `Method area` 可以设置为固定大小，也可以动态扩展\
> > `Method area` 大小决定系统可以保存多少个类，如果系统定义了太多的类，导致方法区溢出，`JVM`抛出`java.lang.OutOfMemoryError: PermGen space/java.lang.OutOfMemoryError: Metaspace`\
> > > 例子：
> > > - 加载大量的第三方jar包
> > > - `Tomcat`部署的工程过多（30-50个）
> > > - 大量动态的生成**反射类**
> >
> > `Method area`生命周期与`JVM`一致，`JVM`启动时分配，关闭后释放。

## [设置method area大小](jvmoptions)与[OOM](oom)

> `JDK7`及之前
> > `-XX:PermSize=20.75M` 设置`PermanentGenerationSpace` 初始值。默认`20.75M`\
> > `-XX:MaxPermSize=82M` 设置`PermanentGenerationSpace` 最大可分配空间。32位机器默认是`64M`，64位机器默认是`82M`
>
> `JDK8`及之后
> > `-XX:MetaspaceSize=21M` 设置`Metaspace`<sub>元空间</sub>初始值，平台不同默认值不同，`64位 -server` 模式`windows`下默认约为`21M`\
> > `-XX:MaxMetaspaceSize=-1` 设置`Metaspace`最大可分配空间，`-1`表示没有限制
>
> 如果不指定大小，默认情况下，`JVM`会耗尽所有的**可用系统内存**，如果`Metaspace`发生溢出，`jvm`会抛出`java.lang.OutOfMemoryError: Metaspace`
>
> 设置初始`MetaspaceSize`大小，对于一个`64bits -server`的`JVM`来说，其默认的`-XX:MetaspaceSize`值为`21MB`,初始的高水位线，一旦触及这个水位线`FullGC`将会被触发并卸载没用的类<sub>这些类对应的类加载器不再存活</sub>\
> `FullGC`后这个高水位线将会**重置**，新的高水位线的值取决于`GC`后释放了多少`Metaspace`
> - 如果释放的空间**不足**，在不超过`MaxMetaspaceSize`时，适当提高`MetaspaceSize`值
> - 如果释放的空间**过多**，则适当降低`MetaspaceSize`值
>
> 如果初始化的高水位线**设置过低**，上述高水位线调整情况会发生很多次。通过`GC`的日志可以观察到`FullGC`多次调用。\
> 为了避免频繁`GC`，建议将`-XX:MetaspaceSize`设置为一个**相对较高的值**

## `method area`内部结构

> `method area`标准的存储内容。*后续会变化*
> - 类型信息
> - `field`<sub>字段</sub>
> - 方法信息
> - `non-final static`变量<sub>静态变量</sub>
> - `static final`全局常量
> - `run-time constant pool`<sub>运行时常量池</sub>
> - `StringTable`<sub>在运行时常量池内</sub>随着`JDK`变化，`StringTable`存储位置也会变化
> - `JIT`<sub>即时编译器</sub>编译后的代码缓存
>
> **类型**信息：`class`<sub>类</sub>、`interface`<sub>接口</sub>、`enum`<sub>枚举</sub>、`annotation`<sub>注解</sub>等
> 1. 这个**类型**的完整有效名称<sub>包名.类名</sub>
> 2. 这个**类型**`直接父类`的完整有效名<sub>对于`interface`或`java.lang.Object`都没有父类</sub>
> 3. 这个**类型**的修饰符<sub>`public`、`abstract`、`final`的某个**子集**</sub>
> 4. 这个**类型**`直接接口`的一个**有序列表**
>
> `field`<sub>字段</sub>: `JVM`必须在`method area`中保存类型信息的所有`field`的**相关信息**以及`field`的**声明顺序**
> > `field`的相关信息包括
> > - `field`名称
> > - `field`类型
> > - `field`修饰符<sub>`public、private、protected、static、final、volatile、transient`的某个子集</sub>
>
> 方法信息
> - 方法名称
> - 方法的返回类型<sub>void也是一种类型</sub>
> - 方法参数的数量和类型<sub>有序</sub>
> - 方法的修饰符<sub>`public、private、protected、static、final、synchronized、native、abstract`的一个子集</sub>
> - 方法的`byte codes`<sub>字节码</sub>、`Operand Stack`、`Local Variables`及**大小**<sub>`abstract`和`native`方法除外</sub>
> - **异常表**<sub>`abstract`和`native`除外</sub>: 每个异常处理的`开始位置`、`结束位置`、代码处理在`Program Counter Register中`的偏移地址、被捕获的异常类的常量池索引
>
> `non-final static`变量<sub>静态变量/类变量</sub>：随着`JDK`变化，存储位置会变化
> - **静态变量**和类关联在一起，随着类的加载而加载，他们称为类数据在逻辑上的一部分
> - **类变量**被类的所有实例共享，即使没有类实例也可以访问
>
> `static final`全局常量
> - 被声明为`final`的**类变量**的处理方法不同，每个全局常量在**编译期**分配
>
> `run-time constant pool`<sub>运行时常量池</sub>
> - `Constant Pool Table`是`class`文件的一部分，用于存放**编译期**生成的各种**字面量**与**符号引用**
> - `Constant Pool Table`在**类加载后**存放到`Method Area`的`Runtime Constant Pool`
> - `run-time constant pool`，在加载类和接口到`jvm`后，就会创建对应的`Runtime Constant Pool`
> - `JVM`为每个已加载的类型<sub>类或接口</sub>都维护一个`Runtime Constant Pool`，`Runtime Constant Pool`中的数据项像**数组**项一样，是通过**索引**访问的
> - `run-time constant pool`中包含多种不同的常量，包括编译期就已经明确的**数值字面量**，也包括到运行期解析后才能够获得的**方法或者字段引用**，此时不再是常量池中的符号地址了，这里换为真实地址
>      - `Runtime Constant Pool`，相对于class文件`Constant Pool Table`的另一重要特征是：**具备动态性**
>      - 动态举例: `String.intern()`<sub>`public native String intern();`</sub>如果`Runtime Constant Pool`中没有该字符串，则在`Runtime Constant Pool`放一个**字符串常量**
> - `run-time constant pool`类似于传统编程语言中的`symbol table`<sub>符号表</sub>，但是它所包含的数据比`symbol table`要更加丰富一些
> - 当创建类或接口的`run-time constant pool`时，如果构造`run-time constant pool`所需的内存空间超过了`method area`所能提供的最大值，则`JVM`会抛`java.lang.OutOfMemoryError`异常
>
> - `JIT`<sub>即时编译器</sub>编译后的**代码缓存**

## 字节码文件

> 一个有效的字节码文件中除了包含**类的版本信息**、`field`<sub>字段</sub>、`method`、`interface`等描述信息外，还包含`Constant Pool Table`<sub>常量池表</sub>，包括各种**字面量**和对**类型**、`field`、`method`的**符号引用**
>
> 字节码文件为什么需要`Constant Pool Table`<sub>常量池表</sub>
> > 一个Java源文件，编译后产生一个字节码文件。字节码需要数据支持，通常这种数据会很大，以至于不能直接存在字节码里，而是换另一种存储方式存在`Constant Pool Table`，字节码使用指向`Constant Pool Table`的**符号引用**。这样可以大大减小字节码文件的大小\
> > 在`JVM stack`的[Dynamic Linking](jvmstacks#dynamic-linking)的时候会将**符号引用**转换为指向`run-time constant pool`的**直接引用**\
> > `Constant Pool Table`，可以看作是一张表，**虚拟机指令**根据这张表找到要执行的**类名、方法名、参数类型、字面量**等类型

## 运行时常量池vs常量池

- `methad area`内部包含`run-time constant pool`
- **字节码文件**内部包含`Constant Pool Table`
- `ClassLoader SubSystem`将**字节码文件**加载到`RuntimeDataArea`，其中字节码文件中的`Constant Pool Table`被加载到`methad area`后，就是`run-time constant pool`.

## `methad area`演进细节

> `jdk7`及以前习惯上把`methad area`称为 `Permanent Generation space`<sub>永久代</sub>\
> `jdk8`开始使用`MetaSpace`<sub>元空间</sub>取代`Permanent Generation space`\
> 本质上`methad area`和`Permanent Generation space`并不等价，仅对`Hotspot JVM`而言两者等价\
> `JVM规范`对如何实现`methad area`不做统一要求，`BEA`的`JRockit`和`IBM`的`J9`中不存在`Permanent Generation space`的概念\
> `Permanent Generation space`导致java程序更容易`OOM`<sub>超过`-XX:MaxPermSize`上限</sub>

> `JDK8`完全废弃`Permanent Generation space`的概念，改用`JRockit`、`J9`一样在本地内存中实现`MetaSpace`来代替\
> `MetaSpace`的本质和`Permanent Generation space`类似，都是对`JVM规范`中`method area`的实现\
> `MeatSpace`不在`JVM`设置的内存中，而是直接使用**本地物理内存**，可以设置的**更大**，更不容易`OOM`<sub> `PermGenSpace`在`JVM`设置的内存中，所以容易`OOM`</sub>\
> 根据`JVM规范`，如果`methad area`无法满足新的内存分配需求时，将抛出`OOM`

> `Hotspot`中`Method area`变化
> - `jdk1.6`及之前有`permanent generation`<sub>永久代</sub>
>     - **永久代**保存信息<sub>JVM内存</sub>
>         - 类型信息
>         - `field`<sub>字段</sub>
>         - 方法信息
>         - `non-final static`变量<sub>静态变量</sub>
>         - `static final`全局常量
>         - `run-time constant pool`<sub>运行时常量池</sub>
>         - `StringTable`<sub>在运行时常量池内</sub>
>         - `JIT`代码缓存
> - `jdk1.7`：有`permanent generation`，逐步”去永久代“，将`StringTable`、`non-final static`变量<sub>静态变量</sub>从`permanent generation`移到`Heap Area`
>     - **永久代**保存信息<sub>JVM内存</sub>
>         - 类型信息
>         - `field`<sub>字段</sub>
>         - 方法信息
>         - `static final`全局常量
>         - `run-time constant pool`<sub>运行时常量池</sub>
>         - JIT代码缓存
>     - `heap area`保存<sub>JVM内存</sub>
>         - `non-final static`变量<sub>静态变量</sub>
>         - `StringTable`
> - `jdk1.8`及之后：无`permanent generation`
>     - `Metaspace`<sub>直接使用的是物理机内存</sub>
>         - 类型信息
>         - `field`
>         - 方法信息
>         - `static final`全局常量
>         - `run-time constant pool`
>         - `JIT`代码缓存
>     - `heap area`保存<sub>JVM内存</sub>
>         - `non-final static`变量<sub>静态变量</sub>
>         - `StringTable`

> [为什么使用Metaspace替换PermanentGenerationSpace](http://openjdk.java.net/jeps/122)
> > Motivation<sub>动机</sub>
> >
> > This is part of the `JRockit` and `Hotspot` convergence<sub>融合</sub> effort<sub>试图</sub>.
> > `JRockit` customers do not need to configure the `permanent generation`<sub>永久代</sub> (since JRockit does not have a `permanent generation`) and are accustomed <sub>习惯于</sub>to not configuring the `permanent generation`.

> > 设置`PermanentGenerationSpace`的大小很难确定
> >
> > 某些场景下，动态加载类过多，容易产生`Perm`区的`OOM`。比如某个实际`Web`工程中，因为功能点比较多，在运行过程中要不断动态加载很多类，经常出现致命错误`OOM`\
> > 使用`Metaspace`和`PermGenSpace`最大区别在于：`Metaspace`并不在JVM中，而是使用**本地物理机内存**，因此默认情况下，`Metaspace`的大小仅受本地内存限制

> > 对`PermanentGenerationSpace`调优很困难<sub>FullGC</sub>

### `StringTable`为什么要调整

> JDK7中将`StringTable`放到`heap area`中，因为`PermGenSpace`的**回收效率很低**，在`FullGC`的时候才会触发. 而`FullGC`是`Old区`空间不足、`PermGenSpace`不足时才会触发,导致`StringTable`回收效率不高，而开发中会有**大量的字符串**被创建，回收效率低会更容易导致`PermGenSpace`空间不足。放在`heap area`能即时回收内存.

### `non-final static`变量放在哪里

> `new`出的**实例对象**都在`heap area`
>
> **对象引用**存放位置
> - **非静态属性**放`heap area`
> - **方法内**的**局部变量**，在`stack frame`的`local variables`内
> - **静态属性**，在`java.lang.Class`对象内<sub>大 `Class` 对象</sub>
>
> `jvm规范`定义的概念模型，所有`Class`相关的信息都应该存放在`methad area`中，但`methad area`如何实现`jvm规范`并未做出规定. 这就成了一件允许不同jvm自己灵活把握的事情\
> `JDK7`及其以后版本的`Hotspot jvm`选择把`non-final static`变量<sub>静态变量</sub>**引用与类型**与`大Class对象`存放在一起，存储于`heap area`

## `methad area` GC

> 主要回收两部分
> > `run-time constant pool`中废弃的常量
> > > - **字面量**：如文本字符串、被声明为`final`的常量值等
> > > - **符号引用**
> > >    - 类和接口的全限定名
> > >    - `field`的名称和描述符
> > >    - `method`的名称和描述符
> >
> > 不再使用的**类型**
> > > 回收的条件
> > > 1. 该类所有的实例都已经被回收，也就是`heap area`中不存在该类及任何派生子类的实例
> > > 2. 加载该类的**类加载器**已经被回收，这个条件除非是经过精心设计的可替换类加载器的场景，如`OSGi`、`JSP`的重加载等，否则通常很难达成
> > > 3. 该类对应的`java.lang.Class`对象没有在任何地方被引用，无法在任何地方通过**反射**访问该类的方法
> > >
> > > `JVM`被允许对**满足上述三个条件**的无用类进行回收，这里说的仅仅是被**允许**，而并不是和对象一样，没有引用就必然会回收，关于是否要对类型进行回收
> > > - Hotspot提供了`-Xnoclassgc`参数进行控制
> > > - 可以使用如下参数查看类加载和卸载信息
> > >     - `-verbose:class`
> > >     - `-XX:+TraceClass-Loading`
> > >     - `-XX:+TraceClassUnLoading`
> > >
> > > 在大量使用`反射`、`动态代理`、`CGLib`等**字节码框架**，动态生成`JSP`以及`OSGi`这类频繁**自定义类加载器**的场景中，通常都需要`JVM`具备**类型卸载的能力**，以保证不会对`method area`造成过大的内存压力
>
> `Hotspot`对`run-time constant pool`的回收策略很明确，只要`run-time constant pool`中的常量没有被任何地方引用，就可以被回收. 回收废弃常量与回收`heap area`中java对象非常类似

## 面试题

> 百度
> > 三面：说一下`JVM`内存模型，有哪些区？分别是干什么的？
> > > 分代
> > > > 新生代：分配对象空间的位置
> > > > > `Eden区`：第二分配位置
> > > > > > `TLAB`: 小块线程私有，第一分配位置
> > > > > 2个`Survivor`区： 两块大小相同的区域<sub>自适应内存分配可能大小不同</sub>
> > > >
> > > > 老年代：生命周期较长或者超大对象分配
> > >
> > > 元空间：
> > > - 类型信息
> > > - `field`
> > > - 方法信息
> > > - `static final`全局常量
> > > - `run-time constant pool`
> > > - `JIT`代码缓存

> 蚂蚁金服
> > `Java8`的内存分代改进：元空间直接使用物理内存，取消`permGen`永久代\
> > `JVM`内存分哪几个区，每个区的作用是什么？\
> > 一面：`JVM`内存分布/内存结构，栈和堆的区别，堆的结构，为什么两个`survivor`区
> > > `stack area` 线程私有, `heap area`  线程共享,  两个`survivor`区，碎片整理，复制算法，优化`GC`
> >
> > 二面：`Eden`区和`survivor`区的比例分配
> > > `-XX:SurvivorRatio=8` 8:1:1
> > > `Eden`过大，`survivor`小，`MinorGC`作用会被削弱，可能生命周期较小的对象更大概率晋升到`Old`
> > > `Eden`过小，`MinorGC`次数会增加，影响应用效率

> 小米
> > JVM内存分区，为什么要有新生代和老年代?
> > > 优化`GC`性能\
> > > 不分代，所有对象放一起，每次GC都要全部检查一遍`STW`\
> > > 分代，新对象都在一起，优先`MinorGC`新对象，不需要经常`FullGC`

> 字节跳动
> > 二面：java的内存分区\
> > 二面：讲一讲JVM运行时数据库区\
> > 什么时候对象会进入老年代
> > > 达到阈值，从`Survivor from`区晋升到`Old`区\
> > > `Survivor`区中某一年龄的对象总大小超过`Survivor`空间的一半，不需要等达到阈值，大于等于年龄的对象一起晋升到`Old`区\
> > > 大对象，`Eden`区放不下，直接放在`Old`

> 京东
> > JVM内存结构，Eden和Survivor比例\
> > JVM内存为什么要分成新生代，老年代，持久代，新生代中为什么要分为`Eden`区和`Survivor`

> 天猫
> > 一面：JVM内存模型以及分区，需要详细到每个区放什么\
> > 一面：JVM的内存模型，java8做了什么修改

> 拼多多
> > JVM内存分哪几个区，每个区的作用是什么

> 美团
> > java内存分配\
> > jvm的永久代中会发生垃圾回收？\
> > 一面：jvm内存分区，为什么要有新生代和老年代
