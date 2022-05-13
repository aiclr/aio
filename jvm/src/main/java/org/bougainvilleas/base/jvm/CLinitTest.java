package org.bougainvilleas.base.jvm;

/**
 * 测试 clinit() 线程安全
 * 类的 static 变量和static 代码块 只会加载一次，且是线程安全的，类似同步加锁
 */
public class CLinitTest {
    public static void main(String[] args) {
        Runnable r=()->{
            System.out.println(Thread.currentThread().getName()+"开始");
            DeadThread deadThread = new DeadThread();
            System.out.println(Thread.currentThread().getName()+"结束");
        };
        Thread r1=new Thread(r,"线程a");
        Thread r2=new Thread(r,"线程b");
        r1.start();
        r2.start();
    }
}

class DeadThread{
    static {
        if(true){
            System.out.println(Thread.currentThread().getName()+"初始化当前类");
            while (true){

            }
        }
    }
}
