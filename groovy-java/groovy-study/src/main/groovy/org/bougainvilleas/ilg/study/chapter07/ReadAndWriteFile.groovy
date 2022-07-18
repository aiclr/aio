package org.bougainvilleas.ilg.study.chapter07

//把文件全部内容读到一个String中
println '把文件全部内容读到一个String中'
println new File('./ObjectDump.groovy').text

//按行读取
println '按行读取'
new File('./ObjectInspect.groovy').eachLine {println it}

//按行读取
println '筛选出包含 print 的行'
println new File('./ObjectInspect.groovy').filterLine { it=~/print/}

//withStream() 自动刷新并关闭一个输入流：将InputStream 实例作为参数发送给闭包
//当从闭包返回，就会刷新并关闭这个 InputStream
//Writer 有一个类似的方法 withWriter()

// InputStream 的 withReader() 会创建一个 BufferedReader 被辅导输入流上，将其传给作为参数接受的闭包
// 也可以通过调用 newReader() 方法获取一个新的 BufferedReader 实例

//对于 InputStream和DataInputStream中的输入，可以通过 iterator() 方法获得一个Iterator，然后使用该迭代器对输入进行迭代
//也可以对ObjectInputStream中的对象进行迭代

//groovy 向文件或流写入内容
//OutputStream ObjectOutputStream Writer类都通过 leftShift() 方法(<< 操作符)
println '写入内容'
new File("/tmp/temp.txt").withWriter {it<<'some data...'}