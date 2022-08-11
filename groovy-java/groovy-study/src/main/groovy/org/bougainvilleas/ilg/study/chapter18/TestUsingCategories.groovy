package org.bougainvilleas.ilg.study.chapter18

import groovy.test.GroovyTestCase

/**
 * MockHelper 两个静态方法，分别对应 someAction() 和 println()
 */
class MockHelper{
    def static result
    def static println(self,text){result=text}
    def static someAction(CodeWithHeavierDependencies self){25}
}
/**
 * 通过 use(MockHelper) 让分类拦截对方法的调用
 * 在适当的情况下会将 CodeWithHeavierDependencies 内的 someAction() 和 println()
 * 替换 为 MockHelper下的 someAction() 和 println()
 */
class TestUsingCategories extends GroovyTestCase{
    void testMyMethod()
    {
        def testObj = new CodeWithHeavierDependencies()
        use(MockHelper){
            testObj.myMethod()
            assertEquals 35,MockHelper.result
        }
    }
}
