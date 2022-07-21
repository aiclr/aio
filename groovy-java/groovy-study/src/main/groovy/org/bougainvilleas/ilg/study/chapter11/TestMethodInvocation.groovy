package org.bougainvilleas.ilg.study.chapter11

import groovy.test.GroovyTestCase

/**
 * 运行方式
 * 需要 手动执行 groovy TestMethodInvocation.groovy
 */
class TestMethodInvocation extends GroovyTestCase {
    /**
     * POJO 调用 metaClass 修改后的 toString
     */
    void testInterceptedMethodCallonPOJO() {
        def val = new Integer(3)
        Integer.metaClass.toString = { -> 'intercepted' }
        assertEquals "intercepted", val.toString()
    }
    /**
     * POGO {@link AnInterceptable#invokeMethod}
     */
    void testInterceptableCalled() {
        def obj = new AnInterceptable()
        assertEquals 'intercepted', obj.existingMethod()
        assertEquals 'intercepted', obj.nonExistingMethod()
    }

    /**
     * POGO 调用 metaClass 修改后的 existingMethod2
     */
    void testInterceptedExistingMethodCalled() {
        AGroovyObject.metaClass.existingMethod2 = { -> 'intercepted' }
        def obj = new AGroovyObject()
        assertEquals 'intercepted', obj.existingMethod2()
    }

    /**
     * POGO 调用 {@link AGroovyObject#existingMethod}
     */
    void testUnInterceptedExistingMethodCalled() {
        def obj = new AGroovyObject()
        assertEquals 'existingMethod', obj.existingMethod()
    }

    /**
     * POGO 调用闭包 {@link AGroovyObject#closureProp}
     */
    void testPropertyThatIsClosureCalled() {
        def obj = new AGroovyObject()
        assertEquals 'closure called', obj.closureProp()
    }

    void testMethodMissingCalledOnlyForNonExistent() {
        def obj = new ClassWithInvokeAndMissingMethod()
        /**
         * POGO 调用闭包 {@link ClassWithInvokeAndMissingMethod#methodMissing}
         */
        assertEquals 'existingMethod', obj.existingMethod()
        /**
         * POGO 调用闭包 {@link ClassWithInvokeAndMissingMethod#methodMissing}
         */
        assertEquals 'missing called', obj.nonExistingMethod()
    }

    void testInvokeMethodCalledForOnlyNonExistent() {
        def obj = new ClassWithInvokeOnly()
        /**
         * POGO 调用闭包 {@link ClassWithInvokeOnly#existingMethod}
         */
        assertEquals 'existingMethod', obj.existingMethod()
        /**
         * POGO 调用闭包 {@link ClassWithInvokeOnly#invokeMethod}
         */
        assertEquals 'invoke called', obj.nonExistingMethod()
    }
    /**
     * POGO 调用不存在方法 且未实现invokeMethod()
     * invokeMethod()默认实现会抛出 MissingMethodException异常，说明调用失败
     * {@link TestMethodInvocation#invokeMethod}的默认实现
     */
    void testMethodFailsOnNonExistent() {
        def obj = new TestMethodInvocation()
        shouldFail(MissingMethodException) { obj.nonExistingMethod() }
    }

}

class AnInterceptable implements GroovyInterceptable {
    def existingMethod() {}

    def invokeMethod(String name, args) { 'intercepted' }
}

class AGroovyObject {
    def existingMethod() { 'existingMethod' }
    def existingMethod2() { 'existingMethod2' }
    def closureProp = { 'closure called' }
}

class ClassWithInvokeAndMissingMethod {
    def existingMethod() { 'existingMethod' }

    def invokeMethod(String name, args) { 'invoke called' }

    def methodMissing(String name, args) { 'missing called' }
}

class ClassWithInvokeOnly {
    def existingMethod() { 'existingMethod' }

    def invokeMethod(String name, args) { 'invoke called' }
}