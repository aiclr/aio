package org.bougainvilleas.base.io.chapter01;

import java.nio.ByteBuffer;

/**
 * hasArray()
 * debug追踪源码
 * @author renqiankun
 */
public class Test9 {

    public static void main(String[] args) {
        ByteBuffer allocate = ByteBuffer.allocate(100);//非直接缓冲区
        allocate.put((byte)1);
        allocate.put((byte)2);
        //使用了一个byte[]数组保存数据
        System.err.println(allocate.hasArray());
        ByteBuffer allocateDirect = ByteBuffer.allocateDirect(100);//直接缓冲区
        allocateDirect.put((byte)1);
        allocateDirect.put((byte)1);
        //直接缓冲区并没有将数据存储到byte[]数组中，而是直接存储在内存中，所以hb是null，返回false
        System.err.println(allocateDirect.hasArray());
    }
}
