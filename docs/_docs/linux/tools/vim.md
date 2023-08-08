---
title: vim
targets:
  - name: top
    link: linux/tools/vim
  - name: 文件格式 LF、CR、CRLF
    link: linux/tools/vim#fileformat
  - name: 异常
    link: linux/tools/vim#异常
  - name: 编辑模式
    link: linux/tools/vim#编辑模式
  - name: 命令模式
    link: linux/tools/vim#命令模式
  - name: 快捷键
    link: linux/tools/vim#快捷键
  - name: 末行模式
    link: linux/tools/vim#末行模式
  - name: 二进制文件
    link: linux/tools/vim#二进制文件
  - name: 行号 搜索 替换
    link: linux/tools/vim#行号-搜索-替换
  - name: 单窗口
    link: linux/tools/vim#single-vim
  - name: 多窗口
    link: linux/tools/vim#multiple-vim
  - name: 复制
    link: linux/tools/vim#copy
  - name: 剪切
    link: linux/tools/vim#cut
  - name: 粘贴
    link: linux/tools/vim#paste
  - name: 撤销
    link: linux/tools/vim#undo
  - name: 粘贴板
    link: linux/tools/vim#粘贴板
  - name: 注意
    link: linux/tools/vim#注意
---

## 命令模式

默认模式。编辑模式/末行模式下按 `esc` 切换为命令模式
```
dd
yy
v
p
P
i
```

打开文件定位到最后一行
```
vim + xxx
```

打开文件定位到`n`行
```
vim +n xxx
```

打开文件定位到第一个与`pattern`匹配的位置
```
vim +/pattern xxx
```

## 末行模式

命令模式按 `:`<sub>英文冒号</sub> 进入末行模式
```
:q
:wq
:q!
:set nu
:set nonu
:set number
:set nonumber
:set ff=unix
:set ff=mac
:set ff=dos
:e a.sh
:open a.sh
:%!xxd
:undo
:u
```

末行模式：另存为
```
:w newfile
```

## 编辑模式

命令模式按 `i` 进入编辑模式

## fileformat

末行模式设置文件格式
```
:set ff=unix
:set ff=mac
:set ff=dos
```

末行模式查看文件格式
```
:set ff?
```

## 异常

继续上次异常退出编辑
```
vim -r xxx
```

异常退出编辑会在同级目录生成临时文件`.xxx.swp` 当确认文件都保存完毕时可删除临时文件

#### 快捷键

向前翻一页 `ctrl f`\
向后翻一页 `ctrl b`\
向前翻半页 `ctrl u`\
向后翻半页 `ctrl d`\
向后翻半页 `ctrl d`\
移到当前行 行首 `^`\
移到当前行 行尾 `$`\
移到首行 `1G`\
移到n行 `nG`\
移到末行 `G`

## 二进制文件

`-b`以二进制模式打开文件
```
vim -b z.png
```

末行模式输入 `:%!xxd` 或 `:%!xxd -r`

## 行号 搜索 替换

> 行号
> > 显示行号：末行模式输入 `:set nu` 或者 `:set number`\
> > 关闭行号：末行模式输入 `:set nonu` 或者 `:set nonumber`
>
> 搜索
> > 向上搜索: 命令模式输入 `?pattern`\
> > 向下搜索: 命令模式输入 `/pattern`\
> > 下一个匹配 `n` 上一个匹配 `N` :上下与向上搜索向下搜索的方向适配
> 
> 替换
> > 当前行p1全部替换为p2 `s/p1/p2/g`\
> > n1至n2行内p1全部替换为p2 `n1,n2s/p1/p2/g`\
> > 整个文本p1全部替换为p2 `g/p1/s//p2/g`

## single vim

vim 末行模式打开 xxx 文本文件
```
:e xxx
```

vim 切换打开的文本文件\
命令模式 `ctrl 6`<sub>round</sub>\
末行模式
- `:bn`<sub>next 下一个</sub>
- `:bp`<sub>prior 上一个</sub>
 
## multiple vim

> 末行模式
> > `:vsplit xxx` 左右分割两个窗口打开xxx文件\
> > `:split xxx` 上下分割两个窗口打开xxx文件

> 命令模式切换窗口
> > `ctrl + w + 方向键/hjkl`\
> > `ctrl+w h` 或 `ctrl+w ⬅️`\
> > `ctrl+w j` 或 `ctrl+w ⬇️`\
> > `ctrl+w k` 或 `ctrl+w ⬆️`\
> > `ctrl+w l` 或 `ctrl+w ➡️`\
> > `ctrl+w w` round 

## copy

命令模式

> `yy` 复制游标所在行整行\
> `2yy`或`y2y` 复制 2 行\
> `y^` 复制至行首，或`y0`\
> `y$` 复制至行尾\
> `yw` 复制一个word\
> `y2w` 复制两个word\
> `yG` 复制至文件尾\
> `y1G` 复制至文件首\
> 要选中内容进行复制，先在命令模式下按 `v` 进入 **Visual Mode**，然后用**方向键** 或 **hjkl** 选择文本，再按 `y` 进行复制

## cut

命令模式

> `dd` 剪切游标所在行整行\
> `d^` 剪切至行首，或`d0`\
> `d$` 剪切至行尾\
> `dw` 剪切一个word\
> `dG` 剪切至文件尾

## paste

命令模式

> `p`<sub>lower case</sub> 粘贴至游标后（下）\
> `P`<sub>upper case</sub> 粘贴至游标前（上）\
> **系统粘贴板** 的内容，也可以直接在命令模式按 `Shift + Insert` 进行粘贴

## undo

> 在命令行模式下用 `:undo` 或 `:u` 命令可以**撤销**最近一次操作

## 粘贴板

vim 有 12 个粘贴板，分别是 0、1、2、...、9、a、“、＋

末行模式 `:reg` 可以查看各个粘贴板里的内容。\
在 vim 中简单用 y 命令只是复制到 "（双引号）粘贴板里，同样用 p 命令粘贴的也是这个粘贴板里的内容；

要将 vim 的内容复制到某个粘贴板，需要先退出编辑模式，再进入命令模式后，选择要复制的内容，\
然后按 **"Ny** <sub>注意带双引号</sub>完成复制，其中 **N** 为粘贴板号<sub>注意是按下 双引号 + 粘贴板号 + y</sub>，\
例如要把内容复制到粘贴板 **a**，选中内容后按 **"ay** 即可。

> 有 2 点需要说明一下：
> > `"` 号粘贴板<sub>临时粘贴板</sub>比较特殊，直接按 y 就复制到这个粘贴板中了，直接按 p 就粘贴这个粘贴板中的内容\
> > `+` 号粘贴板是系统粘贴板
> > > 用 "+y 将内容复制到该粘贴板后，可以使用 ctrl＋v 将其粘贴到其他文档（如 firefox、gedit）中\
> > > 同理，要把在其他地方用 `ctrl＋c` 或右键复制的内容复制到 vim 中，需要在正常模式下按 **"+p**

> 将 vim 某个粘贴板里的内容粘贴进来，需要先退出编辑模式，进入命令模式按 **"Np**。其中 **N** 为粘贴板号\
> 如上所述，命令 **"5p** 会将 **5** 号粘贴板里的内容粘贴进来，按 **"+p** 将系统全局粘贴板里的内容粘贴进来。

## 注意

vim的`~/.vimrc`配置项，屏蔽掉下面这句话：
```shell
set fileencodings=utf-8,gb2312,gbk,gb18030,ucs-bom
```

再用vim打开`jpeg`文件，显示`ffd8 ffc0 0011 0804`和 `ffd9 0a`，显示正确\
vim 为了支持识别和显示中文，规定了 vim 的 `fileencodings`\
当vim打开文件时，会使用规定的编码格式对数据进行解析\
jpeg的文件头`FFD8`、尾`FFD9` 不是任何一个中文的编码，vim找不到对应的中文字，就显示为`？？`，即：`3f3f`
