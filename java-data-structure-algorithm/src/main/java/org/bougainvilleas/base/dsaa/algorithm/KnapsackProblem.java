package org.bougainvilleas.base.dsaa.algorithm;

/**
 * 动态规划算法
 *
 * 本例中要求物品不可重复
 * 列表法
 *  背包总容量=4
 *  则可以将背包容量为1,2,3的最大结果列出来
 *  当计算最大值时，如果放一个后还有剩余空间，则去找剩余空间的最大值
 *  二维数组是有序的，上一行大于下一行，前一列大于后一列
 *
 */
public class KnapsackProblem {
    public static void main(String[] args) {
        //物品重量
        int w[] = {1, 4, 3};
        //物品价值
        int val[] = {1500, 3000, 2000};
        //物品个数
        int n=val.length;
        //背包可容重量
        int m=8;

        //动态规划表
        //行是背包容量
        //列是物品重量
        int[][] v=new int[n+1][m+1];

        //初始化第一行和第一列为0
        for (int i = 0; i < v.length; i++) {
            v[i][0]=0;
        }
        for (int i = 0; i < v[0].length; i++) {
            v[0][i]=0;
        }
        show(v,val,w);

        for (int i = 1; i <v.length; i++) {//i=1表示不处理第一行
            for (int j = 1; j < v[0].length; j++) {//j=1表示不处理第一列
                //公式
                //当前行物品重量大于背包可装重量，不能再将当前行表示的物品放到背包内，所以该位置的总价值为上一行，该位置的价值
                if(w[i-1]>j){
                    v[i][j]=v[i-1][j];
                }else {
                    //可能是该列的，上一行值 v[i-1][j]
                    //可能是当前物品价值 val[i-1] 加  上一行，j-w[i-1]列 v[i-1][j-w[i-1]]组合价值
                    v[i][j]=Math.max(v[i-1][j],val[i-1]+v[i-1][j-w[i-1]]);
                }
            }
        }
        System.err.println("规划表为");
        show(v,val,w);

    }


    
    public static void show(int[][] v,int[] val,int w[]){
        System.err.println("         0    1    2    3    4    ");
        for (int i = 0; i < v.length; i++) {
            if(i==0) {
                System.err.print("       - ");
            }else{
                System.err.print(val[i-1]+"/"+w[i-1]+" - ");
            }
            for (int j = 0; j < v[i].length; j++) {
                if(v[i][j]==0){
                    System.err.printf("%d    ",v[i][j]);
                }else {
                    System.err.printf("%d ",v[i][j]);
                }
            }
            System.err.println();
        }
    }

}
