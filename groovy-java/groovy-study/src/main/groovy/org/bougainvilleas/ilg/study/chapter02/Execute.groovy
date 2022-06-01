package org.bougainvilleas.ilg.study.chapter02

/**
 * 当调用text 时，我们是在调用Groovy在Process 类上添加的getText() 方法，
 * 其功能是将该进程的整个标准输出读到一个String 对象中
 * 如果只是想等待进程结束，
 * waitFor() 或Groovy添加的waitForOrKill() 方法（该方法接受一个以毫秒表示的超时值）会有所帮助
 */
println("java --version".execute().text)
/**
 * linux java.lang.UNIXProcess
 * Windows java.lang.ProcessImpl
 */
println("java --version".execute().getClass().name)