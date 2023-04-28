package org.bougainvilleas.base.dsaa.sort;

import java.util.Arrays;

/**
 * 希尔排序，缩小增量排序
 * 把记录按下标的一定增量分组，对每组使用直接插入排序算法排序，
 * 随着增量逐渐减少，每组包含的关键词越来越多，
 * 当增量减至1时，
 * 整个文件恰被分成一组
 */
public class ShellSort {


    public static void main(String[] args) {
        int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        int temp = 0;

        //交换法,效率不高
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                for (int j = i - gap; j >= 0; j -= gap) {
                    if (arr[j] > arr[j + gap]) {
                        temp = arr[j + gap];
                        arr[j + gap] = arr[j];
                        arr[j] = temp;
                    }
//                    System.err.println(Arrays.toString(arr));
                }
            }
        }
        int[] arr1 = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        arr = arr1;
        //移动法，效率高,插入排序
        //gap增量，逐步缩小增量
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                int j = i;
                int tmp = arr[i];
                if (arr[j] < arr[j - gap]) {
                    while (j - gap >= 0 && tmp < arr[j - gap]) {
                        arr[j]=arr[j-gap];
                        j-=gap;
                    }
                    arr[j]=tmp;
                    System.err.println(gap);
                    System.err.println(Arrays.toString(arr));
                }
            }
        }

    }

}
