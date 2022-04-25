## 编译 

```shell
# -encoding UTF-8 windows 中文编码异常
javac -encoding UTF-8 -d out/helloworld src/helloworld/org/bougainvilleas/helloworld/HelloWorld.java src/helloworld/module-info.java
# -d out 会自动生成模块 helloworld 目录
javac -encoding UTF-8 -d out --module-source-path src -m helloworld
```

## 运行 class

```shell
java -p out/helloworld -m helloworld/org.bougainvilleas.helloworld.HelloWorld
```

## jar

```shell
mkdir -p out/mods
# -c, --create               创建档案
# -v, --verbose              在标准输出中生成详细输出
# -f, --file=FILE            档案文件名。省略时, 基于操作 使用 stdin 或 stdout
# -e, --main-class=CLASSNAME 捆绑到模块化或可执行 jar 档案的独立应用程序 的应用程序入口点
# -C DIR                     更改为指定的目录并包含 以下文件
jar -cvfe out/mods/helloworld.jar org.bougainvilleas.helloworld.HelloWorld -C out/helloworld .
```

## run jar

```shell
java -p out/mods -m helloworld
```

## jlink ***windwos powershell gitbash 异常，使用 cmd***

```shell
mkdir -p out/jlink
jlink --module-path out/mods/:$JAVA_HOME/jmods --add-modules helloworld --launcher app=helloworld --output out/jlink/image
```

## jlink run 

```shell
# linux
./out/jlink/image/bin/app
./out/jlink/image/bin/java --list-modules
# windows
.\out\jlink\image\bin\app.bat
.\out\jlink\image\bin\java --list-modules
```