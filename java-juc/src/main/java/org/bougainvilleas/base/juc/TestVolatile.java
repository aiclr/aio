package org.bougainvilleas.base.juc;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * volatile关键字：当多个线程进行操作共享数据时，可以保证内存中的数据可见
 * 相较于synchronized是一种较为轻量级的同步策略
 *
 * volatile不具有互斥性，所有线程都可以访问共享数据，synchronized就是互斥锁
 * volatile不能保证变量的原子性
 *  原子性：volatile能保证内存可见，但是不能保证多步操作一起执行
 *      i=i++;
 *      底层： int temp=i; 读
 *            i=i+1;      改
 *            temp=i;     写
 * jdk1.5后java.util.concurrent.atomic包下提供常用的原子变量
 *      volatile保证内存可见性
 *      CAS（compare and swap）算法保证原子性，比synchronized
 *          CAS算法是硬件对于并发操作共享数据的支持
 *          CAS包含三个操作数：
 *              内存值V
 *              预估值A
 *              更新的值B
 *              当且仅当V==A时，赋值V=B，否则将不做任何操作
 *  @author renqiankun
 */
public class TestVolatile {

    public static void main(String[] args) {
        TreadDemo treadDemo = new TreadDemo();
        new Thread(treadDemo).start();

        //main线程访问到treadDemo.isFlag()=false
        //treadDemo线程访问到treadDemo.isFlag()=true
        //while 竞争资源的能力更强，导致treadDemo线程修改后的值，迟迟无法提交到主存中，
        //即此时主存中值=false，main线程值false，treadDemo线程值为true
        //可以在主线程打断点测试
//        while (true) {
//            if (treadDemo.isFlag()) {
//                System.out.println("-------");
//                break;
//            }
//        }

        TreadDemo2 treadDemo2 = new TreadDemo2();
        new Thread(treadDemo2).start();
        //加synchronized 同步锁，当主线程访问多线程共享变量时加锁等待其他线程操作共享变量
        while (true) {
            synchronized (treadDemo2) {
                if (treadDemo2.isFlag()) {
                    System.out.println("-------");
                    break;
                }
            }
        }

        TreadDemo3 treadDemo3 = new TreadDemo3();
        new Thread(treadDemo3).start();
        while (true) {
            if (treadDemo3.isFlag()) {
                System.out.println("-------");
                break;
            }
        }

        //原子性
        TestDemo4 testDemo4 = new TestDemo4();
        for (int i = 0; i < 10; i++) {
            new Thread(testDemo4).start();
        }
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //原子变量
        TestDemo5 testDemo5 = new TestDemo5();
        System.out.println("原子变量");
        for (int i = 0; i < 10; i++) {
            new Thread(testDemo5).start();
        }
    }
}

class TreadDemo implements Runnable {
    private volatile boolean flag = false;

    @Override
    public void run() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        flag = true;
        System.out.println("flag= " + isFlag());
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}

class TreadDemo2 implements Runnable {
    private boolean flag = false;

    @Override
    public void run() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        flag = true;
        System.out.println("flag= " + isFlag());
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}

class TreadDemo3 implements Runnable {
    //多线程修改flag值相当于直接修改主存中数据
    private volatile boolean flag = false;

    @Override
    public void run() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        flag = true;
        System.out.println("flag= " + isFlag());
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}

class TestDemo4 implements Runnable{

    private int i;

    @Override
    public void run() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(i++);
    }
}

class TestDemo5 implements Runnable{

    private AtomicInteger i=new AtomicInteger();

    @Override
    public void run() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(i.getAndIncrement());
    }
}

/**
 * 双重检查单例模式
 */
class DoubleCheck{

    private DoubleCheck(){

    }

    private static volatile DoubleCheck doubleCheck;

    public static DoubleCheck getInstance(){
        if(doubleCheck==null){
            synchronized (DoubleCheck.class){
                if(doubleCheck==null){
                    doubleCheck=new DoubleCheck();
                }
            }
        }
        return doubleCheck;
    }

}