package org.bougainvilleas.base.io.chapter01;

import java.nio.CharBuffer;

/**
 * position
 * 将要读取或写入的位置下标
 * @author renqiankun
 */
public class Test3 {
    public static void main(String[] args) {
        char[] chars=new char[]{'a','b','c','d','e'};
        CharBuffer charBuffer = CharBuffer.wrap(chars);
        System.err.println("限制区limit值为 "+charBuffer.limit());
        System.err.println("下一个读取或写入操作位置下标为 "+charBuffer.position());
        charBuffer.position(2);
        System.err.println("设置下一个读取或写入操作的位置小标为 "+charBuffer.position());
        display(charBuffer);
        System.err.println("----");
        System.err.println("限制区limit值为 "+charBuffer.limit());
        System.err.println("下一个读取或写入操作位置下标为 "+charBuffer.position());
        charBuffer.put('z');
        display(charBuffer);
        System.err.println("写入后position会+1变更为 "+charBuffer.position());
    }
    public static void display(CharBuffer buffer){
        char[] chars=new char[buffer.capacity()];
        //通过limit控制读取的长度，
        for (int i = 0; i < buffer.limit(); i++) {
            chars[i]=buffer.get(i);
        }
        System.err.println(new String(chars));
    }
}
