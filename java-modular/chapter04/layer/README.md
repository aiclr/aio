## ModuleLayer

> 层对于模块来说就像类加载器对类一样---一种加载和实例化机制 \
> 当使用 Java 命令启动模块时，将会解析模块图 \
> 解析器使用来自平台本身和模块路径的模块来创建一组解析模块 \
> 主要根据模块描述符中的 requires 子句和 provides/uses 子句完成 \
> 完成解析后就不能在更改所生成的模块图了 \
> 已解析的模块图位于 ModuleLayer 内，层设置了一组连贯的模块 \
> 层本身可以引用父层，并形成一个非循环图

## boot layer

> 当使用 Java 启动一个模块时，Java 运行时将构建一个被称为引导层 boot layer 的初始层 \
> 该层包含了在解析提供给 Java 命令（以带有 -m 的初始模块形式提供，或者使用 --add-modules）的根模块之后所生成的模块图

```shell
# -encoding UTF-8 windows 中文编码异常
# -verbose 编译过程
javac -encoding UTF-8 -verbose -d out --module-source-path src -m boot_layer
# linux find 检索全部 .java文件 编译 全部模块
javac -encoding UTF-8 -verbose -d out --module-source-path src $(find src -name '*.java')

# 运行
java -p out -m boot_layer/org.bougainvilleas.layer.bootlayer.Client
# 打包
mkdir -p out/mods
# -c, --create               创建档案
# -v, --verbose              在标准输出中生成详细输出
# -f, --file=FILE            档案文件名。省略时, 基于操作 使用 stdin 或 stdout
# -e, --main-class=CLASSNAME 捆绑到模块化或可执行 jar 档案的独立应用程序 的应用程序入口点
# -C DIR                     更改为指定的目录并包含 以下文件
jar -cvfe out/mods/App.jar org.bougainvilleas.layer.bootlayer.Client -C out/boot_layer .
# 运行
java -p out/mods -m boot_layer 
# jlink
mkdir -p out/jlink
# linux
# -v, --verbose  启用详细跟踪
#  --compress=<0|1|2>  Enable compression of resources:   Level 0: No compression Level 1: Constant string sharing Level 2: ZIP
jlink -v -p out/mods/:$JAVA_HOME/jmods --add-modules boot_layer --launcher app=boot_layer --output out/jlink/image
# 运行
# linux
./out/jlink/image/bin/app
./out/jlink/image/bin/java --list-modules
# windows
.\out\jlink\image\bin\app.bat
.\out\jlink\image\bin\java --list-modules
```

## 构建 ModuleLayer 

> 构建 ModuleLayer 之前 必须在该层中创建一个描述模块图的 Configuration \
> 对于 boot layer 该过程是在启动时隐式完成的 \
> 在构建 Configuration 时，需要提供一个 ModuleFinder 来定位各个模块 \
> 设置一连串类 以创建一个新的 ModuleLayer 
