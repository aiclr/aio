package org.bougainvilleas.base.io.chapter01;

import java.nio.CharBuffer;

/**
 * limit使用场景是反复地向缓冲区存取数据
 * 示例
 * 第一次向缓冲区存9个
 * 读取9个
 * 第二次向缓冲区存储4个数据
 * 读取时还会读9个，
 * 所以要设置limit让第二次读取时只读4个
 * @author renqiankun
 */
public class Test2 {
    public static void main(String[] args) {
        char[] chars=new char[]{'a','b','c','d','e'};
        CharBuffer charBuffer = CharBuffer.wrap(chars);
        System.err.println("缓冲区容量："+charBuffer.capacity());
        System.err.println("限制操作区容量："+charBuffer.limit());
        display(charBuffer);
        charBuffer.put(0,'o');
        charBuffer.put(1,'p');
        charBuffer.put(2,'q');
        charBuffer.limit(3);
        System.err.println("设置限制区容量为"+charBuffer.limit());
        display(charBuffer);
        System.err.println("----");
        System.err.println("缓冲区容量："+charBuffer.capacity());
        System.err.println("限制操作区容量："+charBuffer.limit());

        //此时可操作位置只有0，1，2
        charBuffer.put(0,'o');
        charBuffer.put(1,'p');
        charBuffer.put(2,'q');
        charBuffer.put(3,'r');//此处是第一个不可读不可写索引,此行异常java.lang.IndexOutOfBoundsException
        charBuffer.put(4,'s');
        charBuffer.put(5,'t');
        charBuffer.put(6,'u');
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
