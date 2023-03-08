# 010204

> 开发系统导引
>
> 静态库实验
> > 编写源文件 [fred.c](source/fred.c) 和 [bill.c](source/bill.c) \
> > 编译 fred.c 和 bill.c `gcc -c bill.c fred.c` 获得 fred.o 和 bill.o \
> > 为库文件创建头文件[lib.h](head/lib.h)，头文件中声明库文件中的函数 \
> > 编写调用库文件程序 [program.c](source/program.c) \
> > 编译 `gcc -c program.c` \
> > 链接方式1 `gcc program.o bill.o` \
> > `ar crv libfoo.a bill.o fred.o`使用`ar`创建归档文件`libfoo.a`并将目标文件`bill.o`和`fred.o`添加进去 \
> > `ranlib libfoo.a`为函数库生成内容表，`Berkeley UNIX`衍生系统必须执行此操作；Linux中使用的是GNU这一步骤可有可无 \
> > 链接方式2 `gcc program.o libfoo.a` 使用库文件`libfoo.a`链接程序 \
> > 链接方式3 `gcc program.o -L. -lfoo` 使用`-l`选项指明访问`libfoo.a`函数库或`libfoo.so`的共享库，因未保存在标准位置，必须使用`-L`选项来告诉编译器在`.`当前目录查找 \
> > `nm a.out`查看`a.out`包含哪些函数，可以看到只包含实际需要的函数，虽然程序中的头文件包含函数库中所有的函数声明，但是并不会将整个函数库包含在最终的程序中 \
> > `nm libfoo.a`查看`libfoo.a`包含那些函数
>
> 共享库
> > 静态库的一个缺点是，当同时运行许多应用程序并且它们都使用来自同一个函数库的函数时，内存中就会有同一函数的多份副本，而且在程序文件自身中也有多份同样的副本。 \
> > 支持共享库的UNIX系统和Linux系统可以克服静态库的缺点。 \
> > 当一个程序使用共享库时，链接方式是：
> > > 程序本身不再包含函数代码，而是引用运行时可访问的共享代码。 \
> > > 当编译好的程序被装载到内存中执行时，函数引用被解析并产生对共享库的调用，如果有必要，共享库才被加载到内存中 \
> > > 系统只保留共享库的一份副本供许多应用程序同时使用，并且在磁盘上也仅保存一份。\
> > > 共享库的更新可以独立于依赖它的应用程序 \
> > > 如文件`/lib/libm.so`是对实际库文件修订版本<sub>/lib/libm.so.N N代表主版本号</sub>的符号链接 \
> > > 当Linux启动应用程序时，会考虑应用程序需要的函数库版本，以防止函数库的新版本致使旧的应用程序不能使用。
> >
> > `ldd a.out` 查看一个程序需要的共享库 \
> > 共享库在许多方面类似Windows中使用的动态链接库。`.so`库对应于`.DLL`文件，它们都是在程序运行时加载，而`.a`库类似于`.LIB`文件，它们都包含在可执行程序中。

|项目|UNIX|Windows|
|:---|:---|:---|
|目标模块|func.o|FUNC.OBJ|
|静态函数库|lib.a|LIB.LIB|
|共享库|*.so|*.DLL|
|程序|a.out|a.EXE|

[return](../README.md)
