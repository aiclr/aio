package org.bougainvilleas.base.io.chapter01;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * 使用List.toArray() 转成数组类型
 * List中存储ByteBuffer数据类型，则可以使用List中的toArray（）方法转成ByteBuffer[]数组类型
 */
public class Test11 {

    public static void main(String[] args) {

        ByteBuffer  buffer1=ByteBuffer.wrap(new byte[]{'a','b','c'});
        ByteBuffer  buffer2=ByteBuffer.wrap(new byte[]{'x','y','z'});
        ByteBuffer  buffer3=ByteBuffer.wrap(new byte[]{'1','2','3'});

        List<ByteBuffer> list=new ArrayList<>();
        list.add(buffer1);
        list.add(buffer2);
        list.add(buffer3);

        ByteBuffer[] byteBuffers=new ByteBuffer[list.size()];
        list.toArray(byteBuffers);
        System.err.println(byteBuffers.length);
        for (int i = 0; i < byteBuffers.length; i++) {
            ByteBuffer byteBuffer=byteBuffers[i];
            while (byteBuffer.hasRemaining()){
                System.err.print((char) byteBuffer.get());
            }
            System.err.println();
        }
    }
}
