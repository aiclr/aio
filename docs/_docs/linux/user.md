<div style="text-align: center;font-size: 40px;">Users and groups</div>

# top

- [archlinux wiki](https://wiki.archlinux.org/title/Users_and_groups)
- [用户和组相关文件](#用户和组相关文件)
- [Group management](#group-management)
- [User management](#user-management)


## 用户和组相关文件

```shell
localhost:~ # cat /etc/passwd
# 用户名:密码:用户标识号:组标识号:组名:主目录:shell
# 密码：用户口令的加密串，不是明文，但是/etc/passwd对所有用户可读，存在安全隐患，许多linux使用shadow技术把加密后的口令存放在/etc/shadow,/etc/passwd文件的口令字段只存放一个特殊的字符例如"x"或“*”
# 用户登陆后启动的进程：负责将用户操作传给内核，这个进程是用户登录到系统后运行的命令解释器或某个特定的程序即：shell
root:x:0:0:root:/root:/bin/bash
......

localhost:~ # cat /etc/group
# 组名:组口令（linux用户组一般不设置口令为空或特殊字符"x"或"*"）:组标识号
root:x:0:
......

localhost:~ # cat /etc/shadow
# 登录名:加密口令:最后一次修改时间:两次修改口令之间所需的最小天数:口令保持有效的最大天数:密码正式失效前多少天开始发出警告:用户不登录但账号仍能保持有效的最大天数:多少天后失效，失效后该账号不再是一个合法账号，也不能再用来登录:
root:$6$pNxL8HuTTjWXDCQM$JsX1DhLGe.mA01bRwmQ4eVCNB1qT8YmIaSD03DWzi2IKE3UtsMydwXjDHQ9gOhTJrektvdMRjnsAqPyXsmY9Q.:18978::::::
......

localhost:~ # stat -c %U text.txt 
root
localhost:~ # stat -c %G text.txt 
root
localhost:~ # stat -c %A text.txt 
-rwxr-xr-x
localhost:~ # find ./ -group root
./
./text.txt
localhost:~ # find ./ -group 0
./
./text.txt
localhost:~ # find ./ -user root
./
./text.txt

```

[top](#top)

## Group management

```shell
# 查看用户所属组
testu@master:~> groups testu
testu : users

# 查看用户详情
testu@master:~> id testu
uid=1000(testu) gid=100(users) groups=100(users)

# To list all groups on the system:
cat /etc/group

# Create new groups with the groupadd command:
groupadd groupname

# To delete existing groups:
groupdel group

# Add users to a group with the gpasswd command
gpasswd -a user group

# add a user to additional groups with usermod 
usermod -aG additional_groups username

# To remove users from a group
gpasswd -d user group

# Modify an existing group with the groupmod command e.g. to rename the old_group group to new_group:
groupmod -n new_group old_group
```

[top](#top)

## User management

```shell
# -m/--create-home the user's home directory is created as /home/username. The directory is populated by the files in the skeleton directory. The created files are owned by the new user.
# -G/--groups 所属组名 默认组名=username; a comma separated list of supplementary groups which the user is also a member of. The default is for the user to belong only to the initial group
# -s/--shell a path to the user's login shell. Ensure the chosen shell is installed if choosing something other than Bash.
useradd -m -G testu -s /bin/bash testu
useradd -m -s /bin/bash testu
# with the -u/--uid and -g/--gid options when creating the user
useradd -r -u 850 -g 850 -s /usr/bin/nologin username

# To add a new user named archie
useradd -m archie
# Although it is not required to protect the newly created user archie with a password, it is highly recommended to do so:
passwd archie

# To change a user's home directory:
usermod -d /my/new/home -m username
ln -s /my/new/home/ /my/old/home

# To change a user's login name:
usermod -l newname oldname

# To change the user's login shell:
usermod -s /bin/bash username

# User accounts may be deleted with the userdel command:
userdel -r username

# To mark a user's password as expired, requiring them to create a new password the first time they log in, type:
chage -d 0 username

```

[top](#top)