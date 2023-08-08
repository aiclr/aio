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
