package org.bougainvilleas.ilg.designpattern

/**
 * Loan(借出，贷与，出借) my Resource pattern
 * 出借我的资源模式
 */
def f = new File('junk.txt')
// 往文件写入几行数据
f.withPrintWriter { pw ->
    pw.println(new Date())
    pw.println(this.class.name)
}
// 打印文件大小
println f.size()
//一次读取一行
f.eachLine { line ->
    println line
}
/**
 * Groovy使用了普通的Java Reader和PrintWriter对象
 * 代码编写器不必担心显式地创建或关闭这些资源
 * 内置的Groovy方法将相应的读取器或写入器借给闭包代码，然后自己整理
 */
def reader = f.newReader()
/**
 * 处理文件中每一行的单词列表
 * 可以使用Groovy的内置函数来实现这一点
 * 也可以自己进行一些资源处理
 */
reader.splitEachLine(' ') { wordList ->
    println wordList
}
/**
 * Notice that we now have an explicit call to close() in our code.
 * If we didn’t code it just right (here we didn’t surround the code in a try … finally block)
 * we run the risk of leaving the file handle open.
 * 如果不用 try-finally 有资源泄漏风险
 */
reader.close()

/**
 * try-finally 重写上方的有风险的代码
 */
def withListOfWordsForEachLine(File f, Closure c) {
    def r = f.newReader()
    try {
        r.splitEachLine(' ', c)
    } finally {
        r?.close()
    }
}

withListOfWordsForEachLine(f) { wordList ->
    println wordList
}