---
title: Linux基础
targets:
- name: Shell:即工作环境
  link: linux/linux#shell即工作环境
---

## Shell:即工作环境

**user**通过**shell**操作硬件,**windows**操作系统只是把**shell**封装成**可视化界面**进行与硬件交互

用户登陆

**~** 表示当前用户目录 **/home/用户名**

**/** 表示 **/root**<sub>根目录</sub>

**pwd** 查看当前所在目录

**#** 为**root**提示符号

**$** 为普通用户

<br/>
<hr/>
<br/>

命令

**command 【option】<sub>命令选项</sub> 【arguments】<sub>命令参数</sub>** 例如 **ls -l /** 查看 **/** 目录下的目录详细信息

**date** 时间

**cal** 日历

**bc** 计算器

**man cal** 和 **info cal** 查看 **命令参数** 命令的帮助文档

<br/>
<hr/>
<br/>

关机命令

**shutdown** 

**-h** 关机 **-r** 重启 **-f** 快速重启

**hh:mm** 执行时间 21:23 21点23分执行

**+m** m分钟后执行

**now** 即+0 立即执行

还可以加广播提示其他用户

```
shutdown -h +10 'the system will shutdown'
```

**centos** **archlinux** 可以执行 **poweroff** 关机

<br/>
<hr/>
<br/>

> 显示文件系统内容
> > Linux提供多用户多任务环境操作实现，文件可存取访问的身份owner，group，other，权限read，write，execute（可执行）
> > > read = r = 4\
> > > write = w = 2\
> > > execute = x =1
> >
> > ls -l 显示当前目录更多文件信息\
> > ls -a 显示当前目录隐藏文件\
> > ls -al 显示当前目录全部文件+文件信息
> > > `drwxr-xr-x. 2 root root 6 Jul 23 10:12 study`

|    文件权限    | 链接数 | 文件所有者 | 文件所属用户组 | 文件大小 |   文件最后修改时间   |  文件名  |
|:----------:|:---:|:-----:|:-------:|:----:|:------------:|:-----:|
| drwxr-xr-x |  2  | root  |  root   |  6   | Jul 23 10:12 | study |


> > > 文件权限

|  d   |   rwx   |    r-x    |    r-x     |
|:----:|:-------:|:---------:|:----------:|
| 文件类型 | 文件所有者权限 | 文件所属用户组权限 | 其他人对此文件的权限 |

> > > 文件类型
> > > > d:目录\
> > > > -:文件
> > > > 1. 纯文本文件（ASCII）
> > > > 2. 二进制文件（binary）
> > > > 3. 数据格式文件（date）
> > > >
> > > > i:连接文件，类似windows下的快捷方式\
> > > > b:设备与设备文件，可存储接口设备与系统外设及存储相关/块设备，在/dev下\
> > > > c:串行端口设备，字符设备文件\
> > > > s:套接字，数据接口文件，网络上的数据连接 /var/run\
> > > > p:管道（FIFO，PIPE） 解决多个程序访问一个文件时造成的错误问题
> > >
> > > 文件扩展名:只是为了让用户了解文件的用途
> > > > linux文件是没有所谓的扩展名的，一个文件是否执行与文件权限的 **【drwxr-xr-x】** 十个属性相关，有x属性则表示可以执行，但是可以执行与执行成功并不一致
> > > >
> > > > 常用扩展名
> > > > >  `*.sh` 脚本或批处理文件 script 脚本是用shell写成的\
> > > > >  `*.Z,*.tar,*.tar.gz,*.zip,*.tg`经打包的压缩文件
> > >
> > > 改变文件属性和权限
> > > > 改变文件所属用户组 `chgrp groupName file`\
> > > > 改变文件所有者 `chown userName file`\
> > > > 改变文件的权限 `chmod 777 file`
> > > >
> > > > 权限计算方法:777是rwxrwxrwx的累加，如下
> > > > - owner = rwx = 4+2+1 = 7
> > > > - group = rwx = 4+2+1 = 7
> > > > - other = rwx = 4+2+1 = 7
> > >
> > > 文件种类
> >
> > **ls -a -l /** 显示/目录下的文件\
> > **ls -F** 列出目录下文件名及其目录类型（后跟*号表示可执行文件，@表示符号连接/表示目录名）\
> > **ls -t** 按照最后修改文件时间列出文件名\
> > **ls -R** 列出当前目录及其子目录的文件名

<br/>
<hr/>
<br/>

操作目录和文件

**mkdir myfile** 在当前目录下创建目录 myfile

**rmdir myfile** 删除当前目录下的myfile目录（myfile目录下必须为空才能删除成功）

**cd ..** 返回上级目录 . 表示当前目录 .. 表示上级目录

**cp XX.log ./one** 复制当前目录下的XX.log文件到 ./ 目录下的one目录内 即当前目录下的one目录内

**cp -r one two** 当前目录下将目录one复制到two内 -r表示可以复制目录及目录内的所有文件（递归）

**cp xx.log xx.log.bak** 当前目录内复制xx.log并将新复制的文件改名为xx.log.bak

**mv xx.log ./one** 将当前目录下的xx.log文件移到当前目录下的one目录内

**mv xx.log xx.syslog** 将当前目录下的xx.log移到当前目录下，并改名为xx.syslog（改名字）

**touch 1.txt** 在当前目录下创建1.txt文件

**file 1.txt** 查看文件类型

**rm -f 1.txt** 删除当前目录下的1.txt文件，强制删除不是递归

**rm -r** 循环删除目录，直到该目录下内容全部被删除，即递归

`rm -f 1*.txt` 删除当前目录下所有1开头的 **.txt** 文件 *号匹配任意字符和字符串

**rm -f 1?.txt** 删除当前目录下1开头的 **.txt** 文件 **?** 号匹配一个字符

**rm -f 11[1-3].txt** 删除当前目录下以11开头1，2，3结尾的 **.txt** 文件[abc]表示a,b,c任一字符,[a-x]a到x的任一字符,[1-9]1到9任一数字

**rm -f 1[!1]*.txt** 删除当前目录下第二个字符不是1的.txt文件 与上一条含义相反

**locate xx.log** 搜索xx.log文件；此操作需要数据库支持，默认7天更新一次，也可以手动更新执行updatedb需耗时

**pwd** print working directory<sub>打印当前所在目录</sub>

<br/>
<hr/>
<br/>

用户：只有 **root** 可以设置其他用户密码，其他用户只能修改自己的密码

**useradd caddy** 增加 **caddy** 用户<sub>root权限</sub>

**passwd caddy** 修改 **caddy** 密码<sub>root权限</sub>

**passwd** 修改自己密码，不用指定用户名<sub>caddy权限</sub>

<br/>
<hr/>
<br/>

显示文件内容

**cat xx.log** 适合显示内容少的，如果太多行，只会显示后面的部分（看不全）

**more xx.log** 解决 **cat** 显示不全的问题 按 **space** 翻页  **q** 键退出（不能倒退查看已经看过的内容）

**less xx.log** 解决 **more** 不能上下翻页的问题 **PageUp PageDown** 翻页 **q** 退出

**head xx.log** 显示文件前几行（默认10行）

**tail xx.log** 显示文件最后几行（默认10行）

**head/tail -10 xx.log** 显示前/后10行

**tac xx.log** 从最后一行开始显示文件内容

**nl xx.log** 显示并输出行号

**od xx.log** 以二进制读取文件