# spring-dependence & log & actuator

## 注意
```text
jar归io.spring.dependency-management管理时
模块间依赖传递会以io.spring.dependency-management版本为准
```

## usage

1. build.gradle

> 可以依赖 模块`implementation(project(":spring-depency"))`
>
> 可以发布到maven 依赖 jar `implementation("org.bougainvilleas.spring:spring-depency:${projectVersion}")`

2. application.yml

```yaml
spring:
  profiles:
    active: base
```