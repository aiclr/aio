package org.bougainvilleas.base.jvm.runtime.heap;

/**
 * -Xms20m -Xmx20m
 * C:\Program Files\Java\jdk1.8.0_191\bin>jvisualvm.exe
 */
public class HeapDemo1 {
    public static void main(String[] args) {
        System.out.println("start...");
        try {
            Thread.sleep(1000*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("end...");
    }
}
