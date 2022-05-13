package org.bougainvilleas.base.dsaa.sort;

import java.util.Arrays;

/**
 * 归并排序 分治算法
 *
 * （先分的，后治）==》栈---》递归
 *
 *  1,5,7,4,3,2
 *  1,5,7  4,3,2
 *  1,5  7  4,3  2
 *  1  5  7  4  3  2
 *  治
 *  1,5  7   3,4  2
 *  1,5,7    2,3,4
 *  1,2,3,4,5,6,7
 *
 *  分治算法
 *  把一个复杂问题分成两个或多个相同或相似的子问题，再把子问题分成更小的子问题，直到最后子问题可以简单的直接求解，
 *  原问题的解即为子问题的解的合并，
 *  快速排序，归并排序，傅立叶变换，快速傅立叶变换
 *
 *  经典问题
 *
 *  二分搜索
 *  大整数乘法
 *  棋盘覆盖
 *  合并排序
 *  快速排序
 *  线性时间选择
 *  最接近 点对 问题
 *  循环赛日程表
 *  汉诺塔
 *
 */
public class MergeSort {

    public static void main(String[] args) {
        int a[]={1,5,7,4,3,2,0};
        int temp[]=new int[a.length];
        fen(a,0,a.length-1,temp);
        System.err.println(Arrays.toString(a));


        //汉诺塔
        int num=3;
        hanoiTower(num,'A','B','C');
    }

    public static void fen(int a[],int left,int right,int[] temp){
        if(left<right){
            int mid=(left+right)/2;
            fen(a, left, mid, temp);
            fen(a, mid+1, right, temp);
            merge(a, left ,mid,right,temp);
        }
    }


    /**
     *
     * @param a 待治数组
     * @param left 左
     * @param mid 中
     * @param right 右
     * @param temp 临时存放有序数组
     */
    public static void merge(int[] a,int left, int mid,int right,int[] temp){
        int i=left;
        int j=mid+1;
        int t=0;
        while (i<=mid&&j<=right){
            if(a[i]<a[j]){
                temp[t]=a[i];
                i++;
            }else {
                temp[t]=a[j];
                j++;
            }
            t++;
        }
        while (j<=right){
            temp[t]=a[j];
            t++;
            j++;
        }
        while (i<=mid){
            temp[t]=a[i];
            t++;
            i++;
        }
        //转移有序数组
        t=0;
        //tempLeft = 0,0,3,3,0
        //right    = 1,2,4,5,5
        int tempLeft=left;
        while (tempLeft<=right){
            a[tempLeft]=temp[t];
            t++;
            tempLeft++;
        }
        System.err.println(Arrays.toString(a));
    }


    /**
     * 汉诺塔 分治
     * 思路
     * 当盘子数大于1时，
     * 把所有盘子看成2个盘子，
     *      1最底层盘子为一个，
     *      2剩余所有盘子为另一个，
     * 1. 把2从A移动到B
     * 2. 把1从A移动到C
     * 3. 把2从B移动到C
     *
     * 即：把2整体,移动到b,然后1移动到c,再将2移动到c,即可
     *     当2整体移动到b这一操作，又可以当作2部分从a柱子按照规则移动到b柱子
     *     即这一行：hanoiTower(num - 1, a, c, b)
     *     把1移动到c，
     *     把中间柱子上的2移动到c
     *     hanoiTower(num - 1, b, a, c);
     *
     * 当成两个盘子，移动逻辑始终都是a->b,a->c,b->c
     *
     * 将盘子层层递归分成两个盘子
     *
     * @param num 盘子数量
     * @param a 第一个柱子 先进后出--栈
     * @param b 第二个柱子 先进后出--栈
     * @param c 第三个柱子 先进后出--栈
     */
    public static void hanoiTower(int num, char a, char b,char c) {
        /**
         * 只有一个盘子
         */
        if (num == 1) {
            System.err.println("第1个盘子从" + a + "移到" + c);
        } else {
            //多个盘子
            //把上面的盘子移动到b
            hanoiTower(num - 1, a, c, b);
            //移动下面的盘子到c
            System.err.println("第" + num + "个盘子从" + a + "移到" + c);
            //把中间柱子上的盘子移动到c
            hanoiTower(num - 1, b, a, c);
        }
    }

}
