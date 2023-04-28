package org.bougainvilleas.base.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 面试题
 * 开启三个线程，线程ID分别为ABC，
 * 每个线程将自己的ID在屏幕上打印10遍，要求输出结果必须按顺序显示
 * ABCABCABC依次递归
 */
public class Testloop {
    public static void main(String[] args) {
        Loop loop = new Loop();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                loop.showA();
            }
        }, "A").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                loop.showB();
            }
        }, "B").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                loop.showC();
            }
        }, "C").start();
    }
}

class Loop {
    int num = 1;
    Lock lock = new ReentrantLock();
    Condition cA = lock.newCondition();
    Condition cB = lock.newCondition();
    Condition cC = lock.newCondition();

    public void showA() {
        lock.lock();
        try {
            if (num != 1) {
                cA.await();
            }
            System.out.print(Thread.currentThread().getName());
            num=2;
            cB.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    public void showB() {
        lock.lock();
        try {
            if(num!=2){
                cB.await();
            }
            System.out.print(Thread.currentThread().getName());
            num=3;
            cC.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void showC() {
        lock.lock();
        try {
            if(num!=3){
                cC.await();
            }
            System.out.print(Thread.currentThread().getName());
            num=1;
            cA.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}
