package org.bougainvilleas.base.jvm.runtime.heap.gc;

import java.util.ArrayList;
import java.util.List;

/**
 * GC
 *   VM options: -Xms9M -Xmx9M -XX:+PrintGCDetails
 *
 *  [PSYoungGen: 2046K->480K(2560K)]  Young区YoungGC前占用2046，回收后占用480，总大小2560
 *  2046K->1449K(9728K) 整个heap YGC前占用2046，YGC后占用1449
 *  [ParOldGen: 6377K->3867K(7168K)] Old区FullGC前占用6377k，FullGC后占用3867K，总大小7168
 *  [Metaspace: 2632K->2632K(1056768K)] 元空间FullGC前占用2632，FullGC后占用2632，总大小1056768
 *  0.0013965 secs GC所花费时间
 *
 * [GC (Allocation Failure) [PSYoungGen: 2046K->480K(2560K)] 2046K->1449K(9728K), 0.0013965 secs] [Times: user=0.13 sys=0.03, real=0.00 secs]
 * [GC (Allocation Failure) [PSYoungGen: 1768K->426K(2560K)] 2737K->2643K(9728K), 0.0009313 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
 * [GC (Allocation Failure) [PSYoungGen: 2130K->266K(2560K)] 4347K->3315K(9728K), 0.0009159 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
 * [Full GC (Ergonomics) [PSYoungGen: 1138K->0K(2560K)] [ParOldGen: 6377K->3867K(7168K)] 7515K->3867K(9728K), [Metaspace: 2632K->2632K(1056768K)], 0.0037059 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
 * [GC (Allocation Failure) --[PSYoungGen: 1704K->1704K(2560K)] 5571K->7235K(9728K), 0.0008064 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
 * [Full GC (Ergonomics) [PSYoungGen: 1704K->0K(2560K)] [ParOldGen: 5531K->5531K(7168K)] 7235K->5531K(9728K), [Metaspace: 2632K->2632K(1056768K)], 0.0023739 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
 * [GC (Allocation Failure) [PSYoungGen: 0K->0K(1536K)] 5531K->5531K(8704K), 0.0002982 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]----------例子中字符串越来越大，YGC后仍不够放，尝试接放到Old区，所以[PSYoungGen: 0K->0K(1536K)]少一个to区大小
 * [Full GC (Allocation Failure) [PSYoungGen: 0K->0K(1536K)] [ParOldGen: 5531K->5518K(7168K)] 5531K->5518K(8704K), [Metaspace: 2632K->2632K(1056768K)], 0.0053840 secs] [Times: user=0.14 sys=0.00, real=0.01 secs]
 * 遍历次数=16
 * Heap
 *  PSYoungGen      total 1536K, used 40K [0x00000000ffd00000, 0x0000000100000000, 0x0000000100000000)
 *   eden space 1024K, 3% used [0x00000000ffd00000,0x00000000ffd0a3c8,0x00000000ffe00000)
 *   from space 512K, 0% used [0x00000000fff80000,0x00000000fff80000,0x0000000100000000)-----------------------form和to不同，是内存分配的自适应造成的
 *   to   space 1024K, 0% used [0x00000000ffe00000,0x00000000ffe00000,0x00000000fff00000)----------------------
 *  ParOldGen       total 7168K, used 5518K [0x00000000ff600000, 0x00000000ffd00000, 0x00000000ffd00000)
 *   object space 7168K, 76% used [0x00000000ff600000,0x00000000ffb63968,0x00000000ffd00000)
 *  Metaspace       used 2664K, capacity 4486K, committed 4864K, reserved 1056768K
 *   class space    used 284K, capacity 386K, committed 512K, reserved 1048576K
 * java.lang.OutOfMemoryError: Java heap space-----------------------------------------OOM前肯定有一次FullGC
 */
public class GCTest {

    public static void main(String[] args) {
        int i = 0;
        try {
            List<String> list = new ArrayList<>();
            String a = "bougainvillea";
            while (true) {
                list.add(a);
                a = a + a;
                i++;
            }
        } catch (Throwable t) {
            t.printStackTrace();
            System.out.println("遍历次数="+i);
        }

    }
}
