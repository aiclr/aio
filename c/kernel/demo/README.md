# 内核驱动程序

> archlinux 需要安装`base-devel`和`linux-headers` [arch wiki](https://wiki.archlinux.org/title/Compile_kernel_module)
> > `sudo pacman -S base-devel linux-headers`
>
> 编译 `make` or `make all`\
> 清理 `make clean` \
> 装载内核 `sudo insmod hello_world.ko` \
> 装载内核附带参数 `sudo insmod hello_world.ko name="Caddy"` \
> 卸载内核 `sudo rmmod hello_world.ko` \
> 查看内核日志验证 `sudo journalctl -b -r`

## Makefile

```
obj-m+=hello_world.o
all:
	make -C /lib/modules/$(shell uname -r)/build/ M=$(PWD) modules
clean:
	make -C /lib/modules/$(shell uname -r)/build/ M=$(shell pwd) clean
```

> `obj-m`表示编译生成可加载模块。\
> `obj-m+=hello_world.o`，这条语句就是显式地将`hello_world.o`编译成`hello_world.ko`,而`hello_world.o`则由`make`的自动推导功能编译`hello_world.c`文件生成。 \
> `all`、`clean`是`makefile`中的伪目标，伪目标并不是一个真正的编译目标，代表一系列要执行的命令集合，通常一个makefile会对应多个操作 \
> `make [OPTION]... [TARGET]...`
> >`-C` ：此OPTION指定linux内核源码的位置，make在编译时将会进入内核源码目录，执行编译，编译完成时返回 \
> > `/lib/modules/$(shell uname -r)/build/`，linux内核源码的目录 \
> > `M=$(PWD)`：需要编译的模块源代码目录 \
> > `[TARGET]`：`modules` 可选选项。默认行为是将源文件编译并生成内核模块，即module(s)
> > 支持的选项：
> > > `modules_install`：安装这个外部模块，默认安装地址是`/lib/modules/$(uname -r)/extra/`，同时可以由内建变量`INSTALL_MOD_PATH`指定安装目录 \
> > > `clean`：清理源文件目录下编译过程生成的文件。 \
> > > `help`：帮助信息
