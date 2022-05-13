package org.bougainvilleas.base.jvm.runtime.heap;

import java.util.ArrayList;
import java.util.Random;

/**
 * -Xms600M -Xmx600M
 * java对象内存分配测试
 * jvisualvm查看
 */
public class HeapInstancetest {
    byte[] buffer=new byte[new Random().nextInt(1024*200)];

    public static void main(String[] args) {
        ArrayList<HeapInstancetest> list=new ArrayList<>();
        while (true){
            list.add(new HeapInstancetest());
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
