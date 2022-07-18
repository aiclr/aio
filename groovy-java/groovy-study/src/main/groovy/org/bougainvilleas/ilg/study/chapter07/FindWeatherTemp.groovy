package org.bougainvilleas.ilg.study.chapter07


/**
 * 扩展模块类
 * 在编译时向现有类添加实例方法或静态方法，并在运行时应用他们
 * 1. 添加的方法必须定义在扩展模块类中（扩展方法必须 static）
 * 2. 需要manifest放一些描述信息，告诉groovy编译器要查找的扩展模块
 *
目录结构
 声明文件
 groovy/manifest/META-INF/services/org.codehaus.groovy.runtime.ExtensionModule
 扩展模块类
 groovy/org/bougainvilleas/ilg/study/chapter07/extension/TempExtension.groovy
 groovy/org/bougainvilleas/ilg/study/chapter07/extension/TempStaticExtension.groovy

 在 groovy 路径下执行 groovyc 编译 会创建 classes 字节码文件夹
 groovyc -d classes org/bougainvilleas/ilg/study/chapter07/extension/*.groovy
 在 groovy 路径下打包 会创建 weatherExtensions.jar
 jar -cf weatherExtensions.jar -C classes org -C manifest .

 在 groovy 路径下执行测试脚本
 groovy -classpath weatherExtensions.jar org/bougainvilleas/ilg/study/chapter07/FindWeatherTemp.groovy

 *
 */
def ticker="101010100"//城市代码

println "Temperature for ${ticker} using instance methos is ${String.getTemp(ticker)}"
println "Temperature for ${ticker} using static method is ${ticker.getTemp()}"