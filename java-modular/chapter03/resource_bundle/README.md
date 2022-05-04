# ResourceBundle 类 公开包中资源文件

> JDK 自身通过服务公开资源的一个示例是 ResourceBundle \
> ResourceBundle 提供了一种机制将本地化作 JDK 的一部分 \
> 本质上是特定于语言环境的键值对列表 \
> 可以实现 ResourceBundle 接口或适用一个属性文件 \
> 使用属性文件更方便，因为默认情况下支持按照预定义的格式加载属性文件 \
> ResourceBundle::getBundle 根据给定的基础名称和语言环境选择正确的资源包\
> ResourceBundle JavaDoc 包含大量有关如何使用回退机制加载特定文件的信息。除了属性文件，还支持其他基于类的格式 \
> 使用了模块，就不存在类路径扫描 \
> 模块中的资源是被封装的，只考虑调用getBundle的模块中的文件 \
> 将不同语言环境的转换结果放入单独的模块是可取的。


```shell
# -encoding UTF-8 windows 中文编码异常
# -verbose 编译过程
javac -encoding UTF-8 -verbose -d out --module-source-path src -m resource_bundle_client
# linux find 检索全部 .java文件 编译
javac -encoding UTF-8 -verbose -d out --module-source-path src $(find src -name '*.java')

# 需要手动添加资源文件到编译后的目录内 此处可以指定一个包内路径 resources
cd src
rsync -Rq $(find . -name '*.properties') ../out
cd ..
# or cp
mkdir -p out/resource_bundle/org/bougainvilleas/resource/bundle/first/resources
cp src/resource_bundle/org/bougainvilleas/resource/bundle/first/sources/*.properties out/resource_bundle/org/bougainvilleas/resource/bundle/first/resources

# 运行
java -p out -m resource_bundle_client/org.bougainvilleas.resource.bundle.second.Client
# 打包
mkdir -p out/mods
# -c, --create               创建档案
# -v, --verbose              在标准输出中生成详细输出
# -f, --file=FILE            档案文件名。省略时, 基于操作 使用 stdin 或 stdout
# -e, --main-class=CLASSNAME 捆绑到模块化或可执行 jar 档案的独立应用程序 的应用程序入口点
# -C DIR                     更改为指定的目录并包含 以下文件
jar -cvfe out/mods/App.jar org.bougainvilleas.resource.bundle.second.Client -C out/resource_bundle_client .
jar -cvf out/mods/resource.jar -C out/resource_bundle .
# 运行
java -p out/mods -m resource_bundle_client 
# jlink
mkdir -p out/jlink
# linux
# -v, --verbose  启用详细跟踪
#  --compress=<0|1|2>  Enable compression of resources:   Level 0: No compression Level 1: Constant string sharing Level 2: ZIP
jlink -v -p out/mods/:$JAVA_HOME/jmods --add-modules resource_bundle_client --launcher app=resource_bundle_client --output out/jlink/image
# 运行
# linux
./out/jlink/image/bin/app
./out/jlink/image/bin/java --list-modules
# windows
.\out\jlink\image\bin\app.bat
.\out\jlink\image\bin\java --list-modules
```