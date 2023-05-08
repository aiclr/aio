# 使用 register_chrdev 注册字符设备

> 注册字符设备可以使用 `register_chrdev` 函数 \
> > `int register_chrdev(unsigned int major,const char *name,struct file_operations * fops);` \
> > `register_chrdev` 函数的`major`参数如果等于`0`,则表示采用系统动态分配的主设备号。
>
> 注销字符设备可以使用 `unregister_chrdev` 函数
> > `int unregister_chrdev(unsigned int maior,const char *name);`
>
> 编译 `make` \
> 另开`terminal`查看内核日志 `sudo journalctl -k -f` \
> 加载内核 `sudo insmod simple_chrdev.ko` \
> `mkmod`创建文件节点 `sudo mknod /dev/fgj c 224 0`
> > `man mkmod` \
> > `mknod [OPTION]... NAME TYPE [MAJOR MINOR]` \
> > NAME `/dev/fgj` \
> > TYPE `c` \
> > MAJOR `224` \
> > MINOR `0` \
> > 查看文件节点 `ll /dev/fgj`
> > > `crw-r--r-- 1 root root 224, 0 May  8 14:55 /dev/fgj`
>
> 查看内核模块 `cat /proc/modules | grep simple_chrdev` \
> 编译应用层可执行程序验证测试 `make test` \
> 应用层可执行程序验证测试 `sudo ./test` \
> 卸载内核模块 `sudo rmmod simple_chrdev` \
> 查看内核模块 `cat /proc/modules | grep simple_chrdev` \
> 清理 `make clean` \
> 删除文件节点 `sudo rm /dev/fgj`
