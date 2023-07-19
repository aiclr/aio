<div style="text-align: center;font-size: 40px;">monitor 监控工具</div>

## 进程监控

### top & htop

```shell
[caddy@blackbox ~]$ top

pacman -S htop
[caddy@blackbox ~]$ htop
交互式的实时linux进程监控工具
```

## 监控磁盘I/O

### iotop

```shell
pacman -S iotop
[caddy@blackbox ~]$ iotop
监控并显示实时磁盘I/O、进程的统计功能。
在查找具体进程和大量使用磁盘读写进程的时候，这个工具就非常有用
只有在kernelv2.6.20及以后的版本中才有。python版本需要 python2.7及以上版本
```

## 输入/输出统计

### iostat

```shell
pacman -S sysstat
[caddy@blackbox ~]$ man iostat
收集显示系统存储设备输入和输出状态统计的简单工具
踪存储设备的性能问题，其中存储设备包括设备、本地磁盘，以及诸如使用NFS等的远端磁盘
[caddy@blackbox ~]$ iostat 
Linux 5.11.10-arch1-1 (blackbox) 	03/28/2021 	_x86_64_	(12 CPU)

avg-cpu:  %user   %nice %system %iowait  %steal   %idle
           0.48    0.00    0.25    0.03    0.00   99.25

Device             tps    kB_read/s    kB_wrtn/s    kB_dscd/s    kB_read    kB_wrtn    kB_dscd
nvme0n1          10.74       320.47        39.38         0.00     965612     118658          0
```

- avg-cpu
    - %user: 在用户级别运行所使用的CPU的百分比.
    - %nice:优先进程消耗的CPU时间，占所有CPU的百分比.
    - %system: 在系统级别(kernel)运行所使用CPU的百分比.
    - %iowait: CPU等待硬件I/O时,所占用CPU百分比.
    - %steal: 管理程序维护另一个虚拟处理器时，虚拟CPU的无意识等待时间百分比.
    - %idle: CPU空闲时间的百分比.
- Device
    - tps: 每秒钟发送到的I/O请求数.
    - KB_read /s: 每秒读取的block数.
    - KB_wrtn/s: 每秒写入的block数.
    - KB_read: 启动到现在 读入的block总数.
    - KB_wrtn: 启动到现在写入的block总数.

## swap虚拟内存统计

### vmstat 采样时间间隔数 采样次数

```shell
vmstat 2 1
vmstat 2 2
vmstat 2
[caddy@blackbox ~]$ man vmstat
[caddy@blackbox ~]$ vmstat 2 2
procs -----------memory---------- ---swap-- -----io---- -system-- ------cpu-----
 r  b   swpd   free   buff  cache   si   so    bi    bo   in   cs us sy id wa st
 0  0      0 30582452  75880 1105616    0    0    36     4  143  219  1  0 99  0  0
 0  0      0 30582576  75880 1105540    0    0     0     0  333  364  0  0 100  0  0
```

- r ：表示运行队列，如果运行队列过大，表示你的CPU很繁忙，一般会造成CPU使用率很高
- b ：表示阻塞的进程数swpd ：虚拟内存已使用的大小，如果大于0，表示你的机器物理内存不足了，如果不是程序内存泄露的原因，那么你该升级内存了或者把耗内存的任务迁移到其他机器
- free ：空闲的物理内存的大小
- buff ： 系统占用的缓存大小
- cache ：直接用来记忆我们打开的文件,给文件做缓冲
- si ：每秒从磁盘读入虚拟内存的大小，如果这个值大于0，表示物理内存不够用或者内存泄露了
- us ：用户CPU时间
- sy ：系统CPU时间
- so ： 每秒虚拟内存写入磁盘的大小，如果这个值大于0，同上。
- sy ： 系统CPU时间，如果太高，表示系统调用时间长，例如是IO操作频繁。
- id ： 空闲 CPU时间，一般来说，id + us + sy = 100
- wt ： 等待IO CPU时间。

## 列出打开的文件

> lsof
> > 显示所有打开的文件和进程。打开的文件包括磁盘文件、网络套接字、管道、设备和进程 \
> > 主要情形之一就是在无法挂载磁盘和显示正在使用或者打开某个文件的错误信息的时候。使用这条命令，你可以很容易地看到正在使用哪个文件

## 网络状态统计

> **Arch Linux has deprecated net-tools in favor of iproute2**

| Deprecated command | Replacement commands |
| :----------------- | :------------------- |
| arp                | ip neighbor          |
| ifconfig           | ip address, ip link  |
| netstat            | ss                   |
| route              | ip route             |

### netstat replace with ss 

> 监控网络性能，定位并解决网络相关问题\
> [arch wiki](https://wiki.archlinux.org/title/Network_configuration#net-tools)\
> [ss man page](https://man.archlinux.org/man/ss.8)

```shell
# Display man page
man ss
# Display all TCP Sockets with service names
ss -at
# Display all TCP Sockets with port numbers
ss -atn
# Display all UDP Sockets
ss -au
```


## 实时局域网IP监控

### iptraf

```shell
pacman -S iptraf-ng
[caddy@blackbox ~]$ sudo iptraf-ng
实时网络（局域网）监控应用
网络的IP流量监控，包括TCP标记、ICMP详细信息、TCP/UDP流量分离、TCP连接包和字节数。
同时还采集有关接口状态的常见信息和详细信息：
TCP、UDP、IP、ICMP、非IP，IP校验和错误，接口活动等
```