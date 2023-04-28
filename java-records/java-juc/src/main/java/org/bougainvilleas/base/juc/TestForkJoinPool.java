package org.bougainvilleas.base.juc;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

/**
 * 多线程+分治思想
 *
 * Fork/Join框架
 * 与线程池区别
 * 1. 底层使用 工作窃取模式 work-stealing
 *      当执行新的任务时，它可以将其拆分成更小的任务执行，并将小任务加到线程队列中，fork，然后再从一个随机线程的队列中偷一个并把它放在自己的队列中
 * 2. 相对于一般的线程池实现，fork/join框架的优势体现再对其中包含的任务的处理方式上
 *      在一般的线程池中，如果一个线程正在执行的任务由于某些原因无法继续运行，那么该线程会处于等待状态
 *      而在fork/join框架实现中，如果某个子问题由于等待另一个子问题的完成而无法继续运行那么处理该子问题的线程会主动寻找其他尚未运行的子问题来执行
 *      这种方式减少了线程的等待时间，提高性能
 *
 *
 * @author renqiankun
 */
public class TestForkJoinPool {

    public static void main(String[] args) {
        Instant now = Instant.now();
        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinTask<Long> task=new ForkJoinSmnCalculate(0L,50000000000L);
        Long sum = pool.invoke(task);
        System.out.println(sum);
        Instant end = Instant.now();
        System.out.println("耗时"+ Duration.between(now,end).toMillis());

        //for循环
        Instant now2 = Instant.now();
        long sum2=0L;
        for (long i = 0; i <= 50000000000L; i++) {
            sum2+=i;
        }
        System.out.println(sum2);
        Instant end2 = Instant.now();
        System.out.println("耗时"+ Duration.between(now2,end2).toMillis());

        //java8 新特性
        Instant now3 = Instant.now();
        Long sum3= LongStream.rangeClosed(0L,50000000000L)
                .parallel()
                .reduce(0L,Long::sum);
        System.out.println(sum3);
        Instant end3 = Instant.now();
        System.out.println("耗时"+ Duration.between(now3,end3).toMillis());

    }



}

class ForkJoinSmnCalculate extends RecursiveTask<Long>{

    private static final long serialVersionUID=1L;
    private long start;
    private long end;
    //临界值
    private static final long THURSHOLD=10000L;

    public ForkJoinSmnCalculate(long start, long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        long length=end-start;
        if(length<=THURSHOLD){
            long sum=0L;
            for (long i = start; i <=end ; i++) {
                sum+=i;
            }
            return sum;
        }else {
            long mid=(start+end)/2;
            ForkJoinSmnCalculate left = new ForkJoinSmnCalculate(start,mid);
            //分，并压入线程队列
            left.fork();
            ForkJoinSmnCalculate right = new ForkJoinSmnCalculate(mid+1,end);
            //分，并压入线程队列
            right.fork();
            return left.join()+right.join();
        }
    }
}
