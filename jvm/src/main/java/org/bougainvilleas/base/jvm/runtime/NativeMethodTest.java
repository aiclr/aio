package org.bougainvilleas.base.jvm.runtime;

/**
 * native method写法
 * 顺序无关
 * 与abstract冲突
 * abstract抽象 表示无方法体
 * native有方法体，只是由非java语言实现
 */
public class NativeMethodTest {

public native void Native1(int x);
native static public long Native2();
native synchronized private float Native3(Object o);
native void Native4(int[] ary) throws Exception;
}
