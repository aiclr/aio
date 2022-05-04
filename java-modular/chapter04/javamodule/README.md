# 对模块的反射

> 反射允许在运行时对所有java元素进行具体化 \
> 类、包、方法等都有反射表示 \
> 开放式模块（open）运行时允许对模块内元素进行深度反射 \
> 模块也可以进行反射，java.lang.Module 提供模块的运行时试图
> > default、查询给定模块的属性、修改、动态更改模块的特性、访问、从模块内部读取资源
> 
> default、查询给定模块的属性 [code](src/java_module_first)
> > java.lang.Module 是对模块反射的入口点 `Module module=String.class.getModule()` 通过模块中的一个类获取Module实例 \
> > ModuleDescriptor 提供有关 module-info 内容的运行时视图 \
> > ModuleDescriptor 实例是只读不可改变的，不能在运行时更改模块的名称
> 
> 修改、动态更改模块的特性
> > 可以在运行时决策需要 export 的包 \
> > **只能导出模块自己的 package 不能导出其他模块的 package: 无法通过模块反射API从外部升级模块的权限** \
> > 可以通过 Module API 向特定的模块添加限制导出 exports myselfPackage to targetModule 
> >
> > 调用者敏感 @CallerSensitive 注释的方法 如：addExports 
> > > 从不同地方调用时，行为方式不同的方法称为调用者敏感方法 caller sensitive 方法 \
> > > 调用者敏感方法可以根据当前的调用堆栈找出哪个类和模块正在调用他们
> > 
> > Module 提供 四个允许运行时修改的方法
> > > addExports(String packageName,Module target) 将先前未到处的包公开给另一个模块 \
> > > addOpens(String packageName,Module target) 开放一个包 以便另一个模块进行深度反射 \
> > > addReads(Module other) 添加当前模块到另一个模块的读取关系 \
> > > addUses(Class<?> serviceType) 表明当前模块想要通过ServiceLoader使用额外的服务类型 \
> > > 没有 addProvides 方法 因为编译时应该知道具体实现，运行时公开实现情况较少
> >
> > 在使用反射之前，通常会试图通过正常的方式在模块之间公开足够的信息 \
> > 在运行时使用反射会改变模块的行为,从而违背java模块系统的原理 \
> > 如果出现了在编译时或启动时没有考虑到的隐式依赖关系，就会使模块系统在早期阶段所提供的许多保证变得无效
> 
> 模块 注释
> > 可以将一些作为Java平台一部分的默认注释应用于模块 \ 
> > 如 @Deprecated 表明过时，应该使用替代模块、@SuppressWarnings \
> > Java9 开始 对不建议使用的元素进行标记，以便在未来的版本中将其删除 @Deprecated(forRemoval=true) \
> > 当需要使用一个不建议使用的模块时，编辑器会生成一条警告信息 \
> > **自定义模块注释，只有在注释的保留策略设置为 RUNTIME 时才有效** \
> > 除了平台定义的注释，框架、构建工具也会使用模块注释

## java_module_first

```shell
# -encoding UTF-8 windows 中文编码异常
# -verbose 编译过程
javac -encoding UTF-8 -verbose -d out --module-source-path src -m java_module_first
# linux find 检索全部 .java文件 编译 全部模块
javac -encoding UTF-8 -verbose -d out --module-source-path src $(find src -name '*.java')

# 运行
java -p out -m java_module_first/org.bougainvilleas.module.first.Client
# 打包
mkdir -p out/mods
# -c, --create               创建档案
# -v, --verbose              在标准输出中生成详细输出
# -f, --file=FILE            档案文件名。省略时, 基于操作 使用 stdin 或 stdout
# -e, --main-class=CLASSNAME 捆绑到模块化或可执行 jar 档案的独立应用程序 的应用程序入口点
# -C DIR                     更改为指定的目录并包含 以下文件
jar -cvfe out/mods/App.jar org.bougainvilleas.module.first.Client -C out/java_module_first .
# 运行
java -p out/mods -m java_module_first 
# jlink
mkdir -p out/jlink
# linux
# -v, --verbose  启用详细跟踪
#  --compress=<0|1|2>  Enable compression of resources:   Level 0: No compression Level 1: Constant string sharing Level 2: ZIP
jlink -v -p out/mods/:$JAVA_HOME/jmods --add-modules java_module_first --launcher app=java_module_first --output out/jlink/image
# 运行
# linux
./out/jlink/image/bin/app
./out/jlink/image/bin/java --list-modules
# windows
.\out\jlink\image\bin\app.bat
.\out\jlink\image\bin\java --list-modules
```

## java_module_anno

```shell
# -encoding UTF-8 windows 中文编码异常
# -verbose 编译过程
javac -encoding UTF-8 -verbose -d out --module-source-path src -m java_module_anno
# linux find 检索全部 .java文件 编译 全部模块
javac -encoding UTF-8 -verbose -d out --module-source-path src $(find src -name '*.java')

# 运行
java -p out -m java_module_anno/org.bougainvilleas.module.anno.Client
# 打包
mkdir -p out/mods
# -c, --create               创建档案
# -v, --verbose              在标准输出中生成详细输出
# -f, --file=FILE            档案文件名。省略时, 基于操作 使用 stdin 或 stdout
# -e, --main-class=CLASSNAME 捆绑到模块化或可执行 jar 档案的独立应用程序 的应用程序入口点
# -C DIR                     更改为指定的目录并包含 以下文件
jar -cvfe out/mods/App.jar org.bougainvilleas.module.anno.Client -C out/java_module_anno .
# 运行
java -p out/mods -m java_module_anno 
# jlink
mkdir -p out/jlink
# linux
# -v, --verbose  启用详细跟踪
#  --compress=<0|1|2>  Enable compression of resources:   Level 0: No compression Level 1: Constant string sharing Level 2: ZIP
jlink -v -p out/mods/:$JAVA_HOME/jmods --add-modules java_module_anno --launcher app=java_module_anno --output out/jlink/image
# 运行
# linux
./out/jlink/image/bin/app
./out/jlink/image/bin/java --list-modules
# windows
.\out\jlink\image\bin\app.bat
.\out\jlink\image\bin\java --list-modules
```