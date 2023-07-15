---
title: windows
targets:
  - name: Top
    link: spring-framework
  - name: 5.3.x
    link: 53x
---
## spring-framework

> fork into my github
> 下载源码 `git clone git@github.com:bougainvilleas/spring-framework.git`
> 切换到想编译的分支 `git checkout -b 5.3.x origin/5.3.x`


## 5.3.x

> [编译源码官方文档](https://github.com/spring-projects/spring-framework/wiki/Build-from-Source)\
> env:
> > archlinux \
> > git \
> > jdk / openjdk 11 \
> > idea 2022.2

> 直接运行 `./gradlew build` 不出意外的话会出现下面列出的意外 \
> 解决问题后使用 `idea`  打开项目

### error

> `./gradlew clean build`

```text
spring-framework/spring-core/src/main/java/org/springframework/core/CoroutinesUtils.java:74: warning: [deprecation] isAccessible() in AccessibleObject has been deprecated
                if (method.isAccessible() && !KCallablesJvm.isAccessible(function)) {
                          ^
error: warnings found and -Werror specified
1 error
1 warning
```

> 解决方案 添加 `@SuppressWarnings("deprecation")`
> > `vim spring-core/src/main/java/org/springframework/core/CoroutinesUtils.java`

```java
	/**
	 * Invoke a suspending function and converts it to {@link Mono} or
	 * {@link Flux}.
	 */
	@SuppressWarnings("deprecation")
	public static Publisher<?> invokeSuspendingFunction(Method method, Object target, Object... args) {
		KFunction<?> function = Objects.requireNonNull(ReflectJvmMapping.getKotlinFunction(method));
		if (method.isAccessible() && !KCallablesJvm.isAccessible(function)) {
			KCallablesJvm.setAccessible(function, true);
		}
        //......
    }

```
