## 编译 

```shell
# -encoding UTF-8 windows 中文编码异常 client requires service 编译 client 会自动编译 service
javac -encoding UTF-8 -d out --module-source-path src -m client
```

## 运行 class

```shell
java -p out -m client/org.bougainvilleas.client.App
# -p --module-path 模块路径 linux 下使用 : 分割 windows 下 使用 ; 分割 且要在 cmd 下运行
java -p out/client:out/service -m client/org.bougainvilleas.client.App
java -p out/client;out/service -m client/org.bougainvilleas.client.App
```

## jar

```shell
mkdir -p out/mods

# -c, --create               创建档案
# -v, --verbose              在标准输出中生成详细输出
# -f, --file=FILE            档案文件名。省略时, 基于操作 使用 stdin 或 stdout
# -e, --main-class=CLASSNAME 捆绑到模块化或可执行 jar 档案的独立应用程序 的应用程序入口点
# -C DIR                     更改为指定的目录并包含 以下文件
jar -cvfe out/mods/client.jar org.bougainvilleas.client.App -C out/client .
jar -cvf out/mods/service.jar -C out/service .
```

## run jar

```shell
java -p out/mods -m client
```

## jlink ***windwos powershell gitbash 异常，使用 cmd***

```shell
# linux
mkdir -p out/jlink
jlink --module-path out/mods/:$JAVA_HOME/jmods --add-modules client --launcher app=client --output out/jlink/image
jlink --module-path out/mods/client.jar:out/mods/service.jar:$JAVA_HOME/jmods --add-modules client --launcher app=client --output out/jlink/image
# windows cmd 执行
jlink --module-path out/mods/;$JAVA_HOME/jmods --add-modules client --launcher app=client --output out/jlink/image
jlink --module-path out/mods/client.jar;out/mods/service.jar;$JAVA_HOME/jmods --add-modules client --launcher app=client --output out/jlink/image
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