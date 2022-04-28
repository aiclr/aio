## 聚合器模块 隐式可读性 requires transitive

- 聚合器模块不包含代码，只有一个模块描述符，为其他模块设置隐式可读性 传递 模块。例如 java.se 模块
- cli 模块 requires transitive facade
- 如果有多个服务实现，可以使用聚合器模块，将需要使用的服务实现组合到一起提供给客户端，这样客户端只需要知道使用哪一个聚合器即可
- ServiceLoader 通过两种方式创建服务实例
    1. 服务实现类：`public 无参构造器`
    2. 服务提供者方法：`public static 具体实现类 provider() 调用 实现类的构造器`
        - 服务提供者方法 不必在服务实现类中，可以在其他类中，这时候要在 `module-info.java` 指定服务提供者类

## 编译

```shell
# -encoding UTF-8 windows 中文编码异常 
# cli requires api 和 facade 编译 cli 会自动编译 api facade svc_a cli
# 因为 facade requires transitive svc_a
javac -encoding UTF-8 -d out --module-source-path src -m cli
# 编译 cli 和 svc_b 。 cli 与 svc_b 并无直接关系 编译cli 不会编译 svc_b
javac -encoding UTF-8 -d out --module-source-path src -m cli,svc_b
```

## 运行 class

```shell
# out 下有服务实现者 
java -p out -m cli/org.bougainvilleas.svc.cli.Client
# -p --module-path 模块路径 linux 下使用 : 分割 windows 下 使用 ; 分割 且要在 cmd 下运行 
# 隐式传递 需要列出全部模块
java -p out/api:out/cli:out/facade:out/svc_a -m cli/org.bougainvilleas.svc.cli.Client
# windows cmd
java -p out/api;out/cli;out/facade;out/svc_a -m cli/org.bougainvilleas.svc.cli.Client
```

## jar

```shell
mkdir -p out/mods

# -c, --create               创建档案
# -v, --verbose              在标准输出中生成详细输出
# -f, --file=FILE            档案文件名。省略时, 基于操作 使用 stdin 或 stdout
# -e, --main-class=CLASSNAME 捆绑到模块化或可执行 jar 档案的独立应用程序 的应用程序入口点
# -C DIR                     更改为指定的目录并包含 以下文件
jar -cvfe out/mods/App.jar org.bougainvilleas.svc.cli.Client -C out/cli .
jar -cvf out/mods/api.jar -C out/api .
jar -cvf out/mods/facade.jar -C out/facade .
jar -cvf out/mods/svc_a.jar -C out/svc_a .
```

## run jar

```shell
java -p out/mods -m cli
java -p out/mods/App.jar:out/mods/api.jar:out/mods/facade.jar:out/mods/svc_a.jar -m cli
# windows cmd
java -p out/mods/App.jar;out/mods/api.jar;out/mods/facade.jar;out/mods/svc_a.jar -m cli
```

## jlink ***windwos powershell gitbash 异常，使用 cmd***

```shell
mkdir -p out/jlink

# linux
# cli require api --add-modules cli 会自动 add api 但是不会 add svc
# -v, --verbose  启用详细跟踪
#  --compress=<0|1|2>  Enable compression of resources:   Level 0: No compression Level 1: Constant string sharing Level 2: ZIP
jlink -v -p out/mods/:$JAVA_HOME/jmods --add-modules cli --launcher app=cli --output out/jlink/image
jlink --compress=0 -v -p out/mods/:$JAVA_HOME/jmods --add-modules cli --launcher app=cli --output out/jlink/image
jlink --compress=1 -v -p out/mods/:$JAVA_HOME/jmods --add-modules cli --launcher app=cli --output out/jlink/image
jlink --compress=2 -v -p out/mods/:$JAVA_HOME/jmods --add-modules cli --launcher app=cli --output out/jlink/image

# windows cmd 执行  
# cli require api --add-modules cli 会自动 add api 但是不会 add svc
# -v, --verbose  启用详细跟踪
#  --compress=<0|1|2>  Enable compression of resources:   Level 0: No compression Level 1: Constant string sharing Level 2: ZIP
jlink -v -p out/mods/;$JAVA_HOME/jmods --add-modules cli --launcher app=cli --output out/jlink/image
jlink -v --compress=0 -p out/mods/;$JAVA_HOME/jmods --add-modules cli --launcher app=cli --output out/jlink/image
jlink -v --compress=1 -p out/mods/;$JAVA_HOME/jmods --add-modules cli --launcher app=cli --output out/jlink/image
jlink -v --compress=2 -p out/mods/;$JAVA_HOME/jmods --add-modules cli --launcher app=cli --output out/jlink/image
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