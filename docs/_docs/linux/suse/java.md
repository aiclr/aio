<div style="text-align: center;font-size: 40px;">opensuse java version</div>

## opensuse java 管理工具 `alternatives --config java`

## example

```shell
caddy@localhost:~> java -version
java version "1.8.0_321"
Java(TM) SE Runtime Environment (build 1.8.0_321-b07)
Java HotSpot(TM) 64-Bit Server VM (build 25.321-b07, mixed mode)
caddy@localhost:~> sudo alternatives --config java
[sudo] root 的密码：
There are 3 choices for the alternative java (providing /usr/bin/java).

  Selection    Path                                    Priority   Status
------------------------------------------------------------
  0            /usr/java/jdk-17.0.2/bin/java            170002000 auto mode
  1            /usr/java/jdk-17.0.2/bin/java            170002000 manual mode
* 2            /usr/java/jdk1.8.0_321-amd64/bin/java    180321    manual mode
  3            /usr/lib64/jvm/jre-11-openjdk/bin/java   2105      manual mode

Press <enter> to keep the current choice[*], or type selection number: 1
update-alternatives: using /usr/java/jdk-17.0.2/bin/java to provide /usr/bin/java (java) in manual mode
caddy@localhost:~> java -version
java version "17.0.2" 2022-01-18 LTS
Java(TM) SE Runtime Environment (build 17.0.2+8-LTS-86)
Java HotSpot(TM) 64-Bit Server VM (build 17.0.2+8-LTS-86, mixed mode, sharing)
```