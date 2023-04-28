package org.bougainvilleas.base.io.chapter01;

import java.nio.ByteBuffer;

public class Test13 {

    public static void main(String[] args) {
        testGetPut();
        testGetPutPiliang();
        System.err.println(test1());
        System.err.println(test2());
    }


    public static void testGetPut(){
        byte[] bytes = new byte[]{1, 2, 3, 4, 5, 6, 7, 8};
        byte[] bytes2 = new byte[]{1, 2, 3, 4, 5, 6, 7, 8};
        ByteBuffer byteBuffer1 = ByteBuffer.wrap(bytes);
        ByteBuffer byteBuffer2 = ByteBuffer.wrap(bytes2, 2, 4);
        System.out.printf("capacity=%d,limit=%d,position=%d \n", byteBuffer1.capacity(), byteBuffer1.limit(), byteBuffer1.position());
        System.out.printf("capacity=%d,limit=%d,position=%d \n", byteBuffer2.capacity(), byteBuffer2.limit(), byteBuffer2.position());
        display(byteBuffer1);
        display(byteBuffer2);
        byteBuffer1.put((byte)11);//会移动 position
        byteBuffer1.put(2,(byte)11);//不会移动 position
        System.err.println(byteBuffer1.get());//会移动 position
        System.err.println(byteBuffer1.get(2));//不会移动 position

        display(byteBuffer1);
        display(byteBuffer2);

        System.out.printf("capacity=%d,limit=%d,position=%d \n", byteBuffer1.capacity(), byteBuffer1.limit(), byteBuffer1.position());
        System.out.printf("capacity=%d,limit=%d,position=%d \n", byteBuffer2.capacity(), byteBuffer2.limit(), byteBuffer2.position());
    }

    public static void testGetPutPiliang(){
        byte[] bytes = new byte[]{1, 2, 3, 4, 5, 6, 7, 8};
        byte[] bytes2 = new byte[]{ 55, 66, 77, 88};
        ByteBuffer byteBuffer1 = ByteBuffer.allocate(10);
        System.out.printf("capacity=%d,limit=%d,position=%d \n", byteBuffer1.capacity(), byteBuffer1.limit(), byteBuffer1.position());
        byteBuffer1.put(bytes);
        System.out.printf("capacity=%d,limit=%d,position=%d \n", byteBuffer1.capacity(), byteBuffer1.limit(), byteBuffer1.position());
        display(byteBuffer1);
        byteBuffer1.position(2);
        //将byte2的66，77，88放入缓冲区第三个位置
        byteBuffer1.put(bytes2,1,3);
        System.out.printf("capacity=%d,limit=%d,position=%d \n", byteBuffer1.capacity(), byteBuffer1.limit(), byteBuffer1.position());
        display(byteBuffer1);

        //接收批量get到的数组
        byte[] bytes3 = new byte[byteBuffer1.capacity()];
        //索引为3的四个字节
        byteBuffer1.get(bytes3,3,4);
        for (int i = 0; i < bytes3.length; i++) {
            System.err.printf("%d\t",bytes3[i]);
        }
        System.err.println();
        System.out.printf("capacity=%d,limit=%d,position=%d \n", byteBuffer1.capacity(), byteBuffer1.limit(), byteBuffer1.position());
    }

    public static void display(ByteBuffer buffer){
        //通过limit控制读取的长度，
        for (int i = 0; i < buffer.limit(); i++) {
            System.err.printf("%d\t",buffer.get(i));
        }
        System.err.println();
    }
    public static Integer test1(){
        Integer a=1;
        return ++a;
    }
    public static Integer test2(){
        Integer a=1;
        return a++;
    }

}
