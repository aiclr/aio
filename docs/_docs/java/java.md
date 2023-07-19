<div style="text-align: center;font-size: 40px;">Java 模块化 编译 打包 运行</div>

## **注意**

> `windows 错误: 编码 GBK 的不可映射字符`\
> 添加编译参数指定字符集编码 `-encoding UTF-8` \
> `javac -encoding UTF-8 -d out/single --module-source-path single -m helloworld`

> `javac -d out --module-source-path src -m helloworld` 的 `--module-source-path src` 从 src 目录下检索java 模块 \
> 但是 需要整个模块代码目录 在src下才能正常编译 \
> IDE项目目录、gradle、maven 有自己的代码目录规范 编译时不是采用`--module-source-path` 应该是编译全部 java 文件进行编译 \
> `javac -d out/helloworld src/helloworld/org/bougainvilleas/ilj/HelloWorld.java src/helloworld/module-info.java` 


## 目录

```shell
ilj
└── src
    └── helloworld
        ├── module-info.java
        └── org
            └── bougainvilleas
                └── ilj
                    └── HelloWorld.java
```

## module-info.java

```java
module helloworld{}
```

## HelloWorld.java **注意包类路径**

```java
package org.bougainvilleas.ilj;

public class HelloWorld {

    public static void main(String[] args) {
        System.err.println("Hello modular world");
    }
    
}

```

## 编译

```shell
# java9 之前
javac -d out \
src/org/bougainvilleas/ilj/A.java \
src/org/bougainvilleas/ilj/B.java

# java9 及以后 会添加反映模块名称的 helloworld 目录  out/helloworld
# 将module-info.java 作为额外源文件进行编译
javac -d out/helloworld \
src/helloworld/org/bougainvilleas/ilj/HelloWorld.java \
src/helloworld/module-info.java

# --module-source-path src 在 src 下检索 模块 helloworld
javac -d out --module-source-path src -m helloworld
```

## 运行classes

```shell
# 运行 模块 classes --module-path = -p   --module = -m
java --module-path out \
--module helloworld/org.bougainvilleas.ilj.HelloWorld
java -p out \
-m helloworld/org.bougainvilleas.ilj.HelloWorld
```

## 打包 **需要先建立 mods 目录**

```shell
mkdir mods

jar -cvfe mods/helloworld.jar \
org.bougainvilleas.ilj.HelloWorld \
-C out/helloworld .
```

## 运行 jar

```shell
# 运行 module jar （构建jar时 指定了入口点 org.bougainvilleas.ilj.Helloworld）
java -p mods -m helloworld
# --show-module-resolution 跟踪模块系统采取的操作
java --show-module-resolution -p mods -m helloworld
# --limit-modules java.base 阻止通过服务绑定解析其他平台模块
java --show-module-resolution --limit-modules java.base \
-p mods -m helloworld
```

## jlink 创建仅包含运行应用程序所需模块的运行时 image

> jlink 不执行自动服务绑定即：不会根据 **uses** 子句自动将服务提供者包含在映像中\
java.base 具有大量uses子句\
所有这些服务类型的提供者位于其他各种平台模块中\
默认绑定所有这些服务将会增大映像的大小 \
不会根据 **uses** 子句自动将服务提供者包含在映像中


```shell
# --module-path mods/:$JAVA_HOME/jmods 构造一个模块路径
# 包含 mods 目录（helloworld 模块位置）
# 包含要链接到 image 中的平台模块的JDK安装目录
# 与 javac java 不同 必须将平台模块显示添加到 jlink 模块路径中
#
# --add-modules helloworld 
# 表示 helloworld 是需要在运行时 image 中运行的根模块
#
# --launcher 定义了一个入口点来直接运行 image 中的模块
# --output 表示运行时 image 的目录名称
jlink --module-path mods/:$JAVA_HOME/jmods \
--add-modules helloworld \
--launcher hello=helloworld \
--output helloworld-image

tree helloworld-image

helloworld-image/
├── bin
│   ├── hello
│   ├── java
│   └── keytool
├── conf
├── include
├── legal
│   └── java.base
├── lib
├── man
└── release
```

## 运行

```shell
tree helloworld-image/bin/

helloworld-image/bin/
├── hello #直接启动 helloworld 模块的可执行脚本
├── java # 仅能解析 helloworld 及其依赖项的 java runtime
└── keytool
# 运行
./helloworld-image/bin/hello
./helloworld-image/bin/java --list-modules

```

---

## 多模块

```shell
src
├── hello
│   ├── module-info.java
│   └── org
│       └── bougainvilleas
│           └── hello
│               └── HelloWorld.java
└── hi
    ├── module-info.java
    └── org
        └── bougainvilleas
            └── hi
                └── HI.java
```

```java
// hello 依赖 hi 模块
// cat src/hi/module-info.java 
module hi
{
    exports org.bougainvilleas.hi;
}
// cat src/hello/module-info.java 
module hello
{
    requires hi;
}

//cat src/hi/org/bougainvilleas/hi/HI.java 
package org.bougainvilleas.hi;

public class HI {

    public static String say() {
       return "hi";
    }
    
}
//cat src/hello/org/bougainvilleas/hello/HelloWorld.java 
package org.bougainvilleas.hello;

import org.bougainvilleas.hi.HI;

public class HelloWorld {

    public static void main(String[] args) {
        System.err.println(HI.say());
    }
    
}

```

## 编译多模块

```shell
# --module-source-path src 在 src 下 检索 模块
# 编译 hello 
# hello 依赖 hi 也会编译 hi
javac -d out --module-source-path src -m hello
# 编译 hi 不会编译 hello
javac -d out --module-source-path src -m hi

```
## 运行 classes

```shell
# module path 需要包含运行所需的所有模块
java -p out -m hello/org.bougainvilleas.hello.HelloWorld
# unix module path 使用 : 号分割
java -p out/hello:out/hi -m hello/org.bougainvilleas.hello.HelloWorld
# windows module path 使用 ; 号分割 注意不要使用 powershell
java -p out/hello;out/hi -m hello/org.bougainvilleas.hello.HelloWorld
```

## 打包 **分模块打包 放到同一目录下**

```shell
out/
├── hello
│   ├── module-info.class
│   └── org
│       └── bougainvilleas
│           └── hello
│               └── HelloWorld.class
└── hi
    ├── module-info.class
    └── org
        └── bougainvilleas
            └── hi
                └── HI.class
# -e, --main-class=CLASSNAME jar名不用与模块名保持一致
jar -cvfe mods/hello.jar org.bougainvilleas.hello -C out/hello .
jar -cvf mods/hi.jar -C out/hi .
```

## 运行 **jar 放到同一目录下**

```shell
mods/
├── hello.jar
└── hi.jar

java -p mods -m hello

```

## jlink

```shell
# -p --module-path mods/:$JAVA_HOME/jmods 构造一个模块路径
# 包含 mods 目录（helloworld 模块位置）
# 包含要链接到 image 中的平台模块的JDK安装目录
# 与 javac java 不同 必须将平台模块显示添加到 jlink 模块路径中
#
# --add-modules hello 
# 表示 hello 是需要在运行时 image 中运行的根模块
# 会 自动将其 requires 的模块添加到 images
# 
# --launcher 定义了一个入口点来直接运行 image 中的模块
# --output 表示运行时 image 的目录名称
jlink -p mods/:$JAVA_HOME/jmods \
--add-modules hello \
--launcher hello=hello \
--output sayHi
sayHi/
├── bin
│   ├── hello
│   ├── java
│   └── keytool
├── conf
├── include
├── legal
├── lib
├── man
└── release
# 运行
./sayHi/bin/hello
```