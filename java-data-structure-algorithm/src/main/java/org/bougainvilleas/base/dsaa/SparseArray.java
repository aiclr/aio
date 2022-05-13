package org.bougainvilleas.base.dsaa;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 稀疏数组压缩数组
 */
public class SparseArray {



    public static void write(int[][] source) {
        File file = new File("/disk/1tdisk/develop/java/base4java/bak.txt");

        try (FileWriter fileWriter = new FileWriter(file.getName())) {
            if (!file.exists()) {
                file.createNewFile();
            }

            for (int i = 0; i < source.length; i++) {
                for (int j = 0; j < source[i].length; j++) {
                    int tmp = source[i][j];
                    fileWriter.write(tmp);
                }
                fileWriter.write("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static int[][] read() {
        return null;
    }


    public static void display(int[][] source) {
        for (int i = 0; i < source.length; i++) {
            for (int j = 0; j < source[i].length; j++) {
                System.out.printf("%d\t", source[i][j]);
            }
            System.out.println();
        }
    }

    /**
     * 稀疏数组压缩 矩阵中无效数据过多，可以考虑压缩
     * eg：
     * 01030
     * 00000
     * 10300
     * 10020
     * 00010
     * 20000
     * 30000
     * 原始数组一共有7行，5列 ===35
     * 123是有效数据，一共有9个
     * 压缩后 10行，3列  30
     * 7 5 9
     * 0 1 1
     * 0 3 3
     * 2 0 1
     * 2 2 3
     * 3 0 1
     * 3 3 2
     * 4 3 1
     * 5 0 2
     * 6 0 3
     *
     * @param source 原始的数组
     * @return 压缩后数组
     * 有多少个点，就有多少行 result[矩阵元素个数+1]，
     * 三列，记录坐标和值
     * 第一行： source的行数，source的列数，元素的总个数
     */
    public static int[][] compress(int[][] source) {
        int val = 0;
        for (int i = 0; i < source.length; i++) {
            for (int j = 0; j < source[i].length; j++) {
                val += source[i][j] == 0 ? 0 : 1;
            }
        }
        int[][] result = new int[val + 1][3];

        result[0][0] = source.length;
        result[0][1] = source[0].length;
        result[0][2] = val;

        int row = 1;

        for (int i = 0; i < source.length; i++) {
            for (int j = 0; j < source[i].length; j++) {
                if (source[i][j] != 0) {
                    result[row][0] = i;
                    result[row][1] = j;
                    result[row][2] = source[i][j];
                    row++;
                }
            }
        }

        return result;
    }

    public static int[][] decompress(int[][] source) {
        int[][] result = new int[source[0][0]][source[0][1]];
        for (int i = 1; i < source.length; i++) {
            result[source[i][0]][source[i][1]] = source[i][2];
        }

        return result;
    }



    public static void main(String[] args) {


        //原始二维数组
        int[][] source = new int[11][7];
        source[1][2] = 1;
        source[2][3] = 2;
        source[3][3] = 2;
        System.out.println("原始数组");
        display(source);
        System.out.println("压缩后的稀疏数组");
        display(compress(source));
        write(compress(source));
        System.out.println("解压缩后的原始数组");
        display(decompress(compress(source)));
    }
}


/**
 * 输入: s = "abcabcbb"
 * w前后出现过
 * pwwkew
 *
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 */
class Solution {

    public int lengthOfLongestSubstring(String s) {
        int maxLength = 0;

        List<Byte> temp = new ArrayList<>();
        byte[] bytes = s.getBytes();
        List<Byte> strList = new ArrayList<>();
        for (int i = 0; i < bytes.length; i++) {
            strList.add(Byte.valueOf(bytes[i]));
        }
        for (int j = 0; j <strList.size(); j++) {
            //从第一位开始遍历将不同值放到temp
            int tmp = 0;
            for (int i=j;i<strList.size();i++) {
                if (!temp.contains(strList.get(i))) {
                    tmp++;
                    temp.add(strList.get(i));
                } else {
                    //置空
                    temp = new ArrayList<>();
                    break;
                }
            }
            if (maxLength < tmp) {
                maxLength = tmp;
            }
        }
        return maxLength;
    }


}


class SingleTon{

    private SingleTon() {

    }

    private static SingleTon singleTon;

    public static synchronized SingleTon instance(){
        if(singleTon==null){
            singleTon=new SingleTon();
        }
        return singleTon;
    }

}


interface Proxy{
    void test();
}

class User implements Proxy{
    @Override
    public void test() {
        System.out.println(123);
    }
}

class UserProxy implements Proxy{

    private Proxy proxy;

    public UserProxy(Proxy proxy) {
        this.proxy = proxy;
    }

    @Override
    public void test() {
        System.out.println(222);

        proxy.test();
        System.out.println(222);

    }
}




































