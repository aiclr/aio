package org.bougainvilleas.base.jvm.runtime.heap;

/**
 * 测试大对象直接进入老年代
 * -Xms60M -Xmx60M -XX:NewRatio=2 -XX:SurvivorRatio=8 -XX:+PrintGCDetails
 * Young
 *       Eden: 16M
 *       s0:2M
 *       s1:2M
 * Old:40M
 *
 *
 * Heap
 *  PSYoungGen      total 18432K, used 1311K [0x00000000fec00000, 0x0000000100000000, 0x0000000100000000)
 *   eden space 16384K, 8% used [0x00000000fec00000,0x00000000fed47e90,0x00000000ffc00000)
 *   from space 2048K, 0% used [0x00000000ffe00000,0x00000000ffe00000,0x0000000100000000)
 *   to   space 2048K, 0% used [0x00000000ffc00000,0x00000000ffc00000,0x00000000ffe00000)
 *  ParOldGen       total 40960K, used 20480K [0x00000000fc400000, 0x00000000fec00000, 0x00000000fec00000)
 *   object space 40960K, 50% used [0x00000000fc400000,0x00000000fd800010,0x00000000fec00000)
 *  Metaspace       used 2634K, capacity 4486K, committed 4864K, reserved 1056768K
 *   class space    used 281K, capacity 386K, committed 512K, reserved 1048576K
 * 没有GC日志信息
 * 说明没有进行过垃圾回收
 * ParOldGen       total 40960K, used 20480K [0x00000000fc400000, 0x00000000fec00000, 0x00000000fec00000)
 * Old区used 20480k说明Old区占用20480k
 *
 */
public class YoungOldAreaTest {
    public static void main(String[] args) {
        //1024*1024*20=20480K=20M
        byte[] bytes = new byte[1024 * 1024 * 20];//20M>16M 直接进入Old区
    }
}
