package org.bougainvilleas.base.io.chapter01;


import java.lang.reflect.Method;
import java.nio.ByteBuffer;

public class Test12 {

    public static void main(String[] args) throws Exception{
        ByteBuffer allocateDirect = ByteBuffer.allocateDirect(100);
        System.err.println("position={"+allocateDirect.position()+"},limit={"+allocateDirect.limit()+"},capacity={"+allocateDirect.capacity()+"}");
        ByteBuffer allocate = ByteBuffer.allocate(100);
        System.err.println("position={"+allocate.position()+"},limit={"+allocate.limit()+"},capacity={"+allocate.capacity()+"}");

        hand();
        jvm();

    }

    public static void jvm(){
        //JVM自动化处理
        //多次运行后，一直在耗费内存
        //进程结束后，不会马上回收内存
        //当触发GC垃圾回收器进行内存的回收
        ByteBuffer buffer1 = ByteBuffer.allocateDirect(Integer.MAX_VALUE);
        byte[] bytes=new byte[]{1};
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            buffer1.put(bytes);
        }
    }

    public static void hand() throws Exception{
        //手动释放内存
        //下面程序运行效果1秒钟之后立即回收内存直接缓冲区所占用的内存
        ByteBuffer buffer = ByteBuffer.allocateDirect(Integer.MAX_VALUE);
        byte[] bytes=new byte[]{1};
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            buffer.put(bytes);
        }
        Thread.sleep(1000);
        Method cleanerMethod=buffer.getClass().getMethod("cleaner");
        cleanerMethod.setAccessible(true);
        Object returnValue=cleanerMethod.invoke(buffer);
        Method cleanMethod=returnValue.getClass().getMethod("clean");
        cleanMethod.setAccessible(true);
        cleanMethod.invoke(returnValue);
    }


}
