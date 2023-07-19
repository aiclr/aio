<div style="text-align: center;font-size: 40px;">jar</div>

> `-c, --create` 创建档案
>
> `-v, --verbose` 在标准输出中生成详细输出
>
> `-f, --file=FILE` 档案文件名。省略时, 基于操作 使用 stdin 或 stdout
>
> `-e, --main-class=CLASSNAME` 捆绑到模块化或可执行 jar 档案的独立应用程序 的应用程序入口点
>
> `-C DIR` 更改为指定的目录并包含 以下文件

---

## demo

```shell
# 需要手动创建目录
mkdir -p out/mods

# -c, --create               创建档案
# -v, --verbose              在标准输出中生成详细输出
# -f, --file=FILE            档案文件名。省略时, 基于操作 使用 stdin 或 stdout
# -e, --main-class=CLASSNAME 捆绑到模块化或可执行 jar 档案的独立应用程序 的应用程序入口点
# -C DIR                     更改为指定的目录并包含 以下文件
jar -cvfe out/mods/App.jar org.bougainvilleas.svc.cli.Client -C out/cli .
jar -cvf out/mods/api.jar -C out/api .

```

---

## `jar --help`

```
[caddy@vbox ~]$ java -version
java version "17.0.2" 2022-01-18 LTS
Java(TM) SE Runtime Environment (build 17.0.2+8-LTS-86)
Java HotSpot(TM) 64-Bit Server VM (build 17.0.2+8-LTS-86, mixed mode, sharing)

[caddy@vbox ~]$ jar --help
Usage: jar [OPTION...] [ [--release VERSION] [-C dir] files] ...
jar creates an archive for classes and resources, and can manipulate or
restore individual classes or resources from an archive.

 Examples:
 # Create an archive called classes.jar with two class files:
 jar --create --file classes.jar Foo.class Bar.class
 # Create an archive using an existing manifest, with all the files in foo/:
 jar --create --file classes.jar --manifest mymanifest -C foo/ .
 # Create a modular jar archive, where the module descriptor is located in
 # classes/module-info.class:
 jar --create --file foo.jar --main-class com.foo.Main --module-version 1.0
     -C foo/ classes resources
 # Update an existing non-modular jar to a modular jar:
 jar --update --file foo.jar --main-class com.foo.Main --module-version 1.0
     -C foo/ module-info.class
 # Create a multi-release jar, placing some files in the META-INF/versions/9 directory:
 jar --create --file mr.jar -C foo classes --release 9 -C foo9 classes

To shorten or simplify the jar command, you can specify arguments in a separate
text file and pass it to the jar command with the at sign (@) as a prefix.

 Examples:
 # Read additional options and list of class files from the file classes.list
 jar --create --file my.jar @classes.list


 Main operation mode:

  -c, --create               Create the archive
  -i, --generate-index=FILE  Generate index information for the specified jar
                             archives
  -t, --list                 List the table of contents for the archive
  -u, --update               Update an existing jar archive
  -x, --extract              Extract named (or all) files from the archive
  -d, --describe-module      Print the module descriptor, or automatic module name
      --validate             Validate the contents of the jar archive. This option
                             will validate that the API exported by a multi-release
                             jar archive is consistent across all different release
                             versions.

 Operation modifiers valid in any mode:

  -C DIR                     Change to the specified directory and include the
                             following file
  -f, --file=FILE            The archive file name. When omitted, either stdin or
                             stdout is used based on the operation
      --release VERSION      Places all following files in a versioned directory
                             of the jar (i.e. META-INF/versions/VERSION/)
  -v, --verbose              Generate verbose output on standard output

 Operation modifiers valid only in create and update mode:

  -e, --main-class=CLASSNAME The application entry point for stand-alone
                             applications bundled into a modular, or executable,
                             jar archive
  -m, --manifest=FILE        Include the manifest information from the given
                             manifest file
  -M, --no-manifest          Do not create a manifest file for the entries
      --module-version=VERSION    The module version, when creating a modular
                             jar, or updating a non-modular jar
      --hash-modules=PATTERN Compute and record the hashes of modules 
                             matched by the given pattern and that depend upon
                             directly or indirectly on a modular jar being
                             created or a non-modular jar being updated
  -p, --module-path          Location of module dependence for generating
                             the hash

 Operation modifiers valid only in create, update, and generate-index mode:

  -0, --no-compress          Store only; use no ZIP compression

 Other options:

  -?, -h, --help[:compat]    Give this, or optionally the compatibility, help
      --help-extra           Give help on extra options
      --version              Print program version

 An archive is a modular jar if a module descriptor, 'module-info.class', is
 located in the root of the given directories, or the root of the jar archive
 itself. The following operations are only valid when creating a modular jar,
 or updating an existing non-modular jar: '--module-version',
 '--hash-modules', and '--module-path'.

 Mandatory or optional arguments to long options are also mandatory or optional
 for any corresponding short options.
```