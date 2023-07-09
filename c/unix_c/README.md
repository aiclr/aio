

### man

> [archlinux wiki](https://wiki.archlinux.org/title/Man_page)

`sudo pacman -S man-pages`

### process

> 父子关系 & 兄弟关系
> `pstree(1)` 可以查看用户级进程树
> `ps -aux` 查看系统的进程情况
> `top` 实时查看进程情况



- 进程状态
  - O 就绪,等待被调度
  - R 运行
  - S 可唤醒睡眠.
  - D
  - T
  - W
  - X
  - Z
  - <
  - N
  - L
  - s
  - |
  - `+`


#### 进程的终止

return 是从函数中返回.如果从main函数返回代表进程的正常终止
exit 代表进程的正常终止,在程序的任何地方使用都会终止当前进程`man 3 exit`


