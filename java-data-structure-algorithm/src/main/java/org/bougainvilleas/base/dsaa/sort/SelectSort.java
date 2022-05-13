package org.bougainvilleas.base.dsaa.sort;

import java.util.Arrays;

/**
 * 选择排序
 * 寻找最小值下标，与第一位换位
 * 从剩余数据找最小值小标，与第二位换位
 * 依次类推
 */
public class SelectSort {

    public static void main(String[] args) {
        int[] a = {1, 8, 49, 50, 3, 10};
        int temp = 0;//最小值
        int index = 0;//最小值下标
        int count = 0;
        for (int i = 0; i < a.length - 1; i++) {
            temp=a[i];
            index = i;
            for (int j = i + 1; j < a.length; j++) {
                count++;
                if (a[index] > a[j]) {
                    temp=a[j];
                    index = j;
                }
            }
            a[index] = a[i];
            a[i] = temp;
            System.err.println(Arrays.toString(a));
        }
        System.err.println(count);

        int[] b = {1, 8, 49, 50, 3, 10};
        a=b;
        count = 0;
        for (int i = 0; i < a.length - 1; i++) {
            temp=a[i];
            index = i;
            for (int j = i + 1; j < a.length; j++) {
                count++;
                if (a[index] < a[j]) {
                    temp=a[j];
                    index = j;
                }
            }
            a[index] = a[i];
            a[i] = temp;
            System.err.println(Arrays.toString(a));
        }
        System.err.println(count);


    }
}
