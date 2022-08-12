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
        file.write "The value is ${val}"
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
