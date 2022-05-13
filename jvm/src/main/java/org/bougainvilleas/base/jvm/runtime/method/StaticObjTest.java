package org.bougainvilleas.base.jvm.runtime.method;

/**
 * staticObj   静态属性引用，在java.lang.CLass的实例里，有一个名为staticObj的实例字段，该java.lang.Class实例对象是在heap中所以的除staticObj引用是在heap区
 * instanceObj 非静态属性引用 在heap中
 * localObj    方法的局部变量引用 在方法栈帧的局部变量表
 * 上述三个new的对象都在heap空间
 *
 * 《java虚拟机规范》定义的概念模型，所有Class相关的信息都应该存放在方法区中，但方法区如何实现《java虚拟机规范》并未做出规定
 *  这就成了一件允许不同虚拟机自己灵活把握的事情
 *  JDK7及其以后版本的Hotspot虚拟机选择把静态变量与类型在java语言一段的映射Class对象存放在一起，存储于Java堆中
 */
public class StaticObjTest {

 static class Test{
     //静态属性
     static ObjectHolder staticObj=new ObjectHolder();
     //非静态属性 随着Test对象实例存放在Java堆
     ObjectHolder instanceObj=new ObjectHolder();
     void foo(){
         //方法的局部变量，引用地址存放在foo()的stack frame的Local variables
         ObjectHolder localObj = new ObjectHolder();
         System.out.println("done");
     }
 }
    private static class ObjectHolder{

    }
    public static void main(String[] args){

    }
}
