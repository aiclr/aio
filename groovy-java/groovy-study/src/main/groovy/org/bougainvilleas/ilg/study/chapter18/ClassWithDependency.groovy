package org.bougainvilleas.ilg.study.chapter18

import groovy.test.GroovyTestCase

/**
 * 例子 methodA 依赖一个文件 file 使得单元测试很难编写
 * 如下 模拟这个file 对象
 * Expando 只能帮助处理 methodA
 *
 * methodB 和 methodC 内部实例化了 FileWriter
 */
class ClassWithDependency {
    /**
     * 使用 File write() 向给定文件对象中写入一条消息
     * 测试思路 不真正写入物理文件，再读取 断言
     *
     * 利用 groovy 动态类型 file 未指明类型，
     * 因此可以发送任何对象，只要具备预期参数的能力（即拥有预期参数所拥有的方法）
     */
    def methodA(val, file) {
        file.write "The value is ${val}."
    }

    def methodB(val) {
        def file = new java.io.FileWriter("output.txt")
        file.write "The value is ${val}."
    }

    def methodC(val) {
        def file = new java.io.FileWriter("output.txt")
        file.write "The value is ${val}."
        file.close()
    }
}

class TestWithExpando extends GroovyTestCase{
    void testMethodA(){
        def testObj=new ClassWithDependency()
        def fileMock=new HandTossedFileMock()
        testObj.methodA(1,fileMock)
        assertEquals "The value is 1.",fileMock.result
    }
}
/**
 * 模拟 file 对象
 */
class HandTossedFileMock{
    def result
    def write(value) {result=value}
}


/**
 * Expando 更简便写法
 * 当向被测方法传递依赖对象时，Expando 很有用
 * 然而 如果方法会在内部创建依赖的对象 如methodB methodC 将于事无补
 *
 */
class TestUsingExpando extends GroovyTestCase{
    void testMethodA(){
        /**
         * 模拟 file 对象
         */
        def fileMock=new Expando(text:'',write:{text=it})
        def testObj = new ClassWithDependency()
        testObj.methodA(1,fileMock)
        assertEquals "The value is 1.",fileMock.text
    }
}

/**
 * 使用Map 模拟对象 与 Expando 类似
 * 然而 如果方法会在内部创建依赖的对象 如methodB methodC 将于事无补
 */
class TestUsingMap extends GroovyTestCase{
    void testMethodA()
    {
        def text=''
        def fileMock=[write:{text=it}]
        def testObj=new ClassWithDependency()
        testObj.methodA(1,fileMock)
        assertEquals "The value is 1.",text
    }
}

/**
 * 使用 StubFor 为 File 创建存根
 * 创建 StubFor 时 提供想创建存根的类 FileWriter
 * 之后为 writer() 方法的存根实现创建闭包
 * fileMock.use 此时会将 FileWriter 的 MetaClass 替换为一个 ProxyMetaClass
 * 在 use后的闭包内，对FileWriter 实例的任何调用都会被路由到该存根
 * 然而 存根和模拟不会帮助拦截器对构造器的调用，下面的例子会在磁盘创建 output.txt 文件
 *
 * StubFor 帮助测试的是 methodB() 是否创建了一个正常的FileWriter 实例，并将期望的内容写入该实例
 * 不过有局限性，没有测试当关闭文件时，这个方法的表现是否正常
 * 即使存根上要求了close() 方法，fileMock.demand.close{}
 * 也不会检查该方法是否真的被调用 methodB 没调用 close() 方法
 * 存根只能简单地代替协作者并验证状态
 *
 * 要验证行为，必须使用模拟
 * 使用 MockFor 类
 */
class TestUsingStubFor extends GroovyTestCase{

    void testMethodB(){
        def testObj=new ClassWithDependency()
        def fileMock=new groovy.mock.interceptor.StubFor(java.io.FileWriter)
        def text
        fileMock.demand.write{text=it.toString()}
        fileMock.demand.close{}
        fileMock.use{
            testObj.methodB(1)
        }
        assertEquals "The value is 1.",text
    }
}

/**
 * methodB 没调用 close() 测试会失败
 * 与存根不同，模拟指出 纵然代码产生了指定结果，但是表现与预期不符
 * methodB 没有调用 demand在测试预期中设置的 close() 方法
 * junit.framework.AssertionFailedError: verify[1]: expected 1..1 call(s) to 'close' but was called 0 time(s).
 */
class TestMethodBUsingMock extends GroovyTestCase{
    void testMethodC(){
        def testObj=new ClassWithDependency()
        def fileMock=new groovy.mock.interceptor.MockFor(java.io.FileWriter)
        def text
        fileMock.demand.write{text=it.toString()}
        fileMock.demand.close{}
        fileMock.use{
            testObj.methodB(1)
        }
        assertEquals "The value is 1.",text
    }
}
/**
 * methodC 调用 close() 测试通过
 */
class TestMethodCUsingMock extends GroovyTestCase{
    void testMethodC(){
        def testObj=new ClassWithDependency()
        def fileMock=new groovy.mock.interceptor.MockFor(java.io.FileWriter)
        def text
        fileMock.demand.write{text=it.toString()}
        fileMock.demand.close{}
        fileMock.use{
            testObj.methodC(1)
        }
        assertEquals "The value is 1.",text
    }
}


