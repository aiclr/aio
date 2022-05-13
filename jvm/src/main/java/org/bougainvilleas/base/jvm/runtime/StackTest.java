package org.bougainvilleas.base.jvm.runtime;

/**
 * 方法中定义的局部变量（local variable）是否线程安全
 */
public class StackTest {


    /**
     * s1声明方式线程安全
     * s1在内部消亡被GC
     */
    public static void method1(){
        StringBuilder s1=new StringBuilder();
        s1.append("a");
        s1.append("b");
    }

    /**
     * s2可能被其他线程使用
     * s2不一定在当前线程消亡
     * @param s2 线程不安全
     */
    public static void method2(StringBuilder s2){
        s2.append("a");
        s2.append("b");
    }

    /**
     * s3返回出去，可能被其他线程强占使用
     * s3在当前线程中未消亡
     * @return
     */
    public static StringBuilder method3(){
        StringBuilder s3=new StringBuilder();
        s3.append("a");
        s3.append("b");
        return s3;
    }

    public static void main(String[] args) {
        StringBuilder s=new StringBuilder();
        new Thread(()->{
            s.append("a");
            s.append("b");
        }).start();
        method2(s);
    }
}
