package org.bougainvilleas.ilg.study.chapter18

import groovy.test.GroovyTestCase
import org.bougainvilleas.ilj.study.chapter18.Car

class CarTest extends GroovyTestCase{
    def car
    /**
     * setUp() 方法 和相应的tearDown() 方法
     * 会将每个测试调用夹在中间，
     * 可以在 setUp() 中初始化对象
     * 可以在 tearDown() 中执行清理或复位操作
     *
     * 可以减少重复代码，帮助隔离测试
     */
    @Override
    void setUp()
    {
        System.err.println("初始化对象")
        car = new Car()
    }

    @Override
    protected void tearDown() throws Exception {
        System.err.println("清理")
    }

    void testInitialize()
    {
        assertEquals 0,car.miles
    }
    void testDrive()
    {
        car.drive(10)
        assertEquals 10,car.miles
    }

    void testDriveNegativeInput()
    {
        car.drive(-10)
        assertEquals 0,car.miles
    }

    def divide(a,b){
        a/b
    }
    void testException()
    {
        try{
            divide(2,0)
            fail "Excepted ArithmeticException ..."
        }catch(ArithmeticException ex){
            assertTrue true
        }
    }

    void testShouldFail(){
        shouldFail{divide(2,0)}
    }
    void testShouldFailEx(){
        shouldFail(ArithmeticException){divide(2,0)}
    }
    void testShouldFailExClosure(){
        shouldFail ArithmeticException,{divide(2,0)}
    }

}
