package org.bougainvilleas.base.jvm.runtime.heap;

/**
 * 栈上分配
 * 实质是标量替换
 *
 * 关闭逃逸分析
 * -Xms1G -Xmx1G -XX:-DoEscapeAnalysis -XX:+PrintGCDetails
 * 80ms左右
 * jvisualvm 抽样器 内存 看到1000 0000个org.bougainvillea.java.jvm.runtime.heap.StackAllocation$User对象
 *
 * 开启逃逸分析，默认开启
 * -Xms1G -Xmx1G -XX:+DoEscapeAnalysis -XX:+PrintGCDetails
 * 4ms左右
 * jvisualvm 抽样器 内存 看到14 7870个org.bougainvillea.java.jvm.runtime.heap.StackAllocation$User对象
 *
 *
 *
 * -Xms256m -Xmx256m -XX:-DoEscapeAnalysis -XX:+PrintGCDetails
 * 有MinorGC
 * [GC (Allocation Failure) [PSYoungGen: 65536K->648K(76288K)] 65536K->656K(251392K), 0.0010624 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
 * [GC (Allocation Failure) [PSYoungGen: 66184K->632K(76288K)] 66192K->640K(251392K), 0.0010675 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
 * 花费时间=50ms
 *
 * -Xms256m -Xmx256m -XX:+DoEscapeAnalysis -XX:+PrintGCDetails
 * 无GC
 *> Task :jvm:StackAllocation.main()
 * 花费时间=3ms
 *
 */
public class StackAllocation
{
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000000; i++) {
            alloc();
        }
        long end = System.currentTimeMillis();
        System.out.println("花费时间="+(end-start)+"ms");

        //为了方便查看heap中对象个数，sleep
        try {
            Thread.sleep(1000*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void alloc(){
        User user=new User();//未逃逸
    }

    static class User{

    }
}
