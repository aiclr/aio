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
jar -cvfe out/mods/helloworld.jar org.bougainvilleas.helloworld.HelloWorld -C out/helloworld .
```

## run jar

```shell
java -p out/mods -m helloworld
```

## jlink ***windwos powershell gitbash 异常，使用 cmd***

```shell
mkdir -p out/jlink
jlink --module-path out/mods/:$JAVA_HOME/jmods --add-modules helloworld --launcher hello=helloworld --output out/jlink/helloworld-image
```

## jlink run 

```shell
# linux
./out/jlink/helloworld-image/bin/hello
./out/jlink/helloworld-image/bin/java --list-modules
# windows
.\out\jlink\helloworld-image\bin\hello.bat
.\out\jlink\helloworld-image\bin\java --list-modules
```