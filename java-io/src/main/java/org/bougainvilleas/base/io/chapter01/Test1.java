package org.bougainvilleas.base.io.chapter01;

import java.nio.*;

/**
 * ByteBuffer、CharBuffer、DoubleBuffer、FloatBuffer、IntBuffer、LongBuffer和ShortBuffer是抽象类，
 * wrap()就相当于创建这些缓冲区的工厂方法
 * @author renqiankun
 */
public class Test1 {
    public static void main(String[] args) {
        byte[] bytes=new byte[]{1,2,3};
        short[] shorts=new short[]{1,2,3,4};
        int[] ints=new int[]{1,2,3,4,5};
        long[] longs=new long[]{1,2,3,4,5,6};
        float[] floats=new float[]{1,2,3,4,5,6,7};
        double[] doubles=new double[]{1,2,3,4,5,6,7,8};
        char[] chars=new char[]{'a','b','c','d'};

        ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);

        ByteBuffer byteBuffer1 = ByteBuffer.wrap(bytes,1,2);
        System.err.println(byteBuffer.capacity());
        System.err.println(byteBuffer.limit());
        System.err.println(byteBuffer.position());
        System.err.println(byteBuffer1.capacity());
        System.err.println(byteBuffer1.limit());
        System.err.println(byteBuffer1.position());
        ShortBuffer shortBuffer = ShortBuffer.wrap(shorts);
        IntBuffer intBuffer = IntBuffer.wrap(ints);
        LongBuffer longBuffer = LongBuffer.wrap(longs);
        FloatBuffer floatBuffer = FloatBuffer.wrap(floats);
        DoubleBuffer doubleBuffer = DoubleBuffer.wrap(doubles);
        CharBuffer charBuffer = CharBuffer.wrap(chars);

        System.err.println(byteBuffer.getClass().getName());
        System.err.println(shortBuffer.getClass().getName());
        System.err.println(intBuffer.getClass().getName());
        System.err.println(longBuffer.getClass().getName());
        System.err.println(floatBuffer.getClass().getName());
        System.err.println(doubleBuffer.getClass().getName());
        System.err.println(charBuffer.getClass().getName());

        System.err.println("------------------------------");

        System.err.println(byteBuffer.capacity());
        System.err.println(shortBuffer.capacity());
        System.err.println(intBuffer.capacity());
        System.err.println(longBuffer.capacity());
        System.err.println(floatBuffer.capacity());
        System.err.println(doubleBuffer.capacity());
        System.err.println(charBuffer.capacity());

    }
}
