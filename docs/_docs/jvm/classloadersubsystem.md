---
layout: content
title: Class Loader SubSystem
targets:
  - name: Top
    link: class-loader-subsystem
  - name: Loading
    link: loading
  - name: Linking
    link: linking
  - name: 🚦&emsp;Verify
    link: verify
  - name: 🚦&emsp;Prepare
    link: prepare
  - name: 🚦&emsp;Resolve
    link: resolve
  - name: Initialization
    link: initialization
  - name: 双亲委派机制
    link: 双亲委派机制
  - name: 沙箱安全机制
    link: 沙箱安全机制
  - name: 类的主动使用和被动使用
    link: 类的主动使用和被动使用

---

# Class Loader SubSystem

> `Class Loader SubSystem`<sub>类加载子系统</sub>负责从**文件系统**或者**网络**中加载`class`文件，`class`文件在文件开头有特定的文件标识。\
> `ClassLoader`只负责`class`文件的**加载**，能否运行由`Execution Engine`决定。\
> 加载的**类信息**存放于`Method Area`<sub>方法区</sub>，`Method Area`中还会存放`run-time constant pool`<sub>**运行时常量池**</sub>信息<sub>`final`</sub>，**字符串字面量**和**数字常量**<sub>这部分常量信息是`class`文件中常量池部分的内存映射</sub>
>
> **加载过程**
> - [Loading](#loading)
> - [Linking](#linking)
>   - [Verify](#verify)
>   - [Prepare](#prepare)
>   - [Resolve](#resolve)
> - [Initialization](#initialization)
>
> 类加载特性和机制
> - [双亲委派机制](#双亲委派机制)
> - [沙箱安全机制](#沙箱安全机制)
> - [类的主动使用和被动使用](#类的主动使用和被动使用)

## Loading

- 通过一个类的**全限定名**<sub>全类名</sub>获取定义此类的**二进制字节流**
- 将这个**字节流**所代表的**静态存储结构**转化为`Method Area`的**运行时数据结构**
- 在内存中创建**一个**代表这个类的`java.lang.Class`对象，作为访问这个类的数据的**访问入口**
- 加载`.class`文件方式
  - 从**本地文件系统**直接加载
  - 通过**网络**获取，典型场景`Web Applet`
  - 从**压缩包**中读取，`jar`包，`war`包基础
  - **运行时计算**生成，**动态代理技术**
  - 由其他文件生成，`JSP`应用
  - 从专有**数据库**中提前`.class`文件，比较少见
  - 从**加密文件**中获取，防止**反编译**的保护措施

### ClassLoader

> JVM规范：所有派生于抽象类`java.lang.ClassLoader`的`ClassLoader`都划分为`User-Defined ClassLoader`<sub>自定义类加载器</sub>\
> JVM支持两种`ClassLoader`
> 1. `Bootstrap ClassLoader` **引导类加载器**，`c`和`c++`实现，嵌套在`jvm`内部
> 2. `User-Defined ClassLoader` **自定义类加载器**，`java`实现，派生于抽象类`java.lang.ClassLoader`
>
> 常见类加载器：
> ```java
> // sun.misc.Launcher
> // sun.misc.Launcher$AppClassLoader
> // sun.misc.Launcher$ExtClassLoader
> 
> // java.net.URLClassLoader
> // java.security.SecureClassLoader
> // java.lang.ClassLoader
>
> // URLClassLoader extends SecureClassLoader
> // SecureClassLoader extends ClassLoader  
> ClassLoader systemClassLoader=ClassLoader.getSystemClassLoader();//sun.misc.Launcher$AppClassLoader 系统类加载器java实现
> ClassLoader extClassLoader = systemClassLoader.getParent();//sun.misc.Launcher$ExtClassLoader 扩展类加载器java实现
> ClassLoader bootstrapClassloader = extClassLoader.getParent();//null 引导类加载器（由c和c++实现）
> ```
> 各种类加载器不是上下层关系，也不是父子类的继承关系，是**包含关系**，类似a文件夹里有b和c这种包含关系
> > `Bootstrap ClassLoader` **引导类加载器**
> > > `c`和`c++`实现，嵌套在`jvm`内部\
> > > 加载`java`核心库<sub>`JAVA_HOME/jre/lib/rt.jar、resources.jar`或`sun.boot.class.path`路径下的内容</sub>用于提供`JVM`自身需要的类\
> > > 没有`parent`<sub>父类加载器</sub>。\
> > > 加载`ExtClassLoader`扩展类加载器和`AppClassLoader`系统类加载器，并指定他们的`parent`<sub>父类加载器</sub>\
> > > 处于**安全**考虑，`Bootstrap ClassLoader`只加载包名为`java、javax、sun`等开头的类
> >
> > `User-Defined ClassLoader` **自定义类加载器**
> > > `ExtClassLoader` 扩展类加载器。`ClassLoader extClassLoader = systemClassLoader.getParent();`
> > > > java实现。实现位置`sun.misc.Launcher`内静态内部类`static class ExtClassLoader extends URLClassLoader`\
> > > > `parent`<sub>父类加载器</sub>为 `Bootstrap ClassLoader`\
> > > > 从`java.ext.dirs`**系统属性**所指定的目录中加载类库\
> > > > 从JDK的安装目录`jre/lib/ext`子目录（扩展目录）下加载类库，如果用户创建的jar放在此目录下，也会自动由`ExtClassLoader`加载
> > >
> > > `SystemClassLoader`系统类加载器<sub>或称为应用程序类加载器`AppClassLoader`</sub>。`ClassLoader systemClassLoader=ClassLoader.getSystemClassLoader();`
> > > > java实现。实现位置`sun.misc.Launcher`内静态内部类`static class AppClassLoader extends URLClassLoader`\
> > > > `parent`<sub>父类加载器</sub>为 `ExtClassLoader` 扩展类加载器\
> > > > 负责加载**环境变量**`classpath`或**系统属性**`java.class.path`指定路径下的类库\
> > > > 程序中的**默认类加载器**，一般java应用的类都是由其来完成加载\
> > > > 通过`CLassLoader.getSystemClassLoader()`可以获取到该类加载器
> > >
> > > `User Defined Class Loader` 用户自定义类加载器
> > > > java日常应用程序开发中，类加载几乎是由`Bootstrap ClassLoader、ExtClassLoader、AppClassLoader`3种类加载器相互配合执行的，在必要时，还可以自主实现类加载器，来定制类的加载方式\
> > > > 自定义类加载器功能：
> > > > - 隔离加载类
> > > >   - 引入多个框架或中间件时，防止同名同路径的类冲突，通过自定义类加载器仲裁，防止冲突
> > > > - 修改类加载的方式
> > > >   - 需要时加载、动态加载
> > > > - 扩展加载源
> > > >   - 扩展class字节码文件的来源方式。本地文件系统、网络、数据库等
> > > > - 防止源码泄漏
> > > >   - 通过自定义类加载器加载加密过的class字节码。分发加密class字节码文件，从而防止反编译。
> > > > 
> > > > 自定义类加载器实现：
> > > > 1. 开发人员可以通过继承抽象类`java.lang.Classloader`类的方式实现自己的类加载器
> > > > 2. `JDK1.2`之前，在自定义类加载器时，总会去继承`ClassLoader`类并重写`loadClass`方法，来实现自定义的类加载器
> > > > 3. `JDK1.2`之后，不再建议用户去重写loadClass方法，建议把自定义的类加载逻辑写在`findClass`方法中
> > > > 4. 在编写自定义类加载器时，如果没有太过于复杂的需求，可以直接继承`URLClassLoader`类，这样就可以避免自己去编写`findClass`方法及其获取**字节码流**的方式，使自定义类加载器编写**更简洁**

## Linking

### Verify

- 确保`class`文件的字节流中包含的信息符合当前`jvm`要求，保证被加载类的**正确性**，保证不会危害`jvm`自身安全
- 验证方式
  - 文件格式验证
  - 元数据验证
  - 字节码验证
  - 符号引用验证

### Prepare

- 为[类变量](./jvmstacks.md#拓展)分配内存并且设置该**类变量的默认初始值**<sub>`static int iClass=1;` 此时赋默认初始值`iClass=0`</sub>
- 不包含用`final`修饰的`static`静态常量，因为`final`在**编译时**分配，`prepare`阶段会**显示初始化**
- 这里**不会**为**实例变量**分配初始化，**类变量**会分配在`Method Area`，而**实例变量随着对象**一起分配到`Heap Area`

### Resolve

- 将`class文件的常量池`内的`symbolic reference`<sub>符号引用</sub>转换为**直接引用**的过程。<sub>`virtual method table`虚方法表创建并开始初始化</sub>
- 事实上，`Resolve`<sub>解析</sub>操作往往会伴随`JVM`在执行完`Initialization`<sub>初始化</sub>之后再执行
- `symbolic reference`<sub>符号引用</sub>就是一组符号来描述所应用的目标，`symbolic reference`<sub>符号引用</sub>的字面量形式明确定义在jvm规范的class文件格式中。 **直接引用**就是直接指向目标的指针、相对偏移量或一个间接定位到目标的句柄
- `Resolve`<sub>解析</sub>主要针对类、接口、字段、类方法、接口方法、方法类型等。对应`class文件的常量池`
  - `CONSTANT_Class_info`
  - `CONSTANT_Fieldref_info`
  - `CONSTANT_Methodref_info`

## Initialization

- `Initialization`<sub>初始化阶段</sub>就是执行`<clinit>()`<sub>**类构造器方法**不是**类的构造器**</sub>的过程<sub>当有**静态变量赋值**，**静态代码块赋值**时才会有`clinit`</sub>
- `<clinit>()`<sub>**类构造器方法**</sub>不需要定义，是`javac`编译器自动收集类中的所有**类静态变量的赋值动作**和**静态代码块中的语句**`合并`而来
- `<clinit>()`<sub>**类构造器方法**</sub>中指令按语句在源文件中出现的顺序执行
- `<clinit>()`<sub>**类构造器方法**</sub>不同于**类的构造器**，**类的构造器**在jvm视角下是`<init>()`
- 若A类具有**父类**B类，`jvm`会保证B类的`<clinit>()`执行完毕后，A类的`<clinit>()`立即执行
- `jvm`必须保证一个类的`<clinit>()`方法在多线程下被同步加锁。类只会被加载一次，并放置到`method area`<sub>JDK8为`metadata`</sub>

## 双亲委派机制

> JVM对class文件采用的是**按需加载**的方式，当需要使用该类时才会将它的class文件加载到内存，生成`Class对象`\
> JVM采用**双亲委派机制**，即把加载类的请求交给`parent`<sub>父类加载器</sub>处理，是一种任务委派模式\
> ![image](/assets/images/jvm/双亲委派.svg)\
> ![image](/assets/images/jvm/双亲委派加载jdbc.jar.svg)\
> 工作原理
>   1. 如果一个类加载器A收到了类加载的请求，并不会立即去执行加载，而是把这个请求委托给`parent`<sub>父类加载器B</sub>加载
>   2. 如果该`parent`<sub>父类加载器B</sub>还存在`parent`<sub>父类加载器C</sub>，则类加载器C继续向上委托，直到请求到达顶层的`BootstrapClassloader`
>   3. 如果`parent`<sub>父类加载器X</sub>可以完成类加载任务，则成功加载返回。如果`parent`<sub>父类加载器X</sub>不能完成加载任务，子加载器才会尝试自己去加载
>
> 优势：
> 1. 避免重复加载类
> 2. 保护程序安全，防止**核心API**被随意篡改:
>     1. 自定义`java.lang.String.main():Error: Main method not found in class java.lang.String`
>     2. 自定义类`java.lang.MyBougainvillea:Exception in thread "main" java.lang.SecurityException: Prohibited package name: java.lang`
> 3. 类隔离说明： `A1,A2`类 由 `ExtClassLoader` 加载；`B1,B2`类 由 `AppClassLoader` 加载。
>     - B1 可以访问 A1 A2 B2
>     - B2 可以访问 A1 A2 B1
>     - A1 可以访问 A2
>     - A2 可以访问 A1

## 沙箱安全机制

- 自定义`java.lang.String`类，加载**自定义String**类的时候根据双亲委派原则会率先使用`BootstrapClassLoader`加载
- 此时加载的是`jdk`自带的`rt.jar`包中`java\lang\String.class` 报错信息说没有main方法是因为`rt.jar`下的`String`类没有`main`方法
- 这样保证对`java`核心源代码的保护这就是**沙箱安全机制**

## 类的主动使用和被动使用

> 判断JVM中两个Class对象是否为同一个类
>   1. 类的完整类名必须一致，包括包名
>   2. 加载这个类的`ClassLoader`<sub>指`ClassLoader`**实例对象**</sub>必须相同
>
> 在`JVM`中即使这两个**类对象**<sub>`Class`对象</sub>来源同一个class文件，被同一个`jvm`所加载，但只要加载他们的`ClassLoader实例对象`不同，那么这两个**类对象**<sub>`Class`对象</sub>也是不相等<sub>`==`</sub>的
>
> `JVM`必须知道一个类型是由`BootstrapClassLoader`加载还是由`用户类加载器`加载
>
> 如果一个类型是由`用户类加载器`加载，那么`JVM`会将这个`用户类加载器`的一个`reference`<sub>引用</sub>作为`类型信息`的一部分保存在`Method Area`中
>
> `Dynamic Linking`<sub>动态链接</sub>解析`一个类型`到`另一个类型`的`reference`<sub>引用</sub>的时候，`JVM`需要保证这`两个类型`的`类加载器`是相同的
>
> **主动使用**
> > 类会执行`Initialization` 阶段
> >
> > 1. 创建类的**实例**
> > 2. 访问某一个类或接口的**静态变量**或者对静态变量赋值
> > 3. 调用类的**静态方法**
> > 4. **反射**：`Class.forName("org.bougainvillea.jvm.Demo")`
> > 5. 初始化一个类的**子类**<sub>加载一个类的时候其父类会先初始化</sub>
> > 6. `JVM`启动时被标明为**启动类**的类
> > 7. `JDK7`开始提供的**动态语言支持**
> >     1. `java.lang.invoke.MethodHandle`实例的解析结果
> >     2. `REF_getStatic、REF_putStatic、REF_invokeStatic`**句柄**对应的类没有初始化则初始化
>
> **被动使用**
> > 类加载阶段不会执行 Initialization 阶段
> > > 比如：静态内部类
> >
> > 除了主动使用的七种情况，其他使用java类的方式都被看作是对类的被动使用，都**不会导致类的初始化**。
