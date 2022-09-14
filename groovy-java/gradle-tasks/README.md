# [A standalone project](https://docs.gradle.org/current/userguide/custom_tasks.html#sec:custom_tasks_standalone_project)

## Using a custom task in another project

> 参考 java-app
> build.gradle
> ```groovy
> buildscript {
>     repositories {
>         mavenLocal()
>         maven {
>             url 'https://maven.aliyun.com/repository/public/'
>         }
>         mavenCentral()
>     }
>     dependencies {
>         classpath("org.bougainvilleas:gradle-tasks:0.0.1")
>     }
> }
> 
> 
> tasks.register('greeting2',org.bougainvilleas.ilg.task.GreetingTask){
>     greeting ='howdy!'
> }
> ```