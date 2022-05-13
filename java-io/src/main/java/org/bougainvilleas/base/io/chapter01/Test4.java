package org.bougainvilleas.base.io.chapter01;

import java.nio.CharBuffer;

/**
 * remaining
 * 缓冲区剩余的可操作空间
 * limit-position=remaining
 * @author renqiankun
 */
public class Test4 {
    public static void main(String[] args) {
        char[] chars=new char[]{'a','b','c','d','e'};
        CharBuffer charBuffer = CharBuffer.wrap(chars);
        System.err.println("限制区limit值为 "+charBuffer.limit());
        System.err.println("下一个读写操作位置为 "+charBuffer.position());
        System.err.println("剩余可操作空间为 "+charBuffer.limit()+"-"+charBuffer.position()+" = "+charBuffer.remaining());

        charBuffer.position(2);
        System.err.println("####");

        System.err.println("限制区limit值为 "+charBuffer.limit());
        System.err.println("下一个读写操作位置为 "+charBuffer.position());
        System.err.println("剩余可操作空间为 "+charBuffer.limit()+"-"+charBuffer.position()+" = "+charBuffer.remaining());
    }
}
