# Class Loader subSystem

- 类加载器子系统负责从文件系统或者网络中加载class文件，class文件在文件开头有特定的文件标识
- ClassLoader只负责class文件的加载，能否运行由Execution Engine决定
- 加载的类信息存放于方法区，方法区中还会存放运行时常量池信息（final），字符串字面量和数字常量（这部分常量信息是class文件中常量池部分的内存映射）

## Loading

- 通过一个类的全限定名（全类名）获取定义此类的二进制字节流
- 将这个字节流所代表的静态存储结构转化为方法区的运行时数据结构
- 在内存中生成一个代表这个类的java.lang.Class对象，作为方法区这个类的各种数据的访问入口
- 加载.class文件方式
    - 从本地文件系统直接加载
    - 通过网络获取，典型场景Web Applet
    - 从压缩包中读取，jar包，war包基础
    - 运行时计算生成，动态代理技术
    - 由其他文件生成，JSP应用
    - 从专有数据库中提前.class文件，比较少见
    - 从加密文件中获取，防止反编译的保护措施

### Java虚拟机规范

- 所有派生于抽象类ClassLoader的类加载器都划分为自定义类加载器

### JVM支持两种类加载器

- Bootstrap ClassLoader 引导类加载器，c和c++实现，嵌套再jvm内部
- User-Defined ClassLoader 自定义加载器，java实现，派生于抽象类java.lang.ClassLoader

### 常见类加载器

- 不是上下层关系，也不是父子类的继承关系，是包含关系，a文件夹里有b和c，类似这种包含关系
- 引导类加载器
    - Bootstrap Class Loader c和c++实现
    - 嵌套再jvm内部
    - 用来加载java核心库（JAVA_HOME/jre/lib/rt.jar、resources.jar或sun.boot.class.path路径下的内容）用于提供JVM自身需要的类
    - 并不继承自java.lang.ClassLoader，没有父加载器
    - 加载扩展类和应用程序类加载器，并指定为他们的父类加载器
    - 处于安全考虑，Bootstrap启动类加载器只加载包名为java、javax、sun等开头的类
- 自定义类加载器
    - Extension Class Loader
        - java语言编写由sun.misc.Launcher的静态内部类static class ExtClassLoader extends URLClassLoader实现
        - 派生于ClassLoader
        - 父类加载器为 Bootstrap Class Loader
        - 从java.ext.dirs系统属性所指定的目录中加载类库
        - 从JDK的安装目录jre/lib/ext子目录（扩展目录）下加载类库，如果用户创建的jar放在此目录下，也会自动由扩展类加载器加载
    - System Class Loader
        - java语言编写由sun.misc.Launcher的静态内部类static class AppClassLoader extends URLClassLoader实现
        - 派生于ClassLoader
        - 父类加载器为 Extension Class Loader
        - 负责加载环境变量classpath或系统属性java.class.path指定路径下的类库
        - 程序中的默认类加载器，一般java应用的类都是由其来完成加载
        - 通过CLassLoader.getSystemClassLoader()可以获取到该类加载器
    - User Defined Class Loader 自定义类加载器

### 自定义类加载器

- java日常应用程序开发中，类加载几乎是由上述3种类加载器相互配合执行的，在必要时，我们还可以自定义类的加载器，来定制类的加载方式
- 自定义类加载器功能
    - 隔离加载类
        - 引入多个框架或中间件时，防止同名同路径的类冲突，进行仲裁，防止冲突
    - 修改类加载的方式
        - 需要时加载 动态加载
    - 扩展加载源
        - 字节码文件的来源方式，本地，网络，数据库等
    - 防止源码泄漏
        - 防止反编译，篡改对class文件加密

1. 开发人员可以通过继承抽象类java.lang.Classloader类的方式实现自己的类加载器
2. JDK1.2之前，在自定义类加载器时，总会去继承ClassLoader类并重写loadClass方法，来实现自定义的类加载器
3. JDK1.2之后，不再建议用户去重写loadClass方法，建议把自定义的类加载逻辑协助findClass方法中
4. 在编写自定义类加载器时，如果没有太过于复杂的需求，可以直接继承URLClassLoader类，这样就可以避免自己去编写findClass方法及其获取字节码流的方式，使自定义类加载器编写更简洁

## Linking

### Verify

- 确保class文件的字节流中包含的信息符合当前虚拟机要求，保证被加载类的正确性，保证不会危害虚拟机自身安全
- 验证方式
    - 文件格式验证
    - 元数据验证
    - 字节码验证
    - 符号引用验证

### Prepare

- 为类变量分配内存并且设置该类变量的默认初始值（int i=1; 此时i=0）
- 不包含用final修饰的static静态常量，因为final在编译时分配，prepare阶段会显示初始化
- 这里不会为实例变量分配初始化，类变量会分配在Method Area方法区，而实例变量会随着对象一起分配到Heap Area堆区

### Resolve

- 将常量池内的符号引用转换为直接引用的过程(虚方法表创建)
- 事实上，Resolve解析操作往往会伴随JVM在执行完初始化之后再执行
- 符号引用就是一组符号来描述所应用的目标，符号引用的字面量形式明确定义在《java虚拟机规范》的class文件格式中。 直接引用就是直接指向目标的指针、相对偏移量或一个间接定位到目标的句柄
- 解析动作主要针对类或接口、字段、类方法、接口方法、方法类型等。对应常量池中
    - CONSTANT_Class_info
    - CONSTANT_Fieldref_info
    - CONSTANT_Methodref_info
    - ......

## Initialization

- 初始化阶段就是执行类构造器方法（不是类的构造器） <clinit>() 的过程（当有静态变量赋值，静态代码块赋值时才会有clinit）
- 此方法不需要定义，是javac编译器自动收集类中的所有类静态变量的赋值动作和静态代码块中的语句合并而来
- 构造器方法中指令按语句在源文件中出现的顺序执行
- <clinit>() 不同于类的构造器
    - 构造器是jvm视角下的<init>()
- 若该类具有父类，jvm会保证 父类的<clinit>() 执行完毕后 子类的 <clinit>()执行
- jvm必须保证一个类的<clinit>() 方法在多线程下被同步加锁
    - 类只会被加载一次，并放置到method area（metadata）

## 双亲委派机制

- JVM对class文件采用的是按需加载的方式，当需要使用该类时才会将它的class文件加载到内存生产Class对象
- JVM采用双亲委派机制，即把请求交给父类处理，一种任务委派模式![image](img/ClassLoader.png)
- 加载jdbc.jar ![image](img/ClassLoaderExample.png)
- 工作原理
    1. 如果一个类加载器收到了类加载的请求，并不会先去加载，而是把这个请求委托给父类的加载器去执行
    2. 如果父类加载器还存在其他父类加载器，则进一步向上委托，依次递归，请求最终将到达顶层的BootstrapClassloader
    3. 如果父类加载器可以完成类加载任务，就成功加载返回如果父类加载器不能完成加载任务，子加载器才会尝试自己去加载
- 优势
    1. 避免类的重复加载
    2. 保护程序安全，防止核心API被随意篡改:
        - 自定义java.lang.String.main():Error: Main method not found in class java.lang.String
        - 自定义类java.lang.MyBougainvillea:Exception in thread "main" java.lang.SecurityException: Prohibited package name:
          java.lang

## 沙箱安全机制

- 自定义java.lang.String类，加载自定义String类的时候根据双亲委派原则会率先使用BootstrapClassLoader加载
- 此时加载的是jdk自带的rt.jar包中java\lang\String.class 报错信息说没有main方法是因为rt.jar下的String类没有main方法
- 这样保证对java核心源代码的保护这就是沙箱安全机制

## 类的主动使用和被动使用

- 判断JVM中两个Class对象是否为同一个类
    1. 类的完整类名必须一致，包括包名
    2. 加载这个类的ClassLoader（指ClassLoader实例对象）必须相同
- 在JVM中即使这两个类对象（Class对象）来源同一个class文件，被同一个虚拟机所加载，但只要加载他们的ClassLoader实例对象不同那么这两个类对象（Class对象）也是不相等的
- JVM必须知道一个类型是由启动类加载器加载还是由用户类加载器加载
- 如果一个类型是由用户类加载器加载，那么JVM会将这个类加载器的一个引用作为类型信息的一部分保存在方法区中
- （动态链接）当解析一个类型到另一个类型的引用的时候，JVM需要保证这两个类型的类加载器是相同的

### 主动使用

- 类会执行 Initialization 阶段

1. 创建类的实例
2. 访问某一个类或接口的静态变量或者对静态变量赋值
3. 调用类的静态方法
4. 反射：Class.forName("org.bougainvillea.Test")
5. 初始化一个类的子类(加载一个类的时候其父类会先初始化)
6. JVM启动时被标明为启动类的类
7. JDK7开始提供的动态语言支持
    - java.lang.invoke.MethodHandle实例的解析结果
    - REF_getStatic、REF_putStatic、REF_invokeStatic句柄对应类没有初始化则初始化

### 被动使用

- 类不会执行 Initialization 阶段
- 除了主动使用的七种情况，其他使用java类的方式都被看作是对类的被动使用，都不会导致类的初始化