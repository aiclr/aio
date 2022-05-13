package org.bougainvilleas.base.io.chapter01;

import java.nio.CharBuffer;

/**
 * mark
 * 索引，调用reset()方法，会将缓冲区的position重置为该索引
 * @author renqiankun
 */
public class Test5 {
    public static void main(String[] args) {
        char[] chars=new char[]{'a','b','c','d','e'};
        CharBuffer charBuffer = CharBuffer.wrap(chars);

        System.err.println("缓冲区容量是 "+charBuffer.capacity());
        charBuffer.position(1);
        System.err.println("设置操作位置 "+charBuffer.position());
        charBuffer.mark();
        System.err.println("在 "+charBuffer.position()+" 处加标记");
        charBuffer.position(2);
        System.err.println("再设置一个不同的操作位置 "+charBuffer.position());
        charBuffer.reset();
        System.err.println("调用reset()重置position为："+charBuffer.position()+" = mark标记位置");
    }
}
