package org.bougainvilleas.base.juc;

/**
 * 模拟CAS算法
 * @author renqiankun
 */
public class TestCompareAndSwap {

    public static void main(String[] args) {
        final CompareAndSwap cas = new CompareAndSwap();
        for (int i = 0; i <10; i++) {
            new Thread(() -> {
                    int i1 = cas.get();
                    boolean b = cas.compareAndSet(i1, (int) (Math.random() * 101));
                    System.out.println(b);
            }).start();
        }
    }
}

class CompareAndSwap{

    private int value;

    //获取内存值
    public synchronized int get(){
        return value;
    }

    //比较
    public synchronized int compareAndSwap(int expectedValue,int newValue){
        int oldValue=value;
        if(oldValue==expectedValue){
            this.value=newValue;
        }
        return oldValue;
    }

    //设置
    public synchronized boolean compareAndSet(int expectedValue,int newValue){
        return expectedValue==compareAndSwap(expectedValue,newValue);
    }
}
