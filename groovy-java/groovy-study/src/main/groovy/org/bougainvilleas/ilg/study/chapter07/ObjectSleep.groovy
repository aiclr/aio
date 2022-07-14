package org.bougainvilleas.ilg.study.chapter07

/**
 * Object sleep() 方法在给定的毫秒时间内睡眠时，该方法会忽略 中断 interrupt
 * 如下 在不同 的线程执行一段代码
 * 在Object 上调用 sleep() 和使用java提供的Thread.sleep() 区别在于
 *      如果有 InterruptedException 前者会压制下来 故不会中断
 *      如果想被中断 不必使用 try-catch 可以在sleep()方法上使用一种变种版本 它接受一个处理中断的闭包
 */
println "Thread 忽略中断"
thread=Thread.start{
    println "Thread started"
    startTime=System.nanoTime()
    new Object().sleep(2000)
    endTime=System.nanoTime()
    println "Thread done in ${(endTime-startTime)/10**9} seconds"
}
new Object().sleep(100)
println "Let's interrupt that thread"
thread.interrupt()
thread.join()


/**
 * 在中断处理器内可以采取任何适当的动作
 * 如果在闭包内返回 false sleep()将继续 就好像没有被中断
 */
def playWithSleep(flag)
{
    thread = Thread.start {
        println 'Thread start'
        startTime=System.nanoTime()
        new Object().sleep(2000){
            println "Interrupted..."+it
            flag
        }
        endTime=System.nanoTime()
        println "Thread done in ${(endTime-startTime)/10**9}"
    }
    thread.interrupt()
    thread.join()
}
println "Thread 未忽略中断"
playWithSleep(true)
println "Thread 好像没有被中断 实际未忽略中断，"
playWithSleep(false)
