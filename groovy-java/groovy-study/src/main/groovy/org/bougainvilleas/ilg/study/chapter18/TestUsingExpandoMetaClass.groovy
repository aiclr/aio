package org.bougainvilleas.ilg.study.chapter18

import groovy.test.GroovyTestCase

class TestUsingExpandoMetaClass extends GroovyTestCase{
    void testMyMethod()
    {
        def result
        def emc=new ExpandoMetaClass(CodeWithHeavierDependencies)
        emc.println={text->result=text}
        emc.someAction={->25}
        emc.initialize()

        def testObj = new CodeWithHeavierDependencies()
        testObj.metaClass=emc//设置 ExpandoMetaClass
        /**
         * 当 myMethod() 调用 println() 和 someAction() 时
         * ExpandoMetaClass 会拦截 这些调用  并将其路由到
         *  emc.println={text->result=text}
         *  emc.someAction={->25}
         */
        testObj.myMethod()
        assertEquals 35,result
    }
}