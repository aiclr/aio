package org.bougainvilleas.base.io.chapter01;

import java.nio.ByteBuffer;

/**
 * hasArray()
 * debug追踪源码
 * @author renqiankun
 */
public class Test10 {

    public static void main(String[] args) {
      byte[] bytes=new byte[]{1,2,3,4,5,6,7,8,9};
        ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
        int remaining=byteBuffer.remaining();
        for (int i = 0; i < remaining; i++) {
            System.err.print(byteBuffer.get()+"\t");
        }
        System.err.println();
        //重置缓冲区
        byteBuffer.clear();
        while (byteBuffer.hasRemaining()){
            System.err.print(byteBuffer.get()+"\t");
        }
        System.err.println();

        //重置缓冲区
        byteBuffer.clear();
        for(;byteBuffer.hasRemaining()==true;){
            System.err.print(byteBuffer.get()+"\t");
        }

        //偏移量
        System.out.println(byteBuffer.arrayOffset());
    }
}
