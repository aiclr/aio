# gradle

| [home](index.md#build-tools)      |
| :-------------------------------- |
| [升级gradle版本](#upgrade)       |
| [jvm参数配置](#gradleproperties) |
| [构建环境](#build-environment)   |

> [官方文档](https://docs.gradle.org/current/userguide/userguide.html) \
> [支持java版本对照](https://docs.gradle.org/current/userguide/compatibility.html) \
> [使用指引](https://docs.gradle.org/current/userguide/getting_started.html) \
> [例子](https://docs.gradle.org/current/samples/index.html) \
> [故障](https://docs.gradle.org/current/userguide/troubleshooting.html) \
> [多项目 includeBuild](https://docs.gradle.org/current/userguide/composite_builds.html#command_line_composite) \
> [多模块](https://docs.gradle.org/current/userguide/intro_multi_project_builds.html) \
> [gradle.properties for Build Environment](https://docs.gradle.org/current/userguide/build_environment.html#sec:gradle_configuration_properties)

## Build Environment

> [Command-line flags](https://docs.gradle.org/current/userguide/command_line_interface.html#command_line_interface) such as `--build-cache`. These have precedence<sub>优先</sub> over properties and environment variables \
> [System properties](https://docs.gradle.org/current/userguide/build_environment.html#sec:gradle_system_properties) such as `systemProp.http.proxyHost=somehost.org` stored in a `gradle.properties` file in a **root** project directory. \
> [Gradle properties](https://docs.gradle.org/current/userguide/build_environment.html#sec:gradle_configuration_properties) such as `org.gradle.caching=true` that are typically stored in a `gradle.properties` file in a project directory or in the `GRADLE_USER_HOME`. \
> [Environment variables](https://docs.gradle.org/current/userguide/build_environment.html#sec:gradle_environment_variables) such as `GRADLE_OPTS` sourced by the environment that executes Gradle.

## command

### upgrade

```shell
#  扫描并查看弃用视图 view the deprecations view of the generated build scan
gradle help --scan
# 或者查看全部警告，来确认是否有弃用内容
gradle help --warning-mode=all
# 手动清除不再支持的 plugins
# to update the project to 7.4.2.
gradle wrapper --gradle-version 7.4.2
# try to run the project
```

## gradle.properties

> `org.gradle.caching=(true,false)`
> > When set to true, Gradle will reuse<sub>重复使用</sub> task outputs from any previous build, when possible, resulting in much faster builds. \
> > Learn more about [using the build cache](https://docs.gradle.org/current/userguide/build_cache.html#build_cache). \
> > By default, the build cache is not enabled.
>
> `org.gradle.caching.debug=(true,false)`
> > When set to true, individual<sub>单独的</sub> input property hashes and the build cache key for each task are logged on the console. \
> > Learn more about [task output caching](https://docs.gradle.org/current/userguide/build_cache.html#sec:task_output_caching). \
> > Default is false
>
> `org.gradle.configureondemand=(true,false)`
> > Enables incubating<sub>孵化</sub> [configuration](https://docs.gradle.org/current/userguide/multi_project_configuration_and_execution.html#sec:configuration_on_demand) on demand<sub>需要</sub>, where Gradle will attempt to configure only necessary projects. \
> > Default is false.
>
> `org.gradle.console=(auto,plain,rich,verbose)`
> > Customize<sub>定制</sub> console output coloring or verbosity<sub>赘述</sub>. \
> > Default depends on how Gradle is invoked<sub>调用</sub>. See [command-line logging](https://docs.gradle.org/current/userguide/command_line_interface.html#sec:command_line_logging) for additional details.
>
> `org.gradle.continuous.quietperiod=(# of quiet period millis)`
> > When using [continuous<sub>连续的</sub> build](https://docs.gradle.org/current/userguide/command_line_interface.html#sec:continuous_build), Gradle will wait for the quiet period<sub>时段</sub> to pass before triggering<sub>触发</sub> another build. \
> > Any additional<sub>额外的</sub> changes within this quiet period restart waiting for the quiet period. \
> > Default is 250 milliseconds.
>
> `org.gradle.daemon=(true,false)`
> > When set to true the [Gradle Daemon](https://docs.gradle.org/current/userguide/gradle_daemon.html#gradle_daemon) is used to run the build. \
> > Default is true, builds will be run using the daemon.
>
> `org.gradle.daemon.idletimeout=(# of idle millis)`
> > Gradle Daemon will terminate<sub>（使）停止</sub> itself after specified<sub>指定</sub> number of idle<sub>空闲</sub> milliseconds. \
> > Default is 10800000 (3 hours).
>
> `org.gradle.debug=(true,false)`
> > When set to true, Gradle will run the build with remote debugging enabled, listening on port 5005. \
> > Note that this is the equivalent<sub>相同的</sub> of adding `-agentlib:jdwp=transport=dt_socket,server=y,suspend=y,address=5005` to the JVM command line and will suspend<sub>挂起</sub> the virtual machine until a debugger is attached<sub>所附的</sub>. \
> > Default is false.
>
> `org.gradle.debug.host=(host address)`
> > Specifies<sub>指定</sub> the host address to listen on or connect to when debug is enabled. \
> > In the server mode on Java 9 and above, passing * for the host will make the server listen on all network interfaces. \
> > By default, no host address is passed to JDWP, so on Java 9 and above, the loopback address is used, while earlier versions listen on all interfaces.
>
> `org.gradle.debug.port=(port number)`
> > Specifies the port number to listen on when debug is enabled. \
> > Default is `5005`.
>
> `org.gradle.debug.server=(true,false)`
> > If set to true and debugging is enabled, Gradle will run the build with the `socket-attach` mode of the debugger. \
> > Otherwise, the `socket-listen` mode is used. \
> > Default is true.
>
> `org.gradle.debug.suspend=(true,false)`
> > When set to true and debugging is enabled, the JVM running Gradle will suspend until a debugger is attached. \
> > Default is true.
>
> `org.gradle.java.home=(path to JDK home)`
> > Specifies the Java home for the Gradle build process. \
> > The value can be set to either a jdk or jre location, however, depending on what your build does, using a JDK is safer. \
> > This does not affect the version of Java used to launch the Gradle client VM ([see Environment variables](https://docs.gradle.org/current/userguide/build_environment.html#sec:gradle_environment_variables)). \
> > A reasonable default is derived from your environment (JAVA_HOME or the path to java) if the setting is unspecified.
>
> `org.gradle.jvmargs=(JVM arguments)`
> > Specifies the JVM arguments used for the Gradle Daemon. \
> > The setting is particularly<sub>尤其；特别</sub> useful for [configuring JVM memory settings](https://docs.gradle.org/current/userguide/build_environment.html#sec:configuring_jvm_memory) for build performance<sub>性能</sub>. \
> > This does not affect the JVM settings for the Gradle client VM. \
> > The default is `-Xmx512m -XX:MaxMetaspaceSize=384m`.
>
> `org.gradle.logging.level=(quiet,warn,lifecycle,info,debug)`
> > When set to quiet, warn, lifecycle, info, or debug, Gradle will use this log level. \
> > The values are not case sensitive<sub>这些值不区分大小写</sub>. [See Choosing a log level](https://docs.gradle.org/current/userguide/logging.html#sec:choosing_a_log_level). \
> > The `lifecycle` level is the default.
>
> `org.gradle.logging.stacktrace=(internal,all,full)`
> > Specifies whether stacktraces<sub>堆栈磁道</sub> should be displayed as part of the build result upon<sub>在...之上</sub> an exception. See also the [--stacktrace command-line option](https://docs.gradle.org/current/userguide/command_line_interface.html#sec:command_line_debugging). \
> > When set to internal, a stacktrace is present<sub>出现</sub> in the output only in case of internal exceptions. \
> > When set to all or full, a stacktrace is present<sub>出现</sub> in the output for all exceptions and build failures. \
> > Using full doesn’t truncate<sub>截断</sub> the stacktrace, which leads to a much more verbose<sub>冗长的</sub> output. \
> > Default is `internal`.
>
> `org.gradle.parallel=(true,false)`
> > When configured, Gradle will fork up<sub>付出；支付</sub> to `org.gradle.workers.max` JVMs to execute projects in parallel. \
> > To learn more about parallel task execution, see [the section on Gradle build performance](https://docs.gradle.org/current/userguide/performance.html#parallel_execution). \
> > Default is false.
>
> `org.gradle.priority=(low,normal)`
> > Specifies the scheduling priority<sub>调度优先级</sub> for the Gradle daemon and all processes launched by it. See also [performance command-line options](https://docs.gradle.org/current/userguide/command_line_interface.html#sec:command_line_performance). \
> > Default is normal.
>
> `org.gradle.vfs.verbose=(true,false)`
> > Configures verbose<sub>冗长的</sub> logging when [watching the file system](https://docs.gradle.org/current/userguide/file_system_watching.html#sec:daemon_watch_fs). \
> > Default is false.
>
> `org.gradle.vfs.watch=(true,false)`
> > Toggles<sub>切换</sub> [watching the file system](https://docs.gradle.org/current/userguide/file_system_watching.html#sec:daemon_watch_fs). \
> > When enabled Gradle re-uses<sub>再利用</sub> information it collects about the file system between builds. \
> > Enabled by default on operating systems where Gradle supports this feature.
>
> `org.gradle.warning.mode=(all,fail,summary,none)`
> > When set to all, summary or none, Gradle will use different warning type display. See [Command-line logging options](https://docs.gradle.org/current/userguide/command_line_interface.html#sec:command_line_logging) for details. \
> > Default is summary.
>
> `org.gradle.welcome=(never,once)`
> > Controls whether Gradle should print a welcome message. \
> > If set to never then the welcome message will be suppressed. \
> > If set to once then the message is printed once for each new version of Gradle. \
> > Default is once.
>
> `org.gradle.workers.max=(max # of worker processes)`
> > When configured, Gradle will use a maximum of the given number of workers. See also [performance command-line options](https://docs.gradle.org/current/userguide/command_line_interface.html#sec:command_line_performance). \
> > Default is number of CPU processors.
>
> example:
> ```properties
> org.gradle.caching=true
> org.gradle.jvmargs=-Xmx2g -XX:MaxMetaspaceSize=512m -XX:+HeapDumpOnOutOfMemoryError -Dfile.encoding=UTF-8
> org.gradle.parallel=true
> ```

[top](#gradle) | [home](index.md#build-tools)
