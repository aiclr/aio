package org.bougainvilleas.base.juc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Lock 同步锁
 * synchronized隐式锁
 * @author renqiankun
 */
public class TestLock {

    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        new Thread(ticket, "a").start();
        new Thread(ticket, "b").start();
        new Thread(ticket, "c").start();
        Ticket2 ticket2 = new Ticket2();
        new Thread(ticket2, "A").start();
        new Thread(ticket2, "B").start();
        new Thread(ticket2, "C").start();
        Ticket3 ticket3 = new Ticket3();
        new Thread(ticket3, "1").start();
        new Thread(ticket3, "2").start();
        new Thread(ticket3, "3").start();
        Ticket4 ticket4 = new Ticket4();
        new Thread(ticket4, "i").start();
        new Thread(ticket4, "II").start();
        new Thread(ticket4, "III").start();
    }
}

/**
 * 同步锁
 */
class Ticket implements Runnable {

    private int ticketNum = 100;

    Lock lock = new ReentrantLock(true);

    @Override
    public void run() {
        while (ticketNum > 0) {
            try {
                //增加延迟 提高锁竞争效果
                TimeUnit.MILLISECONDS.sleep(200L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if(lock.tryLock()){
                try {
                    if (ticketNum > 0) {
                        System.out.println(Thread.currentThread().getName() + "完成售票，余票为：" + --ticketNum);
                    }
                } finally {
                    lock.unlock();
                }
            }else {
                System.out.println(Thread.currentThread().getName() + "未抢到锁不能售票，余票为：" + ticketNum);
            }
        }

    }
}

/**
 * 同步代码块
 */
class Ticket2 implements Runnable {

    private int ticketNum = 100;

    @Override
    public void run() {
        synchronized (this) {
            while (ticketNum > 0) {
                System.out.println(Thread.currentThread().getName() + "完成售票，余票为：" + --ticketNum);
            }
        }
    }
}

/**
 * 同步方法
 */
class Ticket3 implements Runnable {

    private int ticketNum = 100;

    @Override
    public void run() {
        while (ticketNum > 0) {
            System.out.println(Thread.currentThread().getName() + "完成售票，余票为：" + sale());
        }
    }

    private synchronized int sale() {
        return --ticketNum;
    }
}

/**
 * 不控制
 */
class Ticket4 implements Runnable {

    private int ticketNum = 100;

    @Override
    public void run() {
        while (ticketNum > 0) {
            System.out.println(Thread.currentThread().getName() + "完成售票，余票为：" + sale());
        }
    }

    private synchronized int sale() {
        return --ticketNum;
    }
}
