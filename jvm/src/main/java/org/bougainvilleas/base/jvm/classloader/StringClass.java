package org.bougainvilleas.base.jvm.classloader;

/**
 * 双亲委派机制
 *
 * String类由BootstrapClassLoader加载
 * 自定义的java.lang.String不会加载
 *
 */
public class StringClass {
    public static void main(String[] args) {
        String str=new String();
        System.out.println("hi 双亲委派机制");


        MyBougainvillea myBougainvillea = new MyBougainvillea();
        System.out.println(myBougainvillea.getClass().getClassLoader());
    }
}
