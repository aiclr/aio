package org.bougainvilleas.base.dsaa.sort;

import java.util.Arrays;

/**
 * 插入排序
 * 把n个待排序的元素看成一个有序表，一个无序表，
 * 开始时
 * 有序表中只包含一个元素
 * 无序表中有n-1个元素
 * 每次从无序表中取出第一个元素，把它的排序码依次与有序元素的排序码进行比较，
 * 将他插入到有序表中的适当位置，使之成为新的有序标
 */
public class InsertionSort {

    public static void main(String[] args) {
        int[] a = {9, 8, 49, 50, 3, 1};

        int temp = 0;//保存待插入数,可以将待插入数所在的位置留出来，可让有序部分整体后移
        int index = 0;//有序部分的最后一位的下标
        for (int i = 1; i < a.length; i++) {
            index = i - 1;
            temp = a[i];
            //判断越界，判断待插入数和有序部分最后一位的大小
            while (index >= 0 && temp < a[index]) {
                a[index + 1] = a[index];
                index--;
            }
            //判断是否需要赋值，无序部分，刚好位于有序部分的末尾，则不需要赋值，
            if (index + 1 != i) {
                a[index + 1] = temp;
            }
            System.err.println(Arrays.toString(a));
        }
        System.err.println();
        int[] b = {9, 8, 49, 50, 3, 1};
        a = b;
        for (int i = 1; i < a.length; i++) {
            index = i - 1;
            temp = a[i];
            //判断越界，判断待插入数和有序部分最后一位的大小
            while (index >= 0 && temp > a[index]) {
                a[index + 1] = a[index];
                index--;
            }
            //判断是否需要赋值，无序部分，刚好位于有序部分的末尾，则不需要赋值，
            if (index + 1 != i) {
                a[index + 1] = temp;
            }
            System.err.println(Arrays.toString(a));
        }

    }
}
