## 模块化下的资源封装

> 注意
> CLassLoader::getResource* 也可以加载资源
> 在模块上下文中，最好使用 Class 和 Module 上的方法
> ClassLoader 不会像 Class 和 Module 方法那样考虑当前模块的上下文，从而导致潜在的混乱结果 

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