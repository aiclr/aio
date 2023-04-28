package org.bougainvilleas.base.dsaa.recursion;

/**
 * 8皇后问题
 *
 * 8*8棋盘，放置8个皇后，每个皇后不能相互攻击，皇后攻击方式直线和斜线
 * 8*8棋盘，使用一维数组表示，数组下标表示行数，数组值表示列，
 * 思路：穷举法
 *
 *
 *
 */
public class Queen8 {

    int max=8;
    int[] array=new int[max];
    static int judgeCount=0;
    static int count=0;

    public static void main(String[] args) {
        Queen8 queen8 = new Queen8();
        queen8.check(0);
        System.err.printf("判断%d次\n",judgeCount);
        System.err.printf("一共有%d种摆法",count);

    }

    /**
     * 放置第n个皇后
     * @param n
     */
    public void check(int n){
        //8个放置完，打印，退出
        if(n==max){
            print();
            return;
        }
        //从第一行第一列开始放置
        for (int i = 0; i < max; i++) {
            array[n]=i;
            if(judge(n)){
                check(n+1);
            }
        }
    }


    /**
     *
     * @param n 第几个皇后
     * @return true不与前面的几位皇后冲突，false与前几位皇后冲突不能放置
     */
     public boolean judge(int n){
         judgeCount++;
         //遍历行，此处行数递增到n,所以不用判断是否在同一行
         for (int i = 0; i < n; i++) {
             //判断是否在同一列
             if(array[i]==array[n]){
                 return false;
//             }else if((i-array[i])==(n-array[n])){
             }else if(Math.abs(n-i)==Math.abs(array[n]-array[i])){
             //判断是否在对角线，由于是8*8 对角线都是k=1的一次函数y=x+b，只要b值（y-x）一样，则在同一条线上
             //或者判断k值是1也可以，|y1-y2|/|x1-x2|=1  =》 |y1-y2|=|x1-x2|
                 return false;
             }
         }
        return true;
     }

    /**
     * 输出结果
     */
    public void print(){
        count++;
        for (int i = 0; i < max; i++) {
            System.err.printf("%d ",array[i]);
        }
        System.err.println();
    }

}
