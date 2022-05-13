package org.bougainvilleas.base.dsaa.algorithm;

import java.util.Arrays;

/**
 * KMP算法
 * 在文本串中找一个模式串的出现位置，1977年提出
 * 利用之前判断过的信息，通过一个配置值数组，保存模式串中前后最长公共子序列的长度，
 * 每次回溯通过next数组找到前面匹配过的值，省去了大量的计算时间，
 * 比暴力匹配快
 *
 *  部分匹配值
 * eg
 * ABCDABD
 * 前缀
 * A AB ABC ABCD ABCDA ABCDAB
 * 后缀
 * D BD ABD DABD CDABD BCDABD
 * 没有相同部分
 *
 * 如果从前缀找，
 * 先找A,单个字母，没有前缀也没有后缀所以匹配值为0
 * 再看AB，前缀A后缀B,没有相同部分 匹配值为0
 * 同理ABC，ABCD都是0
 * 然后看ABCDA
 * 前缀 A AB ABC ABCD
 * 后缀 A DA CDA BCDA
 * 有一个相同部分A,单个字母,所以匹配值为1
 * 看ABCDAB
 * 前缀A AB ABC ABCD ABCDA
 * 后缀B AB DAB CDAB BCDAB
 * 有一个相同部分AB 两个字母所以匹配值是2
 *
 * 搜索词     A B C D A B D
 * 部分匹配值 0 0 0 0 1 2 0
 */
public class KMPAlgorithm {
    public static void main(String[] args) {
        String str1 = "BBC ABCDAB ABCDABCDABDE";
        String str2 = "ABCDABD";
//        String str1 = "ABCDABCDABCDFABCDABCDABCDE";
//        String str2 = "ABCDABCDABCDE";
        int next[] = kmpNext(str2);
        System.err.println(Arrays.toString(next));

        System.err.println(kmpSearch(str1,str2,next));



    }

    /**
     *
     * @param str1 源字符串
     * @param str2 子串
     * @param next 子串对应的部分匹配表
     * @return -1未找到，返回第一个匹配的值
     */
    public static int kmpSearch(String str1,String str2,int[] next){
        for (int i = 0,j=0; i < str1.length(); i++) {
            //改变j值，让i位置的字符与子串的字符比较
            //一直回溯，最多回溯j=0,即子串的第一个字符，
            // 即从子串的后面往前找一个与str1.charAt(i)相同的值，但是不是依次减1
            // 是按照匹配值表来回溯
            while (j > 0 && str1.charAt(i) != str2.charAt(j)) {
                j = next[j - 1];
            }
            if(str1.charAt(i)==str2.charAt(j)){
                j++;
            }
            //找到
            if(j==str2.length()){
                return i-j+1;
            }

        }
        return -1;
    }

    /**
     * 获取一个字符串的部分匹配值
     *
     * @param dest 字符串
     * @return 匹配值表
     */
    public static int[] kmpNext(String dest) {
        int[] next = new int[dest.length()];
        next[0] = 0;//字符串长度为1,部分匹配值肯定是0
        for (int i = 1, j = 0; i < dest.length(); i++) {
            //kmp核心
            while (j > 0 && dest.charAt(i) != dest.charAt(j)) {
                j = next[j - 1];
            }
            //部分匹配值+1
            if (dest.charAt(i) == dest.charAt(j)) {
                j++;
            }
            next[i] = j;
        }
        return next;
    }


}
