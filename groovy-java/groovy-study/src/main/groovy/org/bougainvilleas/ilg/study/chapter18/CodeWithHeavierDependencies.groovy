package org.bougainvilleas.ilg.study.chapter18

import groovy.test.GroovyTestCase
import org.bougainvilleas.ilj.study.chapter18.CodeWithHeavierDependenciesJava

class CodeWithHeavierDependencies {
    public void myMethod() {
        def value = someAction() + 10
        println(value)
    }

    int someAction() {
        Thread.sleep(5000)//模拟消耗时间的动作
        return Math.random() * 100//模拟某个动作的结果
    }
}

/**
 * 新建用于模拟的 新类
 */
class CodeWithHeavierDependenciesExt extends CodeWithHeavierDependencies{
    def result
    int someAction(){25}
    /**
     * groovy 用println() 代替 System.out.println()
     * 这里提供一个局部的 println() 实现
     */
    def println(text){result=text}
}

class TestCodeWithHeavierDependenciesUsingOverriding extends GroovyTestCase {
    void testMyMethod()
    {
        def testObj=new CodeWithHeavierDependenciesExt()
        testObj.myMethod()
        assertEquals 35,testObj.result
    }
}

/**
 * 测试java代码
 */
class ExtendedJavaCode extends CodeWithHeavierDependenciesJava{
    int someAction(){25}
}

/**
 * 扩展 PrintStream 替换掉 System.out
 * 帮助拦截 System.out.println() 调用
 */
class PrintMock extends PrintStream{
    PrintMock(){super(System.out)}
    def result
    void println(int text){result=text}
}

class TestCodeWithHeavierDependenciesUsingOverridingJava extends GroovyTestCase{
    void testMyMethod()
    {
        def testObj=new ExtendedJavaCode()
        def originalPrintStream=System.out
        def printMock=new PrintMock()
        /**
         * 将 printMock 赋给 System.out
         */
        System.out=printMock
        try{
            /**
             * 内部 的 System.out.println(value);
             * 等价于 printMock.println(value);
             */
            testObj.myMethod()
        }finally{System.out=originalPrintStream}
        assertEquals 35,printMock.result
    }
}

