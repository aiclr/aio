package org.bougainvilleas.base.jvm.runtime.heap;

import java.util.ArrayList;
import java.util.Random;

/**
 * -Xms1M -Xmx1M
 * OutOfMemoryError
 * Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
 *
 * 新生区的Eden区和老年区满的时候，抛出异常信息
 */
public class OOMTest {

    public static void main(String[] args) {
        ArrayList<Picture> list=new ArrayList<>();
        while (true){
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            list.add(new Picture(new Random().nextInt(1024*1024)));
        }
    }
}

class Picture{
    private int size;

    public Picture(int size) {
        this.size = size;
    }
}
