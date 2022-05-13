package org.bougainvilleas.base.jvm.runtime.heap;

/**
 * 获取默认内存大小
 * -Xms600M -Xmx600m
 *
 * C:\Users\renqiankun>jps
 * 2208 GradleDaemon
 * 1300
 * 12856 Jps
 * 12968 GradleDaemon
 * 808 Main
 * 13772 HeapSpaceInitial
 *
 * C:\Users\renqiankun>jstat -gc 13772
 *  S0C    S1C    S0U    S1U      EC       EU        OC         OU       MC     MU    CCSC   CCSU   YGC     YGCT    FGC    FGCT     GCT
 * 25600.0 25600.0  0.0    0.0   153600.0  6144.0   409600.0     0.0     4480.0 775.8  384.0   76.4       0    0.000   0      0.000    0.000
 * S0C和S1C有一个不会计算 所有少25M
 * Runtime.getRuntime().totalMemory()=S0C+EC+OC=25600+153600+409600=588800
 *
 */
public class HeapSpaceInitial {

    public static void main(String[] args) {
        System.out.println(25600+153600+409600);
        System.out.println(588800/1024);
        long initialMemory = Runtime.getRuntime().totalMemory() / 1024 / 1024;
        long maxMemory = Runtime.getRuntime().maxMemory() / 1024 / 1024;
        System.out.println("-Xms"+initialMemory+"M"); //575M
        System.out.println("-Xmx"+maxMemory+"M");// 575M
        System.out.println("系统内存="+initialMemory*64/1024+"G");
        System.out.println("系统内存="+maxMemory*4/1024+"G");
//        try {
//            Thread.sleep(1000*1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }
}
