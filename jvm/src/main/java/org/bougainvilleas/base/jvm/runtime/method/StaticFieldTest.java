package org.bougainvilleas.base.jvm.runtime.method;

/**
 * 结论
 * 静态引用对应的对象实体始终都存放在heap空间
 * new的对象都在heap空间
 *
 * 静态引用位置：
 * jdk6在PermGenSpace
 * jdk7&JDK8在Heap区
 *
 *
 * 证明静态变量实体存放位置 Old区
 *
 * jdk7
 * -Xms200m -Xmx200M -XX:PermSize=300M -XX:MaxPermSize=300m -XX:+PrintGCDetails
 *
 * jdk8
 * -Xms200m -Xmx200M -XX:MetaspaceSize=300M -XX:MaxMetaspaceSize=300m -XX:+PrintGCDetails
 *
 * [B@15db9742
 * Heap
 *  PSYoungGen      total 59904K, used 2068K [0x00000000fbd80000, 0x0000000100000000, 0x0000000100000000)
 *   eden space 51712K, 4% used [0x00000000fbd80000,0x00000000fbf85368,0x00000000ff000000)
 *   from space 8192K, 0% used [0x00000000ff800000,0x00000000ff800000,0x0000000100000000)
 *   to   space 8192K, 0% used [0x00000000ff000000,0x00000000ff000000,0x00000000ff800000)
 *  ParOldGen       total 136704K, used 102400K [0x00000000f3800000, 0x00000000fbd80000, 0x00000000fbd80000)---------------------------------
 *   object space 136704K, 74% used [0x00000000f3800000,0x00000000f9c00010,0x00000000fbd80000)
 *  Metaspace       used 2635K, capacity 4486K, committed 4864K, reserved 1056768K
 *   class space    used 281K, capacity 386K, committed 512K, reserved 1048576K
 *
 *   ParOldGen       total 136704K, used 102400K对应arr的100M
 */
public class StaticFieldTest {
    private static byte[] arr=new byte[1024*1024*100];//100M

    public static void main(String[] args) {
        System.out.println(StaticFieldTest.arr);
//        try {
//            Thread.sleep(1000*1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }
}
