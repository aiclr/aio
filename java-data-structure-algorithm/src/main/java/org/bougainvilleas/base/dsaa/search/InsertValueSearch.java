package org.bougainvilleas.base.dsaa.search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 插值查找
 *
 * 数据较大，关键字分布较均匀（差值较小） 如果差值很离谱，不一定有二分法优秀
 *
 *
 * 如果数据类似等差数列{1,2，3,5,6,7,8,10}
 * 二分法不是最优，二分法永远是一半一半，可以根据差值(findVal-a[left])/(a[right]-a[left])
 *
 * 计算一下所查的值，在离哪端近一些,自适应
 *
 *
 * 二分法中值=（left+right)/2=left+(right-left)/2
 *
 *
 *  {1, 8, 10, 89, 1000, 1000,1234};
 *  找1000
 *  mid=0+(5-0)*(1000-1)/(1234-1)=4*999/1233=3996/1233=3===》89
 *  二分法是2===》10,可见3离1000更近
 *
 * mid=left+(right-left)*(findVal-a[left])/(a[right]-a[left])
 *
 */
public class InsertValueSearch {
    public static void main(String[] args) {
        int[] a=new int[100];
        for (int i = 0; i < 100; i++) {
            a[i]=i;
        }
        a[7]=8;
        a[6]=7;
        System.err.println(Arrays.toString(a));

        System.err.println(insertValueSearchPro(7,a,0,99));


        int b[] = {1, 8, 10, 89, 1000, 1000,1234};
        System.err.println(insertValueSearchPro(1000, b, 0, b.length - 1).toString());
    }

    /**
     * 查找重复
     */
    public static List<Integer> insertValueSearchPro(int finalVal, int a[], int left, int right) {

           // 递归终止   判断是否在区间
            if(left>right||finalVal < a[left] || finalVal > a[right]){
                return new ArrayList<>();
            }
            int mid =left+(right-left)*(finalVal-a[left])/(a[right]-a[left]);
            System.err.println(mid);
            int midVal = a[mid];
            if (finalVal > midVal) {
                return insertValueSearchPro(finalVal, a, mid + 1, right);
            } else if (finalVal < midVal) {
                return insertValueSearchPro(finalVal, a, left, mid - 1);
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
    }

}
