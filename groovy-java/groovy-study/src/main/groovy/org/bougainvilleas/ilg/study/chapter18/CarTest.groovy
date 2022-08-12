package org.bougainvilleas.ilg.study.chapter18

import groovy.test.GroovyTestCase
import org.bougainvilleas.ilj.study.chapter18.Car

import java.util.regex.Pattern

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

    String formatsss(String format,String source){
        String result="";
        if(source!=null && !"".equals(source)){
            result=source;
            try{
                if(format==null || "".equals(format))
                    return source;
                result=String.format(format,Integer.parseInt(source,10));
            }catch (NumberFormatException | IllegalFormatException exception)
            {
                //不是整数 转换失败 返回 原始数据
                //格式化 失败 返回 原始数据
            }
        }
        return result;
    }

    void testFormat()
    {
       assertEquals '013',formatsss("%03d","13")
       assertEquals 'ww3',formatsss("%03d","ww3")
       assertEquals '13',formatsss("","13")
       assertEquals '13',formatsss("%03s","13")
       assertEquals '123',formatsss("123","13")
       assertEquals '',formatsss("%03s",null)

        System.err.println(String.format("123",13))
        Pattern pattern=Pattern.compile('^[1-9]\\d*$');
        assertTrue(pattern.matcher('123').matches())
        assertFalse(pattern.matcher('ss').matches())
        assertFalse(pattern.matcher('-123').matches())

    }

}
