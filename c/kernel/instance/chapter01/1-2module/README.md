# 带参数的可加载模块

> macro<sub>宏</sub> `MODULE_PARM(var,type,right)`用于向模块传递命令行参数。 \
> 参数类型可以是整数、长整形、字符串等类型。\
> 编译 `make` \
> 另开`terminal`查看内核日志 `sudo journalctl -k -f` \
> 加载内核 `sudo insmod smodule.ko itype=2 btype=1 ctype=0XAC stype='a'` \
> 查看内核模块 `cat /proc/modules | grep smodule` \
> 卸载内核模块 `sudo rmmod smodule` \
> 查看内核模块 `cat /proc/modules | grep smodule` \
> 清理 `make clean`
