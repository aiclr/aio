## 检查区分服务实现类

> java 9 ServiceLoader 支持在实例化之前对服务实现类型进行检查 \
> 除了遍历目前所有的实例以外，还可以检查一连串的 ServiceLoader.Provider 描述 \
> ServiceLoader 上的 stream 方法返回一串需要检查的 ServiceLoader.Provider 对象
> 
> Provider 的 type 方法 可以获取服务实现的 java.lang.Class \
> "地图不是领土"
> > 仅仅是拥有一个 Class 对象 并不一定意味着可以在自己的模块中实例化它，模块系统的所有访问检查仍然适用 \
> > Class<? extends Api> clazz = apiProvider.type(); \
> > 当尝试通过如下反射获取一个实例时，如果该类没有导出 那么将会获得 IllegalAccessError \
> > apiProvider.type().newInstance();


## 模块化服务
- ServiceLoader 通过两种方式创建服务实例
    1. 服务实现类：`public 无参构造器`
    2. 服务提供者方法：`public static 具体实现类 provider() 调用 实现类的构造器`
        - 服务提供者方法 不必在服务实现类中，可以在其他类中，这时候要在 `module-info.java` 指定服务提供者类

## 编译

```shell
# -encoding UTF-8 windows 中文编码异常 cli requires api 编译 cli 会自动编译 api
javac -encoding UTF-8 -d out --module-source-path src -m cli
# 编译 cli 和 svc 。 cli与svc并无直接关系 编译cli 不会编译 svc
javac -encoding UTF-8 -d out --module-source-path src -m cli,svc
```

## 运行 class

```shell
# out 下有服务实现者 
java -p out -m cli/org.bougainvilleas.svc.cli.Client
# -p --module-path 模块路径 linux 下使用 : 分割 windows 下 使用 ; 分割 且要在 cmd 下运行 
# 以下不包含服务实现类 则不会有服务实现者被调用
java -p out/api:out/cli -m cli/org.bougainvilleas.svc.cli.Client
java -p out/api:out/cli:out/svc -m cli/org.bougainvilleas.svc.cli.Client
# windows cmd
java -p out/api;out/cli -m cli/org.bougainvilleas.svc.cli.Client
java -p out/api:out/cli;out/svc -m cli/org.bougainvilleas.svc.cli.Client
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
jar -cvf out/mods/svc.jar -C out/svc .
```

## run jar

```shell
java -p out/mods -m cli
java -p out/mods/App.jar:out/mods/api.jar -m cli
java -p out/mods/App.jar:out/mods/api.jar:out/mods/svc.jar -m cli
# windows cmd
java -p out/mods/App.jar;out/mods/api.jar -m cli
java -p out/mods/App.jar;out/mods/api.jar;out/mods/svc.jar -m cli
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
# --add-modules cli,svc
jlink -v -p out/mods/:$JAVA_HOME/jmods --add-modules cli,svc --launcher app=cli --output out/jlink/image

# windows cmd 执行  
# cli require api --add-modules cli 会自动 add api 但是不会 add svc
# -v, --verbose  启用详细跟踪
#  --compress=<0|1|2>  Enable compression of resources:   Level 0: No compression Level 1: Constant string sharing Level 2: ZIP
jlink -v -p out/mods/;$JAVA_HOME/jmods --add-modules cli --launcher app=cli --output out/jlink/image
jlink -v --compress=0 -p out/mods/;$JAVA_HOME/jmods --add-modules cli --launcher app=cli --output out/jlink/image
jlink -v --compress=1 -p out/mods/;$JAVA_HOME/jmods --add-modules cli --launcher app=cli --output out/jlink/image
jlink -v --compress=2 -p out/mods/;$JAVA_HOME/jmods --add-modules cli --launcher app=cli --output out/jlink/image
# --add-modules cli,svc
jlink -v --compress=2 -p out/mods/;$JAVA_HOME/jmods --add-modules cli,svc --launcher app=cli --output out/jlink/image
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