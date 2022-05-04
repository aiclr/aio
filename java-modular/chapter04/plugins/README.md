## plugins 插件体系结构

```shell
# -encoding UTF-8 windows 中文编码异常
# -verbose 编译过程
# linux find 检索全部 .java文件 编译 全部模块
javac -encoding UTF-8 -verbose -d out --module-source-path src $(find src -name '*.java')
javac -encoding UTF-8 -verbose -d out/cli --module-source-path src -m plugins_cli
javac -encoding UTF-8 -verbose -d out/svc --module-source-path src -m plugins_svca,plugins_svcb

# 打包
mkdir -p out/mods/cli
mkdir -p out/mods/svc
# -c, --create               创建档案
# -v, --verbose              在标准输出中生成详细输出
# -f, --file=FILE            档案文件名。省略时, 基于操作 使用 stdin 或 stdout
# -e, --main-class=CLASSNAME 捆绑到模块化或可执行 jar 档案的独立应用程序 的应用程序入口点
# -C DIR                     更改为指定的目录并包含 以下文件
jar -cvfe out/mods/cli/App.jar org.bougainvilleas.plugins.cli.Client -C out/plugins_cli .
jar -cvf out/mods/cli/api.jar -C out/plugins_api .
jar -cvf out/mods/svc/a.jar -C out/plugins_svca .
jar -cvf out/mods/svc/b.jar -C out/plugins_svcb .
# 运行
# 运行 out/mods/svc main(String[] args) 模块路径参数 需要 jar 
java -p out/plugins_cli:out/plugins_api -m plugins_cli/org.bougainvilleas.plugins.cli.Client out/mods/svc
java -p out/mods/cli -m plugins_cli out/mods/svc
# jlink
mkdir -p out/jlink
# linux
# -v, --verbose  启用详细跟踪
#  --compress=<0|1|2>  Enable compression of resources:   Level 0: No compression Level 1: Constant string sharing Level 2: ZIP
jlink -v -p out/mods/cli:$JAVA_HOME/jmods --add-modules plugins_cli --launcher app=plugins_cli --output out/jlink/image
# 运行
# linux
./out/jlink/image/bin/app out/mods/svc
./out/jlink/image/bin/java --list-modules
# windows
.\out\jlink\image\bin\app.bat out/mods/svc
.\out\jlink\image\bin\java --list-modules
```