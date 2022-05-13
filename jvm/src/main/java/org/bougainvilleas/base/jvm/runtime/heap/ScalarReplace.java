package org.bougainvilleas.base.jvm.runtime.heap;

/**
 * 标量替换
 *
 * -Xms100m -Xmx100m -XX:+DoEscapeAnalysis -XX:+PrintGC -XX:-EliminateAllocations
 * [GC (Allocation Failure)  25600K->688K(98304K), 0.0011115 secs]
 * [GC (Allocation Failure)  26288K->688K(98304K), 0.0010129 secs]
 * [GC (Allocation Failure)  26288K->752K(98304K), 0.0008409 secs]
 * [GC (Allocation Failure)  26352K->688K(98304K), 0.0009243 secs]
 * [GC (Allocation Failure)  26288K->736K(98304K), 0.0010967 secs]
 * [GC (Allocation Failure)  26336K->752K(101376K), 0.0010096 secs]
 * [GC (Allocation Failure)  32496K->600K(101376K), 0.0009234 secs]
 * [GC (Allocation Failure)  32344K->600K(101376K), 0.0007950 secs]
 * 消耗时间=47ms
 *
 * -Xms100m -Xmx100m -XX:+DoEscapeAnalysis -XX:+PrintGC -XX:+EliminateAllocations
 * > Task :jvm:ScalarReplace.main()
 * 消耗时间=3ms
 */
public class ScalarReplace {
    /**
     * 聚合量aggregate
     *
     */
    static class User{
        int id;
        String name;
    }

    public static void alloc(){
        //User对象占据约16字节，一亿个约1.5GB，heap空间100M必然会发生GC
        User u=new User();
        u.id=5;
        u.name="bougainvillea";
    }
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000000; i++) {
            alloc();
        }
        long end = System.currentTimeMillis();
        System.out.println("消耗时间="+(end-start)+"ms");
    }
}
