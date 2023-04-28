package org.bougainvilleas.base.grace.chapter02;


import java.util.Scanner;

/**
 * 21.用偶判断，不用奇判断
 * 使用 & 与运算 判断奇偶
 */
public class Au {
    public static void main(String[] args) {
        System.err.println(-1/2);
        System.err.println(-1&1);
        System.err.println(-2&1);
        System.err.println(0&1);
        System.err.println(1&1);
        System.err.println(2&1);

        System.err.println(remainder(-1,2));
        Scanner input = new Scanner(System.in);
        System.err.println("请输入多个数字判断奇数偶数：");
        //1 2 0 -1 -2
        while (input.hasNextInt()) {
            int i = input.nextInt();
            //用奇判断
//            String str = i + "->" + (i % 2 == 1 ? "奇" : "偶");
            //用偶判断
            String str = i + "->" + (i % 2 == 0 ? "偶" : "奇");
            String str2 = i + "->" + ((i&1) == 0 ? "偶" : "奇");
            System.err.println(i+"是"+str);
            System.err.println(i+"是"+str2);
        }

    }

    //模拟java取余%算法
    public static int remainder(int dividend,int divisor){
        return dividend-dividend/divisor*divisor;
    }

}