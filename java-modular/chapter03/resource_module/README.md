## 模块化下的资源封装

## 注意
> CLassLoader::getResource* 也可以加载资源
> > 在模块上下文中，最好使用 Class 和 Module 上的方法 \
> > ClassLoader 不会像 Class 和 Module 方法那样考虑当前模块的上下文，从而导致潜在的混乱结果
> 
> 资源封装只适用于模块中包内的资源,类文件资源（.class文件）例外
> > 即使 .class 在包内 也不会被封装 \
> > 其他所有资源可以被其他 requires 模块自由使用，但是不应该这样做 \
> > 依赖另一个模块的资源并不是完全的模块化，最好只从同一个模块中加载资源 \
> > 如果确实需要使用其他模块的资源，可以考虑通过使用导出类中的方法（该方法提供资源的访问）

## 单模块下资源 访问

```shell
# -encoding UTF-8 windows 中文编码异常
# -verbose 编译过程
javac -encoding UTF-8 -verbose -d out --module-source-path src -m resource_module
# 需要手动添加资源文件到编译后的目录内
cp src/resource_module/top_level_resource.txt out/resource_module/
cp src/resource_module/org/bougainvilleas/resource/second/resource_in_package.txt out/resource_module/org/bougainvilleas/resource/second/
# 运行
java -p out -m resource_module/org.bougainvilleas.resource.second.Client
# 打包
mkdir -p out/mods
# -c, --create               创建档案
# -v, --verbose              在标准输出中生成详细输出
# -f, --file=FILE            档案文件名。省略时, 基于操作 使用 stdin 或 stdout
# -e, --main-class=CLASSNAME 捆绑到模块化或可执行 jar 档案的独立应用程序 的应用程序入口点
# -C DIR                     更改为指定的目录并包含 以下文件
jar -cvfe out/mods/App.jar org.bougainvilleas.resource.second.Client -C out/resource_module .
# 运行
java -p out/mods -m resource_module 
# jlink
mkdir -p out/jlink
# linux
# -v, --verbose  启用详细跟踪
#  --compress=<0|1|2>  Enable compression of resources:   Level 0: No compression Level 1: Constant string sharing Level 2: ZIP
jlink -v -p out/mods/:$JAVA_HOME/jmods --add-modules resource_module --launcher app=resource_module --output out/jlink/image
# 运行
# linux
./out/jlink/image/bin/app
./out/jlink/image/bin/java --list-modules
# windows
.\out\jlink\image\bin\app.bat
.\out\jlink\image\bin\java --list-modules

```


## 跨模块 资源访问

> 注意 resource_client requires resource_module 模块 \
> resource_module 不暴露任何内容（空 module-info.java）
> > 可以随时加载 来自 requires 模块的顶级资源 \
> > META-INF 不是一个有效的包名称（带-号），所以可以访问该目录中的资源 \
> > 在默认情况下 来自其他模块的包中的资源是被封装的，无法获取，getResourceAsStream 会返回null \
> > .class 文件例外 这些文件可以始终从 requires 模块加载 \
> > 通过 Class::forName 可以获取一个 Class<?> 实例，但是通过该实例加载封装资源将返回null

```shell
# -encoding UTF-8 windows 中文编码异常
# -verbose 编译过程
javac -encoding UTF-8 -verbose -d out --module-source-path src -m resource_client,resource_module
# 需要手动添加资源文件到编译后的目录内
cp src/resource_module/top_level_resource.txt out/resource_module/
cp src/resource_module/org/bougainvilleas/resource/second/resource_in_package.txt out/resource_module/org/bougainvilleas/resource/second/
# 包外资源
mkdir -p out/resource_module/META-INF/
touch out/resource_module/META-INF/meta.txt
# 运行
java -p out -m resource_client/org.bougainvilleas.resource.first.Client
# 打包
mkdir -p out/mods
# -c, --create               创建档案
# -v, --verbose              在标准输出中生成详细输出
# -f, --file=FILE            档案文件名。省略时, 基于操作 使用 stdin 或 stdout
# -e, --main-class=CLASSNAME 捆绑到模块化或可执行 jar 档案的独立应用程序 的应用程序入口点
# -C DIR                     更改为指定的目录并包含 以下文件
jar -cvfe out/mods/App.jar org.bougainvilleas.resource.first.Client -C out/resource_client .
jar -cvf out/mods/resource.jar -C out/resource_module .
# 运行
java -p out/mods -m resource_client 
# jlink
mkdir -p out/jlink
# linux
# -v, --verbose  启用详细跟踪
#  --compress=<0|1|2>  Enable compression of resources:   Level 0: No compression Level 1: Constant string sharing Level 2: ZIP
jlink -v -p out/mods/:$JAVA_HOME/jmods --add-modules resource_client --launcher app=resource_client --output out/jlink/image
# 运行
# linux
./out/jlink/image/bin/app
./out/jlink/image/bin/java --list-modules
# windows
.\out\jlink\image\bin\app.bat
.\out\jlink\image\bin\java --list-modules

```