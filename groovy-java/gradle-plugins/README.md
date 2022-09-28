# [gradle plugins](https://docs.gradle.org/current/userguide/custom_plugins.html#sec:custom_plugins_standalone_project)

## publish local maven

1. `publishToMavenLocal`
2. `publishSimplePluginPluginMarkerMavenPublicationToMavenLocal`

## use

> edit root project's `settings.gradle`
> ```groovy
> pluginManagement {
>   repositories {
>     //本地仓库
>     mavenLocal()
>     //gradle 默认 plugin 仓库
>     gradlePluginPortal()
>   }
>   plugins {
>     id 'org.bougainvilleas.java.encrypt' version '0.0.1'
>   }
> }
>```
>
> `standalone project` edit `gradle.build`
> ```groovy
> plugins {
>    id 'org.bougainvilleas.java.encrypt' version '0.0.1'
> }
> 
> classEncrypt {
>     packages = 'org.bougainvilleas.java'
>     password = 'AAABBB'
>     def lst = new ArrayList<String>()
>     lst.add("Skip1.class")
>     lst.add("Skip2.class")
>     skip = lst
> }
> 
> classes.doLast{
>     //MARK 使用命令行参数 传入加密byte
>     //gradle :java-client:build -Ppassword=aaaccc
>     if(this.getProject().getProperties().containsKey('key'))
>     classEncrypt.password="${password}"
>     encrypt.encryptClass()
> }
> ```
>
> `multi-project` add `buildSrc/xxx.gradle`
> ```groovy
> plugins {
>     id 'java'
>     id 'org.bougainvilleas.java.encrypt'
> }
> 
> classEncrypt {
>     packages = 'org.bougainvilleas.java'
>     password = 'AAABBB'
>     def lst = new ArrayList<String>()
>     lst.add("Skip1.class")
>     lst.add("Skip2.class")
>     skip = lst
> }
> 
> classes.doLast{
>     //MARK 使用命令行参数 传入加密byte
>     //gradle :java-client:build -Ppassword=aaaccc
>     if(this.getProject().getProperties().containsKey('key'))
>     classEncrypt.password="${password}"
>     encrypt.encryptClass()
> }
> ```