package org.bougainvilleas.base.dsaa.search;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 二分查找
 * 递归版
 *
 * 非递归版
 *
 * 如果100个数
 * 时间复杂度=以2为底100的对数
 * log   100     2^6<100<2^7,最慢7次就可以找到
 *     2
 *
 * 中值=（left+right）/2
 */
public class BinarySearch {

    public static void main(String[] args) {
        int a[] = {1, 8, 10, 89, 1000, 1234};
        System.err.println(binarySearch(8, a, 0, a.length - 1));

        int b[] = {1, 8, 10, 89, 1000, 1000,1234};
        a=b;
        System.err.println(binarySearchPro(1000, a, 0, a.length - 1).toString());

        //非递归版
        int c[] = {1000, 1000, 1000, 1000, 1000, 1000,1000};
        System.err.println(binarySearchNoRecur(b,1000));
        System.err.println(binarySearchNoRecurPro(b,1000));
        List<Integer> list = binarySearchNoRecurPro(c, 1000);
        Collections.sort(list);
        System.err.println(list);

    }

    /**
     * l   0 0 1
     * mid 2 0
     * r   5 1 1
     *
     */
    public static int binarySearch(int finalVal, int a[], int left, int right) {

        //判断是否在区间
        if (finalVal >= a[left] && finalVal <= a[right]) {
            if(left>right){
                return -1;
            }
            int mid = right + left / 2;
            int midVal = a[mid];
            if (finalVal > midVal) {
               return binarySearch(finalVal, a, mid + 1, right);
            } else if (finalVal < midVal) {
               return binarySearch(finalVal, a, left, mid - 1);
            } else {
                return mid;
            }
        }else return -1;
    }


    /**
     * 查找重复
     */
    public static List<Integer> binarySearchPro(int finalVal, int a[], int left, int right) {

        //判断是否在区间
        if (finalVal >= a[left] && finalVal <= a[right]) {
            if(left>right){
                return new ArrayList<>();
            }
            int mid = right + left / 2;
            int midVal = a[mid];
            if (finalVal > midVal) {
                return binarySearchPro(finalVal, a, mid + 1, right);
            } else if (finalVal < midVal) {
                return binarySearchPro(finalVal, a, left, mid - 1);
            } else {
                List<Integer> list = new ArrayList<>();
                int temp=mid-1;
                while (true){
                    if(temp<0||a[temp]!=finalVal){
                        break;
                    }
                    list.add(temp);
                    temp-=1;
                }

                list.add(mid);

                temp=mid+1;
                while (true){
                    if(temp>a.length-1||a[temp]!=finalVal){
                        break;
                    }
                    list.add(temp);
                    temp=1;
                }
                return list;
            }
        }else return new ArrayList<>();
    }

    //非递归版二分查找

    /**
     * 非递归版二分查找算法
     * @param arr 升序待查找数组 natural ordering ===1,2,3,4
     * @param target 查找目标
     * @return 查找目标的下标，-1表示没找到
     */
    public static int binarySearchNoRecur(int[] arr,int target){
        int left=0;
        int right= arr.length-1;
        while (left<=right){
            int mid =(left+right)/2;
            if(arr[mid]==target){
                return mid;
            }else if(arr[mid]>target){
                right=mid-1;
            }else {
                left=mid+1;
            }
        }
        return -1;
    }
    /**
     * 非递归版二分查找算法,重复值
     * @param arr 升序待查找数组 natural ordering ===1,2,3,4
     * @param target 查找目标
     * @return 查找目标的下标，-1表示没找到
     */
    public static List<Integer> binarySearchNoRecurPro(int[] arr,int target){
        List<Integer> result=new ArrayList<>();
        int left=0;
        int right= arr.length-1;
        int temp;
        while (left<=right){
            int mid =(left+right)/2;
            if(arr[mid]==target){
                temp=mid;
                result.add(temp);
                while (temp-1>=0&&target==arr[temp-1]){
                    result.add(temp-1);
                    temp-=1;
                }
                temp=mid;
                while (temp+1<arr.length&&target==arr[temp+1]){
                    result.add(temp+1);
                    temp+=1;
                }
                return result;
            }else if(arr[mid]>target){
                right=mid-1;
            }else {
                left=mid+1;
            }
        }
        return result;
    }


}
