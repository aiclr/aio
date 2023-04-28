package org.bougainvilleas.base.dsaa.search;

import java.util.Arrays;

/**
 * 斐波那契查找，
 * 黄金分割来分割数组，进行查找
 *
 */
public class FibonacciSearch {

    public static int maxSize=20;
    public static void main(String[] args){
        int [] a={1,8,10,89,1000,1234};

        System.err.println("--------");
        System.err.println(fibSearch(a,1));
        System.err.println("--------");
        System.err.println(fibSearch(a,1234));
    }

    /**
     * 斐波那契查找算法
     * @param a 有序数组
     * @param key 查找的值
     * @return -1=未找到，
     */
    public static int fibSearch(int[] a,int key){
        int low=0;
        int high=a.length-1;
        int k=0;//分割点下标
        int mid=0;//存放分割位置
        int f[]=fib();//斐波那契数列
        //获取分割数值下表,
        while (high>f[k]-1){
            k++;
        }
        //high=5,f[k]=8,8>5,需要扩充数组长度到8,k=5
        int temp[]= Arrays.copyOf(a,f[k]);
        //需要使用a最后的数填充，
        for (int i = high+1; i < temp.length; i++) {
            temp[i]=a[high];
        }
        //{1,8,10,89,1000,1234};===>{1,8,10,89,1000,1234,1234,1234};
        while (low<=high){
            /**
             * mid计算公式
             * 使用斐波那契数，对数组进行分割
             * 要求数组长度必须为斐波那契数，不足则用最后一位来补充
             * 8的黄金比数是5,f[k]的黄金比数是f[k-1]
             * 但是数组下标是从0开始，所以要将黄金比数-1,即f[k-1]-1
             * low+f[k-1]-1=黄金点下标
             * 前半部分数组个数是f[k-1]
             * 所以如果继续遍历前半部分，只需要将k-=1即可，
             * 后半部分数组个数是f[k-2]
             * 如果继续遍历后半部分，需要将k-=2
             *
             * 例如：
             * 代码内数组有6个，不是黄金分割数
             * 所以补充到8个
             * f[k]=8,8的黄金数是f[k-1]=5
             * 所以分割点是第五位，但是考虑到数组下标是从0开始，所以要-1,即：mid下标=f[k-1]-1=5-1=4
             * 如果往左，(左边有f[k-1]=5个数）5的黄金数=3,f[k-1]-1=3-1=2,low不变=0,mid=2
             * 如果往右（右边剩余个数是f[k-2]=3），3的黄金数=2,f[k-1]-1=2-1=1，low=上一个mid值+1=5,mid=6
             *
             * {1,8,10,89,[1000],1234,1234,1234}
             * {1,8,[10],89,1000}                  {1234,[1234],1234}
             * {1,[8],10}                          {89,1000}
             * {[1],8}                             {10}
             */
            mid=low+f[k-1]-1;
            System.err.printf("mid=%d ",mid);
            //左边
            if(key<temp[mid]){
                high=mid-1;
                //f[k]=f[k-1]+f[k-2]
                //全部元素=前部分+后部分
                //前部分=f[(k-1)]=f[(k-1)-1]+f[(k-1)-2]
                k-=1;
            }else if(key>temp[mid]){//往右
                low=mid+1;
                //f[k]=f[k-1]+f[k-2]
                //全部元素=前部分+后部分
                //后部分=f[(k-2)]=f[(k-2)-1]+f[(k-2)-2]
                k-=2;
            }else {
                if(mid<=high){
                    return mid;
                }else return high;
            }
        }
        return -1;

    }



    /**
     * 获取斐波那契数组
     * @return 斐波那契数列 {1,1,2,3,5,8,13,21,34}
     */
    public static int[] fib(){
        int f[]=new int[maxSize];
        f[0]=1;
        f[1]=1;
        for (int i =2; i <maxSize; i++) {
            f[i]=f[i-1]+f[i-2];
        }
        return f;
    }


}
