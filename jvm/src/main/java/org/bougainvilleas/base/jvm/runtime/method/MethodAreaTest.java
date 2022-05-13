package org.bougainvilleas.base.jvm.runtime.method;

/**
 * 静态变量 non-final变量
 */
public class MethodAreaTest {

    public static void main(String[] args) {
        Order order=null;
        order.hello();
        System.out.println(order.count);
    }
}

class Order{
    //
    public static int count=1;
    //static final 全局常量 在编译时就会赋值,javap -v Order.class > test.txt 查看验证
    public static final int number=2;
    public static void hello(){
        System.out.println("hello");
    }
}
