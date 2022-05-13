package org.bougainvilleas.base.jvm.runtime.heap;

/**
 * -Xms10M -Xmx10m -XX:+PrintGCDetails
 */
public class SimpleHeap {
    private int id;
    public SimpleHeap(int id){
        this.id=id;
    }
    public void show(){
        System.out.println(id);
    }

    public static void main(String[] args) {
        SimpleHeap s1 = new SimpleHeap(1);
        SimpleHeap s2 = new SimpleHeap(2);
        int[] a=new int[10];
        Object[] o=new Object[2];
    }

}
