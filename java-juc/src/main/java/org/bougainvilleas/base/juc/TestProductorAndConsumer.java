package org.bougainvilleas.base.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 生产者消费者模式
 * <p>
 * synchronized & lock
 * 多线程等待唤醒机制
 * <p>
 * 易出现问题
 * 1 不添加等待唤醒机制，当生产者发现仓库满了，还会一直进行生产逻辑；同理消费者发现仓库为空，还会一直进行消费逻辑
 * 2 使用if-else控制唤醒
 * 售货：if(产品空){this.wait()} else{this.notifyAll()}
 * 进货：if(产品满){this.wait()} else{this.notifyAll()}
 * 可能出现：当消费者售货没货后，此时在if内等待，
 *         当生产者进货，满仓时，进到生产者的else内唤醒消费者
 *         消费者在if内被唤醒，并不会进到消费者的else代码内，即：不会唤醒生产者线程进行生产，所以生产者一直等待
 * 2.1 改进：去掉else,解决上述问题
 * 售货：if(产品空){
 *        this.wait();
 *      }
 *      this.notifyAll();
 * 进货：if(产品满){
 *        this.wait();
 *      }
 *      this.notifyAll();
 * 可能出现问题：虚假唤醒  JDK API Object类下wait()方法有说明使用while
 *          当多个消费者，多个生产者时，会出现虚假唤醒，可能出现进货量大于仓库容量，可能出现售出-1
 *  分析：仓库只能存一件货，多个售货线程同时发现没货，等待
 *       此时进货1件，满仓，唤醒所有售货线程，其中只能有一个售货线程成功售货，其余售货线程就会售出-1，-2的情况
 *       同理,仓库只能存一件，多个进货线程等待进货，一个售货线程成功售货，唤醒所有进货线程，每个进货线程进一件，超出仓库容量
 *  解决思路：再次判断一下仓库
 *  使用while替换到if即可，
 *
 *
 * synchronized 等待-唤醒
 *  使用wait()、notify（）、notifyAll（）
 * lock
 * 使用Condition对象的方法进行等待-唤醒
 *  await（），signal（）、signalAll（）
 * Condition接口 单个lock可能与多个Condition对象关联
 * 实质是Condition对象被绑定到一个锁上，
 * 要为特定Lock实例获得Condition实例，
 * Lock lock = new ReentrantLock();
 * Condition condition = lock.newCondition();
 *
 * @author renqiankun
 */
public class TestProductorAndConsumer {
    public static void main(String[] args) {
        Clerk clerk = new Clerk();

        Productor productor = new Productor(clerk);
        Consumer consumer = new Consumer(clerk);

        new Thread(consumer, "消费者a").start();
        new Thread(productor, "生产者A").start();
        new Thread(consumer, "消费者b").start();
        new Thread(productor, "生产者B").start();

        ClerkForLock clerk2 = new ClerkForLock();

        ProductorForLock productor2 = new ProductorForLock(clerk2);
        ConsumerForLock consumer2 = new ConsumerForLock(clerk2);

        new Thread(consumer2, "消费者aa").start();
        new Thread(productor2, "生产者AA").start();
        new Thread(consumer2, "消费者bb").start();
        new Thread(productor2, "生产者BB").start();


    }
}

/************************************ synchronized ****************/


/**
 * 店员
 */
class Clerk {
    //商品
    private int product = 0;

    //进货
    public synchronized void get() {
        //此处使用while，防止虚假唤醒
        while (product >= 1) {
            System.out.println(Thread.currentThread().getName() + "：满仓");
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + "：" + ++product);
        this.notifyAll();


//
//        if (product >= 1) {
//            System.out.println(Thread.currentThread().getName() + "：满仓");
//            try {
//                this.wait();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        } else {
//            System.out.println(Thread.currentThread().getName() + "：" + ++product);
//            this.notifyAll();
//        }

        //虚假唤醒
//        if (product >= 1) {
//            System.out.println(Thread.currentThread().getName() + "：满仓");
//            try {
//                this.wait();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//        System.out.println(Thread.currentThread().getName() + "：" + ++product);
//        this.notifyAll();

    }

    //售货
    public synchronized void sale() {
        while (product <= 0) {
            System.out.println(Thread.currentThread().getName() + "：缺货");
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + "：" + --product);
        this.notifyAll();
    }
}

/**
 * 生产者负责进货
 */
class Productor implements Runnable {

    private Clerk clerk;

    public Productor(Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            clerk.get();
        }
    }
}

/**
 * 消费者负责售货
 */
class Consumer implements Runnable {
    private Clerk clerk;

    public Consumer(Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            clerk.sale();
        }
    }
}
/************************************ Lock ****************/


/**
 * 店员
 */
class ClerkForLock {
    //商品
    private int product = 0;
    Lock lock = new ReentrantLock();
    //线程间通信
    Condition condition = lock.newCondition();

    //进货
    public void get() {
        lock.lock();
        try {
            while (product >= 1) {
                System.out.println(Thread.currentThread().getName() + "：满仓");
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName() + "：" + ++product);
            condition.signalAll();
        } finally {
            lock.unlock();
        }

    }

    //售货
    public void sale() {
        lock.lock();
        try {
            while (product <= 0) {
                System.out.println(Thread.currentThread().getName() + "：缺货");
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName() + "：" + --product);
            condition.signalAll();
        } finally {
            lock.unlock();
        }

    }
}

/**
 * 生产者负责进货
 */
class ProductorForLock implements Runnable {

    private ClerkForLock clerk;

    public ProductorForLock(ClerkForLock clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            clerk.get();
        }
    }
}

/**
 * 消费者负责售货
 */
class ConsumerForLock implements Runnable {
    private ClerkForLock clerk;

    public ConsumerForLock(ClerkForLock clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            clerk.sale();
        }
    }
}
