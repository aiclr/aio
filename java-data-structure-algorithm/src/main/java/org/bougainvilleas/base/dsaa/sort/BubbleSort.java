package org.bougainvilleas.base.dsaa.sort;

import java.util.Arrays;

/**
 * 冒泡排序，
 * 前后两位逆序，则交换位置
 */
public class BubbleSort {

    static int A=0;
    static int B=0;

    public static void main(String[] args) {
        int[] a={1,8,49,50,3,10};
        int temp=0;
        boolean flag=false;//优化
        for (int i = 0; i < a.length-1; i++) {
            for (int j = 0; j < a.length-1; j++) {
                A++;
                if(a[j]>a[j+1]){
                    flag=true;
                    temp=a[j+1];
                    a[j+1]=a[j];
                    a[j]=temp;
                }
            }
            if(flag){
                flag=false;
            }else break;
            System.err.println(Arrays.toString(a));

        }
        System.err.printf("%d次 \n",A);
        int[] b={1,8,49,50,3,10};
        a=b;
        for (int i = 0; i < a.length-1; i++) {
            for (int j = 0; j < a.length-1; j++) {
                B++;
                if(a[j]<a[j+1]){
                    temp=a[j+1];
                    a[j+1]=a[j];
                    a[j]=temp;
                }
            }
            System.err.println(Arrays.toString(a));
        }
        System.err.printf("%d次 \n",B);
    }
}
