package org.bougainvilleas.base.dsaa.sort;

import java.util.Arrays;

/**
 * 基数排序
 * 空间换时间
 * 负数不行
 */
public class RadixSort {

    public static void main(String[] args) {
        int arr[] = {53, 3, 542, 748, 14, 214};
//        radixSort1(arr);
        radixSort(arr);
        int i=0;
        System.err.println(i++);
        System.err.println("1=="+i);
        System.err.println(i++);
        System.err.println("2=="+i);
    }

    public static void radixSort(int[] a){
        int max=a[0];
        for (int i = 1; i < a.length; i++) {
            if(a[i]>max){
                max=a[i];
            }
        }
        //获取最大位数
        int maxLength=(max+"").length();
        //桶，10个数组，
        int[][] bucket = new int[10][a.length];
        //记录每个桶内数据个数,bucketElementCounts[0]=第一个桶内数据个数
        int[] bucketElementCounts = new int[10];

        for (int i = 0,n=1;i < maxLength; i++,n*=10) {
            for (int j = 0; j < a.length; j++) {
                int digitOfElement = a[j]/n % 10;
                bucket[digitOfElement][bucketElementCounts[digitOfElement]] = a[j];
                bucketElementCounts[digitOfElement]++;
            }
            int t = 0;
            for (int k = 0; k < bucket.length; k++) {
                if (bucketElementCounts[k] != 0) {
                    for (int j = 0; j < bucketElementCounts[k]; j++) {
                        // t=0;t=t++; 结果t=0; ===> t=t=t+1 java栈
                        //1. t=t; （此时t=0，入java栈，等待2执行)
                        //2. t=t+1; （此时t=t+1,入java栈，执行完，t=1结束, 转到1步的栈执行，但是1里的t=t已经执行过了，即t=0已设置）
                        //所以t=0,但是t其实是1,
                        a[t++] = bucket[k][j];
                    }
                    bucketElementCounts[k]=0;
                }
            }
            System.err.println(Arrays.toString(a));
        }
    }


    /**
     * 拆解
     * @param a
     */
    public static void radixSort1(int a[]) {

        //桶，10个数组，
        int[][] bucket = new int[10][a.length];

        //记录每个桶内数据个数,bucketElementCounts[0]=第一个桶内数据个数
        int[] bucketElementCounts = new int[10];

        //第一轮，个位，个位是几就放到
        // bucket[个位数][桶内个数]，
        //桶内个数(bucketElementCounts[digitOfElement]),每放一个就++;
        for (int j = 0; j < a.length; j++) {
            int digitOfElement = a[j]/1 % 10;
            bucket[digitOfElement][bucketElementCounts[digitOfElement]] = a[j];
            bucketElementCounts[digitOfElement]++;
        }

        int t = 0;
        for (int i = 0; i < bucket.length; i++) {
            if (bucketElementCounts[i] != 0) {
                for (int j = 0; j < bucketElementCounts[i]; j++) {
                    a[t] = bucket[i][j];
                    t++;
                }
                bucketElementCounts[i]=0;
            }
        }
        System.err.println(Arrays.toString(a));

        //第二轮，十位，十位是几就放到
        // bucket[个位数][桶内个数]，
        //桶内个数(bucketElementCounts[digitOfElement]),每放一个就++;
        for (int j = 0; j < a.length; j++) {
            int digitOfElement = a[j] /10 % 10;
            bucket[digitOfElement][bucketElementCounts[digitOfElement]] = a[j];
            bucketElementCounts[digitOfElement]++;
        }

        t = 0;
        for (int i = 0; i < bucket.length; i++) {
            if (bucketElementCounts[i] != 0) {
                for (int j = 0; j < bucketElementCounts[i]; j++) {
                    a[t] = bucket[i][j];
                    t++;
                }
                bucketElementCounts[i]=0;
            }
        }
        System.err.println(Arrays.toString(a));

        //第三轮，百位，百位是几就放到
        // bucket[个位数][桶内个数]，
        //桶内个数(bucketElementCounts[digitOfElement]),每放一个就++;
        for (int j = 0; j < a.length; j++) {
            int digitOfElement = a[j] /100 % 10;
            bucket[digitOfElement][bucketElementCounts[digitOfElement]] = a[j];
            bucketElementCounts[digitOfElement]++;
        }

        t = 0;
        for (int i = 0; i < bucket.length; i++) {
            if (bucketElementCounts[i] != 0) {
                for (int j = 0; j < bucketElementCounts[i]; j++) {
                    a[t] = bucket[i][j];
                    t++;
                }
                bucketElementCounts[i]=0;
            }
        }
        System.err.println(Arrays.toString(a));

    }
}
