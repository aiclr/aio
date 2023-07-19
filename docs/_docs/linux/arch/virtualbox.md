<div style="text-align: center;font-size: 40px;">在virtualbox上安装archlinux</div>

## 注意

- virtualbox 开启 efi
    - system/Motherboard/Extended Fature/Enbale EFI
    - 系统/主板/启用 EFI
- 新增的虚拟硬盘默认为 MBR分区表 `fdisk /dev/sda` 输入 g 修改为GPT分区表
- virtual box windows 版本 不能正确加载grub 不能引导 EFI 会自动进到 Shell
    - 手动执行grub引导脚步 `\EFI\GRUB\grubx64.efi`
    - 注意观察日志 找到 引导程序目录 在Shell下执行以下操作
        - `fs0:` 进入FS0目录
        - `mkdir \EFI\boot`
        - `cp \EFI\GRUB\grubx64.efi \EFI\boot\bootx64.efi`
        - 之后手动执行 `\EFI\boot\bootx64.efi` 进入系统 再重启即可正常引导grub
- 安装过程没有区别

## virtualbox archlinux 安装记录

### 磁盘分区 GPT分区表 + UEFI模式

- 机械盘 `fdisk /dev/sda`
    - `/dev/sda1` : 主引导分区 最少300M 
        - `mkfs.fat -F 32 /dev/sda1`
    - `/dev/sda2` : swap 交换分区 启用休眠 大小 >= 内存大小 
        - `mkswap /dev/sda2`
    - `/dev/sda3` : btrfs 分区 subvolumes 子卷 root、var、home、data
        - `mkfs.btrfs -L all /dev/sda3`

```shell
# 分区 格式化
fdisk /dev/sda
mkfs.fat -F 32 /dev/sda1
mkswap -L swap /dev/sda2
mkfs.btrfs -L all /dev/sda3
# /dev/sda3 使用 btrfs subvolumes
mount /dev/sda3 /mnt
btrfs subvolume create /mnt/home
btrfs subvolume create /mnt/var
btrfs subvolume create /mnt/data
btrfs filesystem show
btrfs subvolume list /mnt
umount /mnt
# 挂载 准备装机
mount -o subvol=root /dev/sda3 /mnt
mkdir -p /mnt/boot /mnt/home /mnt/var /mnt/data
mount /dev/sda1 /mnt/boot
mount -o subvol=home /dev/sda3 /mnt/home
mount -o subvol=var /dev/sda3 /mnt/var
mount -o subvol=data /dev/sda3 /mnt/data
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

- [/etc/pacman.conf](pacman.conf)
- [/etc/pacman.d/mirrorlist](mirrorlist)

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

# 安装基础软件包
pacman -S vi vim networkmanager openssh sudo

# 配置 archlinuxcn 源
vim /etc/pacman.conf
# 末尾添加
[archlinuxcn]
Server = https://mirrors.bfsu.edu.cn/archlinuxcn/$arch

# 报错failed to install packages to new root
pacman-key --refresh-keys

# 设置时区
ln -sf /usr/share/zoneinfo/$Region/$City /etc/localtime
hwclock --systohc

# 本地化
vim /etc/locale.gen
# 取消注释 或者 新增
en_US.UTF-8 UTF-8
zh_CN.UTF-8 UTF-8

# 设置locale
vim /etc/locale.conf
LANG=en_US.UTF-8

# 生成locale信息
locale-gen

# 主机名
vim /etc/hostname
vbox

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
passwd caddy

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

# 用户添加 sudo visudo 可以编辑 /etc/sudoers 需要 vi
visudo /etc/sudoers
caddy   ALL=(ALL) ALL

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