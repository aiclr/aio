---
title: windows
targets:
  - name: Top
    link: windows#windows-kill-process
  - name: windows 处理端口占用
    link: windows#windows-kill-process
  - name: windows netstat
    link: windows#netstat
  - name: windows tasklist
    link: windows#tasklist
  - name: windows taskkill
    link: windows#taskkill
  - name: linux lsof 处理端口占用
    link: windows#lsof
  - name: 提升cmd/powershell权限
    link: runas
  - name: powershell脚本执行权限
    link: executionpolicy
  - name: 新建文件/文件夹
    link: ni
  - name: powershell内运行vim
    link: vim-in-powershell
---

## windows kill process

### netstat

查看帮助
```
netstat /?
```

查找占用端口的PID
```
 netstat -ano | findstr "$port"
```

### tasklist

查看帮助
```
tasklist /?
```

查找 PID 程序名
```
tasklist | findstr "$PID"
```

查找程序
```
tasklist | findstr "YunDetectService.exe"
```

### taskkill

查看帮助
```
taskkill /?
```

结束进程映像名称
```
taskkill /f /t /im "YunDetectService.exe"
```

结束进程PID
```
taskkill /f /t /pid "$PID"
```

## lsof

> 安装 `sudo pacman -S lsof`
> 查找占用端口的PID `lsof -i:8080`
> 结束进场 `kill -9 $PID`

### runas

cmd/powershell 使用管理员权限运行
```
runas ?
runas --help
runas /noprofile /user:administrator powershell
```

## ExecutionPolicy

[powershell脚本执行权限](https://learn.microsoft.com/zh-cn/training/modules/create-run-scripts-use-windows-powershell/6-run-scripts-set-execution-policy)
```
get-executionpolicy
set-executionpolicy remotesigned
```

## ni

`New-Item` 别名 `ni`,[新建文件/文件夹](https://learn.microsoft.com/zh-cn/powershell/module/microsoft.powershell.management/new-item?view=powershell-5.1)
```
get-help new-item
New-Item xxx -ItemType file
New-Item xxx -ItemType directory
```

## vim in powershell

`$profile`输出当前用户 powershell 启动时加载脚本
`C:\Users\leo\Documents\WindowsPowerShell\Microsoft.PowerShell_profile.ps1`
不存在则新建
```
new-item -Path $profile -ItemType "file" -Force
```
编辑`C:\Users\leo\Documents\WindowsPowerShell\Microsoft.PowerShell_profile.ps1`
添加以下内容，重新打开 powershell 使生效
```
$VIMPATH="C:\Program Files (x86)\Vim\vim90\vim.exe" 
Set-Alias vi $VIMPATH 
Set-Alias vim $VIMPATH
```
