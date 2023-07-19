<div style="text-align: center;font-size: 40px;">archlinux 安装记录</div>

# archlinux 安装记录

## 安装前相关知识

### BIOS引导启动系统

#### MBR分区表+BIOS启动

> MBR把硬盘分为很多区块，同时这些区块都配上对应逻辑块地址， \
> MBR分区下每一个分区表的大小是固定的，MBR的第一个扇区内存有启动代码 \
> 启动代码是BIOS启动系统的关键，它负责引导启动操作系统 \
> 主引导代码-->第一个分区信息-->第二个分区信息-->第三个分区信息-->第四个分区信息-->硬盘有效标志

#### BIOS
> BIOS : Basic Input Output System基本输入输出系统。 \
> 简单来说它用于加载电脑最基本的程序代码，而这些代码是存储在CMOS里面的，他同时初始化硬件，检测硬件功能及引导操作系统的任务。

### UEFI引导启动系统

#### GPT分区表

> GPT全称GUID磁盘分区表，表示这块分区表时全局唯一标识磁盘分区表。 \
> 分区表大小不固定，硬盘容量不再限制于2TB。 \
> 在GPT硬盘第一个数据块中也有MBR的引导表示也叫做PMBR \
> UEFI+GPT的系统不需要主引导记录这些东西，开机会快很多。

#### UEFI启动

> UEFI：Unified Extensible Firmware Interface \
统一的可扩展固件接口，可以直接预启动开始加载操作系统 \
可以把很多开机程序都省去（BIOS的加电自检，硬件初始化） \
UEFI图形化界面更加直观，操作者进入BIOS后可以更直观查看各项数据（暂时理解为台式机的EFI BIOS系统界面） \
UEFI启动不需要活动分区，不需要主引导记录，所以UEFI比大多数BIOS快， \
区别主要在启动过程不同，UEFI省略很多的自检步骤，硬盘分区下他们也不同

---

## 文件系统 [File System](https://wiki.archlinux.org/title/File_systems)

| 名称                                                   | example                         | need package          | 描述                                                                              |
| :----------------------------------------------------- | :------------------------------ | :-------------------- | :-------------------------------------------------------------------------------- |
| [fat](https://wiki.archlinux.org/title/FAT)            | mkfs.fat -F 32 -n fat /dev/sdd1 | pacman -S dosfstools  | mkfs.vfat and mkfs.msdos are both symlinks to mkfs.fat, they are the same utility |
| [exfat](https://wiki.archlinux.org/title/File_systems) | mkfs.exfat -L exfat /dev/sdc2   | pacman -S exfatprogs  | windows linux mac 都可访问 U盘使用此格式 (无arch wiki)                            |
| [btrfs](https://wiki.archlinux.org/title/Btrfs)        | mkfs.btrfs  -L btrfs /dev/sdc3  | pacman -S btrfs-progs | copy on write                                                                     |
| [ext4](https://wiki.archlinux.org/title/Ext4)          | mkfs.ext4   /dev/sdc4           | pacman -S e2fsprogs   |                                                                                   |


## dd命令制作启动盘

- 查看U盘 `fdisk -l`
- 如果u盘挂载，卸载掉，否则会提示设备资源正忙 `umount /dev/sdd*`
- 格式化u盘vfat格式文件系统 centos7 提示找不到目录
  - `mkfs -t vfat -F 32 /dev/sdd1` 
  - `mkfs.fat -F 32 /dev/sdd1`
- 写入iso镜像到U盘 `dd if=/root/archlinux.iso of=/dev/sdd1`
- 查看进度和读写速度 新终端执行 `watch -n 5 pkill -USR1 ^dd$`

## 安装系统前检查机器

- 安装过程需要联网,并且配国内镜像源
    - 联网工具 `ip link` & `wifi-menu`
    - 更新时间 `timedatectl set-ntp true`
- 检查启动模式 `ls /sys/firmware/efi/efivars`
- 看分区 `fdisk -l` or `fdisk -l /dev/sdb` or `lsblk`

### 磁盘分区

#### bios模式

```shell
fdisk /dev/sdb
一个系统安装分区---sdb1
mkfs.ext4 /dev/sdb1
mount /dev/sdb1 /mnt
```

#### UEFI模式 需要 GPT 分区表

- 固态盘 `fdisk /dev/nvme0n1`
    - `/dev/nvme0n1p1` : 主引导分区 最少300M 
        - `mkfs.fat -F 32 /dev/nvme0n1p1`
    - `/dev/nvme0n1p2` : swap 交换分区 启用休眠 大小 >= 内存大小 
        - `mkswap /dev/nvme0n1p2`
    - `/dev/nvme0n1p3` : root 分区
        - `mkfs.btrfs -L root /dev/nvme0n1p3`
- 机械盘（btrfs subvolumes） `mkfs.btrfs -L disk /dev/sdc`
    - home 用户
    - var 系统日志
    - code 保存代码
    - data 保存数据

```shell
# 固态盘分区 格式化
fdisk /dev/nvme0n1
mkfs.fat -F 32 /dev/nvme0n1p1
mkswap /dev/nvme0n1p2
mkfs.btrfs -L root /dev/nvme0n1p3
# 机械盘 使用 btrfs subvolumes
mkfs.btrfs -L disk /dev/sdc
mount /dev/sdc /mnt
btrfs subvolume create /mnt/home
btrfs subvolume create /mnt/var
btrfs subvolume create /mnt/data
btrfs subvolume create /mnt/code
btrfs filesystem show
btrfs subvolume list /mnt
umount /mnt
# 挂载 准备装机
mount /dev/nvme0n1p3 /mnt
mkdir -p /mnt/boot /mnt/home /mnt/var /mnt/data /mnt/code
mount /dev/nvme0n1p1 /mnt/boot
mount -o subvol=home /dev/sdc /mnt/home
mount -o subvol=var /dev/sdc /mnt/var
mount -o subvol=data /dev/sdc /mnt/data
mount -o subvol=code /dev/sdc /mnt/code
lsblk
```

#### 格式化时报错/dev/sdb5 is apparently in use by the system 解除占用

```shell
cat /proc/partitions 
dmsetup status
dmsetup remove_all
dmsetup status
```

## 开始安装系统

### archlinuxcn

- 国内加速源 [/etc/pacman.conf](pacman.conf)
- archlinuxcn 源 [/etc/pacman.d/mirrorlist](mirrorlist)

### shell

```shell
vim /etc/pacman.d/mirrorlist
# 开头
Server = https://mirrors.bfsu.edu.cn/archlinux/$repo/os/$arch

# 安装系统
pacstrap /mnt base linux linux-firmware

# 生成挂载信息文件
genfstab -U /mnt >> /mnt/etc/fstab

# 切换到新系统
arch-chroot /mnt

# 安装软件包
pacman -S vi vim networkmanager openssh sudo

# 配置 archlinuxcn 源
vim /etc/pacman.conf
# 末尾
[archlinuxcn]
Server = https://mirrors.bfsu.edu.cn/archlinuxcn/$arch
# 导入archlinuxcn GPG包 
pacman -S archlinuxcn-keyring
#安装报错failed to install packages to new root
pacman-key --refresh-keys

# 设置时区
ln -sf /usr/share/zoneinfo/$Region/$City /etc/localtime
hwclock --systohc

# 本地化
vim /etc/locale.gen
# 取消注释
en_US.UTF-8 UTF-8
zh_CN.UTF-8 UTF-8

# 设置locale
vim /etc/locale.conf
LANG=en_US.UTF-8

# 生成locale信息
locale-gen

# 主机名
vim /etc/hostname
box

# hosts设置
vim /etc/hosts
127.0.0.1    localhost
::1          localhost
127.0.1.1    myhostname.localdomain	myhostname
52.192.72.89 github.com

# 设置root密码
passwd
# 添加用户
useradd -m caddy
# 设置 caddy 密码
passwd caddy

# 用户添加 sudo  visudo 专门编辑 /etc/sudoers 需要 vi
visudo /etc/sudoers
caddy   ALL=(ALL) ALL


# 网络
systemctl enable NetworkManager

# 无线网络
# gui
nmtui
# terminal
nmcli device wifi list
nmcli device wifi connect "your wifi name" password "your wifi password"

# openssh
systemctl enable sshd
## 禁止 root ssh 仅允许 caddy 用户 ssh
vim /etc/ssh/sshd_config
PermitRootLogin no
AllowUsers caddy


# 安装grub
pacman -S grub efibootmgr
# 设置引导
grub-install --target=x86_64-efi --efi-directory=boot --bootloader-id=GRUB
# 生成grub配置
grub-mkconfig -o /boot/grub/grub.cfg
exit
# 取消挂载 重启
lsblk
# 取消多个挂载目录
umount -R /mnt
lsblk
# 或者分别取消挂载
umount /mnt/boot
umount /mnt/code
umount /mnt/data
umount /mnt/var
umount /mnt/home
umount /mnt
lsblk
reboot
```

### [显卡驱动](https://wiki.archlinux.org/title/Xorg#Driver_installation)

```shell
# 查看显卡型号
lspci | grep VGA
# 官方仓库提供的驱动包：
# # +----------------------+--------------------+--------------+
# # |                      |        开源        |     私有     |
# # +----------------------+--------------------+--------------+
# # |         通用         |   xf86-video-vesa  |              |
# # +----------------------+--------------------+--------------+
# # |         Intel        |  xf86-video-intel  |              |
# # +--------+-------------+--------------------+--------------+
# # |        | GeForce 9+  |                    |    nvidia    |
# # +        +-------------+                    +--------------+
# # | nVidia | GeForce 8/9 | xf86-video-nouveau | nvidia-340xx |
# # +        +-------------+                    +--------------+
# # |        | GeForce 6/7 |                    | nvidia-304xx |
# # +--------+-------------+--------------------+--------------+
# # |        AMD/ATI       |   xf86-video-ati   |              |
sudo pacman -S xf86-video-intel（笔记本）
sudo pacman -S nvidia nvidia-utils(nvidia独立显卡)
```

### [xorg](https://wiki.archlinux.org/index.php/Xorg)

```shell
pacman -S xorg xorg-server xorg-xinit xorg-apps
```

### [自动配置文件](https://wiki.archlinux.org/index.php/NVIDIA) 参考进行个性化配置 nvidia-xconfig

```shell
vim /etc/X11/xorg.conf

Section "ServerLayout"
    Identifier     "Layout0"
    Screen      0  "Screen0" 0 0
    InputDevice    "Keyboard0" "CoreKeyboard"
    InputDevice    "Mouse0" "CorePointer"
EndSection

Section "Files"
EndSection

Section "InputDevice"

    # generated from default
    Identifier     "Mouse0"
    Driver         "mouse"
    Option         "Protocol" "auto"
    Option         "Device" "/dev/psaux"
    Option         "Emulate3Buttons" "no"
    Option         "ZAxisMapping" "4 5"
EndSection

Section "InputDevice"

    # generated from default
    Identifier     "Keyboard0"
    Driver         "kbd"
EndSection

Section "Monitor"
    Identifier     "Monitor0"
    VendorName     "Unknown"
    ModelName      "Unknown"
    Option         "DPMS" "1"
EndSection

Section "Device"
    Identifier     "Device0"
    Driver         "nvidia"
    VendorName     "NVIDIA Corporation"
    Option         "NoLogo" "1"
    Option         "RenderAccel" "1"
    Option         "TripleBuffer" "1"
    Option         "RegistryDwords" "PerfLevelSrc=0x3333"
EndSection

Section "Screen"
    Identifier     "Screen0"
    Device         "Device0"
    Monitor        "Monitor0"
    DefaultDepth    24
    SubSection     "Display"
        Depth       24
    EndSubSection
EndSection
```

### KDE图像化界面

```shell
pacman -S plasma kde-applications
pacman -S sddm sddm-kcm
systemctl enable sddm
```

### 字体

```shell
pacman -S fcitx5-im fcitx5-chinese-addons fcitx5-material-color
# 或者
pacman -S fcitx5 fcitx5-configtool fcitx5-qt fcitx5-gtk fcitx5-chinese-addons fcitx5-material-color
# 添加拼音
fcitx5-configtool
```

#### 字体配置

```shell script
# kde使用此配置
/etc/profile
fcitx5 # 开机启动

# i3 使用此配置
~/.config/i3/config

exec --no-startup-id fcitx5
```
