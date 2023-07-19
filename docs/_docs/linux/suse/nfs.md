<div style="text-align: center;font-size: 40px;">Network file system</div>

> [opensuse Kubic](https://en.opensuse.org/SDB:Network_file_system) \
> [archlinux wiki](https://wiki.archlinux.org/title/NFS)
> 
> 挂载网络上的文件系统
> 
> Install NFS kernel server `zypper in nfs-kernel-server` \
> `systemctl status nfs-server.service` \
> `systemctl start nfs-server.service` \
> `systemctl enable nfs-server.service`


## server configure /etc/exports

```
# This shares the directory read-only to ALL hosts
/root/export/this/directory   (ro)
# This shares the directory read-and-write to all hosts in the 192.168.1.0 network, which subnet mask 255.255.255.0
/root/export/this/directory   192.168.1.0/24(rw,async)
# 当客户端使用root用户时无法需要在服务端添加此配置 no_root_squash;默认 root_squash
/root/export/this/directory 192.168.10.0/24(rw,no_root_squash,async)
```

## client configure /etc/fstab

```
192.168.1.2:/root/export/this/directory  /local/mountpoint   nfs   auto    0   0
```