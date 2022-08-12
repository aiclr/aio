package org.bougainvilleas.ilg.study.chapter18

import groovy.test.GroovyTestCase

/**
 * 被测方法有多个模拟对象的实例
 * 这个模拟就会代表所有实例，但是必须为每个实例创建预期要求
 */
class TwoFileUser {
    def useFiles(str) {
        def file1 = new java.io.FileWriter("output1.txt")
        def file2 = new java.io.FileWriter("output2.txt")
        file1.write str
        file2.write str.size()
        file1.flush()
        file2.flush()
        file1.close()
        file2.close()
    }

    /**
     * 多次调用
     */
    def someWriter() {
        def file = new FileWriter('output.txt')
        file.write("one")
        file.write("two")
        file.write(3)
        file.flush()
        file.write(file.getEncoding())
        file.close()
    }
}

class TwoFileUserTest extends GroovyTestCase {

    void testUseFiles() {
        def testObj = new TwoFileUser()
        def testData = 'Multi Files'
        def fileMock = new groovy.mock.interceptor.MockFor(java.io.FileWriter)
        /**
         * 模拟记录了方法被调用的次序和次数
         * 如果被测代码没有满足所要求的预期，模拟将抛出异常，测试失败
         */
        fileMock.demand.write() { assertEquals testData, it }
        fileMock.demand.write() { assertEquals testData.size(), it }
        /**
         * 第一个2表示 预期该方法至少被调用2次
         * 第二个2表示 预期该方法最多被调用2次
         */
        fileMock.demand.flush(2..2) {}
        fileMock.demand.close(2..2) {}

        fileMock.use {
            testObj.useFiles(testData)
        }
    }

    
    /**
     * 指定多次调用功能
     */
    void testSomeWriter() {
        def testObj = new TwoFileUser()
        def params=['one','two',3]
        def index=0
        def fileMock = new groovy.mock.interceptor.MockFor(java.io.FileWriter)
        //0..3 表示最多调用三次
        fileMock.demand.write(3..3) {
            assert it==params[index++]
        }
        fileMock.demand.flush {}
        fileMock.demand.getEncoding { return "whatever" }//return 是可选的
        fileMock.demand.write { assertEquals 'whatever', it.toString() }
        fileMock.demand.close {}
        fileMock.use {
            testObj.someWriter()
        }
    }



    /**
     * MockFor 无法阻止构造器运行，因此在运行测试时，会有两个 文件被创建
     * 此处清理文件 清理文件失败了
     */
    void tearDown() {
        new File('output1.txt')?.delete()
        new File('output2.txt')?.delete()
        new File('output.txt').delete()
    }

    /**
     * 删除临时文件  tearDown 不好使
     */
    void testDel() {
        def file1 = new File('output1.txt')
        file1.delete()
        def file2 = new File('output2.txt')
        file2.delete()
        new File('output.txt').delete()
    }
}


