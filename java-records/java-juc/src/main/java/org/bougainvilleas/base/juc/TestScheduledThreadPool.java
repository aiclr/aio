package org.bougainvilleas.base.juc;

import java.util.Random;
import java.util.concurrent.*;

/**
 * 线程池 参考 indi.ikun.spring.basejava.codequality.chapter09.Ev
 *
 * @author renqiankun
 */
public class TestScheduledThreadPool {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        //创建固定大小线程池，可以延迟或者定时执行任务
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(5);
        for (int i = 0; i < 10; i++) {
            Future<Integer> result = pool.schedule(() -> {
                int num = new Random().nextInt(100);
                System.out.println(Thread.currentThread().getName() + ":" + num);
                return num;
            }, 1, TimeUnit.SECONDS);
            System.out.println(result.get());
        }
        pool.shutdown();
    }
}
