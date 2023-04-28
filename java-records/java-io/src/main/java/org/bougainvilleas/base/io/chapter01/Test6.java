package org.bougainvilleas.base.io.chapter01;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.InvalidMarkException;

/**
 * 1.缓冲区的capacity不能为负数，缓冲区的limit不能为负数，缓冲区的position不能为负数
 * 2.position不能大于其limit------看position(int newPosition)源码即可
 * 3.limit不能大于其capacity------看limit(int newLimit)源码即可
 * 4.如果定义了mark，则在将position或limit调整为小于该mark值时，该mark被丢弃
 * 5.如果未定义mark，那么调用reset()将抛出InvalidMarkException异常------reset()源码
 * 6.如果position大于新的limit则position的值就是新limit值------limit(int newLimit)源码
 * 7.当limit和position值一样时，在指定的position写入数据时会出现异常，因为此位置是被限制的
 * @author renqiankun
 */
public class Test6 {
    public static void main(String[] args) {
        test1();
        System.err.println("####");
        test4();
    }

    /**
     * 如果定义了mark，则在将position或limit调整为小于该mark值时，该mark被丢弃
     */
    private static void test4(){
        char[] chars=new char[]{'a','b','c','d','e'};
        CharBuffer charBuffer = CharBuffer.wrap(chars);
        charBuffer.position(2);
        charBuffer.mark();
        charBuffer.position(1);
        System.err.println("根据position(int newPosition)源码可只position小于mark，mark被设置为-1");
        System.err.println("根据reset()源码可只当mark<0时，抛异常");
        try{
            charBuffer.reset();
        }catch (InvalidMarkException e){
            System.err.println("position调整为小于mark值时，mark被设置为-1，reset()方法异常");
        }
        System.err.println("#####");
        CharBuffer charBuffer2 = CharBuffer.wrap(chars);
        charBuffer2.position(2);
        charBuffer2.mark();
        charBuffer2.limit(1);
        System.err.println("limit(int newLimit)源码可知limit小于mark，mark被设置为-1");
        System.err.println("根据reset()源码可只当mark<0时，抛异常");
        try{
            charBuffer2.reset();
        }catch (InvalidMarkException e){
            System.err.println("limit调整为小于mark值时，mark被设置为-1，reset()方法异常");
        }


    }
    /**
     * 缓冲区的capacity不能为负数，缓冲区的limit不能为负数，缓冲区的position不能为负数
     * 看JDK源码即可
     */
    private static void test1(){
        //public static ByteBuffer allocate(int capacity) {
        //        if (capacity < 0)
        //            throw new IllegalArgumentException();
        //        return new HeapByteBuffer(capacity, capacity);
        //    }
        try{
            ByteBuffer byteBuffer = ByteBuffer.allocate(-1);
        }catch (IllegalArgumentException e){
            System.err.println("缓冲区的capacity不能为负数");
        }

        char[] chars=new char[]{'a','b','c','d','e'};
        CharBuffer charBuffer = CharBuffer.wrap(chars);
        try{
            CharBuffer limit =(CharBuffer)charBuffer.limit(-1);
        }catch (IllegalArgumentException e){
            System.err.println("缓冲区的limit不能为负数");
        }
        try{
            CharBuffer position =(CharBuffer)charBuffer.position(-1);
        }catch (IllegalArgumentException e){
            System.err.println("缓冲区的position不能为负数");
        }
    }
}
