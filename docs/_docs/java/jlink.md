<div style="text-align: center;font-size: 40px;">jlink</div>

> `-v, --verbose`  启用详细跟踪 
>
> `--compress=<0|1|2>`  Enable compression of resources:
> > Level 0: No compression \
> > Level 1: Constant string \
> > sharing Level 2: ZIP 
>
> `-p, --module-path <path>` Module path.
>
> `--add-modules <mod>[,<mod>...]`
> > Root modules to resolve in addition to the initial modules.\
> > <mod> can also be ALL-MODULE-PATH.
>
> `--launcher <name>=<module>[/<mainclass>]`
>> Add a launcher command of the given name for the module and the main class if specified\
>> 如果 创建 jar 包时`jar -cvfe out/mods/App.jar org.bougainvilleas.svc.cli.Client -C out/cli .` \
>> 使用 -e 指定 main class `org.bougainvilleas.svc.cli.Client` \
>> `--launcher <name>=<module>[/<mainclass>]` 可以省略 `[/<mainclass>]`
>
> `--output <path>`  Location of output path

---

## demo

```shell
# 需要手动创建 目录
mkdir -p out/jlink

# 单模块
jlink -v --compress=0 -p out/mods/:$JAVA_HOME/jmods --add-modules cli --launcher app=cli --output out/jlink/image
jlink -v --compress=1 -p out/mods/:$JAVA_HOME/jmods --add-modules cli --launcher app=cli --output out/jlink/image
jlink -v --compress=2 -p out/mods/:$JAVA_HOME/jmods --add-modules cli --launcher app=cli --output out/jlink/image
# 多模块
jlink -v -p out/mods/:$JAVA_HOME/jmods --add-modules cli,svc --launcher app=cli --output out/jlink/image
```

---

## jlink --help

```
[caddy@vbox ~]$ java -version
java version "17.0.2" 2022-01-18 LTS
Java(TM) SE Runtime Environment (build 17.0.2+8-LTS-86)
Java HotSpot(TM) 64-Bit Server VM (build 17.0.2+8-LTS-86, mixed mode, sharing)

[caddy@vbox ~]$ jlink --help
Usage: jlink <options> --module-path <modulepath> --add-modules <module>[,<module>...]
Possible options include:
      --add-modules <mod>[,<mod>...]    Root modules to resolve in addition to the
                                        initial modules. <mod> can also be ALL-MODULE-PATH.
      --bind-services                   Link in service provider modules and
                                        their dependences
  -c, --compress=<0|1|2>                Enable compression of resources:
                                          Level 0: No compression
                                          Level 1: Constant string sharing
                                          Level 2: ZIP
      --disable-plugin <pluginname>     Disable the plugin mentioned
      --endian <little|big>             Byte order of generated jimage
                                        (default:native)
  -h, --help, -?                        Print this help message
      --ignore-signing-information      Suppress a fatal error when signed
                                        modular JARs are linked in the image.
                                        The signature related files of the
                                        signed modular JARs are not copied to
                                        the runtime image.
      --launcher <name>=<module>[/<mainclass>]
                                        Add a launcher command of the given
                                        name for the module and the main class
                                        if specified  
      --limit-modules <mod>[,<mod>...]  Limit the universe of observable
                                        modules
      --list-plugins                    List available plugins
  -p, --module-path <path>              Module path.
                                        If not specified, the JDKs jmods directory
                                        will be used, if it exists. If specified,
                                        but it does not contain the java.base module,
                                        the JDKs jmods directory will be added,
                                        if it exists.
      --no-header-files                 Exclude include header files
      --no-man-pages                    Exclude man pages
      --output <path>                   Location of output path
      --save-opts <filename>            Save jlink options in the given file
  -G, --strip-debug                     Strip debug information
      --suggest-providers [<name>,...]  Suggest providers that implement the
                                        given service types from the module path
  -v, --verbose                         Enable verbose tracing
      --version                         Version information
      @<filename>                       Read options from file
```