<div style="text-align: center;font-size: 40px;">chmod</div>

- SUID 4
- SGID 2
- SBIT 1
- --- 0
- --x 1
- -w- 2
- r-- 4

## SGID set-group-id

```shell
# set-group-id 和 set-gid 授予程序其所在组的访问权限
# 通过chmod 选项 g 设置，该标志对shell脚本程序不起作用，
# 只对可执行的二进制文件有用
# 2 表示 SUID 占据拥有者的x权限位
chmod 2755 a.out # -rwxr-sr-x
chmod g-s a.out  # -rwxr-xr-x
chmod g+s a.out  # -rwxr-sr-x
# S 表示空 没有x权限 S无意义
chmod g-x a.out  # -rwxr-Sr-x
chmod g-s a.out  # -rwxr--r-x
chmod g+xs a.out # -rwxr-sr-x
```

## SUID set-user-id

```shell
# set-user-id 和 set-uid 授予程序其拥有者的访问权限，不是其使用者的访问权限
# 通过chmod 选项 u 设置，该标志对shell脚本程序不起作用，
# 只对可执行的二进制文件有用
# 4 表示 SUID 占据拥有者的x权限位
chmod 4755 a.out # -rwsr-xr-x
chmod u-s a.out  # -rwxr-xr-x
chmod u+s a.out  # -rwsr-xr-x
# S 表示空 没有x权限 S无意义
chmod u-x a.out  # -rwSr-xr-x
chmod u-s a.out  # -rw-r-xr-x
chmod u+xs a.out # -rwsr-xr-x
```

## SBIT sticky-bit

```shell
# SBIT即Sticky Bit，
# 它出现在其他用户权限的执行位上，它只能用来修饰一个目录。
# 当某一个目录拥有SBIT权限时，则任何一个能够在这个目录下建立文件的用户，
# 该用户在这个目录下所建立的文件，只有该用户自己和root可以删除，其他用户均不可以
# 如下末尾的 t 占用 x，如果为大T则表示该目录没有x权限
[caddy@onepiece frp]$ ls -ld /tmp
drwxrwxrwt 10 root root 240 Sep  7 17:31 /tmp

#测试：
mkdir tmp        drwxr-xr-x
# 第一个1表示其他用户权限 SBIT
chmod 1777 /tmp  drwxrwxrwt
chmod o-t /tmp   drwxrwxrwx
chmod o+t /tmp   drwxrwxrwt
#T 没有x权限是就会从小t变成大T，t只有在目录有x权限时才有意义
chmod o-x /tmp   drwxrwxrwT
chmod o+x /tmp   drwxrwxrwt
chmod o-xt /tmp  drwxrwxrw-
```