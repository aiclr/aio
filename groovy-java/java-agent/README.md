# javaagent

> java 解密 class \
> run command `java -javaagent:java-agent.jar="Hi" -jar client.jar` 
> 
> ```text
> ├── java-agent.jar
> ├── META-INF
> │   └── MANIFEST.MF
> └── org
>     └── bougainvilleas
>         └── ilj
>             └── agent
>                 ├── JavaAgent.class
>                 ├── JavaAgent.java
>                 ├── JavaClassFileTransformer.class
>                 └── JavaClassFileTransformer.java
> ```
> 
> 手动 创建 `META-INF/MANIFEST.MF` 注意 `MANIFEST.MF` 需要以空行结尾
> ```manifest
> Manifest-Version: 1.0
> Premain-Class: org.bougainvilleas.ilj.agent.JavaAgent
> 
> 
> ```
> 
> 手动编译 `javac org/bougainvilleas/ilj/agent/*.java` \
> 手动打包 `jar -cvf0M java-agent.jar META-INF/MANIFEST.MF org/bougainvilleas/ilj/agent/*.class`
> > `-0` 表示压缩 \
> > `-M` 表示不自动创建 `MANIFEST.MF`
> 
> 利用 gradle build.gradle 添加 `Premain-Class`

## build.gradle

```groovy
jar.manifest {
    attributes('Premain-Class':"org.bougainvilleas.ilj.agent.JavaAgent")
}
```
