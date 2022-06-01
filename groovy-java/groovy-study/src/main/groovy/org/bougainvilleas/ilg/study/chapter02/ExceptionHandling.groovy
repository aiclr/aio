package org.bougainvilleas.ilg.study.chapter02

/**
 * openFile() 方法没有处理声名狼藉的FileNotFoundException 异常。
 * 如果产生了该异常，它并不会被压制下来。相反，它会被传递给调用代码，由调用代码来处理
 */
def openFile(fileName)
{
    new FileInputStream(fileName)
}
try
{
    new ExceptionHandling().openFile("nofile")
}catch(ex)
{
    /**
     * 需要用户知道此处会有异常
     * 利用catch(ex) （变量ex 前面没有任何类型）可以捕获摆在我们面前的任何异常
     * 注意：
     *      它不能捕获Exception 之外的Error 或Throwable 。
     *      要捕获Error 或Throwable，请使用catch(Throwable throwable)
     *
     */
    println "Oops:"+ex
}