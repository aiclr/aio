# 模块依赖

> Linux 内核模块之间可以相互引用一些符号，这些符号包括函数与变量。 \
> 符号必须导出才能被引用。 \
> 内核使用宏定义 EXPORT_SYMBOL 导出变量与函数。
> > #include <linux/module.h>
>
> 一个模块引用其他模块的符号，称为模块依赖关系。 \
> 被引用的模块必须先安装，引用模块才能安装。

## 验证

> 编译 `make` \
> 另开`terminal`查看内核日志 `sudo journalctl -k -f` \
> 加载内核模块
> > 直接加载`smodule.ko`会报错
> > > `sudo insmod smodule.ko` \
> > > `insmod: ERROR: could not insert module smodule.ko: Unknown symbol in module`
> >
> > 需要先加载`smodule_dep.ko`被依赖模块 `sudo insmod smodule_dep.ko` \
> > 再加载`smodule.ko`模块`sudo insmod smodule.ko`
>
> 查看内核模块 `cat /proc/modules | grep smodule` \
> 卸载内核模块
> > 直接卸载内核模块`smodule_dep.ko`会报错
> > > `sudo rmmod smodule_dep` \
> > > `rmmod: ERROR: Module smodule_dep is in use by: smodule`
> >
> > 需要先卸载`smodule`模块 \
> > 再卸载`smodule_dep`模块
>
> 查看内核模块 `cat /proc/modules | grep smodule` \
> 清理 `make clean`
