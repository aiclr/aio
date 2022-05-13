package org.bougainvilleas.base.jvm.runtime.heap;

/**
 * 查看Young区和Old区的比例
 *
 * -Xms600M -Xmx600M
 * -Xms600M -Xmx600M -XX:NewRatio=2
 *
 * Eden和Survivor0 Survivor1
 *  -Xms600M -Xmx600M
 *  -Xms600M -Xmx600M -XX:SurvivorRatio=8
 *
 * 设置Young区size（-Xmn和-XX:NewRatio=2导致结果不一致时，会以-Xmn值为准）
 * 如下  -XX:NewRatio=2  Young=200M
 *      -Xmn100M
 * -Xms600M -Xmx600M -XX:NewRatio=2 -Xmn100M
 * 结果 使用jvisualvm查看
 * Young=100M
 * Old=500M
 */
public class EdenSurvivortest {
    public static void main(String[] args) {
        System.out.println("查看Yong和Old比例");
        try {
            Thread.sleep(1000*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
