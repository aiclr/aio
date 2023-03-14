# aio

> all in one \
> gradle 8.0 includeBuild 支持复合项目 \
> 相互依赖模块可以不发布到本地maven仓库进行关联
> > 例如：spring-demo下的jpa依赖 spring-dependency 部分模块
> > > 在目录 `spring-demo/` 下执行build `gradle --include-build ../spring-dependency :jpa:build` \
> > > 在目录 `spring-demo/jpa` 下执行build `gradle --include-build ../../spring-dependency build` 

## [C](c/README.md)

> [c primer plus](c/cprimerplus/README.md) \
> [Linux程序设计](c/linuxprogramming/README.md)

## java-modular

> java9 模块化

## [java-modular-gradle](java-modular-gradle/java-modular-svc/README.md)

> gradle manage java9 modular

## [java-151](java-151/README.md)

> 《改善Java程序的151个建议》笔记

## [java-io](java-io/README.md)

> java IO NIO

## [java-juc](java-juc/README.md)

> java juc 多线程

## [java-design-pattern](java-design-pattern/README.md)

> 设计模式 java 实现

## [java-data-structure-algorithm](java-data-structure-algorithm/README.md)

> java 数据结构与算法

## [jvm](jvm/README.md)

> java 虚拟机

## [react](react/README.md)

> [官网文档](https://reactjs.org/tutorial/tutorial.html)

## [go](go/README.md)

> [官网](https://go.dev)

## [rust](rust/README.md)

> [官网](https://www.rust-lang.org)
