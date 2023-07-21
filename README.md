![jekyll-gh-pages](https://github.com/bougainvilleas/aio/actions/workflows/jekyll-gh-pages.yml/badge.svg?branch=snapshot&event=pull_request)
![jekyll-gh-pages](https://github.com/bougainvilleas/aio/actions/workflows/jekyll-gh-pages.yml/badge.svg?branch=snapshot)
![jekyll-gh-pages](https://github.com/bougainvilleas/aio/actions/workflows/jekyll-gh-pages.yml/badge.svg?branch=feature)

# aio

[:compass:](https://bougainvilleas.github.io/lotus/)

> all in one \
> gradle 8.0 includeBuild 支持复合项目 \
> 相互依赖模块可以不发布到本地maven仓库进行关联
> > 例如：spring-demo下的jpa依赖 spring-dependency 部分模块
> > > 在目录 `spring-demo/` 下执行build `gradle --include-build ../spring-dependency :jpa:build` \
> > > 在目录 `spring-demo/jpa` 下执行build `gradle --include-build ../../spring-dependency build`

## [C](c/README.md)

> [《C Primer Plus 第6版 中文版》](c/cprimerplus/README.md) \
> [Linux程序设计 第4版](c/linuxprogramming/README.md) \
> [GTK4](c/gtk4/README.md) \
> [CMake](c/cmake/README.md) \
> [C实现简单工厂模式](c/cmake/simple_factory/README.md)

## [algorithms](algorithms/README.md)

> 算法学习，并使用c、go、groovy实现

## JVM

> [java 虚拟机](jvm/README.md)

## java

> [java9 模块化](java-modular) \
> [gradle 管理 java9 模块](java-modular-gradle/java-modular-svc/README.md) \
> [《改善Java程序的151个建议》笔记](java-151/README.md) \
> [java IO NIO](java-io/README.md) \
> [java juc 多线程](java-juc/README.md) \
> [设计模式 java](java-design-pattern/README.md) \
> [数据结构与算法 java](java-data-structure-algorithm/README.md) \

## [Groovy](groovy-java/README.md)

> [《Groovy 程序设计》](groovy-java/groovy-study/README.md) \
> [Groovy 官网设计模式](groovy-java/groovy-design-pattern/README.md) \
> [javaagent 解密 JAVASE jar](groovy-java/java-agent/README.md) \
> gradle 插件开发
> > [gradle-plugins](groovy-java/gradle-plugins/README.md) \
> > [gradle-tasks](groovy-java/gradle-tasks/README.md)

## [react](react/README.md)

> [官网文档](https://reactjs.org/tutorial/tutorial.html)

## [go](go/README.md)

> [官网](https://go.dev)

## [rust](rust/README.md)

> [官网](https://www.rust-lang.org)
