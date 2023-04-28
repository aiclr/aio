package org.bougainvilleas.base.dsaa.algorithm;


import java.util.Arrays;

/**
 * prim algorithm
 * 普里母算法
 *
 * 修路问题本质是求最小生成树问题
 * Minimum Cost Spanning Tree
 * 给定一个带权无向连通图，如何选取一个生成树，使树上所有边上的权总和为最小，这叫做最小生成树
 *
 * n个顶点，n-1个边
 * 普里母算法和克鲁斯卡尔算法 都是求最小生成树的算法
 *
 *  以B开始，找最小权重，第二行找最小值3是G
 *  然后以 B,G为顶点开始遍历他们的所有权吗，即第二行，第六行，找一个最小值（此时不要计算B到G，G到B,因为已经找过）
 *  分别是B-D 9
 *  G-A 2
 *  G-D 4
 *  G-H 6
 *  最小是2,则下一个点是A
 *
 *  再找BGA的所有最小权
 *
 */
public class PrimAlgorithm {

    public static void main(String[] args) {
        char[]  data=new char[]{'A','B','C','D','E','F','G'};
        int verxs=data.length;
        //邻接矩阵使用二维数组表示，10000大数表示两点不联通
        int [][] weight=new int[][]{
                {10000,5,7,10000,10000,10000,2},
                {5,10000,10000,9,10000,10000,3},
                {7,10000,10000,10000,8,10000,10000},
                {10000,9,10000,10000,10000,4,10000},
                {10000,10000,8,10000,10000,5,4},
                {10000,10000,10000,4,5,10000,6},
                {2,3,10000,10000,4,6,10000}
        };
        MinTree minTree = new MinTree();
        MGraph graph = new MGraph(verxs);
        minTree.createGraph(graph,verxs,data,weight);
        minTree.show(graph);
        minTree.prim(graph,1);

    }



}

/**
 * 创建最小生成树
 */
class MinTree{

    /**
     * 创建图的邻接矩阵
     *
     * @param graph 图对象
     * @param verxs 图对应顶点数
     * @param data 图各个顶点的值
     * @param weight 图的邻接矩阵
     */
    public void createGraph(MGraph graph,int verxs,char data[],int[][] weight){
        int i,j;
        for (i = 0; i < verxs; i++) {
            graph.data[i]=data[i];
            for (j=0;j<verxs;j++){
                graph.weight[i][j]=weight[i][j];
            }
        }
    }

    public void show(MGraph graph){
        for(int[] link:graph.weight){
            System.err.println(Arrays.toString(link));
        }
    }

    /**
     * 普雷母算法
     * @param graph 图
     * @param v 从哪个顶点开始，下标    char[]  data=new char[]{'A','B','C','D','E','F','G'};
     */
    public void prim(MGraph graph,int v){
        //标记顶点是否被访问过
        int[] visited =new int[graph.verxs];
        //标记当前结点已被访问
        visited[v]=1;
        //h1,h2记录顶点下标
        int h1=-1;
        int h2=-1;
        int minWeight=10000;
        //n个顶点有n-1个边，一次k则有一条边
        for (int k=1;k<graph.verxs;k++){

            //从第一行第一列遍历
            for (int i = 0; i < graph.verxs; i++) {
                for (int j = 0; j < graph.verxs; j++) {
                    //只有当遇到开始结点时候，开始找最小权
                    //开始结点是已遍历，进入第二个判断条件
                    //下一个结点必须是未遍历的 visited[j]==0
                    //找最小，用h1,h2记录最小权重顶点下标
                    if(
                        visited[i]==1
                                &&
                        visited[j]==0
                                &&
                        graph.weight[i][j]<minWeight){

                        minWeight=graph.weight[i][j];
                        h1=i;
                        h2=j;
                    }
                }
            }
            System.err.println(graph.data[h1]+"-"+graph.data[h2]+"-"+minWeight);
            visited[h2]=1;//将下一个结点设为已遍历
            minWeight=10000;
        }
    }


}

class MGraph{
    int verxs;
    char[] data;
    int[][] weight;

    public MGraph(int verxs) {
        this.verxs = verxs;
        data=new char[verxs];
        weight=new int[verxs][verxs];
    }
}
