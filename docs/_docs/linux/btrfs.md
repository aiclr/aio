<div style="text-align: center;font-size: 40px;">btrfs</div>

> Btrfs 文件系统特性 `Btrfs (B-tree FS,Butter FS, Better FS)`
> > SUSE12/15 支持生产环境 \
> > GPL开源文件系统 \
> > Oracle 2007年开始研发 \
> > `CoW(copy-on-write)` 写时复制,比就地修改的文件系统有更大的好处 \
> > 主要目标: 取代 ext3/ext4文件系统 \
> > 支持非常大的单个文件大小,实现文件检测 \
> > 支持快照,支持快照的快照(增量备份),可以对单个文件快照 \
> > 内置支持 `raid` ,支持`条带`或`mirror`等常见raid功能 \
> > 透明压缩 \
> > 热移除设备


## `mkfs.btrfs` 格式化并创建 btrfs 

```shell
## eg
mkfs.btrfs -L root /dev/nvme0n1p3
mkfs.btrfs -L disk /dev/sdc

## show
~> sudo btrfs filesystem show
Label: 'root'  uuid: 47c7555a-5a1f-4b19-b251-21ee9c40b398
	Total devices 1 FS bytes used 10.28GiB
	devid    1 size 200.59GiB used 15.02GiB path /dev/nvme0n1p3

Label: 'disk'  uuid: 6ccbdbf6-54b2-49be-ad73-7736146ea43a
	Total devices 1 FS bytes used 193.96GiB
	devid    2 size 931.51GiB used 196.06GiB path /dev/sda


## --help
~ ᐅ mkfs.btrfs --help
Usage: mkfs.btrfs [options] dev [ dev ... ]
Options:
  allocation profiles:
	-d|--data PROFILE           data profile, raid0, raid1, raid1c3, raid1c4, raid5, raid6, raid10, dup or single
	-m|--metadata PROFILE       metadata profile, values like for data profile
	-M|--mixed                  mix metadata and data together
  features:
	--csum TYPE
	--checksum TYPE             checksum algorithm to use, crc32c (default), xxhash, sha256, blake2
	-n|--nodesize SIZE          size of btree nodes
	-s|--sectorsize SIZE        data block size (may not be mountable by current kernel)
	-O|--features LIST          comma separated list of filesystem features (use '-O list-all' to list features)
	-R|--runtime-features LIST  comma separated list of runtime features (use '-R list-all' to list runtime features)
	-L|--label LABEL            set the filesystem label
	-U|--uuid UUID              specify the filesystem UUID (must be unique)
  creation:
	-b|--byte-count SIZE        set filesystem size to SIZE (on the first device)
	-r|--rootdir DIR            copy files from DIR to the image root directory
	--shrink                    (with --rootdir) shrink the filled filesystem to minimal size
	-K|--nodiscard              do not perform whole device TRIM
	-f|--force                  force overwrite of existing filesystem
  general:
	-q|--quiet                  no messages except errors
	-v|--verbose                increase verbosity level, default is 1
	-V|--version                print the mkfs.btrfs version and exit
	--help                      print this help and exit
  deprecated:
	-l|--leafsize SIZE          deprecated, alias for nodesize
```

## 挂载 btrfs

```shell
# 挂载
mount /dev/nvme0n1p3 /mnt
# 卸载 方式1
umount /mnt
# 卸载 方式2
umount /dev/nvme0n1p3

# 挂载
mount /dev/sdc /mnt
# 卸载 方式1
umount /mnt
# 卸载 方式2
umount /dev/sdc

```

## `df -Th`

```text
Filesystem     Type      Size  Used Avail Use% Mounted on
/dev/sda       btrfs     932G  195G  737G  21% /var
/dev/sda       btrfs     932G  195G  737G  21% /home
/dev/sda       btrfs     932G  195G  737G  21% /data
/dev/sda       btrfs     932G  195G  737G  21% /code
```
## `mount`

```text
/dev/sda on /var type btrfs (rw,relatime,space_cache=v2,subvolid=257,subvol=/var)
/dev/sda on /home type btrfs (rw,relatime,space_cache=v2,subvolid=256,subvol=/home)
/dev/sda on /data type btrfs (rw,relatime,space_cache=v2,subvolid=258,subvol=/data)
/dev/sda on /code type btrfs (rw,relatime,space_cache=v2,subvolid=259,subvol=/code)

```



## 往 btrfs 添加 磁盘 移除磁盘

```shell
# 查看帮助
btrf help

# 添加磁盘
## 先挂载要添加到的 btrfs
mount /dev/sdc /mnt
## 添加设备
btrfs device add /dev/sda /mnt
## 强制添加设备 格式化磁盘
btrfs device add -f /dev/sda /mnt
# 查看 btrfs
btrfs filesystem show

# 同一个btrfs 系统 所有磁盘的 UUID 都一样

[root@lotus caddy]# blkid /dev/sda
/dev/sda: LABEL="disk" UUID="6ccbdbf6-54b2-49be-ad73-7736146ea43a" UUID_SUB="f3ed8a08-b49e-48e8-9fe5-7091fca68f2b" BLOCK_SIZE="4096" TYPE="btrfs"

[root@lotus caddy]# blkid /dev/sdc
/dev/sdc: LABEL="disk" UUID="6ccbdbf6-54b2-49be-ad73-7736146ea43a" UUID_SUB="f467bf90-826f-4cdd-902f-6e31d8dd6acb" BLOCK_SIZE="4096" TYPE="btrfs"


# 移除磁盘
# 添加磁盘
## 先挂载要添加到的 btrfs
mount /dev/sdc /mnt
## 移除磁盘 此时会自动进行文件迁移
btrfs device delete /dev/sdc /mnt
# 查看 btrfs
btrfs filesystem show

```

## 多个磁盘数据平衡

```shell
mount /dev/sda /mnt

btrfs filesystem show


# 带提示 倒数10s 开平衡数据
btrfs balance start /mnt
# 直接执行平衡
btrfs balance start --full-balance /mnt


btrfs filesystem show
```

## 查看文件系统

```shell
mount /dev/sda /mnt
[root@lotus caddy]# btrfs filesystem df /mnt | column -t
Data,           single:  total=194.00GiB,  used=193.43GiB
System,         DUP:     total=32.00MiB,   used=48.00KiB
Metadata,       DUP:     total=1.00GiB,    used=570.69MiB
GlobalReserve,  single:  total=238.36MiB,  used=0.00B
```


## `raid` 多磁盘可以使用 `raid`, `raid5` 最少需要`三块磁盘`

```shell
# btrfs filesystem df /mnt | column -t
# -dconvert ====> Data,           single:  total=194.00GiB,  used=193.43GiB
btrfs balance start -dconvert=raidX /mnt

# -mconvert ====> Metadata,       DUP:     total=1.00GiB,    used=570.69MiB
# 同时会修改 System,         DUP:     total=32.00MiB,   used=48.00KiB
btrfs balance start -mconvert=raidX /mnt


```


## 多设备 修改 大小

```shell
# 挂载主卷 
mount /dev/sda /mnt
[root@lotus caddy]# btrfs filesystem show
Label: 'root'  uuid: 47c7555a-5a1f-4b19-b251-21ee9c40b398
	Total devices 1 FS bytes used 10.28GiB
	devid    1 size 200.59GiB used 15.02GiB path /dev/nvme0n1p3

Label: 'disk'  uuid: 6ccbdbf6-54b2-49be-ad73-7736146ea43a
	Total devices 2 FS bytes used 193.97GiB
	devid    2 size 931.51GiB used 196.06GiB path /dev/sda
	devid    3 size 465.76GiB used 0.00B path /dev/sdc
# 调整 `disk` 的编号为3的 /dev/sdc 大小 -3G 
[root@lotus caddy]# btrfs filesystem resize 3:-3G /mnt
Resize device id 3 (/dev/sdc) from 465.76GiB to 462.76GiB

[root@lotus caddy]# btrfs filesystem show
Label: 'root'  uuid: 47c7555a-5a1f-4b19-b251-21ee9c40b398
	Total devices 1 FS bytes used 10.28GiB
	devid    1 size 200.59GiB used 15.02GiB path /dev/nvme0n1p3

Label: 'disk'  uuid: 6ccbdbf6-54b2-49be-ad73-7736146ea43a
	Total devices 2 FS bytes used 193.97GiB
	devid    2 size 931.51GiB used 196.06GiB path /dev/sda
	devid    3 size 462.76GiB used 0.00B path /dev/sdc

# 调整 `disk` 的编号为3的 /dev/sdc 大小 +1G 
[root@lotus caddy]# btrfs filesystem resize 3:+1G /mnt
Resize device id 3 (/dev/sdc) from 462.76GiB to 463.76GiB
[root@lotus caddy]# btrfs filesystem show
Label: 'root'  uuid: 47c7555a-5a1f-4b19-b251-21ee9c40b398
	Total devices 1 FS bytes used 10.28GiB
	devid    1 size 200.59GiB used 15.02GiB path /dev/nvme0n1p3

Label: 'disk'  uuid: 6ccbdbf6-54b2-49be-ad73-7736146ea43a
	Total devices 2 FS bytes used 193.99GiB
	devid    2 size 931.51GiB used 196.06GiB path /dev/sda
	devid    3 size 463.76GiB used 0.00B path /dev/sdc

# 调整 `disk` 的编号为3的 /dev/sdc 大小为最大 max
[root@lotus caddy]# btrfs filesystem resize 3:max /mnt
Resize device id 3 (/dev/sdc) from 463.76GiB to max
[root@lotus caddy]# btrfs filesystem show
Label: 'root'  uuid: 47c7555a-5a1f-4b19-b251-21ee9c40b398
	Total devices 1 FS bytes used 10.28GiB
	devid    1 size 200.59GiB used 15.02GiB path /dev/nvme0n1p3

Label: 'disk'  uuid: 6ccbdbf6-54b2-49be-ad73-7736146ea43a
	Total devices 2 FS bytes used 193.99GiB
	devid    2 size 931.51GiB used 196.06GiB path /dev/sda
	devid    3 size 465.76GiB used 0.00B path /dev/sdc

```


## 子卷 `subvolume`

> 挂载主卷 可以访问子卷内容
> 
> 注意
> > 不要使用 subvolid=ID 挂载子卷 ID 会变 \
> > 使用 subvol=home 挂载子卷


```shell
# 挂载主卷
mount /dev/sda /mnt
btrfs filesystem show
# 创建子卷
btrfs subvolume create /mnt/home
btrfs subvolume create /mnt/var
btrfs subvolume create /mnt/data
btrfs subvolume create /mnt/code
# 查看子卷
[root@lotus caddy]# btrfs subvolume list /mnt
ID 256 gen 43598 top level 5 path home
ID 257 gen 43597 top level 5 path var
ID 258 gen 43452 top level 5 path data
ID 259 gen 43597 top level 5 path code
ID 260 gen 43437 top level 257 path var/lib/portables
ID 261 gen 43437 top level 257 path var/lib/machines

[root@lotus caddy]# btrfs subvolume show /mnt/code
code
	Name: 			code
	UUID: 			b51969d9-8160-f04a-8ec8-7902c697a0a0
	Parent UUID: 		-
	Received UUID: 		-
	Creation time: 		2022-04-04 13:24:11 +0800
	Subvolume ID: 		259
	Generation: 		43599
	Gen at creation: 	11
	Parent ID: 		5
	Top level ID: 		5
	Flags: 			-
	Send transid: 		0
	Send time: 		2022-04-04 13:24:11 +0800
	Receive transid: 	0
	Receive time: 		-
	Snapshot(s):



# 卸载 主卷
umount /mnt
# 创建子卷挂载目录
mkdir -p /mnt/home /mnt/var /mnt/data /mnt/code
# 挂载子卷
mount -o subvol=home /dev/sda /mnt/home
mount -o subvol=var /dev/sda /mnt/var
mount -o subvol=data /dev/sda /mnt/data
mount -o subvol=code /dev/sda /mnt/code
# 查看
lsblk

```


## 子卷快照

```shell
# 挂载子卷
mount -o subvol=code /dev/sda /mnt/code
# 创建快照
btrfs subvolume snapshot /mnt/code /mnt/code_snapshot
# mnt 下会多出一个 code_snapshot 快照文件夹 里面内容与 code 一模一样
# 当需要恢复时可以将 code_snapshot 文件 cp 覆盖掉 code 内文件

# 删除快照
btrfs subvolume delete /mnt/code_snapshot

```

## 删除子卷

```shell
# 挂载主卷
mount /dev/sda /mnt
# 卸载子卷
umount /mnt/data
# 删除子卷
btrfs subvolume delete /mnt/data

```

## 文件系统转换

```shell
# 格式化为ext4
mkfs.ext4 /dev/sdc
# ext4 转 btrfs
btrfs-convert /dev/sdc
# 查看
btrfs filesystem show
# 查看
blkid /dev/sdc

# rollback 撤销 btrfs 转换
btrfs-convert -r /dev/sdc
# 查看
blkid /dev/sdc

```


## 磁盘碎片整理

```shell
btrfs filesystem defragment -r /
```