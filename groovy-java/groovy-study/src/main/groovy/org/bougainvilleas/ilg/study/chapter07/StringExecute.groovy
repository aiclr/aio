package org.bougainvilleas.ilg.study.chapter07
/**
 * String execute()
 * String[] 也支持 execute() 方法 数组的第一个元素会被当做要执行的命令
 *      其余元素则被视作该命令的参数
 *      作为替代 可以使用 List 的execute() 方法
 *
 *
 * wc 是类Unix 系统上一个流行的实用程序 向标准输出打印从其标准输入中发现的单词数、行数和字符数
 [fly@ThinkServer samba]$ wc StringExecute.groovy
 12  20 281 StringExecute.groovy
 [fly@ThinkServer samba]$ cat StringExecute.groovy |wc
 12      20     281
 */
//通过 String的 execute() 获取一个进程实例
process = "wc".execute()
//向wc 标准输入 stdin 写入内容 需要OutputStream
//通过 out 属性从进程获取要写入的内容 可以使用 << 操作符
//一旦把数据写入流中，使用 withWriter 刷新flush并关闭流
process.out.withWriter {
    //将输入发送到进程
    it << "Let the World know...\n"
    it << "Groovy Rocks!\n"
}
//从进程读取输入
println process.in.text
//或
//println process.text

//不好使
String[] command =['groovy','-e','"print \'Groovy\'"']
println "Calling ${command.join(' ')}"
println command.execute().text


/**
 * start() 启动一个非守护线程 并为其提供一个将在单独线程中执行的闭包
 * startDaemon() 启动一个守护线程 daemon thread
 * 如果当前已经没有活跃的非守护线程，守护线程会退出
 *
 *
 * 主线程与创建的非守护线程一退出 守护线程就被中止了
 *
 Main Thread is Thread[main,5,main]. Daemon?false
 Started Thread is Thread[Thread-1,5,main]. Daemon?false
 Started Daemon Thread is Thread[Thread-2,5,main]. Daemon?true
 Finished Started
 */
def printThreadInfo(msg){
    def currentThread=Thread.currentThread()
    println "$msg Thread is ${currentThread}. Daemon?${currentThread.isDaemon()}"
}

printThreadInfo 'Main'
Thread.start{
    printThreadInfo"Started"
    sleep(3000){println "Interrupted"}
    println "Finished Started"
}
sleep(1000)

Thread.startDaemon {
    printThreadInfo "Started Daemon"
    sleep(5000) {println "Interrupted"}
    println "Finished Started Daemon"//不会执行到这里
}


