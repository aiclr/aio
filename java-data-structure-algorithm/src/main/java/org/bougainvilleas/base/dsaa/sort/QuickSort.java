package org.bougainvilleas.base.dsaa.sort;

import java.util.Arrays;

/**
 * 快速排序，冒泡排序的改进
 * 通过一趟排序将要排序的数据分割成独立的两部分，
 * 其中一部分的所有数据都比另外一部分的所有数据都小
 * 然后再按照此方法对两部分数据分别进行快速排序，整个排序过程可以递归
 */
public class QuickSort {

    public static void main(String[] args) {
        int[] a = {-9, 78, 79, 23, 30, 31,32,33,34};

        digui(a, 0, a.length - 1);
        System.err.println(Arrays.toString(a));

    }

    public static void digui(int[] arr, int left, int right) {
        //不直接改变，left,right,后续递归
        int l = left;
        int r = right;

        int pivot = arr[(l + r) / 2];//中间值

        int temp = 0;

        while (l < r) {
            while (arr[l] < pivot) {
                l++;
            }
            while (arr[r] > pivot) {
                r--;
            }
            //数据换位置后，r，l还会继续移动，并不是一半一半，
            //中值前和中值后，两部分，两部分不一定是一样多
            if (l >= r) {
                break;
            }
            temp = arr[r];
            arr[r] = arr[l];
            arr[l] = temp;

            if (arr[r] == pivot) {
                //arr[l]一定比pivot小，直接比较下一位
                l++;
            }
            if (arr[l] == pivot) {
                //arr[r]一定比pivot大，直接比较下一位
                r--;
            }
        }
        System.err.println(Arrays.toString(arr));

        if(l==r){
            l++;
            r--;
        }
        //前半部分递归
        if (left < r) {
            digui(arr, left, r);
        }
        //后半部分递归
        if (right > l) {
            digui(arr, l, right);
        }

    }


}
