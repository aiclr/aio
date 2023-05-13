package org.bougainvilleas.base.jvm.runtime;

/**
 * native method写法
 * 顺序无关
 * 与abstract冲突
 * abstract抽象 表示无方法体
 * native有方法体，只是由非java语言实现
 *
 * java 生成 head头文件
 * jdk10 之前使用 javah 命令
 * jdk10移除javah ,使用 javac -h 代替
 * 详情查看help帮助文档
 * javac -h ../../../../../../../c NativeMethodTest.java
 * 将头文件放到c目录下
 */
public class NativeMethodTest {
  public native void sayHi(String url,String num);
  public native void Native1(int x);
  native static public long Native2();
  native synchronized private float Native3(Object o);
  native void Native4(int[] ary) throws Exception;
}

class Client{
  public static void main(String[] args) {
    System.out.printf("java 访问库目录：%s\n",System.getProperty("java.library.path"));
    System.loadLibrary("sayhi");//访问libsayhi.so库
    NativeMethodTest hi = new NativeMethodTest();
    hi.sayHi("/tmp/","2");
  }
}