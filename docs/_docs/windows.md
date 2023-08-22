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
    link: windows#runas
  - name: powershell脚本执行权限
    link: windows#executionpolicy
  - name: 新建文件/文件夹
    link: windows#ni
  - name: powershell内运行vim
    link: windows#vim-in-powershell
  - name: echo '字符集编码' >> hosts
    link: windows#character-encoding
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

## character encoding

[microsoft docs](https://learn.microsoft.com/zh-cn/powershell/module/microsoft.powershell.core/about/about_character_encoding?view=powershell-5.1)

[Changing the default encoding](https://learn.microsoft.com/en-us/powershell/module/microsoft.powershell.core/about/about_character_encoding?view=powershell-5.1#changing-the-default-encoding)

PowerShell has two default variables that can be used to change the default encoding behavior.

- `$PSDefaultParameterValues`
- `$OutputEncoding`

Beginning in PowerShell 5.1, the redirection operators (`>` and `>>`) call the `Out-File` cmdlet. Therefore, you can set the default encoding of them using the `$PSDefaultParameterValues` preference variable as shown in this example:

```shell
# vim $profile
$PSDefaultParameterValues['Out-File:Encoding'] = 'utf8'
```

Use the following statement to change the default encoding for all cmdlets that have the Encoding parameter.

```shell
# vim $profile
$PSDefaultParameterValues['*:Encoding'] = 'utf8'
```

Windows PowerShell 5.1 Encoding 参数支持以下值

- `Ascii` 使用 Ascii（7位）字符集
- `BigEndianUnicode` 使用具有 big-endian 字节顺序的 UTF-16。
- `BigEndianUTF32` 使用具有 big-endian 字节顺序的 UTF-32。
- `Byte` 将一组字符编码为字节序列。
- `Default` 使用与系统的活动代码页对应的编码， (通常为 ANSI) 。
- `Oem` 使用与系统的当前 OEM 代码页对应的编码。
- `String` 与 Unicode 相同。
- `Unicode` 使用具有 little-endian 字节顺序的 UTF-16。
- `Unknown` 与 Unicode 相同。
- `UTF32` 使用具有 little-endian 字节顺序的 UTF-32。
- `UTF7` 使用 UTF-7。
- `UTF8` 将 UTF-8 (与 BOM) 配合使用。

通常，Windows PowerShell默认使用 `Unicode UTF-16LE` 编码。
但是，Windows PowerShell中 cmdlet 使用的默认编码并不一致。
使用除 `UTF7` 之外 的任何 `Unicode` 编码始终会创建 `BOM`。

For **cmdlets** that write output to files:

- `Out-File` and the redirection<sub>重定向</sub> operators<sub>操作符</sub> `>` and `>>` create `UTF-16LE`, which notably differs from `Set-Content` and `Add-`Content`.
- `New-ModuleManifest` and `Export-CliXml` also create `UTF-16LE` files.
- When the target file is empty or doesn't exist, `Set-Content` and `Add-Content` use `Default` encoding. `Default` is the encoding specified by the active system locale's `ANSI` legacy code page.
- `Export-Csv` creates `Ascii` files but uses different encoding when using `Append` parameter (see below).
- `Export-PSSession` creates `UTF-8` files with `BOM` by default.
- `New-Item -Type File -Value` creates a **BOM-less UTF-8** file.
- `Send-MailMessage` uses `Default` encoding by default.
- `Start-Transcript` creates `Utf8` files with a `BOM`. When the `Append` parameter is used, the encoding can be different (see below).

For **commands** that **append** to an existing file:

- `Out-File -Append` and the `>>` redirection operator make no attempt to match the encoding of the existing target file's content. Instead, they use the `default` encoding unless the Encoding parameter is used. **You must use the files original<sub>原始的</sub> encoding when appending content**.
- In the absence<sub>不存在</sub> of an explicit<sub>明确的</sub> Encoding parameter, `Add-Content` detects<sub>侦察出</sub> the existing encoding and automatically applies it to the new content. If the existing content has **no BOM**, `Default` ANSI encoding is used. The behavior of `Add-Content` is the same in PowerShell (v6 and higher) except the default encoding is `Utf8`.
- `Export-Csv -Append` matches the existing encoding when the target file contains a **BOM**. In the absence<sub>不存在</sub> of a **BOM**, it uses `Utf8` encoding.
- `Start-Transcript -Append` matches the existing encoding of files that include a **BOM**. In the absence<sub>不存在</sub> of a BOM, it defaults to `Ascii` encoding. This encoding can result in<sub>导致</sub> data loss<sub>丢失</sub> or character corruption<sub>腐蚀</sub> when the data in the transcript<sub>转写本</sub> contains multibyte<sub>多字节</sub> characters.

For `cmdlets` that read string data in the absence<sub>不存在</sub> of a BOM:

- `Get-Content` and `Import-`PowerShellDataFile` uses the `Default` ANSI encoding. ANSI is also what the **PowerShell engine** uses when it reads source code from files.
- `Import-Csv`, `Import-CliXml`, and `Select-String` assume **Utf8** in the absence<sub>不存在</sub> of a BOM.

