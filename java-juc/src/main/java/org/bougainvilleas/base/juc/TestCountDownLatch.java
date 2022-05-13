package org.bougainvilleas.base.juc;

import java.util.concurrent.CountDownLatch;

/**
 * 闭锁：CountDownLatch
 * 在完成某些运算时，只有其他所有的运算全部完成时，当前运算才继续执行
 *
 * @author renqiankun
 */
public class TestCountDownLatch {
    public static void main(String[] args) {

        final CountDownLatch latch = new CountDownLatch(5);

        LatchDemo latchDemo = new LatchDemo(latch);
        long start=System.currentTimeMillis();
        for (int i = 0; i < 5; i++) {
            new Thread(latchDemo).start();
        }
        try {
            //等待线程执行完毕
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(System.currentTimeMillis()-start);
    }
}

class LatchDemo implements Runnable {

    private CountDownLatch latch;

    @Override
    public void run() {
        synchronized (this) {
            try {
                for (int i = 0; i < 50000; i++) {
                    if ((i & 1) == 0) {
                        System.out.println(i);
                    }
                }
            } finally {
                //CountDownLatch -1，知道减到0
                latch.countDown();
            }
        }
    }

    public LatchDemo(CountDownLatch latch) {
        this.latch = latch;

    }
}